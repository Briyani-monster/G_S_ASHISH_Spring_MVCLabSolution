package com.greatlearning.student.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.student.models.Student;
import com.greatlearning.student.services.StudentService;


@Controller
@RequestMapping("student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String getStudents(Model theModel){
		List<Student> students=studentService.findAll();
		System.out.println(students);
		theModel.addAttribute("ListOfStudents",students);
		return "list-student";
	}
	@RequestMapping("/showForm")
	public String showFormAdd(Model theModel) {
		Student student=new Student();
		theModel.addAttribute("Student", student);
		return "student-form";
	}
	
	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id) {
		studentService.deleteById(id);
		return "redirect:/student/list";
	}
	
	@PostMapping("/save")
	public String addStudent(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("department") String department,@RequestParam("country") String country) {
		System.out.println(id);
		Student theStudent;
		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setName(name);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
		} else
			theStudent = new Student(id,name,department,country);
		// save the Book
		studentService.save(theStudent);

		// use a redirect to prevent duplicate submissions
		return "redirect:/student/list";
		
	}
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
		System.out.println(theId);
		// get the Book from the service
		Student theStudent = studentService.findById(theId);
		System.out.println("the obj"+ theStudent);
		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Student", theStudent);

		// send over to our form
		return "student-form";
	}

}
