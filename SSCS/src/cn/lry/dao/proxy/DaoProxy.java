package cn.lry.dao.proxy;

import java.util.Set;

import cn.lry.dao.AdminDao;
import cn.lry.dao.CourseDao;
import cn.lry.dao.StuDao;
import cn.lry.dao.factory.AdminDaoFactory;
import cn.lry.dao.factory.CourseDaoFactory;
import cn.lry.dao.factory.StuDaoFactory;
import cn.lry.domains.Admin;
import cn.lry.domains.Course;
import cn.lry.domains.Student;

/**
 * @function dao操作代理，统一studao、courdao、admindao操作
 * @author Glory
 *
 */
public class DaoProxy {

	/**
	 * @statement  重写 方法
	 * @function   添加学生
	 * @param stu
	 *             被添加的学生对象实例
	 * @return     若果操作成功，返回true，否则返回false
	 */
	public static boolean add(Student stu){
		return StuDaoFactory.getInstance().add(stu);
	}
	
	/**
	 * @statement  重写方法
	 * @function   添加课程
	 * @param cour
	 *             被添加的课程对象实例
	 * @return     如果操作失败，返回false ，否则返回true
	 */
	public static boolean add(Course cour){
		return CourseDaoFactory.getInstance().add(cour);
	}
	
	/**
	 * @function    添加学生/课程集合
	 * @param objSet
	 *              要添加对象集合
	 * @param objType
	 *              要添加的对象类型
	 * @return      如果操作失败，返回false，否则返回true
	 */
	public static <T> boolean add(Set<T> objSet, Class<T> objType){
		//ClassType name
		String objName = objType.getSimpleName();
	
		if("Student".equals(objName)){
			for(T  stu: objSet){
				if(!StuDaoFactory.getInstance().add((Student)stu)){
					return false;
				}
			}
		}else if("Course".equals(objName)){
			for(T  cour: objSet){
				if(!CourseDaoFactory.getInstance().add((Course)cour)){
					return false;
				}
			}
		}
		return true;	
	}
	
	/**
	 * @function 查询学生信息		
	 * @param id
	 * 			   学生学号
	 * @return   如果查询到相关记录，返回学生实例，否则返回null
	 */
	public static Student finddStu(int id){
		return StuDaoFactory.getInstance().find(id);
	}
	
	/**
	 * @function 查询课程信息
	 * @param id
	 * 			 课程编号
	 * @return  如果查询到相关记录，返回课程实例，否则返回null
	 */
	public static Course findCour(int id){
		return CourseDaoFactory.getInstance().find(id);
	}
	
	/**
	 * @function 查询管理员信息
	 * @param id
	 * 			 管理员编号
	 * @return  如果查询到相关记录，返回管理员实例，否则返回null
	 */
	public static Admin findAdm(int id){
		return AdminDaoFactory.getInstance().find(id);
	}
	
	/**
	 * @function 根据传递的id号和实体类型查询学生/管理员/课程信息
	 * @param id
	 * 			   编号
	 * @param findClass
	 *           实体类型
	 * @return   根据传递的entityClass类型不一样，
	 *           如果查询到相关记录，返回对应实例，否则返回null
	 */
	public static <T> T find(int id, Class<T> entityClass){
	
		T result = null;
		//ClassType name
		String classType = entityClass.getSimpleName();
		
		if(classType.equals("Student")){
			result = (T) StuDaoFactory.getInstance().find(id);
		}else if(classType.equals("Student")){
			result = (T) CourseDaoFactory.getInstance().find(id);
		}else if(classType.equals("Admin")){
			result = (T) AdminDaoFactory.getInstance().find(id);
		}
		return result;
	}
	
	/**
	 * @statement 重写方法，
	 * @function 根据传递参数  进行更新对象在数据库信息操作
	 * @param stu/cour
	 *           更新对象 
	 * @return   如果更新成功，返回true；如果更新失败，返回false
	 */
	public static boolean update(Student stu){
		return StuDaoFactory.getInstance().update(stu);
	}
	public static boolean update(Course cour){
		return CourseDaoFactory.getInstance().update(cour);
	}
	
	/**
	 * @function 选课操作 
	 * @param stu
	 *           选课的学生对象实体
	 * @param coursSet
	 *           该学生所选的课程集合
	 * @return   操作失败，返回false，否则返回true
	 */
	public static boolean selectCours(Student stu, Set<Course> coursSet){
		//courdao：course Dao操作
		CourseDao courdao = CourseDaoFactory.getInstance();
		
		//遍历传递的课程集合，为每一个课程添加学生实体，然后更新该课程
		for(Course c: coursSet){
			c.setStus(stu);
			if(courdao.update(c)){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * @statement  重写方法
	 * @function   删除学生
	 * @param stu
	 *             被删除学生对象实例
	 * @return     如果操作失败，返回false，否则返回true
	 */
	public static boolean delete(Student stu){
		//courdao：course Dao操作
		CourseDao courdao = CourseDaoFactory.getInstance();
		
		//遍历该学生的选课集合，并从每个课程中移除该学生记录,然后更新课程
		Set<Course> cours = stu.getCourses();
		for(Course c: cours){
			if(!c.getStus().remove(stu) || !courdao.update(c)){
				return false;
			}
		}
		//删除学生
		if(!StuDaoFactory.getInstance().delete(stu)){
			return false;
		}
		return true;
	}
	
	/**
	 * @statement  重写方法
	 * function    删除课程
	 * @param cour
	 *             被删除课程对象实例
	 * @return     如果操作失败，返回false，否则返回true
	 */
	public static boolean delete(Course cour){
		return CourseDaoFactory.getInstance().delete(cour);
	}
	
	/**
	 * @function    移除指定学生的某一个选课记录
	 * @param stu
	 *              移除选课的学生对象实例
	 * @param cour
	 *              被移除的课程对象实例
	 * @return      如果操作失败，返回false，否则返回true
	 */
	public static boolean removeCour(Student stu, Course cour){
		//移除该课程的该学生记录，然后更新课程
		if(!cour.getStus().remove(stu) || 
				!CourseDaoFactory.getInstance().update(cour)){
			return false;
		}
		return true;
	}
	
	/**
	 * @function 移除学生选课记录
	 * @param stu
	 *           需要移除选课的学生对象实例
	 * @param coursSet
	 *           被移除的选课集合
	 * @return   如果操作失败，返回false，否则返回true
	 */
	public static boolean removeCours(Student stu, Set<Course> coursSet){
		//courdao：course Dao操作
		CourseDao courdao = CourseDaoFactory.getInstance();
		
		//遍历该学生的选课集合，并从每个课程中移除该学生记录,然后更新课程
		Set<Course> cours = stu.getCourses();
		for(Course c: cours){
			if(!c.getStus().remove(stu) || !courdao.update(c)){
				return false;
			}
		}
		return true;
	}
};
