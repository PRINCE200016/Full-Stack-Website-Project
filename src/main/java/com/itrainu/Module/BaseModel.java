package com.itrainu.Module;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.catalina.User;
import org.apache.log4j.Logger; 

import com.itrainu.Bean.DropdownListBean;
import com.itrainu.Exception.ApplicationException;
import com.itrainu.Exception.DatabaseException;
import com.itrainu.util.DataUtility;
import com.itrainu.util.JDBCDataSource;

/**
 * The Class BaseModel
 * 
 * @author  Kirti Singh
 *
 */
public abstract class BaseModel implements  Serializable,DropdownListBean {

	private static Logger log =Logger.getLogger(BaseModel.class);

	protected long id;
	protected String createdBy;
	protected String modifiedBy;
	protected Timestamp createdDatetime;
	protected Timestamp modifiedDateTime;
	public static Logger getLog() {
		return log;
	}
	public static void setLog(Logger log) {
		BaseModel.log = log;
	}
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
	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}
	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}
	
	
	
	public int compareTo(BaseModel next){
		return(int)(id-next.getId());
	}
	
	public long nextPK()throws DatabaseException{
		log.debug("Model nextPK Started");;
		Connection conn=null;
		long pk=0;
		try{
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstml=conn.prepareStatement("SELECT MAX(ID)FROM"+getTableName());
			ResultSet rs=pstml.executeQuery();
			while(rs.next()){
				pk=rs.getInt(1);
			}
			rs.close();
		}catch(Exception e){
			log.error("Database Exception..",e);
			throw new DatabaseException("Excertion : Exception in getting PK");
			
		}finally{
			try {
				JDBCDataSource.closeConnection(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.debug("Model nextPK End");
		return pk+1;
		
	}
	
	public abstract String getTableName();
	
	
	
	public void updateCreatesInfo()throws ApplicationException{
		log.debug("Model update Started..."+createdBy);
		
		Connection conn=null;
		
		String sql="UPDATE"+getTableName()+"SET CREATED_BY=?,CREATED_DATETIME=?WHERE ID=?";
		log.debug(sql);
		
		try{
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, createdBy);
			pstmt.setTimestamp(2, DataUtility.getCurrentTimestamp());
			pstmt.setLong(3, id);
			
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		}catch(SQLException e){
			log.error("Database Exception..",e);
			try {
				JDBCDataSource.trnRollback(conn);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new ApplicationException(e.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				JDBCDataSource.closeConnection(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.debug("Model update End");
	}
	
	public void updateModifiedInfo()throws Exception{
		log.debug("Model update Startes");
		Connection conn=null;
		
		
		String sql="UPDATE"+getTableName()+"SET MODIFIED_BY=?,MODIFIED_DATETIME=? WHERE ID=?";
		
		try{
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, modifiedBy);
			pstmt.setTimestamp(2,DataUtility.getCurrentTimestamp());
			pstmt.setLong(3, id);
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		}catch(SQLException e){
			log.error("Database Exception...",e);
			JDBCDataSource.trnRollback(conn);
			
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
		
	}
	
	
	protected <T extends BaseModel> T populateModel(T model,ResultSet rs)throws SQLException{
		model.setId(rs.getLong("ID"));
		model.setCreatedBy(rs.getString("CREATED_BY"));
		model.setModifiedBy(rs.getString("MODIFIED_BY"));
		
		model.setCreatedDatetime(rs.getTimestamp("CREATED_DATETIME"));
		
		model.setModifiedDateTime(rs.getTimestamp("MODIFIED_DATETIME"));
		
		return model;
	}

	

}
