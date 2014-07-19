package it.aive.codeGenerator.importer;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public abstract class XmlImporter extends FileImporter {
	private SAXReader saxReader;
	private Document document;
	public void  initSaxReader() throws Exception {
		saxReader = new SAXReader();
		saxReader.setValidation(false); 
	 }
	
	public void readDocument() throws Exception {
		ClassLoader loader = this.getClass().getClassLoader();
	  	InputStream in = loader.getResourceAsStream(getFileName());
	    Document document= getSaxReader().read(in);
	}

	public Document getDocument()throws Exception {
		if (document==null) {
			readDocument();
		}
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public SAXReader getSaxReader() throws Exception {
		if (saxReader==null) {
			initSaxReader();
		}
		return saxReader;
	}

	public void setSaxReader(SAXReader saxReader) {
		this.saxReader = saxReader;
	}
	
public void clean()  throws Exception {
		super.clean();
		
		setSaxReader(null);
		setDocument(null);
	}
}
