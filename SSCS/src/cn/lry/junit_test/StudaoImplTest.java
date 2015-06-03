package cn.lry.junit_test;

import java.util.Iterator;
import java.util.List;
import org.junit.Test;

import cn.lry.dao.StuDao;
import cn.lry.dao.factory.StuDaoFactory;
import cn.lry.domains.Student;

public class StudaoImplTest {

	@Test
	public void  adds(){
		StuDao studao = StuDaoFactory.getInstance();
		Student s0 = new Student(20134645,"陈荣森","软件学院",1302);
		Student s01 = new Student(20134646,"陈腾扬","软件学院",1302);
		Student s1 = new Student(20134647,"陈泽华","软件学院",1302);
		Student s2 = new Student(20134648,"杜赫然","软件学院",1302);
		Student s3 = new Student(20134649,"房建猷","软件学院",1302);
		Student s4 = new Student(20134650,"冯铄","软件学院",1302);
		Student s41 = new Student(20134651,"郭睿","软件学院",1302);
		Student s5 = new Student(20134652,"胡少晗","软件学院",1302);
		Student s6 = new Student(20134653,"李广龙","软件学院",1302);
		Student s7 = new Student(20134654,"刘荣耀","软件学院",1302);
		Student s8 = new Student(20134655,"邵严恒","软件学院",1302);
		Student s9 = new Student(20134656,"田胜","软件学院",1302);
		Student s10 = new Student(20134657,"田哲","软件学院",1302);
		Student s11 = new Student(20134658,"周鹤达","软件学院",1302);
		Student s12 = new Student(20134659,"朱志文","软件学院",1302);
		Student s13 = new Student(20134660,"王浩","软件学院",1302);
		Student s14 = new Student(20134661,"赵露","软件学院",1302);
		Student s15 = new Student(20134662,"赵卓","软件学院",1302);
		Student s16 = new Student(20134663,"徐献博","软件学院",1302);
		Student s17 = new Student(20134664,"张文昭","软件学院",1302);
		Student s18 = new Student(20134665,"杨旭恒","软件学院",1302);
		Student s19 = new Student(20134666,"孔伟杰","软件学院",1302);
		Student s20 = new Student(20134667,"邹锐","软件学院",1302);
		Student s21 = new Student(20134668,"朱越鹏","软件学院",1302);
		Student s22 = new Student(20134669,"郑媛欣","软件学院",1302);
		Student s23 = new Student(20134670,"张雯婕","软件学院",1302);
		Student s24 = new Student(20134671,"田安捷","软件学院",1302);
		Student s25 = new Student(20134672,"汤成玲","软件学院",1302);
		Student s26 = new Student(20134673,"高美妍","软件学院",1302);
		studao.add(s0);
		studao.add(s01);
		studao.add(s1);
		studao.add(s2);
		studao.add(s3);
		studao.add(s4);
		studao.add(s41);
		studao.add(s5);
		studao.add(s6);
		studao.add(s7);
		studao.add(s8);
		studao.add(s9);
		studao.add(s10);
		studao.add(s11);
		studao.add(s12);
		studao.add(s13);
		studao.add(s14);
		studao.add(s15);
		studao.add(s16);
		studao.add(s17);
		studao.add(s18);
		studao.add(s19);
		studao.add(s20);
		studao.add(s21);
		studao.add(s22);
		studao.add(s23);
		studao.add(s24);
		studao.add(s25);
		studao.add(s26);
	}
	
	@Test
	public void add(){
		StuDao studao = StuDaoFactory.getInstance();
		Student s4 = new Student(20134647,"陈泽华","123456","软件学院",1302);
		studao.add(s4);
	}
	@Test
	public void fund(){
		StuDao studao = StuDaoFactory.getInstance();
		System.out.println("查询结果:"+studao.find(20134654));
		System.out.println("查询结果:"+studao.find(20134651));
		System.out.println("查询结果:"+studao.find(20134646));
	}
	
	
	@Test
	public void update(){
		StuDao studao = StuDaoFactory.getInstance();
		Student s1 = studao.find(20134654);
		Student s2 = studao.find(20134653);
		
		s1.setName("光吊");
		s2.setName("耀哥");
		
		studao.update(s1);
		studao.update(s2);
	}
	
	
	@Test
	public void traverse_1(){
		StuDao studao = StuDaoFactory.getInstance();
		List<Student> list = studao.traverse();
		Iterator<Student> iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
	@Test
	public void delete(){
		StuDao studao = StuDaoFactory.getInstance();
		Student stu = studao.find(20134646);
		studao.delete(stu);
	}
	
	@Test
	public void traverse_2(){
		StuDao studao = StuDaoFactory.getInstance();
		List<Student> list;
		int i;
		for(i=1 ; i<10 ; i++){
			System.out.println("第"+i+"页");
			list = studao.traverse(i,5,null);
			if(list != null){
				for(Student stu: list){
					System.out.println(stu);
				}
			}else{
				System.out.println("未查询到相关记录");
			}
			
		}
	}
};
