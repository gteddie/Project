package stockData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;

import downloader.StockDownloader;
import system.SystemConstant;

public class StockDaoImpl implements StockDao {
	@Override
	public void prepareTable() throws IOException, SQLException {
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
			StockDownloader downloader = new StockDownloader();
			ArrayList<StockBean> beanList = downloader
					.downloadStockData("https://www.twse.com.tw/exchangeReport/STOCK_DAY_AVG_ALL?response=open_data");
			insertBatch(beanList);
		}
	}

	@Override
	public void insert(StockBean bean) throws SQLException {
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
			if (bean.getMonAvgPrice() == null) {
				stmtI.setNull(4, Types.DOUBLE);
			} else {
				stmtI.setDouble(4, bean.getMonAvgPrice());
			}
			;
			stmtI.executeUpdate();
			System.out.println("successfully insert data!");
		}

	}

	@Override
	public void insertBatch(Collection<StockBean> beanCollection) throws SQLException {
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
				if (bean.getMonAvgPrice() == null) {
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
	public void read(int key) throws SQLException, FileNotFoundException, IOException {
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
			if (!rs.next()) {
				System.out.println("Couldn't find stock with stockid = " + key);
				return;
			} else {
				StockBean bean = new StockBean(rs.getInt("StockId"), rs.getString("StockNum"),
						rs.getString("StockName"), rs.getDouble("ClosePrice"), rs.getDouble("MonAVGPrice"));
				String beanString = bean.toString();
				System.out.println(beanString.substring(10,beanString.indexOf("]")));
				File f = new File(dir, bean.getStockId() + ".txt");
				try (FileOutputStream fos = new FileOutputStream(f);
						OutputStreamWriter osw = new OutputStreamWriter(fos);
						PrintWriter pw = new PrintWriter(osw);) {
					pw.write(bean.toString().substring(10,beanString.indexOf("]")));

				}

			}
			System.out.println("資料查詢成功!\n");
		}

	}

	@Override
	public void delete(String stockName, String delType) throws SQLException {
		String sql = "";
		switch (delType) {
		case "name":
			sql = "DELETE FROM Stock WHERE StockName LIKE ?";
			break;
		case "stocknum":
			sql = "DELETE FROM Stock WHERE StockName = ?";
			break;
		default:
			throw new InputMismatchException("invalid input type for deleting record");
		}
		try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
				PreparedStatement stmt = con.prepareStatement(sql);) {
			if (delType == "name") {
				stmt.setString(1, "%" + stockName + "%");
			} else {
				stmt.setString(1, stockName);
			}
			int num = stmt.executeUpdate();
			if (num == 0) {
				System.out.println("Couldn't find data with \"" + stockName + "\" in their stockName");
				return;
			} else {

				System.out.println(num + " of data with \"" + stockName + "\" in their StockName were deleted");
			}

		}
		System.out.println("資料刪除成功!\n");

	}

}
