package stockData;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import system.SystemConstant;

public class Test {
	// 股票代號,股票名稱,收盤價,月平均價
	public static void main(String[] args) throws IOException, SQLException {
		StockDaoImpl dao = new StockDaoImpl();
		dao.insertStockPic("0057","YEAR");

	}
}
