package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bean.Express;
import db.DruidUtil;

public class ExpressDaoImp implements BaseExpressDao {
    private static final String SQL_CREATE_TABLE = "CREATE TABLE web_express(id INT PRIMARY KEY AUTO_INCREMENT,enumber VARCHAR(64) UNIQUE,username VARCHAR(64),userphone VARCHAR(64),company VARCHAR(64),CODE VARCHAR(64) UNIQUE,intime DATETIME,outtime DATETIME,STATUS INT,sysPhone VARCHAR(64))";
    private static final String SQL_FIND_ALL = "SELECT * FROM WEB_EXPRESS";
    private static final String SQL_FIND_BY_ENUMBER = "SELECT * FROM WEB_EXPRESS WHERE ENUMBER=?";
    private static final String SQL_FIND_BY_USERPHONE = "SELECT * FROM WEB_EXPRESS WHERE USERPHONE=?";
    private static final String SQL_INSERT = "INSERT INTO WEB_EXPRESS (ENUMBER,COMPANY,USERNAME,USERPHONE,CODE,SYSPHONE,INTIME,status) VALUES(?,?,?,?,?,?,now(),0)";
    private static final String SQL_DELETE_BY_ID_OR_ENUMBER = "DELETE FROM WEB_EXPRESS WHERE ID=? OR ENUMBER=?";
    private static final String SQL_UPDATE_BY_ID_OR_ENUMBER = "UPDATE WEB_EXPRESS SET ENUMBER=?,COMPANY=?,USERNAME=?,USERPHONE=? WHERE ID=? OR ENUMBER=?";
    private static final String SQL_UPDATE_STATUS_BY_CODE = "UPDATE WEB_EXPRESS SET OUTTIME=now(),CODE=?,STATUS=1 WHERE CODE=?";
	private static final String SQL_FIND_BY_CODE = "SELECT * FROM WEB_EXPRESS WHERE CODE=?";
	private static final String SQL_ISEIXST_EXPRESS_BY_ENUMBER = "SELECT ID FROM WEB_EXPRESS WHERE ENUMBER=?";

	static{
        //1.	创建快递信息表格
        Connection conn = DruidUtil.getConnection();
        Statement state = null;
        try {
            state = conn.createStatement();
            state.execute(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            //e.printStackTrace();
        }finally {
            DruidUtil.close(conn,state,null);
        }

    }


	@Override
	public List<Express> findAll() {
		ArrayList<Express> data = new ArrayList<>();
		//1.	获取连接
		Connection conn = DruidUtil.getConnection();
		//2.	预编译SQL执行环境
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = conn.prepareStatement(SQL_FIND_ALL);
			//3.	执行sql语句 并得到结果集
			result = state.executeQuery();
			//4.	遍历结果集 , 并将每一行数据 存储到对象中 , 将对象存储到集合中
			while(result.next()) {
				int id = result.getInt("ID");
				String eNumber = result.getString("ENUMBER");
				String username = result.getString("USERNAME");
				String userphone = result.getString("USERPHONE");
				String company = result.getString("COMPANY");
				String code = result.getString("CODE");
				Timestamp inTime = result.getTimestamp("INTIME");
				Timestamp outTime = result.getTimestamp("OUTTIME");
				int status = result.getInt("STATUS");
				String sysPhone = result.getString("SYSPHONE");
				Express e = new Express(id, eNumber, username, userphone, company, code, inTime, outTime, status, sysPhone);
				data.add(e);
			}
			//5.	查询完毕, 返回集合
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            DruidUtil.close(conn,state,result);
        }
		return data;
	}

	@Override
	public Express findByENumber(String eNumber) {
		//1.	获取连接
		Connection conn = DruidUtil.getConnection();
		//2.	预编译SQL执行环境
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = conn.prepareStatement(SQL_FIND_BY_ENUMBER);
			state.setString(1, eNumber);
			//3.	执行sql语句 并得到结果集
			result = state.executeQuery();
			//4.	遍历结果集 , 并将每一行数据 存储到对象中 , 将对象存储到集合中
			if(result.next()) {
				int id = result.getInt("ID");
				String username = result.getString("USERNAME");
				String userphone = result.getString("USERPHONE");
				String company = result.getString("COMPANY");
				String code = result.getString("CODE");
				Timestamp inTime = result.getTimestamp("INTIME");
				Timestamp outTime = result.getTimestamp("OUTTIME");
				int status = result.getInt("STATUS");
				String sysPhone = result.getString("SYSPHONE");
				Express e = new Express(id, eNumber, username, userphone, company, code, inTime, outTime, status, sysPhone);
				return e;
			}
			//5.	查询完毕, 返回集合
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            DruidUtil.close(conn,state,result);
        }
		return null;
	}


	/**
	 * 根据取件码查询快递
	 *
	 * @param code 要查询的取件码
	 * @return 查询成功, 返回快递对象 <br> 查询失败返回null
	 */
	@Override
	public Express findByCode(String code) {
		//1.	获取连接
		Connection conn = DruidUtil.getConnection();
		//2.	预编译SQL执行环境
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			state = conn.prepareStatement(SQL_FIND_BY_CODE);
			state.setString(1, code);
			//3.	执行sql语句 并得到结果集
			result = state.executeQuery();
			//4.	遍历结果集 , 并将每一行数据 存储到对象中 , 将对象存储到集合中
			if(result.next()) {
				int id = result.getInt("ID");
				String username = result.getString("USERNAME");
				String userphone = result.getString("USERPHONE");
				String company = result.getString("COMPANY");
				String enumber = result.getString("ENUMBER");
				Timestamp inTime = result.getTimestamp("INTIME");
				Timestamp outTime = result.getTimestamp("OUTTIME");
				int status = result.getInt("STATUS");
				String sysPhone = result.getString("SYSPHONE");
				Express e = new Express(id, enumber, username, userphone, company, code, inTime, outTime, status, sysPhone);
				return e;
			}
			//5.	查询完毕, 返回集合
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DruidUtil.close(conn,state,result);
		}
		return null;
	}

	@Override
	public List<Express> findByUserPhone(String userPhone) {
		ArrayList<Express> data = new ArrayList<>();
		//1.	获取连接
				Connection conn = DruidUtil.getConnection();
				//2.	预编译SQL执行环境
				PreparedStatement state = null;
				ResultSet result = null;
				try {
					state = conn.prepareStatement(SQL_FIND_BY_USERPHONE);
					state.setString(1, userPhone);
					//3.	执行sql语句 并得到结果集
					result = state.executeQuery();
					//4.	遍历结果集 , 并将每一行数据 存储到对象中 , 将对象存储到集合中
					while(result.next()) {
						int id = result.getInt("ID");
						String eNumber = result.getString("ENUMBER");
						String username = result.getString("USERNAME");
						String company = result.getString("COMPANY");
						String code = result.getString("CODE");
						Timestamp inTime = result.getTimestamp("INTIME");
						Timestamp outTime = result.getTimestamp("OUTTIME");
						int status = result.getInt("STATUS");
						String sysPhone = result.getString("SYSPHONE");
						Express e = new Express(id, eNumber, username, userPhone, company, code, inTime, outTime, status, sysPhone);
						data.add(e);
					}
					//5.	查询完毕, 返回集合
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
                    DruidUtil.close(conn,state,result);
                }
				return data;
	}

	//返回结果为null是，表明已存在该快递
	@Override
	public String insert(Express e) {
		
		//1.	获取连接
		Connection conn = DruidUtil.getConnection();
		//2.	预编译SQL执行环境
        PreparedStatement state = null;
		try {
			if(!isExistExpressByENumber(e.getENumber())){
				state = conn.prepareStatement(SQL_INSERT);
				//3.	填充参数
				state.setString(1, e.getENumber());
				state.setString(2, e.getCompany());
				state.setString(3, e.getUsername());
				state.setString(4, e.getUserphone());
				//随机生成取件码
				String code = new Random().nextInt(899999)+100000+"";
				state.setString(5, code);
				state.setString(6, e.getSysPhone());
				//4.	执行SQL语句 , 并根据执行的结果, 返回
				return state.executeUpdate()>0?code:null;
			}
		} catch (SQLException e1) {
			if(e1.getMessage().contains("for key 'CODE'")) {
				//因为取件码重复, 导致插入失败 , 则重新插入
				return insert(e);
			}else {
				e1.printStackTrace();
			}
		}finally {
            DruidUtil.close(conn,state,null);
        }
		return null;
	}

	@Override
	public boolean deleteByIdOrENumber(String eNumber, int id) {
		//1.	获取连接
		Connection conn = DruidUtil.getConnection();
		//2.	准备预编译SQL执行环境
        PreparedStatement state = null;
		try {
            state = conn.prepareStatement(SQL_DELETE_BY_ID_OR_ENUMBER);
			//3.	填充预编译的参数
			state.setInt(1, id);
			state.setString(2, eNumber);
			//4.	执行, 并根据执行结果 返回true/false
			return state.executeUpdate()>0?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    DruidUtil.close(conn,state,null);
        }
		return false;
	}

	@Override
	public boolean updateByIdOrENumber(String eNumber, int id, Express newExpress) {
		//1.	获取连接
				Connection conn = DruidUtil.getConnection();
				//2.	准备预编译SQL执行环境
                PreparedStatement state = null;
				try {
                    state = conn.prepareStatement(SQL_UPDATE_BY_ID_OR_ENUMBER);
					//3.	填充预编译的参数
					state.setString(1, newExpress.getENumber());
					state.setString(2, newExpress.getCompany());
					state.setString(3, newExpress.getUsername());
					state.setString(4, newExpress.getUserphone());
					
					state.setInt(5, id);
					state.setString(6, eNumber);
					//4.	执行, 并根据执行结果 返回true/false
					return state.executeUpdate()>0?true:false;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
				    DruidUtil.close(conn,state,null);
                }
				return false;
	}

	@Override
	public boolean updateStatusByCode(String code) {
		//1.	获取连接
		Connection conn = DruidUtil.getConnection();
		//2.	准备预编译SQL执行环境
        PreparedStatement state = null;
		try {
            state = conn.prepareStatement(SQL_UPDATE_STATUS_BY_CODE);
			//3.	填充预编译的参数
			state.setString(1, null);
			state.setString(2, code);
			//4.	执行, 并根据执行结果 返回true/false
			return state.executeUpdate()>0?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    DruidUtil.close(conn,state,null);
        }
		return false;
	}

	/**
	 * 根据快递单号eNumber判断是否已存在
	 *
	 * @param eNumber
	 * @return
	 */
	@Override
	public boolean isExistExpressByENumber(String eNumber) {
		Connection conn = null;
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			conn = DruidUtil.getConnection();
			state = conn.prepareStatement(SQL_ISEIXST_EXPRESS_BY_ENUMBER);
			state.setString(1,eNumber);
			result = state.executeQuery();
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DruidUtil.close(conn,state,result);
		}
		return false;
	}

}
