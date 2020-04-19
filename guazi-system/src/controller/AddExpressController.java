package controller;

import bean.Express;
import bean.Message;
import dao.BaseExpressDao;
import service.BaseExpressService;
import service.ExpressService;
import util.SendSms;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddExpressController extends HttpServlet {

    private BaseExpressService baseExpressService = new ExpressService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取ajax发送过来参数，并组装形成快递信息
        System.out.println("enter");
        String eNumber = request.getParameter("number");
        String company = request.getParameter("company");
        String username = request.getParameter("name");
        String userphone = request.getParameter("phone");
        Express e = new Express(eNumber,username,userphone,company,"-1");
        String code = baseExpressService.insert(e);
        Message message = null;
        if(code == null ){
            //插入失败,将提示信息发送给ajax，可能单号已存在
            message = new Message("error");
        }else{
            //插入成功，向顾客发送验证码

            message = new Message("ok");
            //由于阿里云短信用完了，所以短信接口给取消了
//            boolean flag = SendSms.send(userphone,code);
        }
        response.getWriter().write(message.toJSON());


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
