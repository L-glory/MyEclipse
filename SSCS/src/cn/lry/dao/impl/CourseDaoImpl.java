package cn.lry.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.lry.dao.CourseDao;
import cn.lry.dao.utils.SessionUtils;
import cn.lry.domains.Course;
import cn.lry.domains.Student;

public class CourseDaoImpl implements CourseDao {

	public CourseDaoImpl(){}
	
	/**
	 * 添加课程
	 * @param cour
	 * @return
	 */
	public boolean add(Course cour){
		Session session = null;
		try{
			//获取session
			session = SessionUtils.getSession();
			//开启事务
			session.beginTransaction();
			//缓存数据
			session.save(cour);
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
	
	/**
	 * 查询课程
	 * @param id
	 * @return
	 */
	public Course find(int id){
		Session session = null;
		Course cour = null;
		try{
			//获取session
			session = SessionUtils.getSession();
			cour = findModule(session,id);
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally{
			//即使return操作，也会执行finally语句
			//关闭session
			SessionUtils.closeSession(session);
		}
		return cour;
	}
	
	/**
	 * 查询核心控制
	 * @param session
	 * @param id
	 * @return
	 */
	private Course findModule(Session session, int id){
		Course cour = null;
		
		//hql语言查询
		String hql ="from Course as cour where cour.id=:flagId";
		//创建query查询对象
		Query query = session.createQuery(hql);
		//设置查询条件
		query.setInteger("flagId", id);
		
		//接收查询结果
		List<Course> list = query.list();
		Iterator<Course> iter = list.iterator();
		//迭代输出
		if(iter.hasNext()){
			cour = iter.next();
		}
		
		return cour;
	}
	
	/**
	 * 课程遍历
	 */
	public List<Course> traverse(){
		Session session = null;
		List<Course> courList = null;
		//创建hql查询语句
		String hql = null;
		
		try{
			//获取session
			session = SessionUtils.getSession();
			
			hql = "from Course as cour";
			Query query = session.createQuery(hql);
			//接收查询结果
			courList = query.list();
			
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally{
			//即使return操作，也会执行finally语句
			SessionUtils.closeSession(session);
		}
		
		return courList;
	}

	/**
	 * @function    分页查询功能实现
	 * @param pageNo
	 *              查询页号   
	 * @param pageSize
	 *              每页查询记录条数
	 * @param map
	 *              查询参数封装
	 * @return      
	 */
	public List<Course> traverse(int pageNo, int pageSize,Map map){
		Session session = null;
		//查询结果集合
		List<Course> courList = null;
		try
		{
			//获取session
			session = SessionUtils.getSession();
			
			String hql = "from Course as cour";
			Query query = session.createQuery(hql);
			
			//设置查询参数  
			//注意先校验map是否为空
			if(map != null){
				Iterator it = map.keySet().iterator();
				while (it.hasNext()){
					Object key = it.next();
					query.setParameter(key.toString(), map.get(key));
				}
			}
			
			//设置查询起始页
			query.setFirstResult((pageNo - 1) * pageSize);
			//设置每页记录条数
			query.setMaxResults(pageSize);

			courList = query.list();

		} catch (RuntimeException e){
			throw e;
		}finally{
			//即使return操作，也会执行finally语句
			SessionUtils.closeSession(session);
		}
		return courList;
	}
	
	/**
	 * 课程更新
	 */
	public boolean update(Course cour){
		Session session = null;
		boolean flag = true;
		try{
			//获取session
			session = SessionUtils.getSession();
			//开启事务、获取处理对象
			Transaction ts = session.beginTransaction();
			session.update(cour);
			//事务提交
			ts.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			flag = false;
		}finally{
			//即使return操作，也会执行finally语句
			SessionUtils.closeSession(session);
		}
		return flag;
	}
	
	//课程删除
	public boolean delete(Course cour){
		Session session = null;
		boolean flag = true;
		try{
			//获取session
			session = SessionUtils.getSession();
			//开启事务、获取处理对象
			Transaction ts = session.beginTransaction();
			session.delete(cour);
			//事务提交
			ts.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			flag = false;
		}finally{
			//即使return操作，也会执行finally语句
			SessionUtils.closeSession(session);
		}
		return flag;
	}
};
