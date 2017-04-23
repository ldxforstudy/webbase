package com.zls.test.util;

import java.util.Arrays;

import org.junit.Test;

public class Strings {

	@Test
	public void testSplit(){
		String str = "1,2,,";
		System.out.println(Arrays.asList(str.split(",")));
	}
}
