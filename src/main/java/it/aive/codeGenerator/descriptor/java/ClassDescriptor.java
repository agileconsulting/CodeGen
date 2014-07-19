/*
 * Autore Marco Baraldi
 */
package it.aive.codeGenerator.descriptor.java;

import it.aive.codeGenerator.descriptor.Descriptor;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassDescriptor.
 */
public class ClassDescriptor  extends Descriptor {

	/** The name. */
	private String name;
	private String packageName;
	private String extendClass="";
	private List importedPackage;
	/**
	 * @return the importedPackage
	 */
	public List getImportedPackage() {
		return importedPackage;
	}

	/**
	 * @param importedPackage the importedPackage to set
	 */
	public void setImportedPackage(List importedPackage) {
		this.importedPackage = importedPackage;
	}

	/** The attributes. */
	private ArrayList attributes = new ArrayList();

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name
	 */
	public void setName(String name) {
	  this.name = name;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
	  return this.name;
	}

	/**
	 * Adds the attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 */
	public void addAttribute(AttributeDescriptor attribute) {
	  attributes.add(attribute);
	}

	/**
	 * Gets the attributes.
	 * 
	 * @return the attributes
	 */
	public ArrayList getAttributes() {
	  return attributes;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
	    return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
	    this.packageName = packageName;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(ArrayList attributes) {
	    this.attributes = attributes;
	}

	/**
	 * @return the extendClass
	 */
	public String getExtendClass() {
	    return extendClass;
	}

	/**
	 * @param extendClass the extendClass to set
	 */
	public void setExtendClass(String extendClass) {
	    this.extendClass = extendClass;
	}

	/**
	 * @return the table
	 */
	

}
