package service;

import dao.UserDaoImp;
import util.MySpring;

public class LoginService {

    private UserDaoImp userDaoImp = MySpring.getBean("dao.UserDaoImp");

    /**
    *
    *    通过用户输入的username,password来从dao层中找到对应的User，进而进行登录验证
     *    -1 登录失败  0用户登录成功 1快递员登录成功
    */

    public String selectOne(String phoneNumber,String password){
        int flag = userDaoImp.findUserByPhoneAndPassword(phoneNumber,password);
        if(flag == -1){
            return "login fail";
        }else if(flag == 0){
            return "user login successful";
        }
        return "courier login successful";
    }
}
