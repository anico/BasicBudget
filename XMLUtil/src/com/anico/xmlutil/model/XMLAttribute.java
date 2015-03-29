package com.anico.xmlutil.model;

import java.util.ArrayList;
import java.util.List;

public class XMLAttribute implements IXMLAttribute{
	
	private String name;
	private String value;
	private List<IXMLAttribute> subTags;
	
	public XMLAttribute(){
		subTags = new ArrayList<IXMLAttribute>();
	}
	
	public XMLAttribute(String name){
		this.name = name;
		subTags = new ArrayList<IXMLAttribute>();
	}
	
	public XMLAttribute(String name, String value){
		this.name = name;
		this.value = value;
		subTags = null;
	}
	
	public XMLAttribute(String name, IXMLAttribute subtag){
		this.name = name;
		this.subTags = new ArrayList<IXMLAttribute>();
		subTags.add(subtag);
	}
	
	public XMLAttribute(String name,List<IXMLAttribute> subTags){
		this.name = name;
		this.subTags = subTags;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<IXMLAttribute> getSubTags(){
		return subTags;
	}
	public void setSubTags(List<IXMLAttribute> subTags) {
		this.subTags = subTags;
	}

}
