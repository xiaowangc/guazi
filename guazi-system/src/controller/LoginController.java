package controller;

import util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String phoneNumber = request.getParameter("ausername");
        String password = request.getParameter("apassword");

       if("admin".equals(phoneNumber) && "123".equals(password)){
           //登录成功，重定向进入主界面，并添加到session中
           //添加一个标记，用来记录进行登录验证
           SessionUtil.setManageFlag(request.getSession());
           response.sendRedirect("index.jsp");

       }else{
           //登录失败，弹出提示框
           PrintWriter writer = response.getWriter();
           writer.println("<script>alert(\"login fail\");window.location.href='login.jsp'; </script>");


       }
        //这里应该由session记录用户登录的一次会话

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
