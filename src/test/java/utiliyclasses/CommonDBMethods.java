package utiliyclasses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.sql.ResultSetMetaData;

public class CommonDBMethods extends CommonMethods{
	
	public static Connection conn;
	
	public static void getDBConnection() throws SQLException {
		Connection connect = connectToDatabase();
		if(connect!=null) {
			conn = connect;
		}
	}
	
	public static ResultSet executeSQLQuery(String sqlStatement) {
		ResultSet rs = null;
		try {
			Statement statement = conn.createStatement();
			 rs = statement.executeQuery(sqlStatement);
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return rs;
		
	}
	
	public static 	List<String> getColumnLabels(ResultSet rs) {
		List<String> metaData = new ArrayList<String>();
		try {
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for(int index=0;index<columnCount;index++) {
				String columnName = rsmd.getColumnLabel(columnCount);
				metaData.add(columnName);			
				}
			System.out.println(metaData);
			return metaData;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return metaData;
	}
	
	public static List<Map<String,String>> createTableFromResultSet(ResultSet rs){
		List<Map<String,String>> rsTable = null; 
		List<String> metaData = new ArrayList<String>();
		Map<String,String> rsRowData = null;
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for(int index=1;index<=columnCount;index++) {
				String columnName = rsmd.getColumnLabel(index);
				metaData.add(columnName);			
				}
			for(String s: metaData) {
				System.out.println(s);
			}
			
			rsTable = new ArrayList<Map<String,String>>();

//			int rowIndex = rs.getRow();
			
				
			while(rs.next()) {
				rsRowData =  new HashMap<String,String>();
				for(int y=1;y<columnCount;y++) {
					String key = rsmd.getColumnLabel(y);
					String value = rs.getString(y);
					if(value==null) {
						System.out.println("Adding to Map"+y+" = "+key + " : NULL");
						rsRowData.put(key, "NULL");
					}else {
						System.out.println("Adding to Map"+y+" = "+key + " : "+value);
						rsRowData.put(key, value);
					}
				}
				System.out.println("////////Next Row///////////");
				rsTable.add(rsRowData);
//				rs.next();
			}
					
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		return rsTable;
		
	}

}
