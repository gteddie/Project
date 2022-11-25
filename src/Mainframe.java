import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import image.*;

public class Mainframe {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub
		Scanner scan = openList();
		String option = "";
		while (scan.hasNext()) {
			option = scan.next();
			if (option.equals("quit")) {
				System.out.println("程式結束......");
				scan.close();
				break;
			};
			switch (option) {
			case "一":
				break;
			case "二":
				break;
			case "三":
				break;
			case "四":
				String filePath = "C:\\Users\\Student\\Desktop\\projectimg";
				ImgDao img = new ImgDaoImpl();
				File dir = new File(filePath);
				for (String fileName : dir.list()) {
					img.insert(fileName);
				}
				System.out.println("執行成功!\n");				
				openList();
				break;
			case "五":
				System.out.print("請輸入圖片ID:");
				ImgDao img2 = new ImgDaoImpl();
				if (scan.hasNextInt()) {
					int n = scan.nextInt();
					img2.download(n);
				}
//				for (int i = 1; i <= 5; i++) {
//					img2.download(i);
//				}
				System.out.println("執行成功!\n");
				openList();
				break;
			}

		}

	}

	public static Scanner openList() {
		System.out.println("功能清單");
		System.out.println("(一)下載資料");
		System.out.println("(二)讀取資料");
		System.out.println("(三)讀取資料並建立檔案");
		System.out.println("(四)存入圖片");
		System.out.println("(五)讀取圖片並建立檔案");
		System.out.println("(quit)離開本程式");
		System.out.print("請選擇功能: ");
		Scanner scan = new Scanner(System.in);
		return scan;
	}
}
