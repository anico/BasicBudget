package com.anico.xmlutil.util;

import java.util.List;

import com.anico.xmlutil.model.IXMLAttribute;

public interface IXMLWriter {

	public void writeAttribute(String name, String value);
	
	public void writeAttribute(IXMLAttribute attribute);
	
	public void writeAttributes(List<IXMLAttribute> attributes);
}
