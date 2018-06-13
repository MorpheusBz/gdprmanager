package eu.dsflat.dashboard;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlLitleConnect {

		static Connection connection = null;  
	    static ResultSet resultSet = null;  
	    static Statement statement = null;  
	 
	    public static void select() {
	    try 
	    {  
	        Class.forName("org.sqlite.JDBC");  
	        connection = DriverManager.getConnection("jdbc:sqlite:/home/mihail/eclipse-workspace/GDPR Manager/lib/db/itsmanager.db");  
	        statement = connection.createStatement();  
	        resultSet = statement  
	                .executeQuery("SELECT user, password FROM users");  
	        while (resultSet.next()) 
	        {  
	            System.out.println("User NAME: " + resultSet.getString("user") + " Pass: " + resultSet.getString("password"));
	        }  
	    } 
	    catch (Exception e1) 
	    {  
	        e1.printStackTrace();  
	    }
	    finally 
	    {  
	        try 
	        {  
	            resultSet.close();  
	            statement.close();  
	            connection.close();  
	        } 
	        catch (Exception e1) 
	        {  
	            e1.printStackTrace();  
	        }  
	    }
	    
		}
	   
	}

