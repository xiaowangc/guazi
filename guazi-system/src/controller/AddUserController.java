package controller;

import bean.Message;
import bean.User;
import service.BaseUserService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseUserService userService = new UserService();
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String flag = request.getParameter("flag");
        int flagValue = Integer.parseInt(flag);

        User user = new User(name,password,phone,flagValue);
        int f = userService.insert(user);
        Message msg = null;
        if(f == 1){
            //插入成功
            msg = new Message("ok");
        }else{
            msg = new Message("error");
        }
        response.getWriter().write(msg.toJSON());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
