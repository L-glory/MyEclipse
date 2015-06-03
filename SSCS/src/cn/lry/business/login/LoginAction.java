package cn.lry.business.login;

import cn.lry.dao.proxy.DaoProxy;
import cn.lry.domains.Student;
import cn.lry.utils.EncryptUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户id
	private int id;
	//密码
	private String pwd;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	private boolean verifyId(int id){
		boolean flag = true;
		if(id == 0 ||  new Integer(id) == null){
			flag = false;
			//匹配：20** ****
		}else if(!Integer.toString(id).matches("^[2]{1}[0]{1}\\d{6}$")){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 密码校验
	 * @param pwd
	 * @return
	 */
	private boolean verifyPwd(String pwd){
		boolean flag = true;
		//密码校验
		if(this.pwd == null || "".equals(this.pwd.trim())){
			flag = false;
			//匹配6-12位字母/数字/下划线组合
		}else if(!this.pwd.trim().matches("^[a-zA-Z0-9_]{6,12}$")){
			flag = false;
		}
		return flag;
	}
	
	public String login() throws Exception {
		
		System.out.println("id = "+this.id);
		System.out.println("pwd = "+this.pwd);
		
		//action返回视图类型定义
		String view = "err";
		Student stu = null;
		
		//校验id,密码格式是否正确
		if(this.verifyId(this.id) && this.verifyPwd(this.pwd)){
			stu = DaoProxy.finddStu(this.id);
			//校验id是否存在对应实体，若存在，校验密码是否正确
			
			System.out.println("实体密码："+stu.getPwd());
			System.out.println("登陆密码："+EncryptUtils.getMD5Algo(this.pwd));
			
			if(stu !=null && 
					stu.getPwd().equals(EncryptUtils.getMD5Algo(this.pwd))){
				//校验成功，设置视图
				view = "stu";
			}
		}
		
		if("stu".equals(view)){
			//设置登陆对象，分发给jsp调用
			ActionContext.getContext().put("stu",stu);
		}else{
			ActionContext.getContext().put("err","ID："+this.id+"\tpassword："+this.pwd);
		}
		
		return view;
	}
};













