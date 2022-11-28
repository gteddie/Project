package image;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface ImgDao {
	public void prepareTable() throws SQLException;
	
	public void insert(String fileName) throws FileNotFoundException, IOException, SQLException;
	
	public void insertBatch() throws FileNotFoundException, IOException, SQLException;

	public void fetchByID(int imgID) throws SQLException, FileNotFoundException, IOException;

}
