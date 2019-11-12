package tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @name FileUtil.java
 * @author hechuan
 * @version 1.1.0
 * @description 文件上传下载
 * @email hechuan@tydic.com
 * @date Created On : 2018-01-17
 */
public class FileUtil{
	protected static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	private static final String filePath;
	
	//设置文件路径
	static{
		if("\\".equals(File.separator)){
			filePath="D:\\app\\upload\\";
		}else{
			filePath="/home/app_user/upload/";
		}
		File file=new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		logger.info("上传下载文件路径:"+filePath);
	}
	//文件下载
	public static void download(HttpServletResponse response,File file) throws IOException{
    	response.setCharacterEncoding("UTF-8");
		response.addHeader("X-Frame-Options","SAMEORIGIN");
		if (!file.exists()) {
	    	response.setContentType("text/html; charset=UTF-8");
        	response.getWriter().write("你下载的文件不存在！");
		}else{
			String fileName=file.getName();
			String downloadFileName=fileName.substring(0,fileName.lastIndexOf("-"))+fileName.substring(fileName.lastIndexOf("."));
			logger.info("下载文件:"+file.getPath());
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment;fileName="+new String(downloadFileName.getBytes("gbk"), "iso8859-1"));// 设置文件名
			//开始下载
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			
		    //long downloadedLength = 0l; //用于记录以完成的下载的数据量，单位是byte
			byte[] buffer = new byte[2048];
			int length;
			while((length=bis.read(buffer))>0){
				bos.write(buffer, 0, length);
				//downloadedLength += b.length; //记录下载量
			}
			if(bis!= null){
				bis.close();
			}
			if(bos!= null){
				bos.close();
			}
		}
	}
	//文件下载
	public static void download(HttpServletResponse response,String fileName) throws IOException{
		File targetFile = new File(filePath, fileName);
		String downloadFileName=fileName.substring(0,fileName.lastIndexOf("-"))+fileName.substring(fileName.lastIndexOf("."));
		logger.info("下载文件:"+targetFile.getPath());
    	response.setCharacterEncoding("UTF-8");
		response.addHeader("X-Frame-Options","SAMEORIGIN");
		if (!targetFile.exists()) {
	    	response.setContentType("text/html; charset=UTF-8");
        	response.getWriter().write("你下载的文件不存在！");
		}else{
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment;fileName="+new String(downloadFileName.getBytes("gbk"), "iso8859-1"));// 设置文件名
			
			//开始下载
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(targetFile));
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			
			
		    //long downloadedLength = 0l; //用于记录以完成的下载的数据量，单位是byte
			byte[] buffer = new byte[2048];
			int length;
			while((length=bis.read(buffer))>0){
				bos.write(buffer, 0, length);
				//downloadedLength += b.length; //记录下载量
			}
			if(bis!= null){
				bis.close();
			}
			if(bos!= null){
				bos.close();
			}
		}
	}
	//文件上传
	public static Map<String,Object> upload(MultipartHttpServletRequest multipartRequest){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Iterator<String> fileGroups=multipartRequest.getFileNames();

		int fileCount=0;
		int errorCount=0;
		List<Map<String,String>> filesInfo=new ArrayList<Map<String,String>>();
		String staffId=multipartRequest.getParameter("uploadStaffId");
		
		while(fileGroups.hasNext()){
			String groupName=fileGroups.next();
			List<MultipartFile> groupFiles=multipartRequest.getFiles(groupName);
			logger.debug("---------------------------文件组["+groupName+"]共有文件"+groupFiles.size()+"个---------------------------");
			for(MultipartFile file:groupFiles){
				fileCount++;
				String orgFileName = file.getOriginalFilename(); 
				String suffix=orgFileName.substring(orgFileName.lastIndexOf("."));
				String newFileName=orgFileName.replace(suffix, "-"+Common.getSuffix()+suffix);
				Map<String,String> fileInfo=new HashMap<String,String>();
				fileInfo.put("fileName", orgFileName);
				try {
					File newFile=new File(filePath, newFileName);
					file.transferTo(newFile);
					fileInfo.put("result", "success");
					fileInfo.put("uploadStaffId", staffId);
					fileInfo.put("fileTitle", orgFileName);
					fileInfo.put("fileType", suffix.substring(1));
					fileInfo.put("filePath", newFile.getAbsolutePath());
					fileInfo.put("fileSize", newFile.length()+"");
				} catch (IllegalStateException e) {
					errorCount++;
					fileInfo.put("result", "上传失败[IllegalStateException]");
					e.printStackTrace();
				} catch (IOException e) {
					errorCount++;
					fileInfo.put("result", "上传失败[IOException]");
					e.printStackTrace();
				} finally {
					filesInfo.add(fileInfo);
				}
				
			}
		}
		resultMap.put("details",filesInfo);
		
		if(fileCount>0&&errorCount==0){
			resultMap.put("result","success");
		}else if(errorCount<fileCount&&errorCount>0){
			resultMap.put("result","other");
		}else{
			resultMap.put("result","failed");
			if(fileCount==0){
				resultMap.put("details","没有需要上传的文件!");
			}
		}
		
		return resultMap;
	}
	
}
