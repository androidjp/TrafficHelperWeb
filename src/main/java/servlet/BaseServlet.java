package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Created by androidjp on 2016/11/22.
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log("初始化参数："+getServletContext().getInitParameter("message"));
        log("------doGet()---------");
        ///首先解析请求
        //this.execute(request,response);
        onGet(request,response);
    }

    protected abstract void onGet(HttpServletRequest request, HttpServletResponse response) throws IOException;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log("------doPost()---------");
        //this.execute(request,response);
        onPost(request,response);
    }

    protected abstract void onPost(HttpServletRequest request, HttpServletResponse response);

    @Override
    protected long getLastModified(HttpServletRequest req) {
        this.log("执行 getLastModified()");
        return -1;
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        //设置编码方式
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String requestURI= request.getRequestURI();///请求URI
        String method = request.getMethod();///访问Servlet的方式（GET/POST）
        String param  = request.getParameter("param"); //客户端提交的参数param值

        ///设置响应格式为：文档类型之 HTML类型
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<!DOCTYPE HTML>");
        pw.println("<html lang=\"en\">");
        pw.println("<head>");
        pw.println("<meta charset=\"UTF-8\">");
        pw.println("<title>请求信息显示</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<p>");
        pw.println("以 "+ method +" 方式访问该页面。取到的params参数为："+ param +"</br>");
        pw.println("<form action='"+requestURI+" ' method='get'>");
        pw.println("</body>");
        pw.println("</html>");

        ////各种参数信息的获取




    }

    ///返回客户端浏览器接受的文件类型
    private String getAccept(String accept){
        StringBuilder sb = new StringBuilder();
        if (accept.contains("image/gif")) sb.append("GIF 文件， ");
        if (accept.contains("image/x-xbitmap")) sb.append("BMP 文件， ");
        if (accept.contains("image/jpeg")) sb.append("JPG 文件， ");
        if (accept.contains("application/vnd.ms-excel")) sb.append("Excel 文件， ");
        if (accept.contains("application/vnd.ms-powerpoint")) sb.append("PPT 文件， ");
        if (accept.contains("application/msword")) sb.append("Word 文件， ");
        return sb.toString().replaceAll(", $", "");
    }

    ////返回客户端语言环境
    private String getLocale(Locale locale){
        if (Locale.SIMPLIFIED_CHINESE.equals(locale)) return "简体中文";
        if (Locale.TRADITIONAL_CHINESE.equals(locale)) return "繁体中文";
        if (Locale.ENGLISH.equals(locale)) return "英文";
        return "未知语言环境";
    }


}
