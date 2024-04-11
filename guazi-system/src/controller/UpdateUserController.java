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

public class UpdateUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseUserService userService = new UserService();
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        String id = request.getParameter("id");
        int idValue = Integer.parseInt(id);
        User user = new User(name,password,phone);
        int f = userService.updateUserById(idValue,user);
        Message msg = f == 1 ? new Message("ok") : new Message("error");
        response.getWriter().write(msg.toJSON());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
