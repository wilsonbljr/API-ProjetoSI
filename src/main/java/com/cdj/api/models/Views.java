package com.cdj.api.models;

public class Views {

	
	public static class External {
		
	}
	
	
	public static class Internal extends External {
		
	}
	
	public static interface Intermediaria1 {
		
	}
	
	public static interface Intermediaria2 {
		
	}
	
	public static class Intermediaria3 implements Intermediaria1, Intermediaria2 {
		
	}
}
