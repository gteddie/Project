package system;

public class SystemConstant {
	private static final String DB = "jdbc:sqlserver";
	private static final String HOST = "localhost";
	private static final int PORT = 1433;
	private static final String DBNAME = "Project";
	private static final String USER = "sa";
	private static final String PASS = "sa123456";
	private static final String DBURL = String.format("%s://%s:%d;databaseName=%s;user=%s;password=%s", DB, HOST, PORT,
			DBNAME, USER, PASS);
	private static final String RESOURCESROOT = "./";
	private static final String QUERYRESULTS = RESOURCESROOT + "queryresults";
	private static final String PROJECTIMG = RESOURCESROOT + "projectimg";
	private static final String IMGOUTPUT = RESOURCESROOT + "imgoutput";
	private static final String STOCKIMG = RESOURCESROOT + "stockimg";

	public static String getDb() {
		return DB;
	}

	public static String getHost() {
		return HOST;
	}

	public static int getPort() {
		return PORT;
	}

	public static String getDbname() {
		return DBNAME;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPass() {
		return PASS;
	}

	public static String getDburl() {
		return DBURL;
	}

	public static String getResourcesroot() {
		return RESOURCESROOT;
	}

	public static String getQueryresults() {
		return QUERYRESULTS;
	}

	public static String getProjectimg() {
		return PROJECTIMG;
	}

	public static String getImgoutput() {
		return IMGOUTPUT;
	}

	public static String getStockimg() {
		return STOCKIMG;
	}

}
