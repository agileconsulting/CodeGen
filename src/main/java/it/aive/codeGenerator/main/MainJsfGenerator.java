/**
 * 
 */
package it.aive.codeGenerator.main;

import it.aive.codeGenerator.project.Project;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author mbaraldi
 *
 */
public class MainJsfGenerator extends MainBase {
	private static BeanFactory factory; ;
	/**
	 * 
	 */
	public MainJsfGenerator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fileName
	 */
	public MainJsfGenerator(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}
	public  void readSpringConf(String fileName){
		 ClassPathResource resource = new ClassPathResource(fileName);
		 factory = new XmlBeanFactory(resource); 
	}
 

	public static void main(String[] args) {
		try {
			MainJsfGenerator istance = new MainJsfGenerator();
			istance.readSpringConf("jsf-project.xml");
			Project me=(Project) factory.getBean("project");
		    me.init();
		    me.generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
