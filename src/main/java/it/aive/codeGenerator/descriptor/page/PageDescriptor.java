/*
 * Created on 10-ott-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package it.aive.codeGenerator.descriptor.page;

import it.aive.codeGenerator.descriptor.Descriptor;
import it.aive.codeGenerator.descriptor.java.ClassDescriptor;
import it.aive.codeGenerator.template.FileTemplate;

import java.util.ArrayList;

/**
 * @author root
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PageDescriptor  extends Descriptor {
	private FileTemplate pageTemplate;
	private FileTemplate backingBeanTemplate;
    private String moduleName;
	private String packagePrefix;
	private String name;
	private String title;
	private ClassDescriptor bean;
	private String beanScope;
	
	private String beanName;
	
	
	public String getBeanScope() {
		return beanScope;
	}
	public void setBeanScope(String beanScope) {
		this.beanScope = beanScope;
	}

	

	
	public ClassDescriptor createBackingBeanDescriptor()throws Exception{
		 
		 bean = new ClassDescriptor();
		 bean.setPackageName(getPackagePrefix());
		 bean.setName(getName()+"BackingBean");
		 bean.setExtendClass("BaseBackingBean");
		 setBeanName(getModuleName()+"_"+getName());
		 setBeanScope("request");
		 return bean;
	}
	
	public FileTemplate getBackingBeanTemplate() {
		return backingBeanTemplate;
	}
	public void setBackingBeanTemplate(FileTemplate backingBeanTemplate) {
		this.backingBeanTemplate = backingBeanTemplate;
	}
	public String getPackagePrefix() {
		return packagePrefix;
	}
	public void setPackagePrefix(String packagePrefix) {
		this.packagePrefix = packagePrefix;
	}
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public FileTemplate getPageTemplate() {
		return pageTemplate;
	}

	public void setPageTemplate(FileTemplate pageTemplate) {
		this.pageTemplate = pageTemplate;
	}
	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public ClassDescriptor getBean() throws Exception {
		if (bean==null) {
			bean=createBackingBeanDescriptor();
		}
		return bean;
	}

	public void setBean(ClassDescriptor bean) {
		this.bean = bean;
	}

	private ArrayList widgets = new ArrayList();
	
	public void addWidget(Widget value) {
		widgets.add(value);
	  }

	  public ArrayList getWidgets() {
		return widgets;
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
	
	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	

	/**
	 * @param list
	 */
	public void setWidgets(ArrayList list) {
		widgets = list;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

}
