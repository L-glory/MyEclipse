package cn.lry.junit_test;

import org.junit.Test;

import cn.lry.domains.Student;
import cn.lry.utils.EncryptUtils;

public class MD5_Test {
	@Test
	public void test(){
		Student s0 = new Student(20134645,"≥¬»Ÿ…≠","»Ìº˛—ß‘∫",1302);
		System.out.println(EncryptUtils.getMD5Algo("fk1SXhKoZr1EmYD089Y+6g=="));
		System.out.println(EncryptUtils.getMD5Algo(new Integer(20134654).toString()));
		//System.out.println(EncryptUtils.getMD5Algo("123456"));
		//System.out.println(s0);K5umdZYLGdk7G8+MPFW17w==
	}
}
