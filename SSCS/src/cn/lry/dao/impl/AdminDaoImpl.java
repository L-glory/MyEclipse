package cn.lry.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import cn.lry.dao.AdminDao;
import cn.lry.dao.utils.SessionUtils;
import cn.lry.domains.Admin;

public class AdminDaoImpl implements AdminDao {

	public AdminDaoImpl(){}
	
	public boolean add(Admin admin){
		Session session = null;
		try{
			//获取session
			session = SessionUtils.getSession();
			//开启事务
			session.beginTransaction();
			//缓存数据
			session.save(admin);
			//提交事务
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
			//session回滚
			if(session!=null)
				session.getTransaction().rollback();
			return false;
		}finally{
			//关闭session
			SessionUtils.closeSession(session);
		}
		
		return true;
	}
	
	public Admin find(int id){
		Session session = null;
		Admin admin = null;
		try{
			//获取session
			session = SessionUtils.getSession();
			//hql语言查询
			String hql ="from Admin as admin where admin.id=:flagId";
			//创建query查询对象
			Query query = session.createQuery(hql);
			//设置查询条件
			query.setInteger("flagId", id);
			
			//接收查询结果
			List<Admin> list = query.list();
			
			Iterator<Admin> iter = list.iterator();
			//迭代输出
			if(iter.hasNext()){
				admin = (Admin)iter.next();
			}
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally{
			//即使return操作，也会执行finally语句
			//关闭session
			SessionUtils.closeSession(session);
		}
		return admin;
	}
};
