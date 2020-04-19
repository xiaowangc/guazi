package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/","/wx/index.jsp","/wx/expressList.jsp","/wx/personQRcode.jsp","/wx/pickExpress.jsp","/wx/addExpress.html",
"/wx/expressAssist.html","/wx/lazyboard.html","/wx/wxUserhome.html","/wx/userCheckStart.html","/wx/wxIdCardUserInfoModify.html","/wx/500.html","/wx/405.html","/wx/404.html"})
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            response.sendRedirect("login.jsp"); //拦截
        }else{
            chain.doFilter(req, resp);//放行，访问该页面
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
