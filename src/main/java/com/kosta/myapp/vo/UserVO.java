package com.kosta.myapp.vo;

public class UserVO {
	private String userid;
	private String userpw;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserVO [userid=").append(userid).append(", userpw=").append(userpw).append("]");
		return builder.toString();
	}
	
	
}
