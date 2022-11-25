package stockData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface StockDataDao {

	public void downloadData(String url) throws IOException, SQLException;

	public void readData(int key) throws SQLException, FileNotFoundException, IOException;

	public void deleteData(String stockName) throws SQLException;
	
	public void deleteData(Double closePrice);
	
//	public void deleteData(Double monAvgPrice);
	
	public void insertData(StockBean bean);
}
