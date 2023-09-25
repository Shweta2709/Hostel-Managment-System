package com.management.hostel.hostelmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.hostel.hostelmanagementsystem.entity.room.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
	
	Room findByRoomNumber(String roomNumber);
	
	List<Room> findByCapacity(int capacity);

	List<Room> findByStatus(String string);

	List<Room> findByIncludesAc(boolean choice);

	List<Room> findByCapacityAndStatus(int capacity, String string);
	
	List<Room> findByCapacityAndStatusAndIncludesAc(int capacity, String string, boolean includesAc);
	

}
