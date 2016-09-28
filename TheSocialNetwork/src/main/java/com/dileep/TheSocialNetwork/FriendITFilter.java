package com.dileep.TheSocialNetwork;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class FriendITFilter implements ContainerRequestFilter{
	
	private static final String AUTHORIZATION_HEADER_KEY="Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX= "Basic";
	public static Response unauthorizedaccess=null;
	
	public static boolean unautorizedAccess(String msg,ContainerRequestContext reqst){
		try{
			unauthorizedaccess = Response.status(Response.Status.UNAUTHORIZED).entity(msg).build();
			ProjectConfigValues.setLogging(Level.INFO,unauthorizedaccess.toString());
			ProjectConfigValues.setLogging(Level.WARNING, msg);
			reqst.abortWith(unauthorizedaccess);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			ProjectConfigValues.getLogging().log(Level.SEVERE, "Some thing went wrong while responding unauthorizing access..");
			return false;
		}
	}
	
	@Override
	public void filter(ContainerRequestContext request) throws IOException {

		ProjectConfigValues.setLogging(Level.INFO,"Inside Friend IT Filter, Checking for username/password..");
		
		List<String> authHeader = request.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		
		if(authHeader!=null){
			String authtoken = authHeader.get(0);
			authtoken = authtoken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			byte[] bt = Base64.getDecoder().decode(authtoken.trim());
			String uname=new String(bt).split(":")[0];
			String passw=new String(bt).split(":")[1];
			//Checking both are not null
			
			if(uname!=null && passw!=null){
				ProjectConfigValues.setLogging(Level.INFO,"Found the Username/Password.. Validating...");
				ProjectConfigValues.setLogging(Level.INFO,"Uname = "+uname+"   Passw = "+passw);
				
			// Checking for uname and password from DB
				boolean isValidated=false;
				try {
					isValidated=AmazonDBModel.isConsumerDataValid(uname,passw);
					System.out.println(isValidated);
				} catch (Exception e) {
					ProjectConfigValues.setLogging(Level.SEVERE,"Unable to get DBConnection...\n"+e.toString());
				}
				if(isValidated){
					ProjectConfigValues.setLogging(Level.INFO,"SuccessFull Validation... Continue with Response");
					return ;
				}
				else{
					unautorizedAccess("Username/Password not matching.. sending 401...",request);
				}
			
			//End of checking password
			
			}else{
				
				//uname passw is null
				unautorizedAccess("Username/Password is Null.. sending 401...",request);
			}
		}
		else{
			unautorizedAccess("User must enter password...",request);
		}
	
	}
}