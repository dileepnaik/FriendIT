package com.dileep.TheSocialNetwork;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.Level;

public class AmazonDBModel {

	public static java.sql.Connection conn = null;
	public static  java.sql.Statement stmt = null;
	
	public static boolean isConsumerDataValid(String uname,String passw)throws Exception{

			Class.forName(ProjectConfigValues.getJDBCDriver()).newInstance();
			ProjectConfigValues.setLogging(Level.INFO,"Connecting to database...");
		    conn = DriverManager.getConnection(ProjectConfigValues.getdBEndpoint(),ProjectConfigValues.getDbUname(),ProjectConfigValues.getDbPassw());
		    if(conn!=null)
		    {
		    	stmt = conn.createStatement();
		    	String sql= "SELECT * FROM FriendIT.consumerTBL where uname=\'"+uname+"\'";;
		    	ResultSet rs=stmt.executeQuery(sql);
		    	rs.first();
		    	System.out.println("Inside RS");
		    		if(uname.equals(rs.getString(2)) && passw.equals(rs.getString(3))){
		    			ProjectConfigValues.setLogging(Level.INFO,"Loggin SuccessFull. Returning True");
		    			return true;
		    		}else
		    			ProjectConfigValues.setLogging(Level.WARNING,"Loggin Fail. Returning False");
		    			return false;
		    }else{
		    	ProjectConfigValues.setLogging(Level.SEVERE,"Unable to get DB connection....");
		    	return false;
		    }
	}
}
