package cn.lry.junit_test;

import java.util.Set;

import org.junit.Test;

import cn.lry.dao.CourseDao;
import cn.lry.dao.StuDao;
import cn.lry.dao.factory.CourseDaoFactory;
import cn.lry.dao.factory.StuDaoFactory;
import cn.lry.domains.Course;
import cn.lry.domains.Student;

public class BusinessTest {

	@Test
	public void chooseCours(){
		//dao操作实例
		StuDao studao = StuDaoFactory.getInstance();
		CourseDao courdao = CourseDaoFactory.getInstance();
		
		Student stu = studao.find(20134646);
		
		Course c1 = courdao.find(1101009);
		Course c2 = courdao.find(1101010);
		Course c3 = courdao.find(1101011);
		
		c1.setStus(stu);
		c2.setStus(stu);
		c3.setStus(stu);
	
		courdao.update(c1);
		courdao.update(c2);
		courdao.update(c3);
	}
	
	@Test
	public void deleteStu(){
		//dao操作实例
		StuDao studao = StuDaoFactory.getInstance();
		CourseDao courdao = CourseDaoFactory.getInstance();
		
		Student stu = studao.find(20134646);
		
		//移除课程中该学生记录
		Set<Course> cours = stu.getCourses();
		for(Course c: cours){
			c.getStus().remove(stu);
			courdao.update(c);
		}
		
		studao.delete(stu);
	}
	
	@Test
	public void deleteCour(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		courdao.delete(courdao.find(1101009));
	}
	
	@Test
	public void removeCour(){
		//dao操作实例
		StuDao studao = StuDaoFactory.getInstance();
		CourseDao courdao = CourseDaoFactory.getInstance();
		
		Student stu = studao.find(20134646);
		
		Set<Course> cours = stu.getCourses();
		for(Course c: cours){
			c.getStus().remove(stu);
			courdao.update(c);
		}
	}
};
