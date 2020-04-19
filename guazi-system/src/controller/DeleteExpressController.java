package controller;

import service.BaseExpressService;
import service.ExpressService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteExpressController extends HttpServlet {

    private BaseExpressService service = new ExpressService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int idValue = Integer.parseInt(id);
        boolean result = service.deleteById(idValue);
        String json;
        if(result){
            //删除成功{"result":"ok"}
            json = "{\"result\":\"ok\"}";
        }else{
            //删除失败
            json = "{\"result\":\"error\"}";
        }
        response.getWriter().append(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
