package cn.lry.dao.factory;

import cn.lry.dao.CourseDao;
import cn.lry.dao.impl.CourseDaoImpl;

public class CourseDaoFactory {

	private static CourseDao courdao = null;
	
	//构造私有化，实现CourseDaoImpl单例
	private CourseDaoFactory(){}
	
	public static CourseDao getInstance(){
		if(courdao == null){
			courdao = new CourseDaoImpl();
		}
		return courdao;
	}
	
};
