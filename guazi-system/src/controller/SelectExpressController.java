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

public class SelectExpressController extends HttpServlet {

    private BaseExpressService service = new ExpressService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        String number = request.getParameter("number");

        if(number != null && !"".equals(number)){
            Express express = service.findByENumber(number);
            Message message = null;
            if(express == null){
                //查找失败
                message = new Message("error");

            }else{
                message = new Message("ok",express);
            }
            response.getWriter().write(message.toJSON());
        }else{
            response.getWriter().write("<script>alert('单号出错或为空')</script>");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
