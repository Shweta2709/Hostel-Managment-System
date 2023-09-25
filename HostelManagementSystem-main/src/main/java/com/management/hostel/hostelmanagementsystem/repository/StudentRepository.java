package com.management.hostel.hostelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.hostel.hostelmanagementsystem.entity.student.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
}
