package com.greatlearning.student.services;

import java.util.List;

import com.greatlearning.student.models.Student;

public interface StudentService {
	public List<Student> findAll();

	public Student findById(int theId);

	public void save(Student thestudent);

	public void deleteById(int theId);

	
}
