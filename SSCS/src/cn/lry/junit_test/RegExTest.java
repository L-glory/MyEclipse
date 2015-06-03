package cn.lry.junit_test;

import org.junit.Test;

public class RegExTest {

	@Test
	public void idTest(){
		String id = "20134654";
		if(id.matches("^[2]{1}[0]{1}\\d{6}$")){
			System.out.println("∆•≈‰");
		}else{
			System.out.println("≤ª∆•≈‰");
		}
	}
	
	@Test
	public void pwdTest(){
		String pwd = "_asc13_4sds";
		if(pwd.matches("^[a-zA-Z0-9_]{6,12}$")){
			System.out.println("∆•≈‰");
		}else{
			System.out.println("≤ª∆•≈‰");
		}
	}
	
};

