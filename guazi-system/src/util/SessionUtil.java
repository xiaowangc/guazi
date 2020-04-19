package util;

import javax.servlet.http.HttpSession;

public class SessionUtil {

    public static void setManageFlag(HttpSession session){

            session.setAttribute("manager",1);

    }

    public static boolean getManageFlag(HttpSession session){
        Integer i = (Integer) session.getAttribute("manager");
        return i != null;
    }

}
