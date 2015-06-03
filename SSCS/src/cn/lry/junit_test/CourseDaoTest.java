package cn.lry.junit_test;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;


import cn.lry.dao.CourseDao;
import cn.lry.dao.StuDao;
import cn.lry.dao.factory.CourseDaoFactory;
import cn.lry.dao.factory.StuDaoFactory;
import cn.lry.domains.Course;
import cn.lry.domains.Student;

public class CourseDaoTest {

	@Test
	public void adds(){
		Course c1 = new Course(1101009,"高等数学（下）","数学");
		Course c2 = new Course(1101013,"高等数学（上）","数学");
		Course c3 = new Course(1101014,"高等代数（下）","数学");
		Course c4 = new Course(1101010,"高等代数（上）","数学");
		Course c5 = new Course(1101015,"大学英语（三）","英语");
		Course c6 = new Course(1101016,"大学英语（四）","英语");
		Course c7 = new Course(1101011,"大学英语（二）","英语");
		Course c8 = new Course(1101012,"体育（一）","体育");
		Course c9 = new Course(1101017,"体育（二）","体育");
		Course c10 = new Course(1101018,"体育（三）","体育");
		Course c11 = new Course(1101019,"体育（四）","体育");
		Course c12 = new Course(1101020,"离散数学","数学");
		Course c13 = new Course(1101021,"数值分析","数学");
		Course c14 = new Course(1101022,"概率论与数理统计","数学");
		Course c15 = new Course(1101023,"web基础","专业");
		Course c16 = new Course(1101024,"JSP与servlet","专业");
		Course c17 = new Course(1101025,"汇编程序设计","专业");
		Course c18 = new Course(1101026,"c++程序设计","专业");
		Course c19 = new Course(1101027,"VB程序设计","专业");
		Course c20 = new Course(1101028,"java程序设计","专业");
		Course c21 = new Course(1101029,"C语言程序设计","专业");
		
		CourseDao courdao = CourseDaoFactory.getInstance();
		courdao.add(c1);
		courdao.add(c2);
		courdao.add(c3);
		courdao.add(c4);
		courdao.add(c5);
		courdao.add(c6);
		courdao.add(c7);
		courdao.add(c8);
		courdao.add(c9);
		courdao.add(c10);
		courdao.add(c11);
		courdao.add(c12);
		courdao.add(c13);
		courdao.add(c14);
		courdao.add(c15);
		courdao.add(c16);
		courdao.add(c17);
		courdao.add(c18);
		courdao.add(c19);
		courdao.add(c20);
		courdao.add(c21);
	}
	
	@Test
	public void add(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		Course c1 = new Course(1101009,"高等数学（下）","数学");
		courdao.add(c1);
	}
	
	@Test
	public void fund(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		System.out.println("查询结果："+courdao.find(1101009));
		System.out.println("查询结果："+courdao.find(1101010));
		System.out.println("查询结果："+courdao.find(1101011));
		System.out.println("查询结果："+courdao.find(1101001));
	}
	
	@Test
	public void update(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		Course cour = courdao.find(1101009);
		cour.setName("课程");
		cour.setType(null);
		courdao.update(cour);
	}
	
	@Test
	public void traverse_1(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		List<Course> list = courdao.traverse();
		
		Iterator<Course> iter = list.iterator();
		while(iter.hasNext()){
			System.out.println("课程："+iter.next());
		}
	}
	
	@Test
	public void traverse_2(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		List<Course> list;
		int i;
		for(i=1 ; i<10 ; i++){
			list = courdao.traverse(i,5,null);
			if(list != null){
				System.out.println("第"+i+"页");
				for(Course cour: list){
					System.out.println(cour);
				}
			}else{
				System.out.println("未查询到相关记录");
			}
		}
	}
	
	@Test
	public void delete(){
		CourseDao courdao = CourseDaoFactory.getInstance();
		Course cour = courdao.find(1101009);
		courdao.delete(cour);
	}
};
