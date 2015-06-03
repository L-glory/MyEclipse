package cn.lry.junit_test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import cn.lry.dao.utils.SessionUtils;
import cn.lry.domains.Student;

public class SessionFactoryTest {

	@Test
	public void fund(){
		
		try{
			Session session = SessionUtils.getSessionFactory().getCurrentSession();
			//开启事务
			session.beginTransaction();
			String hql ="from Student as stu where stu.id=:flagId";
			//创建query查询对象
			Query query = session.createQuery(hql);
			//设置查询关键字
			query.setInteger("flagId",20134654);
			//接收查询结果
			List<Student> list = query.list();
			//提交事务
			session.getTransaction().commit();
			
			Iterator iter = list.iterator();
			//迭代输出
			if(iter.hasNext()){
				System.out.println(iter.next());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
};
