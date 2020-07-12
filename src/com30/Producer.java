package com30;

import java.io.BufferedReader;
import java.io.FileReader;

@SuppressWarnings("unchecked")
public class Producer<S> extends Thread{
	private String fileName;
	private Resource<S> re;
	
	public Producer(String fileName, Resource<S> re){
		this.fileName = fileName;
		this.re = re;
	}
	
	public void run(){
		//criar um stream para ler o arquivo
		try {
	        BufferedReader in = new BufferedReader(new FileReader(fileName));
	        String str = null;	        
	        
	        //ler o arquivo
	        while ((str = in.readLine()) != null) {
	        	//temos uma linha do arquivo
	        	re.putRegister((S) str);	        	
	        }
	        
	        in.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	System.out.println("no connection");
	    } 
	}

}
