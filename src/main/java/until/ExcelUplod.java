package until;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ExcelUplod {
    public String ajaxUploadExcel(HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile("upfile");
        System.out.println(file );
        String msg="";
        if(file.isEmpty()){
            try {
                throw new Exception("文件不存在！");
            } catch (Exception e) {
                e.printStackTrace();
            }
            msg="文件不存在！";
        }

        InputStream in =null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<Object>> listob = null;
        ResultSet rs = null;
        Connection conn= DB.getCononction();
        String sql = "insert into test.zqinfo values(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        //优化插入第一步       设置手动提交
        conn.setAutoCommit(false);
        try {
            msg="文件导入成功！";
            listob = new ExcelUtill().getBankListByExcel(in,file.getOriginalFilename());
            for (int i = 0; i < listob.size(); i++) {
                List<Object> aa=listob.get(i);
                for (int j = 0; j < aa.size(); j++) {
                    ps.setString(j+1, String.valueOf(aa.get(j)));
                }
                ps.addBatch();
                if((i!=0 && i%200==0) || i==listob.size()-1){
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }

        } catch (Exception e) {
            msg="文件导入失败！";
            e.printStackTrace();
        }

        System.out.println(listob);
        return msg;
    }

}
