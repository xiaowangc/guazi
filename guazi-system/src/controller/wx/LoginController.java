package controller.wx;

import bean.Message;
import bean.User;
import service.BaseUserService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginController extends HttpServlet {

//    登录的用户有可能是快递员也有可能是普通用户，因为他们都可以取快递
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        BaseUserService userService = new UserService();
        //查询结果 -1 登录失败 0用户登录成功 1快递员登录成功
        int flag = userService.login(username, password);
        Message message = null;
        User user = null;
        switch (flag){
            case -1:
                message = new Message("error");
                break;
            case 0:
                user = new User(username,password,username,0);
                message = new Message("ok",user);
                break;
            case 1:
                user = new User(username,password,username,1);
                message = new Message("ok",user);
                break;
        }
        //user可能为null
        request.getSession().setAttribute("user",user);
        response.getWriter().write(message.toJSON());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
