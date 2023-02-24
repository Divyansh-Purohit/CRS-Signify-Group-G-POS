/**
 * 
 */
package helper;

/**
 * @author dp201
 *
 */
public class Ids {
	public static int studentId = 102;
	public static int userId = 1002;
	
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	public static final String DB_URL = "jdbc:mysql://localhost/crs_g";

	   //  Database credentials
	public static final String USER = "root";
	public static final String PASS = "Siddharth@1993";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
