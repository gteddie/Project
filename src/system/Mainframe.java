package system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.serial.SerialException;

import downloader.ImgDownloader;
import downloader.StockDownloader;
import image.ImgDao;
import image.ImgDaoImpl;
import stockData.StockDaoImpl;
import stockImg.StockImgDaoImpl;

public class Mainframe {

	public static void main(String[] args) {
		// initializing objects
		StockDaoImpl dao = new StockDaoImpl();
		StockImgDaoImpl dao2 = new StockImgDaoImpl();
		ImgDao img = new ImgDaoImpl();
		ImgDownloader imgD = new ImgDownloader();
		StockDownloader SD = new StockDownloader();
		System.out.println("初始化資料庫......");
		try {
			dao.prepareTable();
		} catch (IOException e2) {
			System.out.println("fail to generate stock table......");
		
		} catch (SQLException e2) {
			System.out.println("fail to generate stock table......");
			
		}
		try {
			dao2.prepareTable();
		} catch (IOException e2) {
			System.out.println("fail to generate stockimg table......");
		} catch (SQLException e2) {
			System.out.println("fail to generate stockimg table......");
		}
		try {
			img.prepareTable();
		} catch (SQLException e2) {
			System.out.println("fail to generate img table......");
		}
		openList();
		Scanner scan = new Scanner(System.in);
		String option = "";
		while (scan.hasNext()) {
			option = scan.next();
			if (option.equals("quit")) {
				System.out.println("  程式結束......");
				scan.close();
				break;
			}
			;
			switch (option) {
			case "一":
				try {
					dao.prepareTable();
				} catch (IOException e1) {
					System.out.println("fail to access data from internet......");
					e1.printStackTrace();
				} catch (SQLException e1) {
					System.out.println("fail to generate table......");
				}
				openList();
				break;
			case "二":
				System.out.print("  請輸入stockId: ");
				if (scan.hasNextInt()) {
					try {
						dao.read(scan.nextInt());
					} catch (FileNotFoundException e) {
						System.out.println("fail to find output directory......");
					} catch (SQLException e) {
						System.out.println("fail to query database......");
					} catch (IOException e) {
						System.out.println("fail to write query file......");
					}
				}else {
					System.out.println("  錯誤的id格式，請輸入數字......");
					scan.next();
				}
				openList();
				break;
			case "三":
				System.out.print("  請輸入股票名稱: ");
				if (scan.hasNext()) {
					try {
						dao.delete(scan.next(), "name");
					} catch (SQLException e) {
						System.out.println("fail to delete data in database......");
					}
				}
				openList();
				break;
			case "四":
				System.out.print("  請輸入圖片網址(結尾為 .檔案類型): ");
				if (scan.hasNext()) {
					String i = scan.next();
					if (i.endsWith(".jpg") || i.endsWith(".png") || i.endsWith(".gif")) {
					
							try {
								imgD.downloadImg(i);
							} catch (SerialException e) {
								System.out.println("Serial object issues......");
							} catch (IOException e) {
								System.out.println("fail to find output path......");
							} catch (SQLException e) {
								System.out.println("fail to pass data......");
							}

					} else {
						System.out.println("invalid url form, please select url ends with .jpg, .png or gif......");
					}
				}
				openList();
				break;

			case "五":
				try {
					img.insertBatch();
				} catch (FileNotFoundException e1) {
					System.out.println("fail to find input files......");
				} catch (IOException e1) {
					System.out.println("fail to create stream......");
				} catch (SQLException e1) {
					System.out.println("fail to insert to database......");
				}
				openList();
				break;
			case "六":
				System.out.print("  請輸入圖片ID: ");

				if (scan.hasNextInt()) {
					int n = scan.nextInt();
					try {
						img.fetchByID(n);
					} catch (FileNotFoundException e) {
						System.out.println("invalid output directory......");
					} catch (SQLException e) {
						System.out.println("fail to find image......");
					} catch (IOException e) {
						System.out.println("fail to write image......");
						
						
					}
				}else {
					System.out.println("  錯誤的圖片id格式，請輸入數字......");
					scan.next();					
				}
				openList();
				break;
			case "七":
				System.out.print("  請輸入股票代碼: ");
				if (scan.hasNext()) {
						try {
							SD.downloadStockImg(scan.next());
						} catch (IOException e) {
							System.out.println("couldn't find that stock on internet......");
							
						}
					 
				}
				openList();
				break;
			case "八":
				System.out.print("  請輸入股票代碼: ");
				if (scan.hasNext()) {

					try {
						dao2.uploadImg(scan.next());
					} catch (SQLException e) {
						System.out.println("fail to insert to database......");
					} catch (IOException e) {
						System.out.println("couldn't find that stock on internet......");

					}

				}
				openList();
				break;
			case "九":
				System.out.print("  請輸入股票代碼: ");
				if (scan.hasNext()) {
					try {
						dao2.fetchImg(scan.next());
					} catch (SQLException e) {
						System.out.println("fail to find image in database......");
					} catch (IOException e) {
						System.out.println("fail to write image......");
						
					}
				}
				openList();
				break;
			default:
				System.out.println("  請輸入正確數值(一~九).....");
				openList();
			}

		}

	}

	public static void openList() {
		System.out.println("   _____________________");
		System.out.println("  |功能清單             |");
		System.out.println("  | (一) 下載資料       |");
		System.out.println("  | (二) 查詢資料       |");
		System.out.println("  | (三) 刪除資料       |");
		System.out.println("  | (四) 下載圖片(網路) |");
		System.out.println("  | (五) 匯入圖片       |");
		System.out.println("  | (六) 匯出圖片       |");
		System.out.println("  | (七) 下載K線(網路)  |");
		System.out.println("  | (八) 匯入K線(網路)  |");
		System.out.println("  | (九) 匯出K線(資料庫)｜");
		System.out.println("  | (quit) 離開本程式   |");
		System.out.println("  |____________________｜");
		System.out.println();
		System.out.print("  請選擇功能: ");

	}
}
