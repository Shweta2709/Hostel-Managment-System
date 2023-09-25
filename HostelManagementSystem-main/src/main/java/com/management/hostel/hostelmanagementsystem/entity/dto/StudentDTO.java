package com.management.hostel.hostelmanagementsystem.entity.dto;

import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentDTO {

	private String name;
	private String email;
	private String phone;
	private String foodPreference;
	private String parentName;
	private String parentPhone;
	private int roomType;
	private boolean includeAc;

	@Override
	public String toString() {
		return "StudentDTO [name=" + name + ", email=" + email + ", phone=" + phone + ", foodPreference="
				+ foodPreference + ", parentName=" + parentName + ", parentPhone=" + parentPhone + ", roomType="
				+ roomType +", includeAC="+ includeAc + "]";
	}

	public StudentDTO() {
		super();
	}

	public StudentDTO(String name, String email, String phone, String foodPreference, String parentName,
			String parentPhone, int roomType, boolean includeAc) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.foodPreference = foodPreference;
		this.parentName = parentName;
		this.parentPhone = parentPhone;
		this.roomType = roomType;
		this.includeAc = includeAc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFoodPreference() {
		return foodPreference;
	}

	public void setFoodPreference(String foodPreference) {
		this.foodPreference = foodPreference;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentPhone() {
		return parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

	public boolean isIncludeAc() {
		return includeAc;
	}

	public void setIncludeAc(boolean includeAc) {
		this.includeAc = includeAc;
	}

}
