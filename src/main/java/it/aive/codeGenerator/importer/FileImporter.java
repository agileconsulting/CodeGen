package it.aive.codeGenerator.importer;


public  abstract  class FileImporter extends Importer {
	 String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void clean()  throws Exception {
		
		fileName=null;
	}
	

}
