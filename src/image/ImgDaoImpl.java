package image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

public class ImgDaoImpl implements ImgDao {
	String dburl = "jdbc:sqlserver://localhost:1433;databaseName=Project;user=sa;password=sa123456";

	@Override
	public void insert(String fileName) throws FileNotFoundException, IOException, SQLException {
		File dir = new File("./projectimg");
		String sql = "INSERT INTO ProjectImage(FileName, FileContent, FileType) VALUES (?,?,?);";
		if (!dir.exists()) {
			dir.mkdir();
		}
		File imgPath = new File(dir, fileName);
		ImgBean img;
		try (FileInputStream fis = new FileInputStream(imgPath); BufferedInputStream bis = new BufferedInputStream(fis);

		) {

			img = new ImgBean(fileName, new SerialBlob(bis.readAllBytes()), fileName.substring(fileName.indexOf(".")));
			try (Connection con = DriverManager.getConnection(dburl);
					PreparedStatement stmt = con.prepareStatement(sql);

			) {
				stmt.setString(1, img.getFileName());
				stmt.setBlob(2, img.getFileContent());
				stmt.setString(3, img.getFileType());
				stmt.executeUpdate();
			}

		}

	}

	@Override
	public void download(int imgID) throws SQLException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ProjectImage WHERE FileID = ?";
		File dir = new File("./imgoutput");
		if (!dir.exists()) {
			dir.mkdir();
		} 
		try (Connection con = DriverManager.getConnection(dburl); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setInt(1, imgID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String fileName = rs.getString("FileName");
				byte[] image = rs.getBytes("FileContent");
				try (FileOutputStream fos = new FileOutputStream(new File(dir, fileName));
						BufferedOutputStream bos = new BufferedOutputStream(fos);) {
					bos.write(image);

				}

			}

		}

	}

}
