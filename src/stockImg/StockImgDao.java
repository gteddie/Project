package stockImg;

import java.io.IOException;
import java.sql.SQLException;

public interface StockImgDao {
	public void prepareImgTable() throws IOException, SQLException;

	public void uploadImg(StockImgBean bean) throws SQLException;

	public void uploadImg(String StockNum) throws SQLException, IOException;

	public void fetchImg(String StockNum) throws SQLException, IOException;
}
