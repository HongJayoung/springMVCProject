package com.kosta.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int bno;
	private String title;
	private String content;
	private Integer writer;
	private Date regdate;
	private Date updatedate;
	private String pic;
	private MultipartFile photos;
	
	private EmpVO emp; 
	
	public BoardVO() {}
	
	public BoardVO(String title, String content, Integer writer) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	public BoardVO(int bno, String title, String content, Integer writer, Date regdate, Date updatedate) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}

	public BoardVO(String title, String content, Integer writer, EmpVO emp) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.emp = emp;
	}

	public MultipartFile getPhotos() {
		return photos;
	}

	public void setPhotos(MultipartFile photos) {
		this.photos = photos;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public EmpVO getEmp() {
		return emp;
	}

	public void setEmp(EmpVO emp) {
		this.emp = emp;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getWriter() {
		return writer;
	}

	public void setWriter(Integer writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardVO [bno=").append(bno).append(", title=").append(title).append(", content=")
				.append(content).append(", writer=").append(writer).append(", regdate=").append(regdate)
				.append(", updatedate=").append(updatedate).append(", pic=").append(pic).append(", emp=").append(emp)
				.append("]");
		return builder.toString();
	}

}
