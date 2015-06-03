package cn.lry.dao;

import java.util.List;
import java.util.Map;

import cn.lry.domains.Course;

public interface CourseDao {

	/**
	 * 添加课程
	 * @param cour
	 * @return
	 */
	public abstract boolean add(Course cour);

	/**
	 * 查询课程
	 * @param id
	 * @return
	 */
	public abstract Course find(int id);

	/**
	 * @function    查询所有课程信息
	 * @return		如果操作成功，返回课程集合，否则返回空
	 */
	public abstract List<Course> traverse();
	
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
	public List<Course> traverse(int pageNo, int pageSize,Map map);

	public abstract boolean update(Course cour);

	public abstract boolean delete(Course cour);

}