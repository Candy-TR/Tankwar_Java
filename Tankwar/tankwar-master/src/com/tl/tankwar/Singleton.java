package com.tl.tankwar;

public class Singleton {

    private static Singleton instance;
    private Singleton(){
    	
    }
    public static synchronized Singleton getSingleton(){
    	if(instance==null){
    		instance= new Singleton();
    	}
    	return instance;
    }
    
    public static String info(){
    	return "@By TR and LQC";
    }
}
