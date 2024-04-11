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

public class SelectUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        String phone = request.getParameter("phone");
        String flag = request.getParameter("flag");
        int flagValue = Integer.parseInt(flag);
        BaseUserService userService = new UserService();
        User user = userService.findUserByPhoneNumber(phone,flagValue);
        Message msg = user != null ? new Message("ok",user) : new Message("error");
        response.getWriter().write(msg.toJSON());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
