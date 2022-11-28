package downloader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import stockData.StockBean;
import stockImg.StockImgBean;
import system.SystemConstant;

public class StockDownloader {

	public StockDownloader() {

	}

	public ArrayList<StockBean> downloadStockData(String url) throws IOException, SQLException {
		URL url_ = new URL(url);
		ArrayList<StockBean> beanList = new ArrayList<>();
		// Load data from url
		try (InputStreamReader isr = new InputStreamReader(url_.openStream());
				BufferedReader br = new BufferedReader(isr);

		) {
			String line = "";
			String[] data;
			br.readLine();
			int count = 0;
			while ((line = br.readLine()) != null) { 
				data = line.replace("\"", "").split(",");
				Double closePrice = data[2].equals("") ? null : Double.parseDouble(data[2]);
				Double MonAvgPrice = data[3].equals("") ? null : Double.parseDouble(data[3]);
				StockBean bean = new StockBean(null, data[0], data[1], closePrice, MonAvgPrice);
				beanList.add(bean);
				count++;
			}
			System.out.printf("Successfully download %d data......%n", count);
			return beanList;
		}
	}

	public void downloadStockImg(String stockNum, String picType) throws IOException {
		String url = String.format("https://goodinfo.tw/tw/image/StockPrice/PRICE_%s_%s.gif", picType, stockNum);
		URL url_ = new URL(url);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		File dir = new File(SystemConstant.getStockimg() + "/" + f.format(calendar.getTime()) + "/" + stockNum);

		if (!dir.exists()) {
			dir.mkdirs();
		}
		File imgOut = new File(dir, stockNum + "_" + picType + ".gif");
		try (BufferedInputStream bis = new BufferedInputStream(url_.openStream());
				FileOutputStream fos = new FileOutputStream(imgOut);
				BufferedOutputStream bos = new BufferedOutputStream(fos);) {
			byte[] img = bis.readAllBytes();
			bos.write(img);

		}
	}

	public void downloadStockImg(String stockNum) throws IOException {
		String[] picType = { "DATE", "WEEK", "MONTH", "QUAR", "YEAR" };
		for (String type : picType) {
			downloadStockImg(stockNum, type);
		}
	}

	public StockImgBean downloadStockImgBean(String stockNum) throws IOException, SerialException, SQLException {
		String[] picType = { "DATE", "WEEK", "MONTH", "QUAR", "YEAR" };
		Blob[] images = new SerialBlob[5];
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		for (int i = 0; i < 5; i++) {
			String url = String.format("https://goodinfo.tw/tw/image/StockPrice/PRICE_%s_%s.gif", picType[i], stockNum);
			URL url_ = new URL(url);
			try (BufferedInputStream bis = new BufferedInputStream(url_.openStream());) {
				byte[] img = bis.readAllBytes();
				SerialBlob image = new SerialBlob(img);
				images[i] = image;
			}

		}

		StockImgBean bean = new StockImgBean(stockNum, date, images[0], images[1], images[2], images[3], images[4]);

		return bean;
	}

}