package controller.wx;

import bean.Message;
import bean.User;
import service.BaseUserService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//这个类只用来注册普通用户
//注册快递员在后台管理系统那里

public class RegController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        BaseUserService userService = new UserService();
        User user = new User(username,password,username,0);

        int flag = userService.insert(user);
        Message message = null;
        if(flag != -1){//注册成功
            message = new Message("ok");
        }else{
            message = new Message("error");
        }
        response.getWriter().write(message.toJSON());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
