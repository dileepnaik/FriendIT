package com.dileep.TheSocialNetwork;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectConfigValues {

		public static String dBEndpoint;
		public static String dbUname;
		public static String dbPassw;
		public static String logpath;
		public static Logger logging;
		public static String JDBCDriver;
		
		public static String getdBEndpoint() {
			return dBEndpoint;
		}
		public static void setdBEndpoint(String dBEndpoint) {
			ProjectConfigValues.dBEndpoint = dBEndpoint;
		}
		public static String getDbUname() {
			return dbUname;
		}
		public static void setDbUname(String dbUname) {
			ProjectConfigValues.dbUname = dbUname;
		}
		public static String getDbPassw() {
			return dbPassw;
		}
		public static void setDbPassw(String dbPassw) {
			ProjectConfigValues.dbPassw = dbPassw;
		}
		public static String getLogpath() {
			return logpath;
		}
		public static void setLogpath(String logpath) {
			ProjectConfigValues.logpath = logpath;
		}
		public static Logger getLogging() {
			return logging;
		}
		public static void setLogging(Logger logging) {
			ProjectConfigValues.logging = logging;
		}
		public static String getJDBCDriver() {
			return JDBCDriver;
		}
		public static void setJDBCDriver(String jDBCDriver) {
			JDBCDriver = jDBCDriver;
		}
		public static void setLogging(Level info, String msg) {
			// TODO Auto-generated method stub
			logging.log(info, msg);
		}
}
