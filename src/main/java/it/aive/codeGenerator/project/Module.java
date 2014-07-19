package it.aive.codeGenerator.project;

import it.aive.codeGenerator.template.GenericTemplate;
import it.aive.codeGenerator.template.TemplateInterface;
import it.aive.utility.StringUtil;

import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


/**
 * @author mbaraldi
 * Un template JSF contiene un insieme di backing bean e
 * un insieme di  jsf che andrà a generare.
 * Contiene ab
 *
 */
public abstract class Module {
	private String name;
	private String description;
	private String shortDescription;
	
	private String sourceDir;
	private String viewDir;
	private String configFileDir;
	
	private String packageName;
		
	private GenericTemplate viewTemplate;
	private GenericTemplate controllerTemplate;
	private GenericTemplate modelTemplate;
	private GenericTemplate configFileTemplate;
	
	
	
    private Project project;
    private Hashtable allModule= new Hashtable();
    public void createDirIfNotExist(String fullPath) throws Exception{
  	  File tmp = new File(fullPath);
  		if (!tmp.exists()) {
  		  boolean  success = (new File(fullPath)).mkdirs();
  		  if (!success) {
  		        throw new Exception("Create File failed");
  		    }
  		}  
    }
    
	public String getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Module(){
	
	}
	
	public abstract void generateCode() throws Exception;
	public void init() throws Exception{
		//inizializzo le directory
		if (!StringUtil.isEmptyString(getConfigFileDir())) {
			createDirIfNotExist(getConfigFileDir());
		}
		if (!StringUtil.isEmptyString(getSourceDir())) {
			createDirIfNotExist(getSourceDir());
		}
		
	
		if (!StringUtil.isEmptyString(getViewDir())) {
			createDirIfNotExist(getViewDir());
		}
		
		
       
      
      
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getViewDir() {
		return viewDir;
	}
	public void setViewDir(String viewDir) {
		this.viewDir = viewDir;
	}
	
	public String getConfigFileDir() {
		return configFileDir;
	}
	public void setConfigFileDir(String configFileDir) {
		this.configFileDir = configFileDir;
	}
	
	
	
	
	public GenericTemplate getViewTemplate() {
		return viewTemplate;
	}

	public void setViewTemplate(GenericTemplate viewTemplate) {
		this.viewTemplate = viewTemplate;
	}

	public GenericTemplate getControllerTemplate() {
		return controllerTemplate;
	}

	public void setControllerTemplate(GenericTemplate controllerTemplate) {
		this.controllerTemplate = controllerTemplate;
	}

	public GenericTemplate getModelTemplate() {
		return modelTemplate;
	}

	public void setModelTemplate(GenericTemplate modelTemplate) {
		this.modelTemplate = modelTemplate;
	}

	public GenericTemplate getConfigFileTemplate() {
		return configFileTemplate;
	}

	public void setConfigFileTemplate(GenericTemplate configFileTemplate) {
		this.configFileTemplate = configFileTemplate;
	}

	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	

	public Hashtable getAllModule() {
		return allModule;
	}

	public void setAllModule(Hashtable allModule) {
		this.allModule = allModule;
	}
	


}
