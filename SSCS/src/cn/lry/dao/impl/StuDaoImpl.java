package cn.lry.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.lry.dao.StuDao;
import cn.lry.dao.utils.SessionUtils;
import cn.lry.domains.Student;

public class StuDaoImpl implements StuDao {
	
	//构造
	public  StuDaoImpl(){}
	
	/**
	 * 添加新学生
	 * @param stu
	 * @return
	 */
	public boolean add(Student stu){
		Session session = null;
		try{
			//获取session
			session = SessionUtils.getSession();
			//开启事务
			session.beginTransaction();
			//缓存数据
			session.save(stu);
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
	 * 根据学生ID查找学生
	 * @param id
	 * @return
	 */
	public Student find(int id){
		Session session = null;
		Student stu = null;
		try{
			//获取session
			session = SessionUtils.getSession();
			stu = findModule(session,id);
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally{
			//即使return操作，也会执行finally语句
			//关闭session
			SessionUtils.closeSession(session);
		}
		return stu;
	}
	
	/**
	 * Id查询模板
	 * 便于更新，删除等操作
	 * @param session
	 * @param id
	 * @return
	 */
	private Student findModule(Session session, int id){
		Student stu = null;
		
		//hql语言查询
		String hql ="from Student as stu where stu.id=:flagId";
		//创建query查询对象
		Query query = session.createQuery(hql);
		//设置查询条件
		query.setInteger("flagId", id);
		
		//接收查询结果
		List<Student> list = query.list();
		
		Iterator<Student> iter = list.iterator();
		//迭代输出
		if(iter.hasNext()){
			stu = (Student)iter.next();
		}
		
		//奇怪了，不打印居然测试失败
		System.out.println(stu);
		return stu;
	}
	
	/**
	 * 遍历学生表
	 * @param travType
	 * @return
	 */
	public List<Student> traverse(){
		Session session = null;
		List<Student> stuList = null;
		//创建hql查询语句
		String hql = null;
		
		try{
			//获取session
			session = SessionUtils.getSession();
			
			hql = "from Student as stu";
			Query query = session.createQuery(hql);
			//接收查询结果
			stuList = query.list();
			
			Iterator iter = stuList.iterator();
			while(iter.hasNext()){
				System.out.println((Student)iter.next());
			}
			
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally{
			//即使return操作，也会执行finally语句
			SessionUtils.closeSession(session);
		}
		
		return stuList;
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
	public List<Student> traverse(int pageNo, int pageSize,Map map){
		Session session = null;
		//查询结果集合
		List<Student> stuList = null;
		try
		{
			//获取session
			session = SessionUtils.getSession();
			
			String hql = "from Student as stu";
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

			stuList = query.list();

		} catch (RuntimeException e){
			throw e;
		}finally{
			//即使return操作，也会执行finally语句
			SessionUtils.closeSession(session);
		}
		return stuList;
	}

	/**
	 * 更新操作
	 * @param stu
	 * @return
	 */
	//@SuppressWarnings("finally")
	public boolean update(Student stu){
		Session session = null;
		boolean flag = true;
		try{
			//获取session
			session = SessionUtils.getSession();
			//开启事务、获取处理对象
			Transaction ts = session.beginTransaction();
			session.update(stu);
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
	
	/**
	 * 删除操作
	 * @param stu
	 * @return
	 */
	public boolean delete(Student stu){
		Session session = null;
		boolean flag = true;
		try{
			//获取session
			session = SessionUtils.getSession();
			//开启事务、获取处理对象
			Transaction ts = session.beginTransaction();
			session.delete(stu);
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
