package com.anico.xmlutil.model;

import java.util.List;

public interface IXMLAttribute {

	public String getName();
	
	public void setName(String name);
	
	public String getValue();

	public void setValue(String value);
	
	public List<IXMLAttribute> getSubTags();
	
	public void setSubTags(List<IXMLAttribute> subTags);
}
