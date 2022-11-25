package stockData;

import java.io.Serializable;

public class StockBean implements Serializable {
	// 股票代號,股票名稱,收盤價,月平均價
	private static final long serialVersionUID = 1L;
	private int stockId;
	private String stockNum;
	private String stockName;
	private double closePrice;
	private double monAvgPrice;

	public StockBean() {

	}

	public StockBean(int stockId, String stockNum, String stockName, double closePrice, double monAvgPrice) {
		this.stockId = stockId;
		this.stockNum = stockNum;
		this.stockName = stockName;
		this.closePrice = closePrice;
		this.monAvgPrice = monAvgPrice;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	public double getMonAvgPrice() {
		return monAvgPrice;
	}

	public void setMonAvgPrice(double monAvgPrice) {
		this.monAvgPrice = monAvgPrice;
	}

	@Override
	public String toString() {
		return "DataBean [stockId=" + stockId + ", stockNum=" + stockNum + ", stockName=" + stockName + ", closePrice="
				+ closePrice + ", monAvgPrice=" + monAvgPrice + "]";
	}

}