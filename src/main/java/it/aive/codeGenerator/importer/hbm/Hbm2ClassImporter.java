package it.aive.codeGenerator.importer.hbm;
import it.aive.codeGenerator.descriptor.java.AttributeDescriptor;
import it.aive.codeGenerator.descriptor.java.ClassDescriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

/**
 * Hbm2ClassImporter
 *
 * <b> Storia delle Modifiche </b>
 * <br>
 * 02/mag/07 09:46:08 Prima versione 
 * Obiettivo di questa classe è di leggere
 * un file hbm e di trasformarlo nei descrittori della classe e della tabella 
 * ricavando le opportune info. Un file hbm contiene sia le informazioni della classe
 * sia della tabella e quindi l'tableImporter ritorna un descrittore della tabella e un descrittore
 * della classe. Qualora la classe abbia una composite ID ritorna anche la descrizione della chiave.
 * In altri termini questo tableImporter ritorna 2 o 3 descrizioni a seconda che ci sia o no una composite id
 * <br>
 * 
 * @author mbaraldi
 * @version 1.0
 *
 */
public class Hbm2ClassImporter extends HbmImporter {
   
    public static String MAIN_CLASS="mainClass";
    public static String COMPOSITE_ID="compositeId";
   
    
    
   
	
    public  Map importResource() throws Exception {
	
	 Map retVal = new HashMap();
	// File resFile = new File();
	 
	
	 
	 // Create first classDesc, Main class form hbm file
	 ClassDescriptor classDesc = new ClassDescriptor();
	 retVal.put(MAIN_CLASS, classDesc);
	 
	 
	 
	 
	 
	 Element root = getDocument().getRootElement();
	 Element classElement = root.element("class");
	 
	 initClassWithNameAndPackage(classDesc, classElement);
	 
	
	 
	 // Now add all simple properties
	 ArrayList tmp= HbmUtil.getAllProperties(getDocument());
	 classDesc.setAttributes(tmp);
	 
	 // Add many to one properties
	 ArrayList manyToOne=HbmUtil.getManyToOne(getDocument());
	 if (!manyToOne.isEmpty()) {
	     classDesc.getAttributes().addAll(manyToOne);
	 }
	 // Add set
	 ArrayList sets= HbmUtil.getAllSet(getDocument());
	 if (!sets.isEmpty()) {
	     classDesc.getAttributes().addAll(sets);
	 }
	 
	 // if class has a composite id another class desc is needed 
	 // otherwise add an id descriptor
	 if (!HbmUtil.isCompositId(getDocument())) {
	     classDesc.addAttribute(HbmUtil.getId(getDocument()));
	 } else  {
		 
	     ClassDescriptor classCompositeId= HbmUtil.getCompositeId(getDocument());
	     AttributeDescriptor tmp1 = new AttributeDescriptor();
	     String type= classCompositeId.getPackageName()+"."+classCompositeId.getName();
	     tmp1.setAttName("id");
	     tmp1.setAttType(type);
	     classDesc.addAttribute(tmp1);
	     retVal.put(COMPOSITE_ID, classCompositeId);
	 }
	 return retVal;
	   
    }

    /**
     * @param table2
     * @param classe
     */
    

    /**
     * @param classDesc
     * @param classe
     */
    private void initClassWithNameAndPackage(ClassDescriptor classDesc, Element classe) {
	 String fullName= classe.attributeValue("name");
	 String className=fullName.substring(fullName.lastIndexOf(".")+1);  
	 String packageName=fullName.substring(0,fullName.lastIndexOf(".")); 
	 classDesc.setPackageName(packageName);
	 classDesc.setName(className);
    }
    
   
   
    

}
