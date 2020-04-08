package com.util;


import java.sql.Connection;

import java.sql.SQLException;
import java.util.ResourceBundle;

import com.exception.ApplicationException;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class JDBCDataSource {

	
	private static JDBCDataSource datasource;

	public JDBCDataSource() {
	}

	private ComboPooledDataSource cpds = null;

	public static JDBCDataSource getInstance() {
		if (datasource == null) {

			ResourceBundle rb = ResourceBundle
					.getBundle("com.bundle.system");

			datasource = new JDBCDataSource();
			datasource.cpds = new ComboPooledDataSource();
			try {
				datasource.cpds.setDriverClass(rb.getString("driver"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			datasource.cpds.setJdbcUrl(rb.getString("url"));
			datasource.cpds.setUser(rb.getString("username"));
			datasource.cpds.setPassword(rb.getString("password"));
			datasource.cpds.setInitialPoolSize(10);
			datasource.cpds.setAcquireIncrement(10);
		
				datasource.cpds.setInitialPoolSize(new Integer((String) rb
					.getString("initialPoolSize")));
			datasource.cpds.setAcquireIncrement(new Integer((String) rb
					.getString("acquireIncrement")));
			datasource.cpds.setMaxPoolSize(new Integer((String) rb
					.getString("maxPoolSize")));
			datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
			datasource.cpds.setMinPoolSize(new Integer((String) rb
					.getString("minPoolSize")));

		}
		return datasource;
	}


	public static Connection getConnection() throws SQLException  {
		return getInstance().cpds.getConnection();
	}
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

public static void trnRollback(Connection connection) {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				try {
					throw new ApplicationException(ex);
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
			}
		}
	}

	}


