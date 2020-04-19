package controller;

import service.ExpressService;
import util.MySpring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QueryExpressAllController extends HttpServlet {

    private ExpressService expressService = MySpring.getBean("service.ExpressService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute("expressAll",expressService.findAll());
        resp.sendRedirect("views/express/list.jsp");
    }
}
