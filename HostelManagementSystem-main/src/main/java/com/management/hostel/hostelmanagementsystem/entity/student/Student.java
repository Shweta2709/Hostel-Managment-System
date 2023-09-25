package com.management.hostel.hostelmanagementsystem.entity.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.management.hostel.hostelmanagementsystem.entity.room.Room;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "food_preference", nullable = false)
	private String foodPreference;

	@Column(name = "parent_name", nullable = false)
	private String parentName;

	@Column(name = "parent_phone", nullable = false)
	private String parentPhone;

	@Column(name = "room_type", nullable = false)
	private int roomType;
	
	@Column(name = "requires_Ac", nullable = false)
	private boolean requiresAc;

	
	@Column(name = "room_number")
	private String roomNumber;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;

	public Student() {
		super();
	}

	public Student(String name, String email, String phone, String foodPreference, String parentName,
			String parentPhone, int roomType, boolean requiresAc) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.foodPreference = foodPreference;
		this.parentName = parentName;
		this.parentPhone = parentPhone;
		this.roomType = roomType;
		this.requiresAc = requiresAc;
	}

	@Override
	public String toString() {
		return String.format(
				"Student [id=%s, name=%s, email=%s, phone=%s, foodPreference=%s, parentName=%s, parentPhone=%s, roomType=%s,requiresAc=%s roomNumber=%s, room=%s]",
				id, name, email, phone, foodPreference, parentName, parentPhone, roomType,requiresAc, roomNumber, room);
	}

	public Long getId() {
		return id;
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

	@JsonBackReference
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}
	
	public boolean isRequiresAc() {
		return requiresAc;
	}

	public void setRequiresAc(boolean requiresAc) {
		this.requiresAc = requiresAc;
	}


	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

}
