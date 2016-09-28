package com.dileep.TheSocialNetwork;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class ConfigurationLoader extends HttpServlet{

	@Override
	public void init() throws ServletException {
		
		  Properties prop = new Properties();
	  		InputStream input = null;
	  		try {
	  			input = new FileInputStream("D://config.properties");
	  			prop.load(input);
	  			
	  			ProjectConfigValues.setLogpath(prop.getProperty("logPath"));

	  			if(ProjectConfigValues.logpath!= null){	
	  				LoggingIntializer.init();
		  			ProjectConfigValues.setLogging(LoggingIntializer.logger);
	  				ProjectConfigValues.getLogging().log(Level.INFO, "logPath = "+ProjectConfigValues.getLogpath());  			
		  			ProjectConfigValues.setdBEndpoint(prop.getProperty("dBEndpoint"));;
		  			ProjectConfigValues.getLogging().log(Level.INFO, "dbEndPoint = "+ProjectConfigValues.getdBEndpoint());
		  			ProjectConfigValues.setDbUname(prop.getProperty("dbUname"));;
		  			ProjectConfigValues.getLogging().log(Level.INFO, "dbUname = "+ProjectConfigValues.getDbUname());
		  			ProjectConfigValues.setDbPassw(prop.getProperty("dbPass"));;
		  			ProjectConfigValues.getLogging().log(Level.INFO, "dbPassw = "+ProjectConfigValues.getDbPassw());
		  			ProjectConfigValues.setJDBCDriver(prop.getProperty("JDBCDriver"));;
		  			ProjectConfigValues.getLogging().log(Level.INFO, "JDBCDriver = "+ProjectConfigValues.getJDBCDriver());
	  			}else{
	  				
	  				throw new Exception("Unable to get the Logging intialization...");
	  			}
	  			
	  			if(ProjectConfigValues.getdBEndpoint()!=null && ProjectConfigValues.getDbPassw()!=null && ProjectConfigValues.getDbUname()!=null && ProjectConfigValues.getLogpath()!=null && ProjectConfigValues.getJDBCDriver()!=null)
	  					ProjectConfigValues.logging.log(Level.INFO,"Intialization success.....");
	  			else
	  				throw new Exception("One or more intialization parameter is missing...");
	  		}catch(Exception e)
	  		{
	  			System.out.println("Exception One or more intialization parameter missing...");
	  			if(ProjectConfigValues.logging!=null)
	  				ProjectConfigValues.logging.log(Level.SEVERE,"Exception One or more intialization parameter missing...");
	  		}
	}
}
