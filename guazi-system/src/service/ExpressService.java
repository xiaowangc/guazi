package service;

import bean.Express;
import dao.BaseExpressDao;
import dao.ExpressDaoImp;

import java.util.List;

public class ExpressService implements BaseExpressService {

    private BaseExpressDao expressDao = new ExpressDaoImp();
    /**
     * 查询所有快件，并返回列表类型
     */
    public List<Express> findAll(){
        return expressDao.findAll();
    }

    /**
     * 根据id删除快件
     *
     * @param id
     */
    @Override
    public boolean deleteById(int id) {

        return expressDao.deleteByIdOrENumber(null,id);
    }

    /**
     * 快件的增加
     *
     * @param e 要增加的快件对象 , 包含了一个快递的所有信息
     * @return 插入成功时, 返回取件码, 插入失败时返回null
     */
    @Override
    public String insert(Express e) {
        return expressDao.insert(e);
    }

    /**
     * 根据取件码查询快递
     *
     * @param code 要查询的取件码
     * @return 查询成功, 返回快递对象 <br> 查询失败返回null
     */
    @Override
    public Express findByCode(String code) {
        return expressDao.findByCode(code);
    }

    /**
     * 根据单号查询快递
     *
     * @param eNumber 要查询的快递单号
     * @return 查询成功, 返回快递对象 <br> 查询失败返回null
     */
    @Override
    public Express findByENumber(String eNumber) {
        return expressDao.findByENumber(eNumber);
    }

    /**
     * 修改快递 , 通过id
     *
     * @param id         快递的id
     * @param newExpress 新的快递信息.
     * @return 修改的结果, true表示修改成功, false表示修改失败
     */
    @Override
    public boolean updateById(int id, Express newExpress) {
        return expressDao.updateByIdOrENumber(null,id,newExpress);
    }

    /**
     * 通过用户的手机号, 查询用户所有快递
     *
     * @param userPhone 用户手机号码
     * @return 包含用户所有快递的集合
     */
    @Override
    public List<Express> findByUserPhone(String userPhone) {
        return expressDao.findByUserPhone(userPhone);
    }

    /**
     * 根据取件码 修改快递状态为已取件, 修改取件时间为当前时间
     *
     * @param code 取件码
     * @return 取件的结果, true表示取件成功, false表示取件失败
     */
    @Override
    public boolean updateStatusByCode(String code) {
        return expressDao.updateStatusByCode(code);
    }

    /**
     * 查询所有快件的数量
     */
    public int queryAllExpressNumber(){
        return expressDao.findAll().size();
    }

    /**
     * 根据eNumber快递单号判断是否存在该快递
     *
     * @param eNumber
     * @return
     */
    @Override
    public boolean isExistExpressByENumber(String eNumber) {

        return expressDao.isExistExpressByENumber(eNumber);
    }

    /**
     * 根据快递的状态查询其状态的所有数量
     */
    public int queryExpressNumberByStatus(int status){
        List<Express> all = expressDao.findAll();
        int sum = 0;
        for(Express e:all){
            if(e.getStatus() == status){
                sum += 1;
            }
        }
        return sum;
    }

}
