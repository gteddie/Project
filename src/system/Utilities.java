package system;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import image.ImgBean;
import stockImg.StockImgBean;

public class Utilities {
	public static void CreateStockImageByBean(StockImgBean bean) throws SQLException, IOException {
		String stockNum = bean.getStockNum();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		File dir = new File(SystemConstant.getStockimg() + "/" + f.format(calendar.getTime()) + "/" + stockNum);

		if (!dir.exists()) {
			dir.mkdirs();
		}
		String[] picTypes = { "DATE", "WEEK", "MONTH", "QUAR", "YEAR" };
		ArrayList<Blob> images = new ArrayList<>();
		images.add(bean.getDayLine());
		images.add(bean.getWeekLine());
		images.add(bean.getMonthLine());
		images.add(bean.getQuarterLine());
		images.add(bean.getYearLine());
		for (int i = 0; i < 5; i++) {
			File imgOut = new File(dir, stockNum + "_" + picTypes[i] + ".gif");
			try (BufferedInputStream bis = new BufferedInputStream(images.get(i).getBinaryStream());
					FileOutputStream fos = new FileOutputStream(imgOut);
					BufferedOutputStream bos = new BufferedOutputStream(fos);) {
				byte[] image = bis.readAllBytes();
				bos.write(image);

			}

		}

	}

	public static void CreateImageByBean(ImgBean bean, File dir)
			throws FileNotFoundException, IOException, SQLException {
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(dir, bean.getFileName() + "." + bean.getFileType());
		try (BufferedInputStream bis = new BufferedInputStream(bean.getFileContent().getBinaryStream());
				FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos);) {

			byte[] image = bis.readAllBytes();
			bos.write(image);

		}
	}
}
