package test;

import bean.Express;
import bean.User;
import dao.BaseExpressDao;
import dao.BaseUserDao;
import dao.ExpressDaoImp;
import dao.UserDaoImp;

import java.util.List;

public class DBTest {

    public static void main(String[] args) {
        ExpressDaoImp ex = new ExpressDaoImp();
//        String str = ex.insert(new Express("654321","pache","14735343688","安能快递","77788885858"));
//        System.out.println(str);
        boolean flag = ex.isExistExpressByENumber("4567");
        System.out.println(flag);
    }
}
