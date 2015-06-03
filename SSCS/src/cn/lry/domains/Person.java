package cn.lry.domains;

import cn.lry.utils.EncryptUtils;

public class Person {

	//用户id
	private int id;
	//用户姓名
	private String name;
	//用户密码
	private String pwd;
	
	public Person(){}
	public Person(int id, String name, String pwd){
		this.id = id;
		this.name = name;
		//MD5算法加密
		this.pwd = EncryptUtils.getMD5Algo(pwd);
	}
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		//MD5算法加密
		this.pwd = EncryptUtils.getMD5Algo(new Integer(id).toString());
	}
	public int getId() {
		return id;
	}
	//注意：不可以更改Id，（主键不允许修改）
	public void setId(int id){
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
};
