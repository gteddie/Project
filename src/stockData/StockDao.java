package stockData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface StockDao {

	public void prepareTable() throws IOException, SQLException;

	public void read(int key) throws SQLException, FileNotFoundException, IOException;

	public void delete(String stockName, String delType) throws SQLException;

	public void insert(StockBean bean) throws SQLException;

	public void insertBatch(Collection<StockBean> beanCollection) throws SQLException;

}
