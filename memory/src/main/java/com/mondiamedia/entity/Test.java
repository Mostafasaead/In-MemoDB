package com.mondiamedia.entity;

import java.util.ArrayList;
import java.util.List;

public class Test {
//int x;
//boolean y;
//String z;
	public static void main(String[] args) {
		 List<Integer> heights = new ArrayList<>();
		 heights.add(null);
		  Integer h = heights.get(0); 
		  System.err.println(h);
		  }         // NullPointerException}
	 public static void main(String arg1) {
	        System.out.println("main(String arg1)");
	    }

	    public static void main(String arg1, String arg2) {
	        System.out.println("main(String arg1, String arg2)");
	    }

}
	