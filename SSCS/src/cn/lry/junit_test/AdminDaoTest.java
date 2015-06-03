package cn.lry.junit_test;

import org.junit.Test;

import cn.lry.dao.AdminDao;
import cn.lry.dao.factory.AdminDaoFactory;
import cn.lry.domains.Admin;

public class AdminDaoTest {

	@Test 
	public void fund(){
		AdminDao admindao = AdminDaoFactory.getInstance();
		System.out.println(admindao.find(1001));
		System.out.println(admindao.find(1002));
		System.out.println(admindao.find(1003));
		System.out.println(admindao.find(1004));
		
	}
	
	@Test
	public void add(){
		Admin admin1 = new Admin(1001,"admin_1","123456");
		Admin admin2 = new Admin(1002,"admin_2","123456");
		Admin admin3 = new Admin(1003,"admin_3","123456");
		Admin admin4 = new Admin(1004,"admin_4","123456");
		
		AdminDao admindao = AdminDaoFactory.getInstance();
		admindao.add(admin1);
		admindao.add(admin2);
		admindao.add(admin3);
		admindao.add(admin4);
	}
};
