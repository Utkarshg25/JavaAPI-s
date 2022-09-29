package com.springapi.springapi.controller;

import java.io.IOException;
//import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springapi.springapi.entities.Course;
import com.springapi.springapi.services.CourseService;

@RestController
public class MyController {
	@Autowired
	private CourseService courseService;
	
	
	
@GetMapping("/home")
public String home() {
	return "This is home page";
}




@GetMapping("/courses")
public List<Course> getCourses(){
	return this.courseService.getCourses();
}



@GetMapping("/courses/{courseId}")
public Course getCourse(@PathVariable String courseId,HttpServletRequest req,HttpServletResponse res)throws Exception {
	try {
	Course course = this.courseService.getCourse(Long.parseLong(courseId));
	if(course != null) return course;
	else {
		throw new Exception("course not found");
	}
	}
	catch(Exception e) {
		res.setHeader("error",e.getMessage());
//		res.setStatus(HttpStatus.FORBIDDEN);
		res.setStatus(404);
		Map<String,String> error = new HashMap<>();
		error.put("message",e.getMessage());
		res.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(res.getOutputStream(),error);
	}
	return null;
	
}



@PostMapping("/courses")
public Course addCourses(@RequestBody Course course) {
	return this.courseService.addCourse(course);
	
}



@PutMapping("/courses")
public Course updateCourse(@RequestBody Course course) {
	return this.courseService.updateCourse(course);
}




@DeleteMapping("/courses/{courseId}")
public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId,HttpServletResponse res) throws  IOException {
	try {
		this.courseService.deleteCourse(Long.parseLong(courseId));
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	catch(Exception e) {
		res.setHeader("error",e.getMessage());
//		res.setStatus(HttpStatus.FORBIDDEN);
		res.setStatus(404);
		Map<String,String> error = new HashMap<>();
		error.put("message",e.getMessage());
		res.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(res.getOutputStream(),error);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}



}
