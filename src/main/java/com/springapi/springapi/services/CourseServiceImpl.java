package com.springapi.springapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springapi.springapi.entities.Course;
@Service
public class CourseServiceImpl implements CourseService {
	
	List<Course> list;
	
	

	public CourseServiceImpl() {
		super();
		list = new ArrayList<>();
		list.add(new Course(123,"Java Core", "In this course you'll learn core concepts of java."));
		list.add(new Course(877687,"SpringBoot","In this course you'll learn basics about springboot"));
		
	}



	@Override
	public List<Course> getCourses() {
		
		return list;
	}



	@Override
	public Course getCourse(long courseId) {
		Course c=null;
		for(Course course:list) {
			if(course.getId()==courseId)
			{
				c=course;
				break;
			}
			
			
		}
		return c;
	}



	@Override
	public Course addCourse(Course course) {
		// TODO Auto-generated method stub
		list.add(course);
		return course;
	}



	@Override
	public Course updateCourse(Course course) {
		// TODO Auto-generated method stub
		
		list.forEach(e ->{
			if(e.getId()==course.getId()) {
				e.setTitle(course.getTitle());
				e.setDescription(course.getDescription());
			}
		});
		return course;
	}



	@Override
	public void deleteCourse(Long courseId) {
		// TODO Auto-generated method stub
		int size = list.size();
		list= this.list.stream().filter(e-> e.getId()!=courseId).collect(Collectors.toList());

	}
	
	

}
