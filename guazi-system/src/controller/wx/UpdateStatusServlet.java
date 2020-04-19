package controller.wx;

import bean.Message;
import service.BaseExpressService;
import service.ExpressService;
import sun.awt.AWTAccessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//修改快递为已取

public class UpdateStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        BaseExpressService expressService = new ExpressService();
        boolean flag = expressService.updateStatusByCode(code);
        Message message = null;
        if ( flag ){
            //更行快递状态成功（未取 --> 已取）
            message = new Message("ok");
        }else{
            message = new Message("error");
        }
        response.getWriter().write(message.toJSON());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
