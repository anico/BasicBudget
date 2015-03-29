package com.anico.xmlutil.util;

import java.io.File;
import java.util.List;

import com.anico.xmlutil.model.IXMLAttribute;

public interface IXMLReader {

	public List<IXMLAttribute> readFile(String path);
	
	public List<IXMLAttribute> readFile(File file);
}
