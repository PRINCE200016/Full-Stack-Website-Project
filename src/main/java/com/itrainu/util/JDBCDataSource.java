package com.itrainu.util;

import java.beans.PropertyVetoException;
//import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class JDBCDataSource {

	private static JDBCDataSource datasource;

	private static ComboPooledDataSource cpds = null;

	private JDBCDataSource() {

	}

	public static JDBCDataSource getInstance() {

		ResourceBundle rb = ResourceBundle.getBundle("com.itrainu.Bundle.system");

		if (datasource == null) {

			datasource = new JDBCDataSource();
			datasource.cpds = new ComboPooledDataSource();

			try {
				datasource.cpds.setDriverClass(rb.getString("driver"));
		} 
				catch (PropertyVetoException e) {
				System.out.println(e);
				e.printStackTrace();
			}

			datasource.cpds.setJdbcUrl(rb.getString("url"));
			datasource.cpds.setUser(rb.getString("username"));
			datasource.cpds.setPassword(rb.getString("password"));
			datasource.cpds.setInitialPoolSize(Integer.parseInt(rb.getString("initialpoolsize")));
			System.out.println("intitalpoolsize= " + datasource.cpds.getInitialPoolSize());
			datasource.cpds.setMaxPoolSize(Integer.parseInt(rb.getString("maxpoolsize")));
			datasource.cpds.setAcquireIncrement(Integer.parseInt(rb.getString("acquireincrement")));

		}

		return datasource;

	}

	public static Connection getConnection() throws Exception {

		System.out.println("in GetConnection");

		return getInstance().cpds.getConnection();

	}

	public static void closeConnection(Connection conn) throws Exception {

		if (conn != null) {

			conn.close();

		}

	}

	public static void trnRollback(Connection conn) throws Exception {

		if (conn != null) {

			conn.rollback();

		}

	}

}
