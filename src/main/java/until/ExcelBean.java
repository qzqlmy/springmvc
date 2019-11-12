package until;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @名称: <code>ExcelBean</code>
 * @描述: Excel数据实体
 * @依赖: poi,poi-ooxml
 * @作者: Lyndon
 * @版本: 1.0.0
 * @日期: 创建于2019年01月01日
 */
@Slf4j
public class ExcelBean {
	//excel文件名称
	private String fileName;
	//是否在Sheet中展示Column字段
	private boolean isContainColumnName=false;
	//Sheet页数据 Map中必须包含name,column、data
	private List<Map<String,Object>> sheets=null;
	//临时存放Sheet中的字段
	private List<String> columnAttrs=null;
	
	public ExcelBean(String fileName,Map<String,Object> sheet) {
		this.fileName=fileName;
		this.sheets= new ArrayList<>();
		this.sheets.add(sheet);
	}
	public ExcelBean(String fileName,List<Map<String,Object>> sheets) {
		this.fileName=fileName;
		this.sheets=sheets;
	}
	public void setContainColumnName(boolean isContainColumnName) {
		this.isContainColumnName = isContainColumnName;
	}
	
	public Workbook getExcel() {
		//实体属性数据校验
		if(fileName==null||fileName.trim().length()<1){
//			log.error("---------文件名不能为空！---------");
			return null;
		}
		if(!fileName.toLowerCase().endsWith(".xls")&&!fileName.toLowerCase().endsWith(".xlsx")){
//			log.error("---------文件类型不是excel支持的类型！---------");
			return null;
		}

		//Excel表格
		Workbook wb;
		if(fileName.toLowerCase().endsWith(".xls")) wb=new HSSFWorkbook();
		else wb=new XSSFWorkbook();
		
		int sheetCounter=0;
		for(int i=0;i<sheets.size();i++){
			Map<String,Object> sheetOption=sheets.get(i);
			//数据准备
			String sheetName= Common.strVal(sheetOption.get("name"));
			String columnMapping=Common.strVal(sheetOption.get("column"));
			Object dataObj=sheetOption.get("data");
			if(sheetName.length()>0&&columnMapping.length()>0){
				//Sheet表头数据整理
				List<?> headers= Common.json2List(columnMapping);
				if(headers==null){
//					log.error("---------字段与名称映射集JSON转换错误！---------");
					continue;
				}
				sheetCounter++;
				columnAttrs= new ArrayList<>();
				Map<String,Object> headerAttrs=calcHeaderAttr(headers,1);
				//最大行列数
				Integer maxRowSize=(Integer)headerAttrs.get("rowSize");
				Integer maxColSize=(Integer)headerAttrs.get("colSize");
				//表头字段、名称、行列等信息
				List<?> headList=(List<?>)headerAttrs.get("list");
				//创建Sheet页,并设置单元格格式
		        Sheet sheet=wb.createSheet();
		        wb.setSheetName(i, sheetName);
		        sheet.setDisplayGridlines(false);
		        sheet.setHorizontallyCenter(true);
		        //创建表头列默认样式
		        CellStyle headerStyle=getCellStyle(wb,true);
		        CellStyle bodyStyle=getCellStyle(wb,false);
		        //---------------------------表头填充---------------------------
		        //创建空表头
		        int rowIndex=0,rowLimit=maxRowSize;
				if(isContainColumnName) rowLimit++;//包含字段时多加一行
		        for(;rowIndex<rowLimit;rowIndex++){
			        Row columnsRow=sheet.createRow(rowIndex);
			        columnsRow.setHeightInPoints(20);
			        for(int j=0;j<maxColSize;j++){
			        	Cell cell=columnsRow.createCell(j);
			        	cell.setCellStyle(headerStyle);
			        }
		        }
		        //设置表头属性
		        setHeaderAttr(headList,sheet,0,maxRowSize);
		        //---------------------------表数据填充---------------------------
		        if(dataObj!=null){
			        List<Map<String,Object>> sheetData=(List<Map<String,Object>>)dataObj;
			        for(Map<String,Object> rowMap:sheetData){
				        Row row=sheet.createRow(rowIndex++);
				        int cellIndex=0;
				        for(String colName:columnAttrs){
				        	Cell cell=row.createCell(cellIndex++);
				        	cell.setCellValue(Common.strVal(rowMap.get(colName)));
				        	cell.setCellStyle(bodyStyle);
				        }
			        }
		        }
		        //自适应excel宽度
		        for(int j=0;j<columnAttrs.size();j++){
			        sheet.autoSizeColumn(j,true);
		        }
			}else{
//				log.error("---------第"+i+"个sheet缺少必要的属性！---------");
			}
		}
		if(sheetCounter==0) return null;
		
		return wb;
	}
	//递归创建单元格，并设置值
	private void setHeaderAttr(List<?> attrList, Sheet sheet, int startColumn, int maxRowSize){
		int startCol=startColumn;
        for(Object attr:attrList){
			Map<?,?> attrMap=(Map<?,?>)attr;
			String name=String.valueOf(attrMap.get("name"));			
			Integer colSize=(Integer)attrMap.get("colSize");
			Integer level=(Integer)attrMap.get("level");
			//单元格的横纵坐标开始于结束位置
			int startRow=level-1;
			int endRow=maxRowSize;
			int endCol=startCol+colSize;
			if(attrMap.containsKey("child")){
				endRow=startRow;
				List<?> childList=(List<?>)attrMap.get("child");
				setHeaderAttr(childList,sheet,startCol,maxRowSize);
			}
			//填充
			sheet.getRow(startRow).getCell(startCol).setCellValue(name);
			//是否有要合并的单元格
			if(endRow-startRow>1||endCol-startCol>1){
				sheet.addMergedRegion(new CellRangeAddress(startRow,endRow,startCol,endCol));
			}
			if(!attrMap.containsKey("child")&&isContainColumnName){
				String column=String.valueOf(attrMap.get("column"));
				sheet.getRow(startRow+1).getCell(startCol).setCellValue(column);
			}
			startCol=endCol;
        }
	}
	//递归计算表头各个属性值
	private Map<String,Object> calcHeaderAttr(List<?> list,int level){
		int colCount=0,rowCount=0;
		Map<String,Object> newMap=new HashMap<String,Object>();
		List<Map<String,Object>> newList=new ArrayList<Map<String,Object>>();
		for(Object thisCol:list){
			Map<String,Object> makeMap=new HashMap<String,Object>();
			Map<?,?> thisMap=(Map<?,?>)thisCol;
			if(thisMap.containsKey("child")){
				List<?> thisList=(List<?>)thisMap.get("child");
				Map<String,Object> returnMap=calcHeaderAttr(thisList,level+1);
				List<?> childList=(List<?>)returnMap.get("list");
				Integer colSize=(Integer)returnMap.get("colSize");
				rowCount=(Integer)returnMap.get("rowSize");
				
				makeMap.put("name", thisMap.get("name"));
				makeMap.put("child", childList);
				makeMap.put("level", level);
				makeMap.put("colSize", colSize);
				makeMap.put("rowSize", rowCount+1);
				
				colCount+=colSize;
			}else{
				makeMap.put("name", thisMap.get("name"));
				makeMap.put("column", thisMap.get("column"));
				makeMap.put("level", level);
				makeMap.put("rowSize", 1);
				makeMap.put("colSize", 1);
				columnAttrs.add(String.valueOf(thisMap.get("column")));
				colCount++;
			}
			
			newList.add(makeMap);
		}
		rowCount++;
		newMap.put("list", newList);
		newMap.put("colSize", colCount);
		newMap.put("rowSize", rowCount);
		
		return newMap;
	}
	//创建excel简单样式
	private CellStyle getCellStyle(Workbook wb, boolean isHeader){
        //设置标题样式
		CellStyle cellStyle=wb.createCellStyle();
        //水平、垂直居中
		if(isHeader){
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
		}else{
			cellStyle.setAlignment(HorizontalAlignment.LEFT);
		}
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setWrapText(false);//不换行

        return cellStyle;
	}
}