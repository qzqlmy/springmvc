package controlleree;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GetHeadPortrait;
import model.teacher;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Component
@RequestMapping("Uploader")
public class UploaderController extends BaseControlle {
    //@ResponseBody
    @Autowired
    private SqlSession sqlSession;
    @RequestMapping("/uploadImg")
    public void uploadPicture(MultipartHttpServletRequest request, HttpServletResponse response){
        ResponseResult result = new ResponseResult();
        Map<String, Object> map = new HashMap<String, Object>();
        File targetFile=null;
        String url="";//返回存储路径
        int code=1;
        MultipartFile file = request.getFile("file");
        System.out.println(file);
        String fileName=file.getOriginalFilename();//获取文件名加后缀
        if(fileName!=null&&fileName!=""){
            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
            String path = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名

            //先判断文件是否存在
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fileAdd = sdf.format(new Date());
            //获取文件夹路径
            File file1 =new File(path+"/"+fileAdd);
            System.out.println(file1+"aaaaaaaaaaaaaaaaaaaaaa");
            //如果文件夹不存在则创建
            if(!file1 .exists()  && !file1 .isDirectory()){
                file1 .mkdirs();
            }
            //将图片存入文件夹
            targetFile = new File(file1, fileName);
            System.out.println(targetFile+"GGGGGGGGGG"+file1);

            try {
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                url=returnUrl+fileAdd+"/"+fileName;
                code=0;
                result.setCode(code);
                result.setMessage("图片上传成功");
                String username= String.valueOf(session.getAttribute("studentid"));
                GetHeadPortrait HeadPortrai=new GetHeadPortrait(username,url);
                List<GetHeadPortrait> HeadPortraits =sqlSession.selectList("xuanke.queryHeadPortrait",username);
                if(HeadPortraits.size()>0){
                    sqlSession.update("xuanke.modifyHeadPortrait",HeadPortrai);
                }else{
                    sqlSession .insert("xuanke.createHeadPortrait",HeadPortrai);
                }

                map.put("url", url);
                result.setResult(map);
            } catch (Exception e) {
                e.printStackTrace();
                result.setMessage("系统异常，图片上传失败");
            }
        }
        writeJson(response, result);
    }
    @RequestMapping("/uploadImga")
    public void uploadmodelPicture(MultipartHttpServletRequest request, HttpServletResponse response){
        ResponseResult result = new ResponseResult();
        Map<String, Object> map = new HashMap<String, Object>();
        File targetFile=null;
        String url="";//返回存储路径
        int code=1;
        MultipartFile file = request.getFile("file");
        System.out.println(file);
        String fileName=file.getOriginalFilename();//获取文件名加后缀
        if(fileName!=null&&fileName!=""){
            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
            String path = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名

            //先判断文件是否存在
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fileAdd = sdf.format(new Date());
            //获取文件夹路径
            File file1 =new File(path+"/"+fileAdd);
            System.out.println(file1+"aaaaaaaaaaaaaaaaaaaaaa");
            //如果文件夹不存在则创建
            if(!file1 .exists()  && !file1 .isDirectory()){
                file1 .mkdirs();
            }
            //将图片存入文件夹
            targetFile = new File(file1, fileName);
            System.out.println(targetFile+"GGGGGGGGGGhhhhhhhhh"+file1);

            try {
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                url=returnUrl+fileAdd+"/"+fileName;
                code=0;
                result.setCode(code);
                result.setMessage("图片上传成功kkk");
                String username= String.valueOf(session.getAttribute("studentid"));
                GetHeadPortrait HeadPortrai=new GetHeadPortrait(username,url);
                List<GetHeadPortrait> HeadPortraits =sqlSession.selectList("xuanke.queryHeadPortrait",username);
                if(HeadPortraits.size()>0){
                    sqlSession.update("xuanke.modifyHeadPortrait",HeadPortrai);
                }else{
                    sqlSession .insert("xuanke.createHeadPortrait",HeadPortrai);
                }

                map.put("url", url);
                result.setResult(map);
            } catch (Exception e) {
                e.printStackTrace();
                result.setMessage("系统异常，图片上传失败jjj");
            }
        }
        writeJson(response, result);
    }

}
