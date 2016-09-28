package com.dileep.TheSocialNetwork;

import java.sql.DriverManager;
import java.sql.*;

public class DBModels {
	public static java.sql.Connection conn = null;
	public static  java.sql.Statement stmt = null;
	public static void getConnection(){
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(ProjectConfigValues.getdBEndpoint(),ProjectConfigValues.getDbUname(),ProjectConfigValues.getDbPassw());

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		    //  String sql=""
		      
		      String sql;
		      sql = "SELECT * FROM FriendIT.consumerTBL";
		      ResultSet rs = stmt.executeQuery(sql);
		      rs.first();
		      System.out.println(rs.getString(2));
		      
		   }catch(Exception e){
			   
		   }
	}
	
}
