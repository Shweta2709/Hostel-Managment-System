package com.management.hostel.hostelmanagementsystem.entity.dto;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomDTO {

	private String roomNumber;
	private int capacity;
	private boolean includesAc;
	private String status;
	private int vacancy;

	@Override
	public String toString() {
		return String.format("RoomDTO [roomNumber=%s, capacity=%s, includesAc=%s, status=%s, vacancy=%s]", roomNumber,
				capacity, includesAc, status, vacancy);
	}

	public RoomDTO() {
		super();
	}

	public RoomDTO(String roomNumber, int capacity, boolean includesAc, String status, int vacancy) {
		super();
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.includesAc = includesAc;
		this.status = status;
		this.vacancy = vacancy;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean getIncludesAc() {
		return includesAc;
	}

	public String getStatus() {
		return status;
	}

	public int getVacancy() {
		return vacancy;
	}

}
