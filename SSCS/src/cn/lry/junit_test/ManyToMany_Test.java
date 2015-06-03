package cn.lry.junit_test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.lry.dao.utils.SessionUtils;
import cn.lry.domains.Admin;
import cn.lry.domains.Course;
import cn.lry.domains.Student;

public class ManyToMany_Test {

private  Session session = null;
	
	@Before
    public void inilizat(){
    	session = SessionUtils.getSession();
    }
	@Test
	public void add() {
		
		Course c1 = new Course(1101001,"高等数学","数学");
		Course c2 = new Course(1101002,"高等代数","数学");
		Course c3 = new Course(1101003,"大学英语(一)","英语");
		Course c4 = new Course(1101004,"体育(三)","体育");
		
		Student s1 = new Student(20134646,"陈腾扬","123456","软件学院",1302);
		Student s2 = new Student(20134651,"郭睿","123456","软件学院",1302);
		Student s3 = new Student(20134654,"刘荣耀","123456","软件学院",1302);
		Student s4 = new Student(20134667,"邹锐","123456","软件学院",1302);
		
		//设置对象关联
		/*注意：只构建一次对象关联（课程 --> 学生 / 学生 --> 课程）
		 * 重复构建会导致主键冲突异常(联合主键：Course_id & student_id)*/
		Set<Student> stus = new HashSet<Student>();
		stus.add(s1);
		stus.add(s2);
		stus.add(s3);
		stus.add(s4);
		
		c1.setStus(stus);
		c2.setStus(stus);
		c3.setStus(stus);
		c4.setStus(stus);
		
		try{
//			//获取session
//			session = SessionUtils.getSession();
			//开启事务
			session.beginTransaction();
			//缓存数据
			
			session.save(s1);
			session.save(s2);
			session.save(s3);
			session.save(s4);
			
			session.save(c1);
			session.save(c2);
			session.save(c3);
			session.save(c4);

			//提交事务
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			//session回滚
			if(session!=null)
				session.getTransaction().rollback();
		}
	}
	
	@Test
	public void addCours(){
		Course c1 = new Course(1101009,"高等数学（下）","数学");
		Course c2 = new Course(1101013,"高等数学（上）","数学");
		Course c3 = new Course(1101014,"高等代数（下）","数学");
		Course c4 = new Course(1101010,"高等代数（上）","数学");
		Course c5 = new Course(1101015,"大学英语(三)","英语");
		Course c6 = new Course(1101016,"大学英语(四)","英语");
		Course c7 = new Course(1101011,"大学英语(二)","英语");
		Course c8 = new Course(1101012,"体育(一)","体育");
		Course c9 = new Course(1101017,"体育(二)","体育");
		Course c10 = new Course(1101018,"体育(四)","体育");
		Course c11 = new Course(1101019,"体育(五)","体育");
		
		try{
			session.beginTransaction();
			//缓存数据
			
			session.save(c1);
			session.save(c2);
			session.save(c3);
			session.save(c4);
			session.save(c5);
			session.save(c6);
			session.save(c7);
			session.save(c8);
			session.save(c9);
			session.save(c10);
			session.save(c11);
			

			//提交事务
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			//session回滚
			if(session!=null)
				session.getTransaction().rollback();
		}
	}
	
	@Test
	public void addAdmin(){
		Admin admin1 = new Admin(1001,"admin_1","123456");
		Admin admin2 = new Admin(1002,"admin_2","123456");
		Admin admin3 = new Admin(1003,"admin_3","123456");
		Admin admin4 = new Admin(1004,"admin_4","123456");
		try{
			//开启事务
			session.beginTransaction();
			//缓存数据
			session.save(admin1);
			session.save(admin2);
			session.save(admin3);
			session.save(admin4);
			//提交事务
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			//session回滚
			if(session!=null)
				session.getTransaction().rollback();
		}
	}
	
	@Test
	public void fund(){
		try{
//			
			String hql ="from Student as stu where stu.id=:flagId";
			//String hql ="from Course as cour where cour.id=:flagId";
			//创建query查询对象
			Query query = session.createQuery(hql);
			//设置查询关键字
			query.setInteger("flagId", 20134654);
			
			//接收查询结果
			List<Student> list = query.list();
			Iterator iter = list.iterator();
			//迭代输出
			if(iter.hasNext()){
				System.out.println(iter.next());
			}
			
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}
	
	
	
	public Student query(int id) {
		Student stu = null;
		
		try{
//			
			String hql ="from Student as stu where stu.id=:flagId";
			//创建query查询对象
			Query query = session.createQuery(hql);
			//设置查询关键字
			query.setInteger("flagId", id);
			
			//接收查询结果
			List<Student> list = query.list();
			Iterator iter = list.iterator();
			//迭代输出
			if(iter.hasNext()){
				stu = (Student)iter.next();
			}
			
			System.out.println(stu);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		
		return stu;
	}
	
	@Test
	public void traverseStu(){
		String hql ="from Student as stu";
		Query query = session.createQuery(hql);
		//接收查询结果
		List<Student> list = query.list();
		Iterator iter = list.iterator();
		//迭代输出
		while(iter.hasNext()){
			System.out.println((Student)iter.next());
		}
	}
	
	@Test
	public void traverseCours(){
		String hql ="from Course as cours";
		Query query = session.createQuery(hql);
		//接收查询结果
		List<Course> list = query.list();
		Iterator iter = list.iterator();
		//迭代输出
		while(iter.hasNext()){
			System.out.println((Course)iter.next());
		}
	}
	
	@Test
	public void update(){
		
		//Student stu = query(20134654);
		Student stu = new Student(20134646,"陈腾扬","654321","软件学院",2013);
		stu.setAcademy("文法学院");
		
		Course c1 = new Course(1101005,"数值分析","数学");
		Course c2 = new Course(1101006,"线性代数","数学");
		Course c3 = new Course(1101007,"大学口语（三）","英语");
		Course c4 = new Course(1101008,"概率论与数理统计","数学");
		Set<Course> cours = new HashSet<Course>();
		cours.add(c1);
		cours.add(c2);
		cours.add(c3);
		cours.add(c4);
		
		stu.setCourses(cours);
		try{
//			//获取session
//			session = SessionUtils.getSession();
			//开启事务、获取处理对象
			Transaction ts = session.beginTransaction();
			
			//先保存课程信息
//			session.save(c1);
//			session.save(c2);
//			session.save(c3);
//			session.save(c4);
//			
			//更新学生信息
			session.update(stu);
			//事务提交
			ts.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void delete(){
		Course cour = new Course(1101003,"大学英语(一)","英语");
		Student stu = new Student(20134646,"陈腾扬","123456","软件学院",1302);
		try{
//			//获取session
//			session = SessionUtils.getSession();
			//开启事务、获取处理对象
			Transaction ts = session.beginTransaction();
			session.delete(cour);
			//事务提交
			ts.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void close(){
		SessionUtils.closeSession(session);
	}
};
