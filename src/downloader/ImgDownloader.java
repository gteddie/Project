package downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import image.ImgBean;
import system.SystemConstant;
import system.Utilities;

public class ImgDownloader {
	public ImgDownloader() {

	}

	public void downloadImg(String url) throws IOException, SerialException, SQLException {
		String fileName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
		String fileType = url.substring(url.lastIndexOf(".") + 1);
		URL url_ = new URL(url);
		File dir = new File(SystemConstant.getProjectimg());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		byte[] image;
		try (BufferedInputStream bis = new BufferedInputStream(url_.openStream());) {
			image = bis.readAllBytes();
			Blob fileContent = new SerialBlob(image);
			ImgBean bean = new ImgBean(fileName, fileContent, fileType);
			Utilities.CreateImageByBean(bean, new File(SystemConstant.getProjectimg()));
		}
		System.out.println("圖片下載成功");
	}
}
