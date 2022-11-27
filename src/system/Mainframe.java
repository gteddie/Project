package system;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import image.ImgDao;
import image.ImgDaoImpl;
import stockData.StockDaoImpl;


public class Mainframe {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub
		openList();
		Scanner scan = new Scanner(System.in);
		String option = "";
		while (scan.hasNext()) {
			option = scan.next();
			if (option.equals("quit")) {
				System.out.println("程式結束......");
				scan.close();
				break;
			};
			StockDaoImpl dao= new StockDaoImpl();
			ImgDao img = new ImgDaoImpl();
			switch (option) {
			case "一":
				dao.prepareTable();
				openList();
				break;
			case "二":
				System.out.print("請輸入stockId: ");
				if (scan.hasNextInt()) {
					dao.read(scan.nextInt());
				}
				System.out.println("資料查詢成功!\n");
				openList();
				break;
			case "三":
				System.out.print("請輸入股票名稱: ");
				if (scan.hasNext()) {
					dao.delete(scan.next(),"name");
				}
				System.out.println("資料刪除成功!\n");				
				openList();
				break;
			case "四":
				File dir = new File(SystemConstant.getProjectimg());
				for (String fileName : dir.list()) {
					img.insert(fileName);
				}
				System.out.println("圖片匯入成功!\n");				
				openList();
				break;
			case "五":
				System.out.print("請輸入圖片ID: ");
				
				if (scan.hasNextInt()) {
					int n = scan.nextInt();
					img.fetchByID(n);
				}
				System.out.println("圖片下載成功!\n");
				openList();
				break;
			default:
				System.out.println("請輸入正確數值(一~五).....");
				openList();
			}

		}

	}

	public static void openList() {
		System.out.println(" ___________________");
		System.out.println("|功能清單           |");
		System.out.println("| (一) 下載資料     |");
		System.out.println("| (二) 查詢資料     |");
		System.out.println("| (三) 刪除資料     |");
		System.out.println("| (四) 匯入圖片     |");
		System.out.println("| (五) 匯出圖片     |");
		System.out.println("| (quit) 離開本程式 |");
		System.out.println("|__________________｜");
		
		System.out.print("請選擇功能: ");

	}
}
//public static Scanner openList() {
//	System.out.println("功能清單");
//	System.out.println("(一)下載資料");
//	System.out.println("(二)讀取資料");
//	System.out.println("(三)讀取資料並建立檔案");
//	System.out.println("(四)存入圖片");
//	System.out.println("(五)讀取圖片並建立檔案");
//	System.out.println("(quit)離開本程式");
//	System.out.print("請選擇功能: ");
//	Scanner scan = new Scanner(System.in);
//	return scan;
//}
//}
