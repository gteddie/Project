package stockData;

import java.io.IOException;
import java.sql.SQLException;

public class Test {
	// 股票代號,股票名稱,收盤價,月平均價
	public static void main(String[] args) throws IOException, SQLException {
		StockDataDao dao= new StockDataDaoImpl();
//		dao.downloadData("https://www.twse.com.tw/exchangeReport/STOCK_DAY_AVG_ALL?response=open_data");
		

	}
}




