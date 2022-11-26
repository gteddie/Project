package stockData;

import java.io.Serializable;

public class StockBean implements Serializable {
	// 股票代號,股票名稱,收盤價,月平均價
	private static final long serialVersionUID = 1L;
	private Integer stockId;
	private String stockNum;
	private String stockName;
	private Double closePrice;
	private Double monAvgPrice;

	public StockBean() {

	}


	public StockBean(Integer stockId, String stockNum, String stockName, Double closePrice, Double monAvgPrice) {
		super();
		this.stockId = stockId;
		this.stockNum = stockNum;
		this.stockName = stockName;
		this.closePrice = closePrice;
		this.monAvgPrice = monAvgPrice;
	}




	public Integer getStockId() {
		return stockId;
	}


	public void setStockId(Integer stockId) {
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


	public Double getClosePrice() {
		return closePrice;
	}


	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}


	public Double getMonAvgPrice() {
		return monAvgPrice;
	}


	public void setMonAvgPrice(Double monAvgPrice) {
		this.monAvgPrice = monAvgPrice;
	}


	@Override
	public String toString() {
		return "DataBean [stockId=" + stockId + ", stockNum=" + stockNum + ", stockName=" + stockName + ", closePrice="
				+ closePrice + ", monAvgPrice=" + monAvgPrice + "]";
	}

}