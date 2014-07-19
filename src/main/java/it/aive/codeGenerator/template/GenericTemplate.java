package it.aive.codeGenerator.template;


import it.aive.codeGenerator.descriptor.Descriptor;

import java.util.Hashtable;
 
public abstract class  GenericTemplate implements TemplateInterface {
	 private String name; 
	private Descriptor  descriptor;
	 private String templateFile;
	 private Hashtable parametrs = new Hashtable ();
	 GeneratorUtility utility = new GeneratorUtility ();
	 public Hashtable getParametrs() {
		return parametrs;
	}

	public void setParametrs(Hashtable parametrs) {
		this.parametrs = parametrs;
	}

	public Descriptor getDescriptor() {
			return descriptor;
		}

		public void setDescriptor(Descriptor descriptor) {
			this.descriptor = descriptor;
		}
		
		  /**
		   @return
		   @roseuid 45266BD400EC
		    */
		   public String getTemplateFile() 
		   {
			return templateFile;    
		   }
		   /**
		   @param string
		   @roseuid 45266BD4010A
		    */
		   public void setTemplateFile(String string) 
		   {
			templateFile = string;    
		   }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public GeneratorUtility getUtility() {
			return utility;
		}

		public void setUtility(GeneratorUtility utility) {
			this.utility = utility;
		}
		   
		  
	
}
