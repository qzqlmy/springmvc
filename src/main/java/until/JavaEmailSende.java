package until;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class JavaEmailSende {
    public static void sendEmail(HttpServletRequest request, HttpServletResponse response, String toEmailAddress, String emailTitle, String emailContent)throws Exception{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("upfile");
            String fileName=file.getOriginalFilename();//获取文件名加后缀
           String path="";
        if(fileName!=null&&fileName!=""){
            path = request.getSession().getServletContext().getRealPath("upload/massage"); //文件存储位置
            File dir = new File(path);
            if(!dir.exists()){
                dir.mkdirs();
            }

            file.transferTo(new File(path+File.separator+fileName));
            System.out.println(path);
        }



        Properties props = new Properties();

        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        /**SSL认证，注意腾讯邮箱是基于SSL加密的，所有需要开启才可以使用**/
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        //创建会话
        Session session = Session.getInstance(props);

        //发送的消息，基于观察者模式进行设计的
        Message msg = new MimeMessage(session);
        //存放附件或摘要
        Multipart multipart = new MimeMultipart();

        if (fileName!=null&&fileName!="") {
            String filePath=path+"\\"+ fileName;
            File usFile = new File(filePath);
            // 添加附件的内容
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(usFile);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            //MimeUtility.encodeWord可以避免文件名乱码
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(usFile.getName()));
            multipart.addBodyPart(attachmentBodyPart);
        }
        BodyPart contentPart = new MimeBodyPart();
        //使用StringBuilder，因为StringBuilder加载速度会比String快，而且线程安全性也不错
        StringBuilder builder = new StringBuilder();
        String content= emailContent +"<br>"+"时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        contentPart.setContent(content, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        msg.setSubject(emailTitle);
        msg.setContent(multipart);
        msg.setFrom(new InternetAddress("1575478241@qq.com"));

        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "1575478241@qq.com", "ipikcqjfciqnbaej");
        //发送消息
        System.out.println(emailTitle+"__________________________"+emailContent);
        transport.sendMessage(msg, new Address[] { new InternetAddress(toEmailAddress) });
        transport.close();
    }
}
