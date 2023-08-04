package utiliyclasses;
import utiliyclasses.ConfigReader;
import utiliyclasses.Constants;
import java.sql.*;


public class DatabaseConnector{
	

	public static Connection connectToDatabase() {
		ConfigReader.readConfigProperties(Constants.CONFIG_PROP_PATH);
		String dbURL = ConfigReader.getPropertyValue("dbURL");
		String dbUsername = ConfigReader.getPropertyValue("username");
		String dbPassword = ConfigReader.getPropertyValue("password");
		Connection connection = null;
		
		try {
			 connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			if(connection!=null) return connection;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
}
