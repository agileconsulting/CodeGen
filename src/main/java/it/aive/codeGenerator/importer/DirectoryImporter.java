package it.aive.codeGenerator.importer;

import it.aive.utility.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DirectoryImporter {
FileImporter importer;
String directory;
String fileExtension;
List importedFile;
List importedResources;

public void importAll() throws Exception {
	importedResources = new ArrayList();
	String[] fileList=  FileUtil.returnFileListRec(getDirectory());
	for (int i = 0; i < fileList.length; i++) {
		String nomeFile=fileList[i].substring(getDirectory().length()+1);
		if (nomeFile.indexOf(getFileExtension())>0) {
	      getImporter().clean(); 
	      getImporter().setFileName(nomeFile);
	      Map res=getImporter().importResource();
	      importedResources.add(res);
	      importedFile.add(nomeFile);
		}
    }
	
	
}

public String getDirectory() {
	return directory;
}
public void setDirectory(String directory) {
	this.directory = directory;
}
public String getFileExtension() {
	return fileExtension;
}
public void setFileExtension(String fileExtension) {
	this.fileExtension = fileExtension;
}

public FileImporter getImporter() {
	return importer;
}
public void setImporter(FileImporter importer) {
	this.importer = importer;
}

public List getImportedFile() {
	return importedFile;
}

public void setImportedFile(List importedFile) {
	this.importedFile = importedFile;
}

public List getImportedResources() {
	return importedResources;
}

public void setImportedResources(List importedResources) {
	this.importedResources = importedResources;
}
}