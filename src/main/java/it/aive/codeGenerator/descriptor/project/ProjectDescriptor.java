package it.aive.codeGenerator.descriptor.project;

import it.aive.codeGenerator.descriptor.Descriptor;

public class ProjectDescriptor extends Descriptor {
	private String name;
	private String description;
	private String shortDescription;
	
	private String frontEndDir;
	private String modelDir;
	private String backupDir;
	
	private String pagesPrefixDir;
	private String controllerPrefixDir;
	private String modelPrefix;
	private String webConfigPrefix;
	private String controllerPackagePrefix;
	private String modelPackagePrefix;
	
	private String templateProjectFrontEndDir;
	private String templateProjectBackEnd;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getFrontEndDir() {
		return frontEndDir;
	}
	public void setFrontEndDir(String frontEndDir) {
		this.frontEndDir = frontEndDir;
	}
	public String getModelDir() {
		return modelDir;
	}
	public void setModelDir(String modelDir) {
		this.modelDir = modelDir;
	}
	public String getBackupDir() {
		return backupDir;
	}
	public void setBackupDir(String backupDir) {
		this.backupDir = backupDir;
	}
	public String getPagesPrefixDir() {
		return pagesPrefixDir;
	}
	public void setPagesPrefixDir(String pagesPrefixDir) {
		this.pagesPrefixDir = pagesPrefixDir;
	}
	public String getControllerPrefixDir() {
		return controllerPrefixDir;
	}
	public void setControllerPrefixDir(String controllerPrefixDir) {
		this.controllerPrefixDir = controllerPrefixDir;
	}
	public String getModelPrefix() {
		return modelPrefix;
	}
	public void setModelPrefix(String modelPrefix) {
		this.modelPrefix = modelPrefix;
	}
	public String getWebConfigPrefix() {
		return webConfigPrefix;
	}
	public void setWebConfigPrefix(String webConfigPrefix) {
		this.webConfigPrefix = webConfigPrefix;
	}
	public String getControllerPackagePrefix() {
		return controllerPackagePrefix;
	}
	public void setControllerPackagePrefix(String controllerPackagePrefix) {
		this.controllerPackagePrefix = controllerPackagePrefix;
	}
	public String getModelPackagePrefix() {
		return modelPackagePrefix;
	}
	public void setModelPackagePrefix(String modelPackagePrefix) {
		this.modelPackagePrefix = modelPackagePrefix;
	}
}
