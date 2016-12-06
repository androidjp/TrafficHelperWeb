package servlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import utils.ColorUtil;
import utils.StringRandomUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 登录时跳转的Servlet
 * Created by androidjp on 2016/11/22.
 */
public class LoginServlet extends BaseServlet{

    protected void onGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pin = StringRandomUtil.getStringRandom(4);
        Color color = ColorUtil.getRadomColor();
        Color reverseColor = ColorUtil.getReverseColor(color);
        int width  =300;
        int height = 70;

        response.setContentType("image/jpeg");
        request.getSession(true).setAttribute("randomString", pin);

        BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
        g.setColor(color);
        g.fillRect(0,0,width,height);
        g.setColor(reverseColor);
        g.drawString(pin,width/2,height/2);
        Random random = new Random();
        int n = random.nextInt(100);
        for (int i=0;i<n;i++){
            g.drawRect(random.nextInt(width),random.nextInt(height),1,1);
        }

        ServletOutputStream out = response.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);///转成JPEG格式
        encoder.encode(bi);///对图片编码
        out.flush();///输出到客户端


    }

    protected void onPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
