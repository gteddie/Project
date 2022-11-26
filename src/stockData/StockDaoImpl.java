package stockData;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import system.SystemConstant;

public class StockDaoImpl implements StockDao {

	public ArrayList<StockBean> downloadData(String url) throws IOException, SQLException {
		URL url_ = new URL(url);
		ArrayList<StockBean> beanList = new ArrayList<>();
		// Load data from url
		try (InputStreamReader isr = new InputStreamReader(url_.openStream());
				BufferedReader br = new BufferedReader(isr);

		) {
			String line = "";
			String[] data;
			br.readLine();
			int count = 0;
			while ((line = br.readLine()) != null) {
				data = line.replace("\"", "").split(",");
				Double closePrice = data[2] == "" ? null : Double.parseDouble(data[2]);
				Double MonAvgPrice = data[3] == "" ? null : Double.parseDouble(data[3]);
				StockBean bean = new StockBean(null, data[0], data[1], closePrice, MonAvgPrice);
				beanList.add(bean);
				count++;
			}
			System.out.printf("Successfully download %d data......%n", count);
			return beanList;
		}
	}

	@Override
	public void createTable() throws IOException, SQLException {
		// create table and drop table
		String dropTableSql = "DROP TABLE IF EXISTS dbo.Stock";
		String createTableSql = "CREATE TABLE Stock(" + "StockId INT NOT NULL IDENTITY,"
				+ "StockNum NVARCHAR(50) NOT NULL," + "StockName NVARCHAR(50) NOT NULL," + "ClosePrice FLOAT,"
				+ "MonAvgPrice FLOAT" + ");";
		try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
				PreparedStatement stmtD = con.prepareStatement(dropTableSql);
				PreparedStatement stmtC = con.prepareStatement(createTableSql);) {
			stmtD.executeUpdate();
			stmtC.executeUpdate();
			System.out.println("successfully create stock table......");
		}
	}

	@Override
	public void insertData(StockBean bean) throws SQLException {
		String insertSql = "INSERT INTO STOCK(StockNum, StockName, ClosePrice, MonAvgPrice) VALUES(?,?,?,?)";
		try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
				PreparedStatement stmtI = con.prepareStatement(insertSql);

		) {
			stmtI.setString(1, bean.getStockNum());
			stmtI.setString(2, bean.getStockName());
			if (bean.getClosePrice() == null) {
				stmtI.setNull(3, Types.DOUBLE);
			} else {
				stmtI.setDouble(3, bean.getClosePrice());
			}
			;
			if (bean.getClosePrice() == null) {
				stmtI.setNull(4, Types.DOUBLE);
			} else {
				stmtI.setDouble(4, bean.getMonAvgPrice());
			}
			;
			stmtI.executeUpdate();
			System.out.println("successfully insert data!");
		}

	}

	public void insertBatchData(Collection<StockBean> beanCollection) throws SQLException {
		String insertSql = "INSERT INTO STOCK(StockNum, StockName, ClosePrice, MonAvgPrice) VALUES(?,?,?,?)";
		try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
				PreparedStatement stmtI = con.prepareStatement(insertSql);

		) {
			int num = 0;
			for (StockBean bean : beanCollection) {
				stmtI.setString(1, bean.getStockNum());
				stmtI.setString(2, bean.getStockName());
				if (bean.getClosePrice() == null) {
					stmtI.setNull(3, Types.DOUBLE);
				} else {
					stmtI.setDouble(3, bean.getClosePrice());
				}
				;
				if (bean.getClosePrice() == null) {
					stmtI.setNull(4, Types.DOUBLE);
				} else {
					stmtI.setDouble(4, bean.getMonAvgPrice());
				}
				stmtI.addBatch();
				num++;

				if (num % 500 == 0 || num == beanCollection.size()) {
					stmtI.executeBatch();
					System.out.printf("number of stocks updated: %d%n", num);

				}
			}
		}
		System.out.println("finish inserting all stock data......");
	}

	@Override
	public void readData(int key) throws SQLException, FileNotFoundException, IOException {
		String sql = "SELECT * FROM Stock WHERE StockId = ?";
		try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
				PreparedStatement stmt = con.prepareStatement(sql);

		) {

			stmt.setInt(1, key);
			ResultSet rs = stmt.executeQuery();
			File dir = new File(SystemConstant.getQueryresults());
			if (!dir.exists()) {
				dir.mkdir();
			}
			if (rs.next()) {
				StockBean bean = new StockBean(rs.getInt("StockId"), rs.getString("StockNum"),
						rs.getString("StockName"), rs.getDouble("ClosePrice"), rs.getDouble("MonAVGPrice"));
				System.out.println(bean.toString().substring(10));
				File f = new File(dir, bean.getStockId() + ".txt");
				try (FileOutputStream fos = new FileOutputStream(f);
						OutputStreamWriter osw = new OutputStreamWriter(fos);
						PrintWriter pw = new PrintWriter(osw);) {
					pw.write(bean.toString());

				}

			}
		}

	}

	@Override
	public void deleteData(String stockName) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Stock WHERE StockName LIKE \'%" + stockName + "%\'";
		try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
				PreparedStatement stmt = con.prepareStatement(sql);) {
//			stmt.setString(1, stockName);
			int num = stmt.executeUpdate();
			if (num == 0) {
				System.out.println("Couldn't find data with \"" + stockName + "\" in their stockName");
			} else {

				System.out.println(num + " of data with \"" + stockName + "\" in their StockName were deleted");
			}

		}

	}

	@Override
	public void deleteData(Double closePrice) {
	}

	public void insertStockPic(String stockNum, String picType) throws IOException {
		String url = String.format("https://goodinfo.tw/tw/image/StockPrice/PRICE_%s_0056.gif", picType);
		URL url_ = new URL(url);
		File dir = new File(SystemConstant.getStockimg());
		if (!dir.exists()) {
			dir.mkdir();
		}
		File file = new File(dir,stockNum + );
		try (BufferedInputStream bis = new BufferedInputStream(url_.openStream());) {
			byte[] img = bis.readAllBytes();
		}
		
		
	}

}
