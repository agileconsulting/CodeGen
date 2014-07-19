package it.aive.codeGenerator.descriptor.project;

import java.util.List;

import it.aive.codeGenerator.descriptor.Descriptor;

public class ModuleDescriptor extends Descriptor{
	private String name;
	private String description;
	private String shortDescription;
	private String controllerDir;
	private String viewDir;
	private String modelDir;
	private String configFileDir;
	private String packageNameModel;
	private String packageNameController;
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
	public String getControllerDir() {
		return controllerDir;
	}
	public void setControllerDir(String controllerDir) {
		this.controllerDir = controllerDir;
	}
	public String getViewDir() {
		return viewDir;
	}
	public void setViewDir(String viewDir) {
		this.viewDir = viewDir;
	}
	public String getModelDir() {
		return modelDir;
	}
	public void setModelDir(String modelDir) {
		this.modelDir = modelDir;
	}
	public String getConfigFileDir() {
		return configFileDir;
	}
	public void setConfigFileDir(String configFileDir) {
		this.configFileDir = configFileDir;
	}
	public String getPackageNameModel() {
		return packageNameModel;
	}
	public void setPackageNameModel(String packageNameModel) {
		this.packageNameModel = packageNameModel;
	}
	public String getPackageNameController() {
		return packageNameController;
	}
	public void setPackageNameController(String packageNameController) {
		this.packageNameController = packageNameController;
	}
	
	
}
