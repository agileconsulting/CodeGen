package it.aive.codeGenerator.project;


import it.aive.codeGenerator.importer.SchemaImporter;
import it.aive.codeGenerator.template.GenericTemplate;
import it.aive.utility.StringUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author mbaraldi
 * Obbiettivo di questa classe è quello di fare da contenitore
 * ad una serie di moduli. Si da per scontato che tutta l'infranstruttura
 * del progetto sia già stata creata: librerie ecc. utilizzando altri stumenti (Ad es. maven)
 * L'obbiettivo non è quindi quello di costruire la struttura del progetto ma quella di
 * L'obbiettivo di questa classe è solo quella di generare dei moduli utilizzando dei
 * template,
 * Gli step sono:
 * 1 Creazione dell'infrastruttura del progetto a mano o con altri strumenti (es maven)
 * 2 lancio del project che lancia la generazione di codice
 */
public class Project {
	private SchemaImporter schema;
	



	
	




	


	private String name;
	private String description;
	private String shortDescription;
	
	private String viewDir;
	private String sourceDir;
	private String configDir;
	private String backupDir;
	private String packagePrefix;
	

    private List   modules;
   


public void init() throws Exception {
	for (Iterator iterator = getModules().iterator(); iterator.hasNext();) {
		Module module = (Module) iterator.next();
		String tmp=getPackagePrefix();
		tmp=StringUtil.substitute(".", "\\", tmp);
		String sourceDir=getSourceDir()+tmp+ "\\"+ module.getName();
		String pagesDir=getViewDir()+  "secure\\"+module.getName();
		String fileConfigDir=getConfigDir();
		
		module.setSourceDir(sourceDir);
		module.setConfigFileDir(fileConfigDir);
		module.setViewDir(pagesDir);
	    module.setProject(this);
	    module.setPackageName(getPackagePrefix()+"."+module.getName());
	  
	    module.init();
	}
}


public String getSourceDir() {
	return sourceDir;
}


public void setSourceDir(String sourceDir) {
	this.sourceDir = sourceDir;
}


public String getPackagePrefix() {
	return packagePrefix;
}


public void setPackagePrefix(String packagePrefix) {
	this.packagePrefix = packagePrefix;
}


public void generateCode() throws Exception {
	for (Iterator iterator = getModules().iterator(); iterator.hasNext();) {
		Module module = (Module) iterator.next();
		
	    module.generateCode();
	}
}
public SchemaImporter getSchema() {
	return schema;
}

public void setSchema(SchemaImporter schema) {
	this.schema = schema;
}
public List getModules() {
	return modules;
}
public void setModules(List modules) {
	this.modules = modules;
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








public String getConfigDir() {
	return configDir;
}




public void setConfigDir(String configDir) {
	this.configDir = configDir;
}




public String getBackupDir() {
	return backupDir;
}




public void setBackupDir(String backupDir) {
	this.backupDir = backupDir;
}






}
