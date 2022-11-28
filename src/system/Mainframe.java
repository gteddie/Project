package system;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import downloader.ImgDownloader;
import downloader.StockDownloader;
import image.ImgDao;
import image.ImgDaoImpl;
import stockData.StockDaoImpl;
import stockImg.StockImgDaoImpl;


public class Mainframe {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		// initializing objects
		StockDaoImpl dao= new StockDaoImpl();
		StockImgDaoImpl dao2 = new StockImgDaoImpl();
		ImgDao img = new ImgDaoImpl();
		ImgDownloader imgD = new ImgDownloader();
		StockDownloader SD = new StockDownloader();
		System.out.println("初始化資料庫......");
		dao.prepareTable();
		dao2.prepareTable();
		img.prepareTable();
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
				openList();
				break;
			case "三":
				System.out.print("請輸入股票名稱: ");
				if (scan.hasNext()) {
					dao.delete(scan.next(),"name");
				}				
				openList();
				break;
			case "四":
				System.out.print("請輸入圖片網址(結尾為 .檔案類型): ");
				if (scan.hasNext()) {
					String i = scan.next();
					if(i.endsWith(".jpg")||i.endsWith(".png")||i.endsWith(".gif")) {
						try {
							imgD.downloadImg(i);												
						}catch(Exception e) {
							System.out.println("fail to find image from " + i);
						}
					} else {
						System.out.println("錯誤的圖片網址......");
					}
				}				
				openList();
				break;
				
			case "五":
				img.insertBatch();				
				openList();
				break;
			case "六":
				System.out.print("請輸入圖片ID: ");
				
				if (scan.hasNextInt()) {
					int n = scan.nextInt();
					img.fetchByID(n);
				}
				openList();
				break;
			case "七":
				System.out.print("請輸入股票代碼: ");
				if (scan.hasNext()) {
					try {
						SD.downloadStockImg(scan.next());						
					}catch(FileNotFoundException e) {
						System.out.println("couldn't find that stock on internet......");
					}
				}				
				openList();
				break;
			case "八":
				System.out.print("請輸入股票代碼: ");
				if (scan.hasNext()) {
					try {
						dao2.uploadImg(scan.next());						
					}catch(FileNotFoundException e) {
						System.out.println("couldn't find that stock on internet......");
					}
				}				
				openList();
				break;
			case "九":
				System.out.print("請輸入股票代碼: ");
				if (scan.hasNext()) {
					dao2.fetchImg(scan.next());
				}				
				openList();
				break;
			default:
				System.out.println("請輸入正確數值(一~九).....");
				openList();
			}

		}

	}

	public static void openList() {
		System.out.println(" _____________________");
		System.out.println("|功能清單             |");
		System.out.println("| (一) 下載資料       |");
		System.out.println("| (二) 查詢資料       |");
		System.out.println("| (三) 刪除資料       |");
		System.out.println("| (四) 下載圖片(網路) |");
		System.out.println("| (五) 匯入圖片       |");
		System.out.println("| (六) 匯出圖片       |");
		System.out.println("| (七) 下載K線(網路)  |");
		System.out.println("| (八) 匯入K線(網路)  |");
		System.out.println("| (九) 匯出K線(資料庫)|");
		System.out.println("| (quit) 離開本程式   |");
		System.out.println("|____________________｜");
		
		System.out.print("請選擇功能: ");

	}
}

