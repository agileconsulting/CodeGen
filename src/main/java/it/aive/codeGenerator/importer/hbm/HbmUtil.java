/**
 * 
 */
package it.aive.codeGenerator.importer.hbm;

import it.aive.codeGenerator.descriptor.java.AttributeDescriptor;
import it.aive.codeGenerator.descriptor.java.ClassDescriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;

/**
 * HbmUtil
 *
 * <b> Storia delle Modifiche </b>
 * <br>
 * 11/mag/07 09:34:36 Prima versione
 * <br>
 * 
 * @author mbaraldi
 * @version 1.0
 *
 */
public class HbmUtil {
    private static final Map HbmTypeConversion = new HashMap();

	static {
	    HbmTypeConversion.put( "char", "Character" );
	    HbmTypeConversion.put( "byte", "Byte" );
	    HbmTypeConversion.put( "short", "Short" );
	    HbmTypeConversion.put( "int", "Integer" );
	    HbmTypeConversion.put( "long", "Long" );
	    HbmTypeConversion.put( "boolean", "Boolean" );
	    HbmTypeConversion.put( "float", "Float" );
	    HbmTypeConversion.put( "double", "Double" );
	    HbmTypeConversion.put( "string", "String" );
	    HbmTypeConversion.put( "big_decimal", "java.math.BigDecimal" );
	    HbmTypeConversion.put( "date", "java.util.Date" );
	    HbmTypeConversion.put( "time", "java.util.Date" );
	    HbmTypeConversion.put( "timestamp", "java.util.Date" );
	    HbmTypeConversion.put( "blob", "java.sql.Blob" );
	    

	}


    
    public static ArrayList getAllProperties(Document document) {
	ArrayList retVal = new ArrayList();
	List list = document.selectNodes("//property");
	for (Iterator iter = list.iterator(); iter.hasNext();) {
	    org.dom4j.tree.DefaultElement attribute = (org.dom4j.tree.DefaultElement) iter.next();
	    //String e= attribute.("column");
	   // List l=attribute.elements();
	   // Element e=attribute.element("column");
	    AttributeDescriptor att = new AttributeDescriptor();
	    att.setAttName(attribute.attributeValue("name"));
	    String tmp= (String) attribute.attributeValue("type");
	    att.setAttType((String)HbmTypeConversion.get(tmp));
	    retVal.add(att);
	}
	return retVal;
    }
 
    public static AttributeDescriptor getId(Document document) {
	AttributeDescriptor att = new AttributeDescriptor();
	List list = document.selectNodes("//id");
	for (Iterator iter = list.iterator(); iter.hasNext();) {
	    org.dom4j.tree.DefaultElement attribute = (org.dom4j.tree.DefaultElement) iter.next();
	    
	    att.setAttName(attribute.attributeValue("name"));
	    String tmp= (String) attribute.attributeValue("type");
	    att.setAttType((String)HbmTypeConversion.get(tmp));
	   
	}
	return att;
    }
 
    public static ArrayList getManyToOne(Document document) {
	ArrayList retVal = new ArrayList();
	
	List list = document.selectNodes("//many-to-one");
	for (Iterator iter = list.iterator(); iter.hasNext();) {
	    org.dom4j.tree.DefaultElement attribute = (org.dom4j.tree.DefaultElement) iter.next();
	    AttributeDescriptor att = new AttributeDescriptor();
	    att.setAttName(attribute.attributeValue("name"));
	    String tmp= (String) attribute.attributeValue("class");
	    att.setAttType(tmp);
	    retVal.add(att);
	}
	return retVal;
    }
    public static ArrayList getAllSet(Document document) {
	ArrayList retVal = new ArrayList();
	
	List list = document.selectNodes("//set");
	for (Iterator iter = list.iterator(); iter.hasNext();) {
	    org.dom4j.tree.DefaultElement attribute = (org.dom4j.tree.DefaultElement) iter.next();
	    AttributeDescriptor att = new AttributeDescriptor();
	    att.setAttName(attribute.attributeValue("name"));
	    att.setAttType("java.util.Set");
	    retVal.add(att);
	}
	return retVal;
    }   
     public static boolean isCompositId(Document document){
		boolean retVal = false;
		
		List list = document.selectNodes("//composite-id");
		for (Iterator iter = list.iterator(); iter.hasNext();) {
		    iter.next();
		    retVal=true;
		}
		return retVal;
     } 
     public static ClassDescriptor getCompositeId(Document document){
	 ClassDescriptor retVal = new ClassDescriptor();
	 List list = document.selectNodes("//composite-id");
	  for (Iterator iter = list.iterator(); iter.hasNext();) {
		    org.dom4j.tree.DefaultElement attribute = (org.dom4j.tree.DefaultElement) iter.next();
		    String fullName= attribute.attributeValue("class");
			 String className=fullName.substring(fullName.lastIndexOf(".")+1);  
			 String packageName=fullName.substring(0,fullName.lastIndexOf(".")); 
			 retVal.setPackageName(packageName);
			 retVal.setName(className);
		}
	  list = document.selectNodes("//key-property");
	  for (Iterator iter = list.iterator(); iter.hasNext();) {
		    org.dom4j.tree.DefaultElement attribute = (org.dom4j.tree.DefaultElement) iter.next();
		    AttributeDescriptor attDesc =new AttributeDescriptor();
		    attDesc.setAttName(attribute.attributeValue("name"));
		    String tmp= (String) attribute.attributeValue("type");
		    attDesc.setAttType((String)HbmTypeConversion.get(tmp));
		    retVal.addAttribute(attDesc);
		}
	 return retVal;
     }
}