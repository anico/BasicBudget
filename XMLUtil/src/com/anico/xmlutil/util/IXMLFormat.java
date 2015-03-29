package com.anico.xmlutil.util;

import java.io.File;

public interface IXMLFormat {

	public String formatXML(String xml, boolean tabs);
	
	public String formatXML(File file, boolean tabs);
}
