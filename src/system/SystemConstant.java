package system;
public class SystemConstant {
	public static String DB = "jdbc:sqlserver";
	public static String HOST = "localhost";
	public static int PORT = 1433;
	public static String DBNAME = "Project";
	public static String USER = "sa";
	public static String PASS = "sa123456";
	public static String DBURL = String.format("%s://%s:%d;databaseName=%s;user=%s;password=%s", DB, HOST, PORT, DBNAME,
			USER, PASS);

	public static String getDB() {
		return DB;
	}

	public static String getHOST() {
		return HOST;
	}

	public static int getPORT() {
		return PORT;
	}

	public static String getDBNAME() {
		return DBNAME;
	}

	public static String getUSER() {
		return USER;
	}

	public static String getPASS() {
		return PASS;
	}

	public static String getDBURL() {
		return DBURL;
	}

}
