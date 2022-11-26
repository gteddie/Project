package stockData;

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

import system.SystemConstant;

public class StockDaoImplBeforeRefactor implements StockDao {

	@Override
	public void downloadData(String url) throws IOException, SQLException {
		URL url_ = new URL(url);
		ArrayList<String[]> al = new ArrayList<>();
		// Load data from url
		try (InputStreamReader isr = new InputStreamReader(url_.openStream());
				BufferedReader br = new BufferedReader(isr);

		) {
			String line = "";
			while ((line = br.readLine()) != null) {
//			System.out.println(line);
				al.add(line.replace("\"", "").split(","));
			}
		}
		al.remove(0);
		// create table and drop table
		String dropTableSql = "DROP TABLE IF EXISTS dbo.Stock";
		String createTableSql = "CREATE TABLE Stock(" + "StockId INT NOT NULL IDENTITY,"
				+ "StockNum NVARCHAR(50) NOT NULL," + "StockName NVARCHAR(50) NOT NULL," + "ClosePrice FLOAT,"
				+ "MonAvgPrice FLOAT" + ");";
		String insertSql = "INSERT INTO STOCK(StockNum, StockName, ClosePrice, MonAvgPrice) VALUES(?,?,?,?)";
		try (Connection con = DriverManager.getConnection(SystemConstant.getDBURL());
				PreparedStatement stmtD = con.prepareStatement(dropTableSql);
				PreparedStatement stmtC = con.prepareStatement(createTableSql);
				PreparedStatement stmtI = con.prepareStatement(insertSql);

		) {
			stmtD.executeUpdate();
			stmtC.executeUpdate();
			System.out.println("successfully create database!");
			for (String[] sar : al) {
//				Double closePrice = sar[2] == "" ? null : Double.parseDouble(sar[2]);
//				Double MonAvgPrice = sar[3] == "" ? null : Double.parseDouble(sar[3]);
//				StockBean bean = new StockBean(null, sar[1], sar[2],closePrice,MonAvgPrice);
//				insertData(bean);
				stmtI.setString(1, sar[0]);
				stmtI.setString(2, sar[1]);
				if (sar[2].equals("")) {
					stmtI.setNull(3, Types.DOUBLE);
				} else {
					stmtI.setDouble(3, Double.parseDouble(sar[2]));
				}
				if (sar[3].equals("")) {
					stmtI.setNull(4, Types.DOUBLE);
				} else {
					stmtI.setDouble(4, Double.parseDouble(sar[3]));
				}
				stmtI.executeUpdate();
			}
		}
		System.out.println("successfully finish download and insert!");

	}

	@Override
	public void insertData(StockBean bean) throws SQLException {
		String insertSql = "INSERT INTO STOCK(StockNum, StockName, ClosePrice, MonAvgPrice) VALUES(?,?,?,?)";
		try (Connection con = DriverManager.getConnection(SystemConstant.getDBURL());
				PreparedStatement stmtI = con.prepareStatement(insertSql);

		) {
			stmtI.setString(1, bean.getStockNum());
			stmtI.setString(2, bean.getStockName());
			if(bean.getClosePrice() == null) {
				stmtI.setNull(3, Types.DOUBLE);
			}else {				
				stmtI.setDouble(3, bean.getClosePrice());
			};
			if(bean.getClosePrice() == null) {
				stmtI.setNull(4, Types.DOUBLE);
			}else {				
				stmtI.setDouble(4, bean.getMonAvgPrice());
			};
			stmtI.executeUpdate();
		}
		

	}

	@Override
	public void readData(int key) throws SQLException, FileNotFoundException, IOException {
		String sql = "SELECT * FROM Stock WHERE StockId = ?";
		try (Connection con = DriverManager.getConnection(SystemConstant.getDBURL());
				PreparedStatement stmt = con.prepareStatement(sql);

		) {

			stmt.setInt(1, key);
			ResultSet rs = stmt.executeQuery();
			File dir = new File("./");
			if (rs.next()) {
				StockBean bean = new StockBean(rs.getInt("StockId"), rs.getString("StockNum"),
						rs.getString("StockName"), rs.getDouble("ClosePrice"), rs.getDouble("MonAVGPrice"));
				System.out.println(bean.toString());
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
		System.out.println(sql);
		try (Connection con = DriverManager.getConnection(SystemConstant.getDBURL());
				PreparedStatement stmt = con.prepareStatement(sql);) {
//			stmt.setString(1, stockName);
			int num = stmt.executeUpdate();
			System.out.println(num + " of data with stockname = " + stockName + "deleted");

		}

	}

	@Override
	public void deleteData(Double closePrice) {
	}
	
	
	
	public void insertStockPic(String stockName, String picType) throws MalformedURLException {
		String url = String.format("https://goodinfo.tw/tw/image/StockPrice/PRICE_%s_0056.gif", picType);
		URL url_ = new URL(url);
	}
	

}
