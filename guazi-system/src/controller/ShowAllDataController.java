package controller;

import service.BaseExpressService;
import service.BaseUserService;
import service.ExpressService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAllDataController extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseUserService userService = new UserService();
        BaseExpressService expressService = new ExpressService();
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("userNum",userService.userNumber());
        session.setAttribute("courierNum",userService.courierNumber());
        //待取快递数量
        session.setAttribute("existExpress",expressService.queryExpressNumberByStatus(0));
        //所有快递数量
        session.setAttribute("allExpress",expressService.queryAllExpressNumber());

        response.sendRedirect("views/console.jsp");
    }
}
