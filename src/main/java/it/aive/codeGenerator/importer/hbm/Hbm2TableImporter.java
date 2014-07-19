package it.aive.codeGenerator.importer.hbm;

import it.aive.codeGenerator.descriptor.database.DbTableDescriptor;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

public class Hbm2TableImporter  extends HbmImporter {
	 public static String TABLE="table";
	 public  Map importResource() throws Exception {
		 Map retVal = new HashMap();
		 DbTableDescriptor table = new DbTableDescriptor();
		 retVal.put(TABLE, table);
		 Element root = getDocument().getRootElement();
		 Element classElement = root.element("class");
		 initTable(table,classElement);
		 
		 
		 return retVal;	
	 }
	 
	 
	 private void initTable(DbTableDescriptor table2, Element elem) {
		 String name= elem.attributeValue("table");
		 String schema=elem.attributeValue("schema");;  
		 table2.setTableName(name);
		 table2.setSchema(schema);
	    }
}
