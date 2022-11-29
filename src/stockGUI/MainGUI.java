package stockGUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import downloader.ImgDownloader;
import downloader.StockDownloader;
import image.ImgDao;
import image.ImgDaoImpl;
import stockData.StockDaoImpl;
import stockImg.StockImgDaoImpl;

class MainGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container c;
	private JLabel title;
	private JTextArea console;
	private JLabel read;
	private JTextField readText;
	private JButton readBtn;
	private JLabel delete;
	private JTextField deleteText;
	private JButton deleteBtn;
	private JLabel downPic;
	private JTextField downPicText;
	private JButton downPicBtn;
	private JLabel inOut;
	private JTextField inOutText;
	private JButton inBtn;
	private JButton outBtn;
	private JLabel downK;
	private JTextField downKText;
	private JButton downKBtn;
	private JLabel inK;
	private JTextField inKText;
	private JButton inKBtn;
	private JLabel outK;
	private JTextField outKText;
	private JButton outKBtn;
	private JButton refreshBtn;
	String font = "微軟正黑體";
	int fontSize = 20;
	StockDaoImpl dao = new StockDaoImpl();
	StockImgDaoImpl dao2 = new StockImgDaoImpl();
	ImgDao img = new ImgDaoImpl();
	ImgDownloader imgD = new ImgDownloader();
	StockDownloader SD = new StockDownloader();

//////////////////////////////////////////////	

	public MainGUI() throws IOException, SQLException {
		dao.prepareTable();
		dao2.prepareTable();
		img.prepareTable();
		setTitle("JDBC資料查詢");
		setBounds(300, 90, 900, 600);
		setSize(1200, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		ImageIcon image = new ImageIcon("../Project/stocklogo.jpg");
		setIconImage(image.getImage());
		getContentPane().setBackground(new Color(40, 46, 62));
		Logo logo = new Logo();
		logo.setBounds(1040, 0, 130, 130);
		c = getContentPane();
		c.add(logo);
		c.setLayout(null);

		title = new JLabel("功能表單");
		title.setFont(new Font(font, Font.PLAIN, 25));
		title.setSize(300, 25);
		title.setLocation(80, 40);
		title.setForeground(Color.white);
		c.add(title);
///////////////////////////////////
		read = new JLabel("查詢股票(ID): ");
		read.setFont(new Font(font, Font.PLAIN, 20));
		read.setSize(150, 20);
		read.setLocation(80, 120);
		read.setForeground(Color.white);
		c.add(read);

		readText = new JTextField();
		readText.setFont(new Font(font, Font.PLAIN, 15));
		readText.setSize(100, 20);
		readText.setLocation(210, 120);
		c.add(readText);

		readBtn = new JButton("查詢");
		readBtn.setFont(new Font(font, Font.BOLD, 15));
		readBtn.setSize(65, 20);
		readBtn.setBackground(new Color(44, 133, 128));
		readBtn.setForeground(Color.white);
		readBtn.setLocation(330, 120);
		readBtn.addActionListener(this);
		c.add(readBtn);
/////////////////////////////////////////////////////////
		delete = new JLabel("刪除股票(名稱): ");
		delete.setFont(new Font(font, Font.PLAIN, 20));
		delete.setSize(150, 20);
		delete.setLocation(80, 170);
		delete.setForeground(Color.white);
		c.add(delete);

		deleteText = new JTextField();
		deleteText.setFont(new Font(font, Font.PLAIN, 15));
		deleteText.setSize(190, 20);
		deleteText.setLocation(230, 170);
		c.add(deleteText);

		deleteBtn = new JButton("刪除");
		deleteBtn.setFont(new Font(font, Font.BOLD, 15));
		deleteBtn.setSize(65, 20);
		deleteBtn.setBackground(new Color(44, 133, 128));
		deleteBtn.setForeground(Color.white);
		deleteBtn.setLocation(440, 170);
		deleteBtn.addActionListener(this);
		c.add(deleteBtn);
		////////////////////////////////////////////////////////
		downPic = new JLabel("下載圖片(URL): ");
		downPic.setFont(new Font(font, Font.PLAIN, 20));
		downPic.setSize(150, 20);
		downPic.setLocation(80, 220);
		downPic.setForeground(Color.white);
		c.add(downPic);

		downPicText = new JTextField();
		downPicText.setFont(new Font(font, Font.PLAIN, 15));
		downPicText.setSize(340, 20);
		downPicText.setLocation(80, 250);
		c.add(downPicText);

		downPicBtn = new JButton("下載");
		downPicBtn.setFont(new Font(font, Font.BOLD, 15));
		downPicBtn.setSize(65, 20);
		downPicBtn.setBackground(new Color(44, 133, 128));
		downPicBtn.setForeground(Color.white);
		downPicBtn.setLocation(440, 250);
		downPicBtn.addActionListener(this);
		c.add(downPicBtn);
		////////////////////////////////////////////////////////////
		inOut = new JLabel("匯出(ID)/匯入: ");
		inOut.setFont(new Font(font, Font.PLAIN, 20));
		inOut.setSize(150, 20);
		inOut.setLocation(80, 300);
		inOut.setForeground(Color.white);
		c.add(inOut);

		inOutText = new JTextField();
		inOutText.setFont(new Font(font, Font.PLAIN, 15));
		inOutText.setSize(100, 20);
		inOutText.setLocation(230, 300);
		c.add(inOutText);

		outBtn = new JButton("匯出");
		outBtn.setFont(new Font(font, Font.BOLD, 15));
		outBtn.setSize(65, 20);
		outBtn.setBackground(new Color(44, 133, 128));
		outBtn.setForeground(Color.white);
		outBtn.setLocation(350, 300);
		outBtn.addActionListener(this);
		c.add(outBtn);

		inBtn = new JButton("匯入");
		inBtn.setFont(new Font(font, Font.BOLD, 15));
		inBtn.setSize(65, 20);
		inBtn.setBackground(new Color(44, 133, 128));
		inBtn.setForeground(Color.white);
		inBtn.setLocation(425, 300);
		inBtn.addActionListener(this);
		c.add(inBtn);
///////////////////////////////////////////////////////     
		downK = new JLabel("下載K線(代碼): ");
		downK.setFont(new Font(font, Font.PLAIN, 20));
		downK.setSize(150, 20);
		downK.setLocation(80, 350);
		downK.setForeground(Color.white);
		c.add(downK);

		downKText = new JTextField();
		downKText.setFont(new Font(font, Font.PLAIN, 15));
		downKText.setSize(100, 20);
		downKText.setLocation(230, 350);
		c.add(downKText);

		downKBtn = new JButton("下載");
		downKBtn.setFont(new Font(font, Font.BOLD, 15));
		downKBtn.setSize(65, 20);
		downKBtn.setBackground(new Color(44, 133, 128));
		downKBtn.setForeground(Color.white);
		downKBtn.setLocation(350, 350);
		downKBtn.addActionListener(this);
		c.add(downKBtn);
//////////////////////////////////////////////////     
		inK = new JLabel("匯入K線(代碼)");
		inK.setFont(new Font(font, Font.PLAIN, 20));
		inK.setSize(150, 20);
		inK.setLocation(80, 400);
		inK.setForeground(Color.white);
		c.add(inK);

		inKText = new JTextField();
		inKText.setFont(new Font(font, Font.PLAIN, 15));
		inKText.setSize(100, 20);
		inKText.setLocation(230, 400);
		c.add(inKText);

		inKBtn = new JButton("匯入");
		inKBtn.setFont(new Font(font, Font.BOLD, 15));
		inKBtn.setSize(65, 20);
		inKBtn.setBackground(new Color(44, 133, 128));
		inKBtn.setForeground(Color.white);
		inKBtn.setLocation(350, 400);
		inKBtn.addActionListener(this);
		c.add(inKBtn);
///////////////////////////////////////////////////////////    
		outK = new JLabel("匯出K線(代碼)");
		outK.setFont(new Font(font, Font.PLAIN, 20));
		outK.setSize(150, 20);
		outK.setLocation(80, 450);
		outK.setForeground(Color.white);
		c.add(outK);

		outKText = new JTextField();
		outKText.setFont(new Font(font, Font.PLAIN, 15));
		outKText.setSize(100, 20);
		outKText.setLocation(230, 450);
		c.add(outKText);

		outKBtn = new JButton("匯出");
		outKBtn.setFont(new Font(font, Font.BOLD, 15));
		outKBtn.setSize(65, 20);
		outKBtn.setBackground(new Color(44, 133, 128));
		outKBtn.setForeground(Color.white);
		outKBtn.setLocation(350, 450);
		outKBtn.addActionListener(this);
		c.add(outKBtn);
/////////////////////////////////////////////////////////////		

		refreshBtn = new JButton("刷新面板");
		refreshBtn.setFont(new Font(font, Font.BOLD, 20));
		refreshBtn.setSize(150, 25);
		refreshBtn.setBackground(new Color(44, 133, 128));
		refreshBtn.setForeground(Color.white);
		refreshBtn.setLocation(80, 500);
		refreshBtn.addActionListener(this);
		c.add(refreshBtn);
/////////////////////////////////////////////////////////////     

		console = new JTextArea();
		console.setFont(new Font(font, Font.PLAIN, 16));
		console.setSize(400, 500);
		console.setLocation(600, 80);
		console.setLineWrap(true);
		console.setEditable(false);
		console.setBackground(new Color(16, 25, 32));
		console.setForeground(Color.white);
		c.add(console);

		setVisible(true);

		PrintStream outStream = new PrintStream(new TextAreaOutputStream(console), false, "UTF-8");
		console.setFont(new Font("Arial", 0, 14));
		System.setOut(outStream);
		System.setErr(outStream);

	}

	// method actionPerformed()
	// to get the action performed
	// by the user and act accordingly
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == readBtn) {
			try {
				int key = Integer.parseInt(readText.getText());
				readText.setText("");
				dao.read(key);

			} catch (IOException e1) {
				System.out.println("fail to write query file......");
			} catch (SQLException e1) {
				System.out.println("fail to query database......");
			}
		} else if (e.getSource() == deleteBtn) {
			String name = deleteText.getText();
			try {
				dao.delete(name, "name");
				deleteText.setText("");
			} catch (SQLException e1) {
				System.out.println("fail to delete data in database......");
			}

		} else if (e.getSource() == downPicBtn) {
			String url_ = downPicText.getText();
			if (url_.endsWith(".jpg") || url_.endsWith(".png") || url_.endsWith(".gif")) {
				
					try {
						imgD.downloadImg(url_);
						downPicText.setText("");
					} catch (SerialException e1) {
						System.out.println("Serial object issues......");
					} catch (IOException e1) {
						System.out.println("fail to find output path......");
					} catch (SQLException e1) {
						System.out.println("fail to pass data......");
					}
			
			} else {
				System.out.println("invalid image input resource, plaese select url ends with jpg, png or gif!");
			}

		} else if (e.getSource() == inBtn) {
			try {
				img.insertBatch();
			} catch (FileNotFoundException e1) {
				System.out.println("fail to find input files......");
			} catch (IOException e1) {
				System.out.println("fail to create stream......");
			} catch (SQLException e1) {
				System.out.println("fail to insert to database......");
			}

		} else if (e.getSource() == outBtn) {
			String imgId = inOutText.getText();
			try {
				img.fetchByID(Integer.parseInt(imgId));
				inOutText.setText("");
			} catch (NumberFormatException e1) {
				System.out.println("Invalid ImgId......");
			} catch (FileNotFoundException e1) {
				System.out.println("invalid output directory......");
			} catch (SQLException e1) {
				System.out.println("fail to find image......");
			} catch (IOException e1) {
				System.out.println("fail to write image......");
			}
		} else if (e.getSource() == downKBtn) {
			String stockNum = downKText.getText();
			try {
				SD.downloadStockImg(stockNum);
				downKText.setText("");
				JFrame picFrame = new PicFrame(stockNum);
				picFrame.setVisible(true);
			} catch (IOException e1) {
				System.out.println("fail to download candlestick chart, try another stock Number!");
				
			}

		} else if (e.getSource() == inKBtn) {
			String stockNum = inKText.getText();
			try {
				dao2.uploadImg(stockNum);
				inKText.setText("");
			} catch (SQLException e1) {
				System.out.println("fail to insert to database......");
				e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("couldn't find that stock on internet......");
			}
		} else if (e.getSource() == outKBtn) {
			String stockNum = outKText.getText();
			try {
				dao2.fetchImg(stockNum);
				outKText.setText("");
				JFrame picFrame3 = new PicFrame(stockNum);
				picFrame3.setVisible(true);
			} catch (SQLException e1) {
				System.out.println("fail to fetch image from database......");
			} catch (IOException e1) {
				System.out.println("fail to write image to file......");
			}
		} else if(e.getSource() == refreshBtn) {
			console.setText("");
		}

	}
}
