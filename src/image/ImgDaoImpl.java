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

import system.SystemConstant;

public class ImgDaoImpl implements ImgDao {

	@Override
	public void prepareTable() throws SQLException {
		// TODO Auto-generated method stub
		String dropTableSql = "DROP TABLE IF EXISTS dbo.ProjectImage";
		String createTableSql = " CREATE TABLE ProjectImage( " + " FileID int NOT NULL IDENTITY, "
				+ " FileName nvarchar(max), " + " FileContent image, " + " FileType nvarchar(50)); ";

		try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
				PreparedStatement stmtD = con.prepareStatement(dropTableSql);
				PreparedStatement stmtC = con.prepareStatement(createTableSql);) {
			stmtD.executeUpdate();
			stmtC.executeUpdate();
			System.out.println("Finish creating img table......");
		}

	}

	@Override
	public void insert(String fileName) throws FileNotFoundException, IOException, SQLException {
		String sql = "INSERT INTO ProjectImage(FileName, FileContent, FileType) VALUES (?,?,?);";
		File dir = new File(SystemConstant.getProjectimg());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File imgPath = new File(dir, fileName);
		ImgBean img;
		try (FileInputStream fis = new FileInputStream(imgPath); BufferedInputStream bis = new BufferedInputStream(fis);

		) {

			img = new ImgBean(fileName.substring(0, fileName.indexOf(".")), new SerialBlob(bis.readAllBytes()),
					fileName.substring(fileName.indexOf(".") + 1));
			try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
					PreparedStatement stmt = con.prepareStatement(sql);

			) {
				stmt.setString(1, img.getFileName());
				stmt.setBlob(2, img.getFileContent());
				stmt.setString(3, img.getFileType());
				stmt.executeUpdate();
			}

		}
//		System.out.println("圖片匯入成功!\n");

	}

	@Override
	public void insertBatch() throws FileNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub
		File dir = new File(SystemConstant.getProjectimg());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (dir.list().length == 0) {
			System.out.println("no img in folder.....");
			return;
		}
		int count = 0;
		for (String fileName : dir.list()) {
			insert(fileName);
			count++;
		}
		System.out.println(count + "張圖片匯入成功!\n");

	}

	@Override
	public void fetchByID(int imgID) throws SQLException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ProjectImage WHERE FileID = ?";
		File dir = new File(SystemConstant.getImgoutput());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try (Connection con = DriverManager.getConnection(SystemConstant.getDburl());
				PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);) {
			stmt.setInt(1, imgID);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				System.out.println("Couldn't find image with id = " + imgID);
				return;
			} else {
				rs.beforeFirst();
			}
			if (rs.next()) {
				String fileName = rs.getString("FileName") + "." + rs.getString("FileType");
				byte[] image = rs.getBytes("FileContent");
				try (FileOutputStream fos = new FileOutputStream(new File(dir, fileName));
						BufferedOutputStream bos = new BufferedOutputStream(fos);) {
					bos.write(image);

				}

			}

		}
		System.out.println("圖片匯出成功!\n");

	}

}
