package cn.lry.domains;

public class Admin extends Person {

	/**
	 * @function    无参构造器
	 */
	public Admin() {
		super();
	}
	
	/**
	 * @function    构造器
	 * @param id
	 *              管理员ID
	 * @param name
	 *              管理员姓名
	 * @param pwd
	 *              管理员密码
	 */
	public Admin(int id, String name, String pwd) {
		super(id, name, pwd);
	}
	
	/**
	 * @statement    重写toString()方法
	 * @function     对象信息输出
	 * @return       返回对象实例的基本信息
	 */
	@Override
	public String toString() {
		return "Admin [getId()=" + getId() + ", getName()=" + getName()
				+ ", getPwd()=" + getPwd() + "]";
	}
	
};
