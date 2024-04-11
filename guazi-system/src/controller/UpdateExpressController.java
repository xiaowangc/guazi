package controller;

import bean.Express;
import bean.Message;
import service.BaseExpressService;
import service.ExpressService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateExpressController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        int idValue = Integer.parseInt(id);
        String number = request.getParameter("number");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String company = request.getParameter("company");
        Express e = new Express(number,name,phone,company,"-1");
        BaseExpressService service = new ExpressService();
        boolean b = service.updateById(idValue,e);
        Message msg = b ? new Message("ok") : new Message("error");
        response.getWriter().write(msg.toJSON());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
