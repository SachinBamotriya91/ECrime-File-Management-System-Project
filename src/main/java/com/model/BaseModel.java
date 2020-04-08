package com.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import com.bean.DropdownListBean;
import com.bean.UserBean;
import com.exception.ApplicationException;
import com.exception.DatabaseException;
import com.util.DataUtility;
import com.util.JDBCDataSource;
import org.apache.log4j.Logger;

public abstract class BaseModel implements Serializable,DropdownListBean,
Comparable<BaseModel> {
	/**
	 * SerialVerionID of Class
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(BaseModel.class);

	protected long id;
	protected String createdBy;
	protected String modifiedBy;
	protected Timestamp createdDatetime;
	protected Timestamp modifiedDatetime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}
	public int compareTo(BaseModel next) {
		return (int) (id - next.getId());
	}
	public abstract String getTableName();
	public long nextPK() throws DatabaseException{
		log.debug("Model nextPK Started");
		Connection conn = null;
		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT MAX(ID) FROM " + getTableName());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	public void updateCreatedInfo(){
		log.debug("Model update Started..." +  createdBy);

		Connection conn = null;

		String sql = "UPDATE " + getTableName()
				+ " SET CREATED_BY=?, CREATED_DATETIME=? WHERE ID=?";
		log.debug(sql);

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, createdBy);
			pstmt.setTimestamp(2, DataUtility.getCurrentTimestamp());
			pstmt.setLong(3, id);
		
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (SQLException e) {
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
			try {
				throw new ApplicationException(e);
			} catch (ApplicationException e1) {
				e1.printStackTrace();
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	/**
	 * Updates modified by info
	 * 
	 * @param model
	 * @throws Exception
	 */
	public void updateModifiedInfo(UserBean bean) {

		log.debug("Model update Started");
		Connection conn = null;

		String sql = "UPDATE " + getTableName()
				  + " SET MODIFIED_BY=?, MODIFIED_DATETIME=? WHERE ID=?";

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getLogin());
			pstmt.setTimestamp(2, DataUtility.getCurrentTimestamp());
			pstmt.setLong(3, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (SQLException e) {
			log.error("Database Exception..", e);
			JDBCDataSource.trnRollback(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
		
	}
	protected <T extends BaseModel> T populateModel(T model, ResultSet rs)
			throws SQLException {
		model.setId(rs.getLong("ID"));
		model.setCreatedBy(rs.getString("CREATED_BY"));
		model.setModifiedBy(rs.getString("MODIFIED_BY"));
		model.setCreatedDatetime(rs.getTimestamp("CREATED_DATETIME"));
		model.setModifiedDatetime(rs.getTimestamp("MODIFIED_DATETIME"));
		return model;
	}

}
