package cn.lry.junit_test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import cn.lry.dao.proxy.DaoProxy;
import cn.lry.domains.Course;
import cn.lry.domains.Student;

public class DaoProxyTest {

	@Test
	public void find(){
		System.out.println(DaoProxy.find(20134654, Student.class));
	}
	
	@Test
	public void ttest(){
		Set<Student> stuSet = new HashSet<Student>();
		Set<Course> courSet = new HashSet<Course>();
		instanceTest(stuSet);
		instanceTest(courSet);
	}
	
	private <T> void instanceTest(Set<T> objSet){
		T t = null;
		if(t instanceof Student){
			System.out.println("Student: true");
		} if(t instanceof Course){
			System.out.println("Course: true");
		}else{
			System.out.println("false");
		}
	}
};
