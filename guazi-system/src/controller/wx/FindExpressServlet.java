package controller.wx;

import bean.Express;
import bean.Message;
import bean.User;
import dao.BaseExpressDao;
import service.BaseExpressService;
import service.BaseUserService;
import service.ExpressService;
import service.UserService;
import util.DateFormatUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FindExpressServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        //code为取件码
        String code = request.getParameter("code");
        BaseExpressService expressService = new ExpressService();
        Express express = expressService.findByCode(code);
        Message message = null;
        if(express == null){
            message = new Message("error");
        }else{
            String time = DateFormatUtil.format(express.getInTime());
            express.setCode(time);
            message = new Message("ok",express);
        }
        response.getWriter().write(message.toJSON());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
