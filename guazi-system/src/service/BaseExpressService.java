package service;

import bean.Express;

import java.util.List;

public interface BaseExpressService {

    /**
     * 查询所有快件，并返回列表类型
     */
   List<Express> findAll();
    /**
     * 根据id删除快件
     */
   boolean deleteById(int id);

    /**
     * 快件的增加
     * @param e	要增加的快件对象 , 包含了一个快递的所有信息
     * @return 插入成功时, 返回取件码, 插入失败时返回null
     */
    String insert(Express e);

    /**
     * 根据取件码查询快递
     * @param code 要查询的取件码
     * @return 查询成功, 返回快递对象 <br> 查询失败返回null
     */
    Express findByCode(String code);

 /**
  * 根据单号查询快递
  * @param eNumber 要查询的快递单号
  * @return 查询成功, 返回快递对象 <br> 查询失败返回null
  */
 Express findByENumber(String eNumber);

    /**
     * 修改快递 , 通过id
     * @param id 快递的id
     * @param newExpress 新的快递信息.
     * @return 修改的结果, true表示修改成功, false表示修改失败
     */
    boolean updateById(int id, Express newExpress);

    /**
     * 通过用户的手机号, 查询用户所有快递
     * @param userPhone 用户手机号码
     * @return 包含用户所有快递的集合
     */
    List<Express> findByUserPhone(String userPhone);

    /**
     * 根据取件码 修改快递状态为已取件, 修改取件时间为当前时间
     * @param code 取件码
     * @return 取件的结果, true表示取件成功, false表示取件失败
     */
    boolean updateStatusByCode(String code);

    /**
     * 根据快递的状态查询其状态的所有数量
     */
    int queryExpressNumberByStatus(int status);

    /**
     * 查询所有快件的数量
     */
    int queryAllExpressNumber();

    /**
     * 根据eNumber快递单号判断是否存在该快递
     * @param eNumber
     * @return
     */
    boolean isExistExpressByENumber(String eNumber);
}
