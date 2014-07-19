package it.aive.codeGenerator.template;

import it.aive.codeGenerator.descriptor.java.ClassDescriptor;
import it.aive.utility.StringUtil;

import java.io.File;


/**
 * BeanTemplate
 * Template for creating a bean
 * <b> Storia delle Modifiche </b>
 * <br>
 * 10/mag/07 15:46:07 Prima versione
 * <br>
 * 
 * @author mbaraldi
 * @version 1.0
 *
 */
public  class BeanTemplate  extends FileTemplate {

public static String DEFAULT_TEMPLATE_FILE_NAME="template\\BeanTemplate.vm";
      


public void init() throws Exception{
   
		
		if (getDescriptor() instanceof ClassDescriptor) {
			ClassDescriptor classDesc=(ClassDescriptor)getDescriptor();
			if (classDesc.getPackageName()== null) {
				throw new Exception("Definire il package");
			}
		  String filePath=StringUtil.substitute(".", ""+File.separatorChar, classDesc.getPackageName());
		  setDestinationDirectory(getDestinationDirectory()+filePath);
		  setDestinationFileName(classDesc.getName()+".java");
		   
		}
		
		if (getTemplateFile()==null) {
		  setTemplateFile(DEFAULT_TEMPLATE_FILE_NAME);
		}
		
		super.init();
	}

	
}





