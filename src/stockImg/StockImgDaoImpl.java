package stockImg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import downloader.StockDownloader;
import system.SystemConstant;
import system.Utilities;

public class StockImgDaoImpl implements StockImgDao {

	public StockImgDaoImpl() {
		
	}

	@Override
	public void prepareImgTable() throws IOException, SQLException {
		// create table and drop table
		String dropTableSql = "DROP TABLE IF EXISTS dbo.StockImage";
		String createTableSql = " CREATE TABLE StockImage( " 
				+ " StockNum nvarchar(50) NOT NULL ,"
				+ " ImgDate date NOT NULL, "
				+ " DayLine image , " 
				+ " WeekLine image , " 
				+ " MonthLine image , "
				+ " QuarterLine image , "
				+ " YearLine image); ";
		try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
				PreparedStatement stmtD = con.prepareStatement(dropTableSql);
				PreparedStatement stmtC = con.prepareStatement(createTableSql);) {
			stmtD.executeUpdate();
			stmtC.executeUpdate();
			System.out.println("successfully create stock image table......");
		}
	}
	@Override
	public void uploadImg(StockImgBean bean) throws SQLException {
		String sql = "INSERT INTO StockImage VALUES(?,?,?,?,?,?,?)";
		try(Connection con = DriverManager.getConnection(SystemConstant.getDburl());
			PreparedStatement stmt = con.prepareStatement(sql);
				) {
			stmt.setString(1,bean.getStockNum());
			stmt.setDate(2, bean.getImgDate());
			stmt.setBlob(3,bean.getDayLine());
			stmt.setBlob(4,bean.getWeekLine());
			stmt.setBlob(5,bean.getMonthLine());
			stmt.setBlob(6,bean.getQuarterLine());
			stmt.setBlob(7,bean.getYearLine());
			stmt.executeUpdate();
			
		}

	}
	@Override
	public void uploadImg(String StockNum) throws SQLException, IOException {
		StockDownloader downloader = new StockDownloader();
		StockImgBean bean = downloader.downloadStockImgBean(StockNum);
		uploadImg(bean);	
		
		
	}

	@Override
	public void fetchImg(String StockNum) throws SQLException, IOException {
		String sql = "SELECT * FROM StockImage WHERE StockNum = ?;";
		try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
				PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, StockNum);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				StockImgBean bean = new StockImgBean(rs.getString("StockNum"), rs.getDate("imgDate"),
						rs.getBlob("DayLine"), rs.getBlob("WeekLine"), rs.getBlob("MonthLine"),
						rs.getBlob("QuarterLine"), rs.getBlob("YearLine"));
				Utilities.CreateStockImageByBean(bean);
			}
		}		
		

	}



}
