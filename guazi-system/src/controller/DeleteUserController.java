package controller;

import bean.Message;
import service.BaseUserService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseUserService userService = new UserService();
        String id = request.getParameter("id");
        int idValue = Integer.parseInt(id);
        int f = userService.deleteUserById(idValue);
        Message msg = null;
        if(f == 1){
            //删除成功
            msg = new Message("ok");
        }else {
            msg = new Message("error");
        }
        response.getWriter().write(msg.toJSON());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
