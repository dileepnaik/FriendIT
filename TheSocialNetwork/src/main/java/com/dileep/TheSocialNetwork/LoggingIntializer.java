package com.dileep.TheSocialNetwork;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingIntializer {
		 static Logger logger = null;
		 static FileHandler fh = null;
		 public static void init() throws Exception{
		 logger=Logger.getLogger(LoggingIntializer.class.getName());
		 fh=new FileHandler(ProjectConfigValues.logpath, false);
		 Logger l = Logger.getLogger("");
		 fh.setFormatter(new SimpleFormatter());
		 l.addHandler(fh);
		 l.setLevel(Level.CONFIG);
		 }
}
