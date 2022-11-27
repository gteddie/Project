package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import stockImg.StockImgBean;
import system.SystemConstant;
import system.Utilities;

public class StockTest {

	public static void main(String[] args) throws IOException, SQLException {
//		StockImgDaoImpl dao = new StockImgDaoImpl();
//		dao.prepareImgTable();
//		dao.uploadImg("0058");
		String StockNum = "0058";
		String ImgDate = "2022-11-28";
		String sql = "SELECT * FROM StockImage WHERE StockNum = ? AND ImgDate = ?;";
		try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
				PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, StockNum);
			stmt.setString(2, ImgDate);
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
