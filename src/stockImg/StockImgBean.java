package stockImg;

import java.sql.Blob;
import java.sql.Date;


public class StockImgBean {
	private String stockNum;
	private Date imgDate;
	private Blob dayLine;
	private Blob weekLine;
	private Blob monthLine;
	private Blob quarterLine;
	private Blob yearLine;

	public StockImgBean() {

	}

	public StockImgBean(String stockNum, Date imgDate, Blob dayLine, Blob weekLine, Blob monthLine,
			Blob quarterLine, Blob yearLine) {
		this.stockNum = stockNum;
		this.imgDate = imgDate;
		this.dayLine = dayLine;
		this.weekLine = weekLine;
		this.monthLine = monthLine;
		this.quarterLine = quarterLine;
		this.yearLine = yearLine;
	}

	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}

	public Date getImgDate() {
		return imgDate;
	}

	public void setImgDate(Date imgDate) {
		this.imgDate = imgDate;
	}

	public Blob getDayLine() {
		return dayLine;
	}

	public void setDayLine(Blob dayLine) {
		this.dayLine = dayLine;
	}

	public Blob getWeekLine() {
		return weekLine;
	}

	public void setWeekLine(Blob weekLine) {
		this.weekLine = weekLine;
	}

	public Blob getMonthLine() {
		return monthLine;
	}

	public void setMonthLine(Blob monthLine) {
		this.monthLine = monthLine;
	}

	public Blob getQuarterLine() {
		return quarterLine;
	}

	public void setQuarterLine(Blob quarterLine) {
		this.quarterLine = quarterLine;
	}

	public Blob getYearLine() {
		return yearLine;
	}

	public void setYearLine(Blob yearLine) {
		this.yearLine = yearLine;
	}

	@Override
	public String toString() {
		return "StockImgBean [stockNum=" + stockNum + ", imgDate=" + imgDate + ", dayLine=" + dayLine + ", weekLine="
				+ weekLine + ", monthLine=" + monthLine + ", quarterLine=" + quarterLine + ", yearLine=" + yearLine
				+ "]";
	}

}
