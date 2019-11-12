package until;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名称: <code>ExcelUtil</code>
 * 描述: Excel工具类
 * 作者: Lyndon
 * 版本: 1.0.0
 * 日期: 创建于2019年02月13日
 */
public class ExcelUtil{
	/**
	 * 简单列类型转json字符串
	 * @param	columnsString	字段用,隔开;例如："本地网ID:latn_id,本地网名称:latn_name,营业区ID:org_id,营业区名称:org_name"
	 * @return	字符串
	 */
	public static String getSimpleColumnStructureJson(String columnsString) {
		String jsonStr=null;
		try{
			String[] columns=columnsString.split(",");
			List<Map<String,String>> columnArrays=new ArrayList<Map<String,String>>();
			for(String colum:columns){
				Map<String,String> columnAttr=new HashMap<String,String>();
				String[] attr=colum.split(":");
				columnAttr.put("name", attr[0]);
				columnAttr.put("column", attr[1]);
				columnArrays.add(columnAttr);
			}
			jsonStr= Common.toJson(columnArrays);
		} catch (Exception e){
			System.out.println("---------[getSimpleColumnStructureJson]json转换异常！---------");
			e.printStackTrace();
		}
		return jsonStr;
	}
	/**
	 * excel导入
	 * @param	fileType	excel类型;xls(excel 2003)、xlsx(excel 2007)
	 * @param	is			文件输入流
	 * @return	Map 		其中数据行的列为String
	 */
	public static Map<String,List<List<String>>> importExcel(String fileType, InputStream is){
		Map<String,List<List<String>>> excelData=null;
		try {
			if("xls".equals(fileType)||"xlsx".equals(fileType)){
				excelData= new HashMap<>();
				Workbook wb;
				if("xls".equals(fileType)) wb=new HSSFWorkbook(is);
				else wb=new XSSFWorkbook(is);

				for(Sheet sheet:wb){
					List<List<String>> sheetData= new ArrayList<>();
					for(Row row:sheet){
						if(row==null){
							break;//有空行认为结束
						}
						List<String> rowData= new ArrayList<>();
						for(Cell cell:row){
							rowData.add(cell.getColumnIndex(), cell.getStringCellValue());
						}
						sheetData.add(rowData);
					}
					excelData.put(sheet.getSheetName(),sheetData);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return excelData;
	}
	/**
	 * excel单页导出
	 * @param   response				请求响应
	 * @param	fileName				文件名称,必须包含后缀.xls或.xlsx
	 * @param	sheet					表配置项,每个Map必须包含name、column、data
	 */
	public static void exportExcel(HttpServletResponse response, String fileName, Map<String,Object> sheet) throws IOException {
		//设置下载参数
		Workbook wb=new ExcelBean(fileName,sheet).getExcel();
		if(wb!=null){
			String fileType=fileName.substring(fileName.lastIndexOf(".")+1);
			String newFileName=fileName.substring(0,fileName.lastIndexOf("."))+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
			response.setCharacterEncoding("UTF-8");
			response.setContentType("octets/stream");
			response.addHeader("Content-Type", "text/"+fileType+"; charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename="+new String(newFileName.getBytes("gbk"), "iso8859-1"));
			wb.write(response.getOutputStream());
		}else{
			new ObjectMapper().writeValue(response.getWriter(), new HashMap(){{put("code", "8000");put("message", "生成excel异常！");}});
		}
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}



}
