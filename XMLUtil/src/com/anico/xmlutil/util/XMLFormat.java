package com.anico.xmlutil.util;

import java.io.File;

public class XMLFormat implements IXMLFormat{

	@Override
	public String formatXML(String xml, boolean tabs) {
		
		validateXML(xml);
		
//		int tabCount = 0;
		
		String formattedXML = "";
		String remainingXML = xml;
		
		int openStart = remainingXML.indexOf("<", 0);
		int openEnd;
		
		int closeStart = remainingXML.indexOf("</", openStart);
		int closeEnd;
		
		while((openStart >= 0)&&(closeStart >= 0)){
	
			openEnd = remainingXML.indexOf(">", 0);
			closeEnd = remainingXML.indexOf(">",closeStart);
			
			formattedXML += remainingXML.substring(openStart, openEnd+1);
			remainingXML = remainingXML.substring(openEnd+1);
			
			if(remainingXML.indexOf("<") == (remainingXML.indexOf("</"))){
				 closeStart = remainingXML.indexOf("<\\", openEnd);
				 closeEnd = remainingXML.indexOf(">",closeStart);
				 formattedXML += remainingXML.substring(0, closeEnd+1);
				 formattedXML += "\n";
				 remainingXML = remainingXML.substring(closeEnd+1);
				 openStart = remainingXML.indexOf("<");
				 closeStart = remainingXML.indexOf("</");
			}else{
				formattedXML += "\n";
				String innerXML = formatXML(remainingXML, true);
				formattedXML += innerXML;
				innerXML = innerXML.replaceAll("\n", "");
				remainingXML = remainingXML.substring(innerXML.length());
				openStart = remainingXML.indexOf("<");
				closeStart = remainingXML.indexOf("</");
			}
			
			
		}
		
		
		
		return formattedXML;
	}

	@Override
	public String formatXML(File file, boolean tabs) {
		String xml = "";
		return formatXML(xml,tabs);
	}
	
	private boolean validateXML(String xml){
		
		int index = xml.indexOf("<");
		int startCount = 0;
		int endCount = 0;
		int termCount = 0;
		
		
		while(index >= 0){
			startCount +=1;
			index = xml.indexOf("<",index+1);
		}
		
		index = xml.indexOf("</",0);
		while(index >= 0){
			endCount += 1;
			index = xml.indexOf("</",index+1);
		}
		
		index = xml.indexOf(">",0);
		while(index >=1){
			termCount += 1;
			index = xml.indexOf(">",index+1);
		}
		
		if(startCount != (endCount*2)){
			System.err.println("Invalid xml format, start tag brackets do no equal close tag brackets");
			return false;
		}else if(startCount != termCount){
			System.err.println("Invalid xml format, right brackets do not equal left brackets");
			return false;
		}else if((startCount == 0)||(endCount == 0)||(termCount == 0)){
			System.err.println("Brackets Missing");
			return false;
		}else{
			return true;
		}
	}
}
