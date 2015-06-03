package cn.lry.dao;

import cn.lry.domains.Admin;

public interface AdminDao {

	public abstract boolean add(Admin admin);
	public abstract Admin find(int id);

}