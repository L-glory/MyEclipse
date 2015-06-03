package cn.lry.dao.factory;

import cn.lry.dao.StuDao;
import cn.lry.dao.impl.StuDaoImpl;

public class StuDaoFactory {

	private static StuDao studao = null;
	private StuDaoFactory(){}
	
	public static StuDao getInstance(){
		if(studao == null){
			studao = new StuDaoImpl();
		}
		return studao;
	}
};
