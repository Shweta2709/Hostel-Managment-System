package com.management.hostel.hostelmanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.hostel.hostelmanagementsystem.entity.dto.RoomDTO;
import com.management.hostel.hostelmanagementsystem.entity.dto.StudentDTO;
import com.management.hostel.hostelmanagementsystem.entity.room.Room;
import com.management.hostel.hostelmanagementsystem.entity.student.Student;
import com.management.hostel.hostelmanagementsystem.service.RoomService;
import com.management.hostel.hostelmanagementsystem.service.StudentService;

@RestController
@CrossOrigin
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private RoomService roomService;

	@GetMapping("/students")
	public List<Student> retrieveAllStudents() {
		return studentService.getAllStudents();
	}

	@PostMapping("/add-student")
	public ResponseEntity<String> addStudent(@RequestBody StudentDTO studentDTO) {
		Student student = new Student(studentDTO.getName(), studentDTO.getEmail(), studentDTO.getPhone(),
				studentDTO.getFoodPreference(), studentDTO.getParentName(), studentDTO.getParentPhone(),studentDTO.getRoomType(),studentDTO.isIncludeAc());
		student.setRoom(null);
		student.setRoomNumber(null);
		String roomNumber = roomService.allocateRoom(student);
		return new ResponseEntity<>("Allocated Room: " + roomNumber, HttpStatus.ACCEPTED);

	}

	@GetMapping("/student-room-Number")
	public ResponseEntity<String> getStudentRoomNumber(@RequestParam long id) {
		String studentRoomNumber = roomService.getRoomNumber(id);

		return new ResponseEntity<String>("Room Number: " + studentRoomNumber, HttpStatus.OK);
	}

	@GetMapping("/rooms")
	public ResponseEntity<List<Room>> getAllRooms() {
		return new ResponseEntity<List<Room>>(roomService.getAllRooms(), HttpStatus.OK);
	}

	@PostMapping("/add-rooms")
	public ResponseEntity<String> addListOfRooms(@RequestBody List<RoomDTO> roomsDTO) {
		List<Room> rooms = new ArrayList<>();
		for (RoomDTO roomDTO : roomsDTO) {
			rooms.add(new Room(roomDTO.getRoomNumber(), roomDTO.getCapacity(), roomDTO.getIncludesAc(),
					roomDTO.getStatus(), roomDTO.getVacancy(), null));
		}
		roomService.addListOfRooms(rooms);
		return new ResponseEntity<String>("added", HttpStatus.CREATED);
	}

	@DeleteMapping("/delete-students")
	public ResponseEntity<String> deleteStudent(@RequestParam long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<String>("Deleted!!", HttpStatus.OK);
	}
	
	@GetMapping("/total-rooms")
	public ResponseEntity<Integer> getTotalRooms() {
		List<Room> roomList = roomService.getAllRooms();
		return new ResponseEntity<Integer>(roomList.size(), HttpStatus.OK);
	}
	
	@GetMapping("/total-students")
	public ResponseEntity<Integer> getTotalStudents() {
		List<Student> studentList = studentService.getAllStudents();
		return new ResponseEntity<Integer>(studentList.size(), HttpStatus.OK);
	}
	
	@GetMapping("/available-rooms")
	public ResponseEntity<Integer> getAvailableRooms() {
		List<Room> availableRooms = roomService.getAvailableRooms() ;
		return new ResponseEntity<Integer>(availableRooms.size(), HttpStatus.OK);
	}
	
	@GetMapping("/filled-rooms")
	public ResponseEntity<Integer> getFilledRooms() {
		List<Room> roomList = roomService.getAllRooms();
		List<Room> availableRooms = roomService.getAvailableRooms() ;
		int filledRooms = roomList.size() - availableRooms.size();
		return new ResponseEntity<Integer>(filledRooms, HttpStatus.OK);
	}
	
	@GetMapping("/vacant-beds")
	public ResponseEntity<Integer> getVacantBeds() {
		List<Room> roomList = roomService.getAllRooms();
		int vacant =0;
		for(Room room: roomList) {
			vacant += room.getVacancy();
		}
		return new ResponseEntity<Integer>(vacant, HttpStatus.OK);
	}

}
