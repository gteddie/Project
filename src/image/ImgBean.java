package image;
import java.io.Serializable;
import java.sql.Blob;

public class ImgBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private Blob fileContent;
	private String fileType;

	public ImgBean() {

	};

	public ImgBean(String fileName, Blob fileContent, String fileType) {
		this.fileName = fileName;
		this.fileContent = fileContent;
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Blob getFileContent() {
		return fileContent;
	}

	public void setFileContent(Blob fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
