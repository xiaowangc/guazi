package service;

import bean.User;
import dao.BaseUserDao;
import dao.UserDaoImp;

import java.util.List;

public class UserService implements BaseUserService {

    private BaseUserDao userDao = new UserDaoImp();
    /**
     * 查询所有用户
     *
     * @return 查询结果
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 根据用户类型标记， 查询用户
     *
     * @param flag 用户标记： 0 普通用户  1快递员
     * @return 查询的结果
     */
    @Override
    public List<User> findUsersByFlag(int flag) {
        return userDao.findUsersByFlag(flag);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<User> findUsers() {
        return this.findUsersByFlag(0);
    }

    /**
     * 查询所有快递员
     *
     * @return
     */
    @Override
    public List<User> findCouriers() {
        return this.findUsersByFlag(1);
    }

    /**
     * 查询普通用户人数
     */
    public int userNumber(){
        return this.findUsers().size();
    }

    /**
     * 查询快递员的人数
     */
    public int courierNumber(){
        return this.findCouriers().size();
    }



    /**
     * 根据昵称或电话号码查询用户
     *
     * @param userNameOrPhoneNumber 用户昵称或电话号
     * @param type                  查询的用户类型 0 普通用户 1快递员
     * @return 查询结果
     */
    @Override
    public User findUserByPhoneNumber(String userNameOrPhoneNumber, int type) {
        return userDao.findUserByPhoneNumber(userNameOrPhoneNumber,type);
    }

    /**
     * 根据电话号码和密码查询用户信息
     *
     * @param phoneNumber 电话号
     * @param password    密码
     * @return 查询结果 -1 登录失败 0用户登录成功 1快递员登录成功
     */
    @Override
    public int login(String phoneNumber, String password) {
        return userDao.findUserByPhoneAndPassword(phoneNumber,password);
    }

    /**
     * 插入用户
     *
     * @param user 用户信息， 至少包含账号，密码，电话号，标记（0用户，1快递员）
     * @return 插入的结果  1成功
     */
    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    /**
     * 根据ID 修改用户信息
     *
     * @param id   用户ID
     * @param user 新的用户信息
     * @return 修改结果 1成功
     */
    @Override
    public int updateUserById(int id, User user) {
        return userDao.updateUserById(id,user);
    }

    /**
     * 根据ID 删除用户信息
     *
     * @param id 用户ID
     * @return 修改结果 1成功
     */
    @Override
    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    /**
     * 根据帐号+密码 更改密码
     *
     * @param phone   手机号
     * @param oldPass 旧密码
     * @param newPass 新密码
     * @return 修改结果 ，1成功
     */
    @Override
    public int updatePassword(String phone, String oldPass, String newPass) {
        return userDao.updatePasswordByPhoneAndPassword(phone,oldPass,newPass);
    }
}
