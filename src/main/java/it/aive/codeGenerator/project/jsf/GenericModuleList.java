package it.aive.codeGenerator.project.jsf;

import it.aive.codeGenerator.project.Module;
import it.aive.codeGenerator.template.FileTemplate;
import it.aive.utility.FileUtil;
import it.aive.utility.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * Genera una lista di moduli
 * 
 * @author mbaraldi
 *
 */
public class GenericModuleList extends Module {
 //private List tableNames;
 private List moduleNameList;



 
 


public List getModuleNameList() {
	return moduleNameList;
}



public void setModuleNameList(List moduleNameList) {
	this.moduleNameList = moduleNameList;
}



public void init() throws Exception{
	
	
	
	
		
		
	
	 
	 
	
 }
 


public void generateCode() throws  Exception{
	
	
	
	
   for (Iterator iterator = getModuleNameList().iterator(); iterator.hasNext();) {
		 
	   String moduleName = (String) iterator.next();
		  
	  
		
	
		if (!StringUtil.isEmptyString(getViewDir())) {
			createDirIfNotExist(getProject().getViewDir()+File.separator+"secure"+File.separator+moduleName);
		}
		createFacesConfig(moduleName);
		createModuleConfig(moduleName);
		createBusinessService(moduleName);
	     
		 
	}
    
    
   

}
private void createModuleConfig(String moduleName)throws Exception{
	 System.out.println("creating module config for "+moduleName);
	 FileTemplate moduleConfig  =  new FileTemplate ();
	 
	 String tmp=getProject().getPackagePrefix();
	 tmp=StringUtil.substitute(".", "\\", tmp);
	 String moduleConfigDir=getProject().getConfigDir()+ "\\"+tmp+"\\"+ moduleName;
	 moduleConfig.setTemplateFile("template\\JSFCrudModuleConfig.vm");
	 moduleConfig.setDestinationDirectory(moduleConfigDir);
	 moduleConfig.setDestinationFileName(moduleName+".xml");
	 moduleConfig.getParametrs().put("moduleName", moduleName);
	 moduleConfig.getParametrs().put("packageName", getProject().getPackagePrefix()+"."+moduleName);
	 moduleConfig.generateCode();
	
	 
	 
    // lo aggiungo al  WEB.XML cercando il marker
	String springConf =FileUtil.readFile(getProject().getConfigDir()+"mainSpring.xml", FileUtil.FILE_ON_FILESYSTEM);
    String marker= "</beans>";
    tmp=getProject().getPackagePrefix()+"."+moduleName;
    tmp=StringUtil.substitute(".", "/", tmp);
    String nuovoConfig="<import resource=\""+tmp+"/"+moduleName+".xml"+ "\"/>\n"+marker;
    if (springConf.indexOf(nuovoConfig)==-1) {
    	springConf=StringUtil.substitute(marker, nuovoConfig, springConf);
	}
   
    FileUtil.writeFile(getProject().getConfigDir()+"mainSpring.xml", springConf.getBytes(), FileUtil.FILE_ON_FILESYSTEM);

   
   
    
    
}

private void createBusinessService(String moduleName) throws Exception{
	 System.out.println("creating Business Servixe  for "+moduleName);
	 FileTemplate businessServiceTemplate  =  new FileTemplate ();
	 businessServiceTemplate.setTemplateFile("template\\JSFCrudInterface.vm");
	
	 String tmp=getProject().getPackagePrefix();
	 tmp=StringUtil.substitute(".", "\\", tmp);
	 String destDir=getProject().getSourceDir()+ "\\"+tmp+"\\"+ moduleName;
	 businessServiceTemplate.setDestinationDirectory(destDir);
	 businessServiceTemplate.setDestinationFileName(moduleName+"BusinessService.java");
	 businessServiceTemplate.getParametrs().put("moduleName", moduleName);
	 businessServiceTemplate.getParametrs().put("packageName", getProject().getPackagePrefix()+"."+moduleName);
	 businessServiceTemplate.generateCode();
	
	 businessServiceTemplate.setTemplateFile("template\\JSFCrudInterfaceImpl.vm");
	 
	 businessServiceTemplate.setDestinationFileName(moduleName+"BusinessServiceImpl.java");
	 businessServiceTemplate.getParametrs().put("moduleName", moduleName);
	 businessServiceTemplate.getParametrs().put("packageName", getProject().getPackagePrefix()+"."+moduleName);
	 businessServiceTemplate.generateCode();
	 
}
 private void createFacesConfig(String moduleName)throws Exception{
	 System.out.println("creating faces config for "+moduleName);
	 FileTemplate facesConfig = (FileTemplate)getConfigFileTemplate();
	 String webXMLDir=getProject().getViewDir()+"WEB-INF" +File.separator;
	 String facesConfigDir=	webXMLDir+"faces-config"+File.separator;
	 String facesConfigFileName=moduleName+"-faces-config.xml";
	 // Genero il file config
	 facesConfig.setDestinationDirectory(facesConfigDir);
	 facesConfig.setDestinationFileName(facesConfigFileName);
	 facesConfig.getParametrs().put("pageList", new ArrayList());
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
