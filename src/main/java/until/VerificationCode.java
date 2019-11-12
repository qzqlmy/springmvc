package until;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * 名称: VerificationCode
 * 描述: 验证码工具
 * 作者: Lyndon
 * 版本: 1.0.O
 * 日期: 创建于2019年03月25日
 */
@Slf4j
public class VerificationCode<main> {
    private static final int DEFAULT_IMAGE_WIDTH=128;
    private static final int DEFAULT_IMAGE_HEIGHT=32;
    private static final int DEFAULT_LINE_COUNT=16;
    private static final Random RANDOM_MATH=new Random();
    // 验证码范围,去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
    private static final char[] UPPER_CHAR_MAP={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private static final char[] NUMBER_MAP={'1','2','3','4','5','6','7','8','9'};
    private static final char[] LOWER_CHAR_MAP={'a','b','c','d','e','f','g','h','i','j','k','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static ArrayList<char[]> CODE_LIST=new ArrayList(){{ add(UPPER_CHAR_MAP);add(NUMBER_MAP);add(LOWER_CHAR_MAP); }};
    //随机颜色
    private static Color randomColor(){
        return new Color(
                RANDOM_MATH.nextInt(255),
                RANDOM_MATH.nextInt(255),
                RANDOM_MATH.nextInt(255));
    }
    //随机四位字符
    private static char[] randomCode(){
        int index=RANDOM_MATH.nextInt(CODE_LIST.size());
        int startIndex=index-1;
        int endIndex=index+1;
        if(startIndex<0) startIndex=2;
        if(endIndex>2) endIndex=0;
        char[] c1=CODE_LIST.get(startIndex);
        char[] c2=CODE_LIST.get(index);
        char[] c3=CODE_LIST.get(endIndex);
        char[] c4=CODE_LIST.get(RANDOM_MATH.nextInt(CODE_LIST.size()));

        return new char[]{
                c1[RANDOM_MATH.nextInt(c1.length)],
                c2[RANDOM_MATH.nextInt(c2.length)],
                c3[RANDOM_MATH.nextInt(c3.length)],
                c4[RANDOM_MATH.nextInt(c4.length)]};
    }
    /** 
     * 验证码图片
     * @param   vWidth      图片长
     * @param   vHeight     图片高
     * @param   code        内容
     * @return  BufferedImage
     */
    private static BufferedImage getCodeImage(int vWidth,int vHeight,char[] code){
        Font codeFont=new Font("微软雅黑",Font.PLAIN, vHeight*3/4);
        BufferedImage bi = new BufferedImage(vWidth, vHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D gd = bi.createGraphics();
        int sWidth=gd.getFontMetrics(codeFont).stringWidth(new String(code));
        float yOffset = gd.getFontMetrics(codeFont).getAscent();
        float xOffset = vWidth/10.0f;
        float xSize=sWidth/4.0f+(vWidth*4/5.0f-sWidth)/(code.length-1);//文字占4/5
        bi = gd.getDeviceConfiguration().createCompatibleImage(vWidth, vHeight, Transparency.TRANSLUCENT);
        gd.dispose();
        gd = bi.createGraphics();
        gd.setStroke(new BasicStroke(1f));
        gd.setFont(codeFont);
        //干扰线
        for (int i = 0; i < DEFAULT_LINE_COUNT; i++) {
            int xs = RANDOM_MATH.nextInt(vWidth);//x坐标开始
            int ys = RANDOM_MATH.nextInt(vHeight);//y坐标开始
            gd.setColor(randomColor());
            gd.drawLine(xs, ys,
                    xs + RANDOM_MATH.nextInt(vWidth / 5),
                    ys + RANDOM_MATH.nextInt(vHeight / 5));
        }
        gd.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        for (char ch : code) {
            gd.setColor(randomColor());
            gd.drawString(String.valueOf(ch), xOffset, yOffset);
            xOffset+=xSize;
        }
        gd.dispose();

        return bi;
    }
    //生成验证码信息
    public static String[] getCode(){
        // 随机产生codeCount个字符的验证码。
        char[] thisCode=randomCode();
        BufferedImage imgMap = getCodeImage(DEFAULT_IMAGE_WIDTH,DEFAULT_IMAGE_HEIGHT,thisCode);
        String imageType="png";
        ByteArrayOutputStream baos=null;
        String base64Image;
        baos=new ByteArrayOutputStream();
        try {
            ImageIO.write(imgMap, imageType, baos);
            base64Image="data:image/"+imageType+";base64,"+new BASE64Encoder().encode(baos.toByteArray());
        } catch (IOException e) {
            base64Image=null;
//            log.error("---------生成验证码图片异常！---------");
            e.printStackTrace();
        } finally {
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(base64Image==null) return null;
        else return new String[]{String.valueOf(thisCode),base64Image};
    }

    public static void main(String[] args) throws Exception {
        // 随机产生codeCount个字符的验证码。
        System.out.println(new String(randomCode()));
    }
}
