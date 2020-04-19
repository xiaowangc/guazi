package controller.wx;

import bean.Express;
import bean.Message;
import service.BaseExpressService;
import service.ExpressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddExpressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String expressId = request.getParameter("expressId");
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        String company = request.getParameter("company");
        Express express = new Express(expressId,username,userPhone,company,"-1");
        BaseExpressService expressService = new ExpressService();
        String flag = expressService.insert(express);//插入成功后返回一个取件码code
        Message message = null;
        if(flag != null){
            message = new Message("ok");
        }else{
            message = new Message("error");
        }
        response.getWriter().write(message.toJSON());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
