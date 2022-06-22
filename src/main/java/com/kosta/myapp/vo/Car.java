package com.kosta.myapp.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//JavaBeans 조건 : 변수는 접근지정자가 private, default 생성자 필수, getter/setter 필수
//VO(Value Object)
//DTO(Data Transfer Object)

@Component //=> <bean id="car" class="com.kosta.mavenApp.section3.Car"></bean>
public class Car {
	@Value("BMW520")
	private String model;
	@Value("1000")
	private int price;
	private String color;
	
	public Car() {}
	
	public Car(String model, int price, String color) {
		super();
		this.model = model;
		this.price = price;
		this.color = color;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [model=").append(model).append(", price=").append(price).append(", color=").append(color)
				.append("]");
		return builder.toString();
	}
}
