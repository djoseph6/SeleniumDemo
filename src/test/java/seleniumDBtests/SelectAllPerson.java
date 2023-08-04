package seleniumDBtests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utiliyclasses.CommonDBMethods;
import utiliyclasses.DatabaseConnector;

public class SelectAllPerson extends CommonDBMethods{
	
	@BeforeClass
	public void prepareTest() throws SQLException {
		CommonDBMethods.getDBConnection();
		
		
	}
	
	@Test
	public void printDBTable() {
		String query = "select * from person";
		ResultSet rs = CommonDBMethods.executeSQLQuery(query);
		List<Map<String,String>> rsTable = CommonDBMethods.createTableFromResultSet(rs);
		
		for(int x=0;x<=rsTable.size()-1;x++) {
			Map<String,String>rowData = rsTable.get(x);
			Set<String> keys = rowData.keySet();
			for(String key : keys) {
				System.out.print(key+" : "+rowData.get(key)+" | ");
			}
			System.out.println();
		}
		
		
		
	}

}
