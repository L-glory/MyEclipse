package cn.lry.dao.factory;

import cn.lry.dao.AdminDao;
import cn.lry.dao.impl.AdminDaoImpl;

public class AdminDaoFactory {
	
	private static AdminDao admindao = null;
	private AdminDaoFactory(){}
	
	public static AdminDao getInstance(){
		if(admindao == null){
			admindao = new AdminDaoImpl();
		}
		return admindao;
	}
};
