package com.management.hostel.hostelmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.hostel.hostelmanagementsystem.entity.room.Room;
import com.management.hostel.hostelmanagementsystem.entity.student.Student;
import com.management.hostel.hostelmanagementsystem.exceptions.RoomUnavailableException;
import com.management.hostel.hostelmanagementsystem.repository.RoomRepository;
import com.management.hostel.hostelmanagementsystem.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class RoomService {

	private static Logger log = Logger.getLogger(RoomService.class);

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private StudentRepository studentRepository;

	// to get all rooms
	public List<Room> getAvailableRooms() {
		return roomRepository.findByStatus("available");
	}

	// to get rooms acc to capacity
	public List<Room> getRoomsAccordingToCapacity(int capacity) {
		return roomRepository.findByCapacity(capacity);
	}

	public List<Room> getAvailableRoomsAccordingToCapacity(int roomType) {
		return roomRepository.findByCapacityAndStatus(roomType, "available");

	}

	public List<Room> getAvailableRoomsAccordingToCapacityAndAcPreference(int roomType, boolean includeAc) {
		return roomRepository.findByCapacityAndStatusAndIncludesAc(roomType, "available", includeAc);

	}

	// to get room by id
	public Optional<Room> getRoomById(Long id) {
		return roomRepository.findById(id);
	}

	// to find room according to AC preference
	public List<Room> getRoomsAccordingToAc() {
		return roomRepository.findByIncludesAc(true);
	}

	public Room getRoomByRoomNumber(String roomNumber) {
		return roomRepository.findByRoomNumber(roomNumber);
	}

	// to allocateRoom
	@Transactional
	public String allocateRoom(Student student) {
		boolean requiresAc = student.isRequiresAc();
		List<Room> rooms = roomRepository.findByCapacityAndStatusAndIncludesAc(student.getRoomType(), "available",
				requiresAc);

		for (Room room : rooms) {
			String roomNumber = fillStudentsInVacantRooms(room, student);
			if (roomNumber != null) {
				return roomNumber;
			}
		}

		throw new RoomUnavailableException("Rooms not available!!");

	}

	@Transactional
	public String fillStudentsInVacantRooms(Room room, Student student) {
		log.info("Inside fillStudentsInVacantRooms....");
		int vacancy = room.getVacancy();
		if (vacancy != 0) {
			room.setVacancy(vacancy - 1);
			student.setRoom(room);

			student.setRoomNumber(room.getRoomNumber());

			studentRepository.save(student);
			if (room.getVacancy() == 0) {
				room.setStatus("not available");
			}

			return room.getRoomNumber();
		}
		return null;
	}

	// to release room
	@Transactional
	public void releaseRoom(Room room) {
		room.setStatus("available");
		room.setVacancy(room.getVacancy() + 1);
		roomRepository.save(room);

		room.getStudents().clear();
	}

	// to return room number
	public String getRoomNumber(Long id) {
		Student student = studentRepository.getReferenceById(id);
		return student.getRoomNumber();

	}

	// Add a list of rooms by admins
	public void addListOfRooms(List<Room> rooms) {
		roomRepository.saveAll(rooms);
	}

	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

}
