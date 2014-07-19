//Source file: C:\\dev\\progetti\\CodeGenerator\\src\\java\\it\\aive\\codeGenerator\\CodeTemplate.java

/*
 * Created on 4-ott-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package it.aive.codeGenerator.template;



import it.aive.utility.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

/**
AL'output di questo template per la generazione di codice è un file.
Tutti i template che lo estendono generano un file
@author mbaraldi
 */
public  class FileTemplate extends GenericTemplate {
   
   String destinationDirectory;
   String backupDirectory;
   private String destinationFileName;
  
  
   
 /**
 * Init all code Template loaded Reouces
 * @throws Exception
 */
public  void init() throws Exception{
	
		Map descriptorDesc= it.aive.utility.UtilityBean.describe(getDescriptor());
		getParametrs().putAll(descriptorDesc);

}

/**
         * @throws java.lang.Exception
         * @roseuid 45266BD40091
         */
  public  void go()  throws Exception{
	  backupdestinationFile();
      init();
      generateCode();
  }
  private void createDirIfNotExist(String fullPath) throws Exception{
	  File tmp = new File(fullPath);
		if (!tmp.exists()) {
		  boolean  success = (new File(fullPath)).mkdirs();
		  if (!success) {
		        throw new Exception("Create File failed");
		    }
		}  
  }
  
  public void backupdestinationFile() throws Exception{
     if ( destinationFileExist() && (getBackupDirectory()!=null) ) {
	     String backupFile= getBackupDirectory()+File.separatorChar+getDestinationFileName();
	     String backupDir=backupFile.substring(0,backupFile.lastIndexOf(File.separatorChar));
	     createDirIfNotExist(backupDir);
	     FileUtil.copyFile(getFullDestinationFileName(), backupFile); 
        }
   }
  
  public boolean destinationFileExist() throws Exception{
      File tmp = new File(getFullDestinationFileName());
      boolean retVal= tmp.exists();
     return retVal;
  }
  
  public  String  getFullDestinationFileName()  throws Exception{
      return getDestinationDirectory()+File.separatorChar+getDestinationFileName();
      
  }
  
  public void generateCode() throws Exception 
  {
  	
     
  
  	
	try {
	    Properties props = new Properties();
	    props.put("input.encoding", "utf-8");
	    props.put("resource.loader", "class");
	    props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	   
	    Velocity.init(props);

		
		VelocityContext context = new VelocityContext();
		Hashtable parm=getParametrs();
		Enumeration en =parm.keys();
		while (en.hasMoreElements()) {
			String key = (String)en.nextElement();
			Object value=parm.get(key);
			context.put(key,value);
		}
		
		context.put("utility", utility);
		
		
		Template template = Velocity.getTemplate(getTemplateFile());
		write(template,context);
	}  catch (Exception e) {
		
	String errore="Error "+	e.getMessage()+" in generating file code from template file " +getTemplateFile() ;
	 throw new Exception(errore);
	}    
  }
   /**
   @param template
   @param context
   @throws java.lang.Exception
   @roseuid 45266BD400AB
    */
  protected void write(Template template, VelocityContext context) throws Exception    {
	   
	createDirIfNotExist(getDestinationDirectory());
	String tmp=getDestinationDirectory()+ File.separatorChar +getDestinationFileName();
	
    FileWriter  writer = new FileWriter(tmp);
    template.merge(context,writer);
    writer.flush();
    writer.close();    
   }
   
   /**
   @return
   @roseuid 45266BD400EA
    */
   public String getDestinationDirectory() 
   {
	return destinationDirectory;    
   }
   
   
   
 
   
   /**
   @param string
   @roseuid 45266BD400FA
    */
   public void setDestinationDirectory(String string) 
   {
	destinationDirectory = string;    
   }
   
  
   
  
   
   /**
   @return
   @roseuid 45266BD40129
    */
   public String getDestinationFileName() 
   {
	return destinationFileName;    
   }
   
   /**
   @param string
   @roseuid 45266BD40138
    */
   public void setDestinationFileName(String string) 
   {
	destinationFileName = string;    
   }

/**
 * @return the backupDirectory
 */
public String getBackupDirectory() {
    return backupDirectory;
}

/**
 * @param backupDirectory the backupDirectory to set
 */
public void setBackupDirectory(String backupDirectory) {
    this.backupDirectory = backupDirectory;
}



}
/**

 
void CodeTemplate.setParametrs(java.util.Hashtable){
	parametrs = map;
}
 
 
 */
