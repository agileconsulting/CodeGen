/*
 * Created on 10-ott-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package it.aive.codeGenerator.descriptor.page;

import it.aive.codeGenerator.descriptor.Descriptor;
import it.aive.codeGenerator.descriptor.java.AttributeDescriptor;

/**
 * @author root
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Widget extends Descriptor {
 String	type;  
 String name;  
 String value;
 String classe;
 String id;
 String style;
AttributeDescriptor boundAttribute;


/**
 * @return the boundAttribute
 */
public AttributeDescriptor getBoundAttribute() {
	return boundAttribute;
}

/**
 * @param boundAttribute the boundAttribute to set
 */
public void setBoundAttribute(AttributeDescriptor boundAttribute) {
	this.boundAttribute = boundAttribute;
}

public String getClasse() {
	return classe;
}

public void setClasse(String classe) {
	this.classe = classe;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getStyle() {
	return style;
}

public void setStyle(String style) {
	this.style = style;
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}

/**
 * @return
 */
public String getName() {
	return name;
}

/**
 * @return
 */
public String getType() {
	return type;
}


/**
 * @param string
 */
public void setName(String string) {
	name = string;
}

/**
 * @param string
 */
public void setType(String string) {
	type = string;
}



}
