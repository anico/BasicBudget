package com.anico.xmlutil.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.anico.xmlutil.model.IXMLAttribute;
import com.anico.xmlutil.model.XMLAttribute;

public class XMLReader implements IXMLReader{

	private Scanner in;
	private List<IXMLAttribute> attributes;
	String xml = "";
	
	public XMLReader(){
		attributes = new ArrayList<IXMLAttribute>();
	}

	public List<IXMLAttribute> readFile(String path){
		
		setScanner(path);
		return readAttributes();
	}
	
	public List<IXMLAttribute> readFile(File file){
		setScanner(file);
		return readAttributes();
	}

	private List<IXMLAttribute> readAttributes(){
		
		xml = "";
		while(in.hasNextLine()){
			xml += in.nextLine();
		}
		in.close();
		
		if(!verifyXML(xml)){
			return null;
		}
		
		attributes.clear();
		in = new Scanner(xml);

		int startIndex = 0;
		while(startIndex >= 0){
			
			attributes.add(readAttribute());
			startIndex = xml.indexOf("<");
		}
		
		return attributes;
	}
	
	private XMLAttribute readAttribute(){
		
		XMLAttribute ret = new XMLAttribute();
		
		int startIndex = xml.indexOf("<");
		int endIndex = xml.indexOf(">");
		
		ret.setName(xml.substring(startIndex+1, endIndex));
		
		xml = xml.substring(endIndex+1);
		
		startIndex = xml.indexOf("<");
		endIndex = xml.indexOf("</");
		
		if(startIndex == endIndex){
			ret.setValue(xml.substring(0, endIndex));
			xml = xml.substring(xml.indexOf(">",endIndex)+1);
		}else{
			while(xml.indexOf("</") != 0){
				ret.getSubTags().add(readAttribute());
			}
			xml = xml.substring(xml.indexOf(">")+1);
		}
		
		return ret;
	}
	
	private boolean verifyXML(String xml){
		
		int startTags = 0;
		int endTags = 0;
		int index = 0;
		
		while(index >= 0){
			index = xml.indexOf("<", index+1);
			if(index >= 0){
				startTags +=1;
			}
		}
		index = 0;
		while(index >=0){
			index = xml.indexOf("</", index+1);
			if(index >= 0){
				endTags += 1;
			}
		}
		
		if(startTags == (endTags*2 - 1)){
			return true;
		}else{
			System.err.println("Invalid XML format, # of start tags != # of end tags");
			return false;
		}
		
	}
	
	private void setScanner(String path) {
		
		if(in != null){
			in.close();
		}
		File file = new File(path);
		try {
			this.in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void setScanner(File file){
		
		if(in != null){
			in.close();
		}
		try {
			this.in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
