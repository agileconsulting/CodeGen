package it.aive.codeGenerator.main;

import it.aive.codeGenerator.importer.SchemaImporter;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class MainDb extends MainBase {
	// Recupero l'importer per il database
	// contiene tutte le info per accedere al db
	SchemaImporter schema ;
	
	
 public void readSchemaImporter(){
	schema = (SchemaImporter)factory.getBean("schemaImp");
	
}

public MainDb() {
	try {
		readSpringConf("dbScript.xml");
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
public MainDb(String  fileName) {
	try {
		readSpringConf(fileName);
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
public SchemaImporter getSchema() {
	return schema;
}
public void setSchema(SchemaImporter schema) {
	this.schema = schema;
}
}
