package it.aive.codeGenerator.importer.hbm;

import it.aive.codeGenerator.importer.XmlImporter;

import java.io.InputStream;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public abstract class HbmImporter extends XmlImporter {
	 public void  initSaxReader() throws Exception {
	        super.initSaxReader(); 
	    	
	    	EntityResolver resolver = new EntityResolver() {
		    public InputSource resolveEntity(String publicId, String systemId) {
		        if ( publicId.equals("-//Hibernate/Hibernate Mapping DTD 3.0//EN") ) {
		            InputStream in = this.getClass().getClassLoader().getResourceAsStream("dtd\\hibernate.dtd");
		            return new InputSource( in );
		        }
		        return null;
		    }
		};
		
		getSaxReader().setEntityResolver( resolver );
		
		
		  
	    }
}
