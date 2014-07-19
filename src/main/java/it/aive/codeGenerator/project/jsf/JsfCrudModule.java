package it.aive.codeGenerator.project.jsf;

import it.aive.codeGenerator.descriptor.java.AttributeDescriptor;
import it.aive.codeGenerator.descriptor.java.ClassDescriptor;
import it.aive.codeGenerator.descriptor.page.Calendar;
import it.aive.codeGenerator.descriptor.page.Column;
import it.aive.codeGenerator.descriptor.page.ListInputPageDescriptor;
import it.aive.codeGenerator.descriptor.page.TextBox;
import it.aive.codeGenerator.descriptor.page.Widget;
import it.aive.codeGenerator.project.Module;
import it.aive.codeGenerator.template.FileTemplate;
import it.aive.utility.FileUtil;
import it.aive.utility.StringUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class JsfCrudModule extends Module {
 //private List tableNames;
 private List domainObjectList;
 
 private List domainObjectDescriptorList = new ArrayList();
 private List pageList= new ArrayList();



 
 



public List getDomainObjectDescriptorList() {
	return domainObjectDescriptorList;
}

public void setDomainObjectDescriptorList(List domainObjectDescriptorList) {
	this.domainObjectDescriptorList = domainObjectDescriptorList;
}

public List getPageList() {
	return pageList;
}

public void setPageList(List pageList) {
	this.pageList = pageList;
}

public List getDomainObjectList() {
		return domainObjectList;
	}

	public void setDomainObjectList(List domainObjectList) {
		this.domainObjectList = domainObjectList;
	}
 






 


private ListInputPageDescriptor createPageDescriptor( ClassDescriptor classe )throws Exception{
	ListInputPageDescriptor	retVal =new ListInputPageDescriptor();
	retVal.setName(classe.getName());
	retVal.setModuleName(getName());
	retVal.setPackagePrefix(getPackageName());
	 
  	
	
	retVal.setDomainObject(classe);
	
    FileTemplate listPageTemplate =  new FileTemplate();
    listPageTemplate.setTemplateFile("template\\JSFCrudListPage.vm");
    listPageTemplate.setDestinationDirectory(getViewDir());
    listPageTemplate.setDestinationFileName("List"+classe.getName()+".xhtml");
    listPageTemplate.getParametrs().put("page", retVal);
    
    FileTemplate insertUpdateDeleteTemplate=new FileTemplate();
    insertUpdateDeleteTemplate.setTemplateFile("template\\JSFCrudInputPage.vm");
    insertUpdateDeleteTemplate.setDestinationDirectory(getViewDir());
    insertUpdateDeleteTemplate.setDestinationFileName("InsertUpdateDelete"+classe.getName()+".xhtml");
    insertUpdateDeleteTemplate.getParametrs().put("page", retVal);
    
    
     FileTemplate beanTemplate = new FileTemplate();
     beanTemplate.setTemplateFile("template\\JSFCrudBackingBean.vm");
	 beanTemplate.setDestinationDirectory(getSourceDir());
	 beanTemplate.setDestinationFileName(classe.getName()+"BackingBean.java");
	 beanTemplate.getParametrs().put("page", retVal);
	 retVal.getBean().setExtendClass("DataTableBackingBean");
	 
	 
	 
	 retVal.setInputTemplate(insertUpdateDeleteTemplate);
	 retVal.setListTemplate(listPageTemplate);
	 retVal.setBackingBeanTemplate(beanTemplate);
	
	
	 
	 return retVal;
 }


private ClassDescriptor findClass(String className) throws Exception{
	ClassDescriptor classe =new ClassDescriptor();
	classe.setPackageName(Class.forName(className).getPackage().getName());
	classe.setName(Class.forName(className).getSimpleName());
	Field[] campi=Class.forName(className).getDeclaredFields();
	ArrayList listaCampi=new ArrayList();
	for (int i = 0; i < campi.length; i++) {
		AttributeDescriptor attDesc= new AttributeDescriptor();
		attDesc.setAttName(campi[i].getName());
		
		String type=campi[i].getType().toString();
		int index=type.indexOf("class");
		if (index==-1) {
			index=type.indexOf("interface");
			index=index+9;
		} else {
			index=index+5;
		}
		String tmp=type.substring(index, type.length()).trim();
		attDesc.setAttType(tmp);
		if (isGood(tmp)) {
			listaCampi.add(attDesc);
		}else {
			System.out.println("Excluded class "+tmp );
		}
		
	}
	classe.setAttributes(listaCampi);
return classe;
}

public   boolean isGood(String className)throws Exception{
	return  className.endsWith("Date")|| className.endsWith("BigDecimal")||  className.endsWith("String") ||Class.forName(className).isPrimitive();
}

public void init() throws Exception{
	
	 super.init();
	
	 for (Iterator iterator = getDomainObjectList().iterator(); iterator.hasNext();) {
		
		String className = (String) iterator.next();
		 		
	     
		// Trovo il descrittore di tabella e aggiungo alla lista
		 ClassDescriptor classDescription= findClass(className);
		 getDomainObjectDescriptorList().add(classDescription);
		 	
			 
		 getPageList().add(createPageDescriptor(classDescription));
		
		
		
	 }
	 
	 
	 
	
 }
 


public void generateCode() throws  Exception{
	
	
	
	
   for (Iterator iterator = getPageList().iterator(); iterator.hasNext();) {
		 
	   ListInputPageDescriptor page = (ListInputPageDescriptor) iterator.next();
		  
	   page.createPage();
	     
		 
	}
    
    // Creo il faces Config
	createFacesConfig();
	createModuleConfig();
	createBusinessService();
   

}
private void createModuleConfig()throws Exception{
	 System.out.println("creating module config for "+getName());
	 FileTemplate moduleConfig  =  new FileTemplate ();
	 
	 String tmp=getProject().getPackagePrefix();
	 tmp=StringUtil.substitute(".", "\\", tmp);
	 String moduleConfigDir=getProject().getConfigDir()+ "\\"+tmp+"\\"+ getName();
	 moduleConfig.setTemplateFile("template\\JSFCrudModuleConfig.vm");
	 moduleConfig.setDestinationDirectory(moduleConfigDir);
	 moduleConfig.setDestinationFileName(getName()+".xml");
	 moduleConfig.getParametrs().put("moduleName", getName());
	 moduleConfig.getParametrs().put("packageName", getPackageName());
	 moduleConfig.generateCode();
	
	 
	 
    // lo aggiungo al  WEB.XML cercando il marker
	String springConf =FileUtil.readFile(getProject().getConfigDir()+"mainSpring.xml", FileUtil.FILE_ON_FILESYSTEM);
    String marker= "</beans>";
    tmp=getPackageName();
    tmp=StringUtil.substitute(".", "/", tmp);
    String nuovoConfig="<import resource=\""+tmp+"/"+getName()+".xml"+ "\"/>\n"+marker;
    if (springConf.indexOf(nuovoConfig)==-1) {
    	springConf=StringUtil.substitute(marker, nuovoConfig, springConf);
	}
   
    FileUtil.writeFile(getProject().getConfigDir()+"mainSpring.xml", springConf.getBytes(), FileUtil.FILE_ON_FILESYSTEM);

   
   
    
    
}

private void createBusinessService() throws Exception{
	 System.out.println("creating Business Servixe  for "+getName());
	 FileTemplate businessServiceTemplate  =  new FileTemplate ();
	 businessServiceTemplate.setTemplateFile("template\\JSFCrudInterface.vm");
	 businessServiceTemplate.setDestinationDirectory(getSourceDir());
	 businessServiceTemplate.setDestinationFileName(getName()+"BusinessService.java");
	 businessServiceTemplate.getParametrs().put("moduleName", getName());
	 businessServiceTemplate.getParametrs().put("packageName", getPackageName());
	 businessServiceTemplate.generateCode();
	
	 businessServiceTemplate.setTemplateFile("template\\JSFCrudInterfaceImpl.vm");
	 
	 businessServiceTemplate.setDestinationFileName(getName()+"BusinessServiceImpl.java");
	 businessServiceTemplate.getParametrs().put("moduleName", getName());
	 businessServiceTemplate.getParametrs().put("packageName", getPackageName());
	 businessServiceTemplate.generateCode();
	 
}
 private void createFacesConfig()throws Exception{
	 System.out.println("creating faces config for "+getName());
	 FileTemplate facesConfig = (FileTemplate)getConfigFileTemplate();
	 String webXMLDir=getProject().getViewDir()+"WEB-INF" +File.separator;
	 String facesConfigDir=	webXMLDir+"faces-config"+File.separator;
	 String facesConfigFileName=getName()+"-faces-config.xml";
	 // Genero il file config
	 facesConfig.setDestinationDirectory(facesConfigDir);
	 facesConfig.setDestinationFileName(facesConfigFileName);
	 facesConfig.getParametrs().put("pageList", getPageList());
	 facesConfig.generateCode();
     // lo aggiungo al  WEB.XML cercando il marker
	 String webConf =FileUtil.readFile(webXMLDir+"web.xml", FileUtil.FILE_ON_FILESYSTEM);
     String marker= "<!--JSF-FILE-CONFIG-->";
     String nuovoConfig="\t \t ,/WEB-INF/faces-config/"+facesConfigFileName+"\n"+marker;
     if (webConf.indexOf(nuovoConfig)==-1) {
    	 webConf=StringUtil.substitute(marker, nuovoConfig, webConf);
	}
    
     FileUtil.writeFile(webXMLDir+"web.xml", webConf.getBytes(), FileUtil.FILE_ON_FILESYSTEM);
 }

 






 
	
	










}
