package com.model;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import com.bean.UserBean;
import com.exception.ApplicationException;
import com.exception.DuplicateRecordException;
import com.exception.RecordNotFoundException;
import com.util.EmailBuilder;
import com.util.EmailMessage;
import com.util.EmailUtility;
import com.util.JDBCDataSource;

public class UserModel  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(UserModel.class);
	//ResourceBundle rb=ResourceBundle.getBundle("com.bundle.system");
	/*String formator=rb.getString("formate.date");
	SimpleDateFormat sdf=new SimpleDateFormat(formator);*/
	
	@Override
	public String getTableName() {
		
		return "st_user";
	}
	public String getKey() {
		return null;
	}
	


	public String getValue() {
	
		return null;
	}

	public long add(UserBean bean) throws ApplicationException {
		log.debug("Model add Started");
		long pk = 0;
		Connection conn=null;
		try {
			pk = nextPK();
		
				// Get auto-generated next primary key
			//	System.out.println(pk + " in ModelJDBC");			
				conn=JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement ps=conn.prepareStatement("insert into "+getTableName()+" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setLong(1, pk);
				ps.setString(2, bean.getFirstName());
				ps.setString(3, bean.getLastName());
				ps.setString(4, bean.getLogin());
				ps.setString(5, bean.getPassword());
				ps.setDate(6, new java.sql.Date(bean.getDob().getTime()));
				ps.setString(7, bean.getMobileNo());
				ps.setLong(8, bean.getRoleId());
				ps.setInt(9, bean.getUnsuccessfulLogin());
				ps.setString(10, bean.getGender());
				ps.setTimestamp(11, bean.getLastLogin());
				ps.setString(12, bean.getUserLock());
				ps.setString(13, bean.getRegisteredIp());
				ps.setString(14, bean.getLastLoginIp());
				ps.setString(15, bean.getCreatedBy());
				ps.setString(16, bean.getModifiedBy());
				ps.setTimestamp(17, bean.getCreatedDatetime());
				ps.setTimestamp(18, bean.getModifiedDatetime());
				ps.executeUpdate();
				conn.commit(); // End transaction
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Database Exception..", e);
			}
	try {
		conn.rollback();
	} catch (Exception ex) {
		ex.printStackTrace();
		throw new ApplicationException(
				"Exception : add rollback exception " + ex.getMessage());
	}
/*	throw new ApplicationException("Exception : Exception in add User");
*/ finally {
	JDBCDataSource.closeConnection(conn);
}
log.debug("Model add End");
return pk;
}
	public void update(UserBean bean) throws ApplicationException,
	DuplicateRecordException {
log.debug("Model update Started");
Connection conn=null;

/*UserBean beanExist = findByLogin(bean.getLogin());
// Check if updated LoginId already exist
if (beanExist != null && !(beanExist.getId() == bean.getId())) {
	throw new DuplicateRecordException("LoginId is already exist");
}*/

try {
	conn = JDBCDataSource.getConnection();
	conn.setAutoCommit(false); // Begin transaction
	PreparedStatement ps = conn
			.prepareStatement("UPDATE "
					+ getTableName()
					+ " SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,UNSUCCESSFUL_LOGIN=?,GENDER=?,LAST_LOGIN=?,USER_LOCK=?,REGISTERED_IP=?,LAST_LOGIN_IP=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
	ps.setString(1, bean.getFirstName());
	ps.setString(2, bean.getLastName());
	ps.setString(3, bean.getLogin());
	ps.setString(4, bean.getPassword());
	ps.setDate(5, new java.sql.Date(bean.getDob().getTime()));
	ps.setString(6, bean.getMobileNo());
	ps.setLong(7, bean.getRoleId());
	ps.setInt(8, bean.getUnsuccessfulLogin());
	ps.setString(9, bean.getGender());
	ps.setTimestamp(10, bean.getLastLogin());
	ps.setString(11, bean.getUserLock());
	ps.setString(12, bean.getRegisteredIp());
	ps.setString(13, bean.getLastLoginIp());
	ps.setString(14, bean.getCreatedBy());
	ps.setString(15, bean.getModifiedBy());
	ps.setTimestamp(16, bean.getCreatedDatetime());
	ps.setTimestamp(17, bean.getModifiedDatetime());
	ps.setLong(18, bean.getId());
	ps.executeUpdate();
	conn.commit(); // End transaction
	ps.close();
	}
catch (Exception e) {
	e.printStackTrace();
	log.error("Database Exception..", e);
	try {
		conn.rollback();
	} catch (Exception ex) {
		e.printStackTrace();
		throw new ApplicationException(
				"Exception : Delete rollback exception "
						+ ex.getMessage());
	}
	throw new ApplicationException("Exception in updating User ");
}
finally {
	JDBCDataSource.closeConnection(conn);
}
log.debug("Model update End");
updateModifiedInfo(bean);
}
	public boolean delete() throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn=null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement ps = conn.prepareStatement("DELETE FROM "
					+ getTableName() + " WHERE ID=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			conn.commit(); // End transaction
			ps.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	return true;
	}
	
	public UserBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		Connection conn=null;
		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE ID=?");
		UserBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getTimestamp(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getInt(8));
				bean.setUnsuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setUserLock(rs.getString(12));
				bean.setRegisteredIp(rs.getString(13));
				bean.setLastLoginIp(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	public List<UserBean> search(UserBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}
	
	@SuppressWarnings("deprecation")
	public List<UserBean> search(UserBean bean, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		Connection conn=null;
		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName()
						+ "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
			}
			if (bean.getPassword
					() != null && bean.getPassword().length() > 0) {
				sql.append(" AND PASSWORD like '" + bean.getPassword() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getGender());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO = " + bean.getMobileNo());
			}
			if (bean.getRoleId() > 0) {
				sql.append(" AND ROLE_ID = " + bean.getRoleId());
			}
			if (bean.getUnsuccessfulLogin() > 0) {
				sql.append(" AND UNSUCCESSFUL_LOGIN = "
						+ bean.getUnsuccessfulLogin());
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" AND GENDER like '" + bean.getGender() + "%'");
			}
			if (bean.getLastLogin() != null
					&& bean.getLastLogin().getTime() > 0) {
				sql.append(" AND LAST_LOGIN = " + bean.getLastLogin());
			}
			if (bean.getRegisteredIp() != null
					&& bean.getRegisteredIp().length() > 0) {
				sql.append(" AND REGISTERED_IP like '" + bean.getRegisteredIp()
						+ "%'");
			}
			if (bean.getLastLoginIp() != null
					&& bean.getLastLoginIp().length() > 0) {
				sql.append(" AND LAST_LOGIN_IP like '" + bean.getLastLoginIp()
						+ "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println(sql);
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getTimestamp(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getInt(8));
				bean.setUnsuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setUserLock(rs.getString(12));
				bean.setRegisteredIp(rs.getString(13));
				bean.setLastLoginIp(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}
	
	public List<?> list() throws ApplicationException {
		return list(0, 0);
	}
	/**
	 * Get List of User with pagination
	 * 
	 * @return list : List of users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	
	public List<UserBean> list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		Connection conn=null;
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		StringBuffer sql = new StringBuffer("select * from " + getTableName());
		// if page size is greater than zero then apply pagination
	/*	if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			
		}*/
	//sql.append(" limit " + pageNo + "," + pageSize );
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserBean bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getTimestamp(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getInt(8));
				bean.setUnsuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setUserLock(rs.getString(12));
				bean.setRegisteredIp(rs.getString(13));
				bean.setLastLoginIp(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}
	public UserBean authenticate(String login, String password)
			throws ApplicationException {
		log.debug("Model authenticate Started");
		Connection conn=null;
		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE LOGIN = ? AND PASSWORD = ?");

		log.info("SQL : " + sql);

		UserBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getTimestamp(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getInt(8));
				bean.setUnsuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setUserLock(rs.getString(12));
				bean.setRegisteredIp(rs.getString(13));
				bean.setLastLoginIp(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model authenticate End");
		return bean;
	
		}
	public UserBean findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		Connection conn=null;
		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE LOGIN=?");
		UserBean bean = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getTimestamp(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getInt(8));
				bean.setUnsuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setUserLock(rs.getString(12));
				bean.setRegisteredIp(rs.getString(13));
				bean.setLastLoginIp(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}
	public boolean userLock(String login) throws RecordNotFoundException,
	ApplicationException {
		log.debug("Service lock Started");
		
		boolean flag = false;
		UserBean beanExist = null;
		try {
			beanExist = findByLogin(login);
			if (beanExist != null) {
				beanExist.setUserLock(UserBean.ACTIVE);
				update(beanExist);
				flag = true;
	} 
			else {
		throw new RecordNotFoundException("LoginId not exist");
	}
} 	catch (DuplicateRecordException e) {
		log.error("Application Exception..", e);
		throw new ApplicationException("Database Exception");
}
		log.debug("Service lock End");
		return flag;
		}
	public List<UserBean> getRoles(UserBean bean) throws ApplicationException {
		log.debug("Model get roles Started");
		Connection conn=null;
		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName()
				+ " WHERE role_Id=?");
		List<UserBean> list = new ArrayList<UserBean>();
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, bean.getRoleId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getTimestamp(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getInt(8));
				bean.setUnsuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setUserLock(rs.getString(12));
				bean.setRegisteredIp(rs.getString(13));
				bean.setLastLoginIp(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model get roles End");
		return list;
	}
	public boolean changePassword(Long id, String oldPassword,
			String newPassword) throws RecordNotFoundException,
			ApplicationException {

		log.debug("model changePassword Started");
		boolean flag = false;
		UserBean beanExist = null;

		beanExist = findByPK(id);
		if (beanExist != null && beanExist.getPassword().equals(oldPassword)) {
			beanExist.setPassword(newPassword);
			try {
				update(beanExist);
			} catch (DuplicateRecordException e) {
				log.error(e);
				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
		} else {
			throw new RecordNotFoundException("Login not exist");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", beanExist.getLogin());
		map.put("password", beanExist.getPassword());
		map.put("firstName", beanExist.getFirstName());
		map.put("lastName", beanExist.getLastName());

		String message =EmailBuilder.getChangePasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(beanExist.getLogin());
		msg.setSubject("OCFMS  Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		log.debug("Model changePassword End");
		return flag;

	}
	public UserBean updateAccess(UserBean bean) throws ApplicationException {
		return null;
	}
	public long registerUser(UserBean bean) throws ApplicationException,
	DuplicateRecordException {

		log.debug("Model add Started");

		 add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);
			
		EmailMessage msg = new EmailMessage();
			
		msg.setTo(bean.getLogin());
		msg.setSubject("Registration is successful for OCFMS Project");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
			
			EmailUtility.sendMail(msg);
			return  0;//pk;
		}
	public boolean resetPassword(UserBean bean) throws ApplicationException {

		String str=String.valueOf(new Date().getTime());
		
	//	String newPassword = str.substring(str.length()-4);
		//UserBean userData = findByPK(bean.getId());
	//	userData.setPassword(newPassword);

		try {
			update(bean);
		} catch (DuplicateRecordException e) {
			return false;
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());
		map.put("firstName", bean.getFirstName());
		map.put("lastName", bean.getLastName());

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Password has been reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		updateModifiedInfo(bean);

		return true;
	}
	public boolean forgetPassword(String login) throws ApplicationException,
				RecordNotFoundException {
			UserBean userData = findByLogin(login);
			boolean flag = false;
			
			if (userData == null) {
				throw new RecordNotFoundException("Email ID does not exists !");
			}
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("login", userData.getLogin());
			map.put("password", userData.getPassword());
			System.out.println(userData.getPassword());
			map.put("firstName", userData.getFirstName());
			map.put("lastName", userData.getLastName());
/*			map.put("otp", otp);
*/			String message = EmailBuilder.getForgetPasswordMessage(map);
			EmailMessage msg = new EmailMessage();
			msg.setTo(login);
			msg.setSubject("OCHMS Your PassWord Recovered ");
			msg.setMessage(message);
			msg.setMessageType(EmailMessage.HTML_MSG);
			EmailUtility.sendMail(msg);
			flag = true;
			
			return flag;
			}
		public  String generateOTP(String login) throws ApplicationException, RecordNotFoundException{
			Date date=new Date();
			long otp=date.getTime();
			String otp1=String.valueOf(otp);
			String otp2=otp1.substring(otp1.length()-4);
			UserBean userData = findByLogin(login);
			boolean flag = false;
			if (userData == null) {
				throw new RecordNotFoundException("Email ID does not exists !");
			}
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("login", userData.getLogin());
			map.put("firstName", userData.getFirstName());
			map.put("lastName", userData.getLastName());
			map.put("otp", otp2);
			String message = EmailBuilder.getOTPMessage(map);
			EmailMessage msg = new EmailMessage();
			msg.setTo(login);
			msg.setSubject("OCHMS sent you  OTP ");
			msg.setMessage(message);
			msg.setMessageType(EmailMessage.HTML_MSG);
			EmailUtility.sendMail(msg);
			flag = true;
			return otp2;
			
		}
/*	public long dateToLong(Date d){
		long l=d.getTime();
		return l;
	}
*/

	
}
