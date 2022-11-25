package image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface ImgDao {
	public void insert(String fileName) throws FileNotFoundException, IOException, SQLException;

	public void download(int imgID) throws SQLException, FileNotFoundException, IOException;
}
