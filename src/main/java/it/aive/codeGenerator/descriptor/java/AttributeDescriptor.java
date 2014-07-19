package it.aive.codeGenerator.descriptor.java;

import it.aive.codeGenerator.descriptor.Descriptor;


public class AttributeDescriptor extends Descriptor {
   String attType;
   String attName;
   String length;
   boolean mandatory;
   String scope="private";
/**
 * @return
 */
public String getAttName() {
	return attName;
}

/** 
 * @return
 */
public String getScope() {
	return scope;
}

/**
 * @return
 */
public String getAttType() {
	return attType;
}

/**
 * @param string
 */
public void setAttName(String string) {
	attName = string;
}

/**
 * @param string
 */
public void setScope(String string) {
	scope = string;
}

/**
 * @param string
 */
public void setAttType(String string) {
	attType = string;
	
}

/**
 * @return
 */


} 