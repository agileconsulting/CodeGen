package it.aive.codeGenerator.descriptor.page;

import it.aive.codeGenerator.descriptor.java.ClassDescriptor;
import it.aive.codeGenerator.template.FileTemplate;

import java.util.ArrayList;
import java.util.List;

public class ListInputPageDescriptor extends PageDescriptor {
FileTemplate listTemplate;
FileTemplate inputTemplate;
ClassDescriptor domainObject;


/**
 * @return the domainObject
 */
public ClassDescriptor getDomainObject() {
	return domainObject;
}
/**
 * @param domainObject the domainObject to set
 */
public void setDomainObject(ClassDescriptor domainObject) {
	this.domainObject = domainObject;
}
public ClassDescriptor createBackingBeanDescriptor()throws Exception{
	 
	super.createBackingBeanDescriptor();
	ArrayList att = new ArrayList();
	for (int i = 0; i < getDomainObject().getAttributes().size(); i++) {
		att.add(getDomainObject().getAttributes().get(i));
	}
	getBean().setAttributes(att);
	return getBean();   
}
/**
 * @return the domainObjectPackage
 */


public FileTemplate getListTemplate() {
	return listTemplate;
}
public void setListTemplate(FileTemplate listTemplate) {
	this.listTemplate = listTemplate;
}
public FileTemplate getInputTemplate() {
	return inputTemplate;
}
public void setInputTemplate(FileTemplate inputTemplate) {
	this.inputTemplate = inputTemplate;
}

public void createPage() throws Exception{
	getBackingBeanTemplate().generateCode();
	getInputTemplate().generateCode();
	getListTemplate().generateCode();
}
}
