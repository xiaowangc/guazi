package controller;

import bean.Message;
import bean.User;
import service.BaseUserService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QueryUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseUserService userService = new UserService();
        String flag = request.getParameter("flag");

        HttpSession session = request.getSession();
        if("0".equals(flag)){
            List<User> userList = userService.findUsers();
            session.setAttribute("userList",userList);
            response.sendRedirect("views/user/list.jsp");
        }else if("1".equals(flag)){
            List<User> courierList = userService.findCouriers();
            session.setAttribute("courierList",courierList);
            response.sendRedirect("views/courier/list.jsp");
        }else{
           response.getWriter().write("<script>alert('find fail')</script>");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
