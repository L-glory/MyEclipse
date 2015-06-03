package cn.lry.dao;

import java.util.List;
import java.util.Map;

import cn.lry.domains.Student;

public interface StuDao {

	/**
	 * 添加新学生
	 * @param stu
	 * @return
	 */
	public abstract boolean add(Student stu);

	/**
	 * 根据学生ID查找学生
	 * @param id
	 * @return
	 */
	public abstract Student find(int id);

	/**
	 * 遍历学生表
	 * @param travType
	 * @return
	 */
	public abstract List<Student> traverse();

	
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
	public List<Student> traverse(int pageNo, int pageSize,Map map);
	
	/**
	 * 更新操作
	 * @param stu
	 * @return
	 */
	//@SuppressWarnings("finally")
	public abstract boolean update(Student stu);

	/**
	 * 删除操作
	 * @param stu
	 * @return
	 */
	//@SuppressWarnings("finally")
	public abstract boolean delete(Student stu);

}