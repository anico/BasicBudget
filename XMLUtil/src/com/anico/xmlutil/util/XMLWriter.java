package com.anico.xmlutil.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.anico.xmlutil.model.IXMLAttribute;
import com.anico.xmlutil.model.XMLAttribute;

public class XMLWriter implements IXMLWriter{

	private File outputFile;
	private FileWriter writer;
	private PrintWriter out;
	
	public XMLWriter(String outputFilePath){
		setOutputFile(outputFilePath);
	}
	
	public XMLWriter(File outputFile){
		setOutputFile(outputFile);
	}
	
	public void writeAttribute(String name, String value){
		
		writeStartTag(name);
		out.print(value);
		writeEndTag(name);
 	}
	
	public void writeAttribute(IXMLAttribute attribute){
		
		writeStartTag(attribute.getName());
		if( (attribute.getSubTags() == null)||(attribute.getSubTags().isEmpty()) ){
			out.print(attribute.getValue());
		}else{
			for(IXMLAttribute attr : attribute.getSubTags()){
				writeAttribute(attr);
			}
		}
		writeEndTag(attribute.getName());
	}
	
	public void writeAttributes(List<IXMLAttribute> attributes){
		
		for(IXMLAttribute attr : attributes){
			writeAttribute(attr);
		}
	}
	
	public FileWriter getWriter(){
		return writer;
	}
	
	public void close(){
		this.out.close();
		try {
			this.writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getOutputFile() {
		return outputFile;
	}
	
	public void setOutputFile(String outputFilePath) {
		
		if(this.writer != null){
			try {
				this.out.close();
				this.writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
		this.outputFile = new File(outputFilePath);
		try {
			this.writer = new FileWriter(this.outputFile);
			this.out = new PrintWriter(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setOutputFile(File outputFile) {
		
		if(this.writer != null){
			try {
				this.out.close();
				this.writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.outputFile = outputFile;
		try {
			this.writer = new FileWriter(this.outputFile);
			this.out = new PrintWriter(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeStartTag(String name){
		out.print("<"+name+">");
	}
	
	private void writeEndTag(String name){
		out.print("</"+name+">");
	}
	
	public static void main(String[] args){
		
		XMLWriter writer = new XMLWriter("C:\\Development\\Files\\test.xml");
		XMLAttribute attr1 = new XMLAttribute("name1","value1");
		XMLAttribute attr2 = new XMLAttribute("name2","value2");
		
		XMLAttribute attr4 = new XMLAttribute("name4","value4");
		XMLAttribute attr5 = new XMLAttribute("name5","value5");
		XMLAttribute attr6 = new XMLAttribute("name6","value6");
		List<IXMLAttribute> lst = new ArrayList<IXMLAttribute>();
		lst.add(attr4);
		lst.add(attr5);
		XMLAttribute attr3 = new XMLAttribute("name3",lst);
		List<IXMLAttribute> attrs = new ArrayList<IXMLAttribute>();
		attrs.add(attr1);
		attrs.add(attr2);
		attrs.add(attr3);
		attrs.add(attr6);
		writer.writeAttributes(attrs);
		writer.close();
		
	}
}
