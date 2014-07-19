package it.aive.codeGenerator.main;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public abstract class MainBase {
	BeanFactory factory; 
	
	
	
	 
 
 public void readSpringConf(String fileName){
	 ClassPathResource resource = new ClassPathResource(fileName);
	 factory = new XmlBeanFactory(resource); 
 }

public MainBase() {
	
}
public MainBase(String fileName) {
	readSpringConf(fileName);
}

public BeanFactory getFactory() {
	return factory;
}
public void setFactory(BeanFactory factory) {
	this.factory = factory;
}


}
