package it.aive.codeGenerator.importer;

import it.aive.codeGenerator.descriptor.database.DbTableDescriptor;
import it.aive.codeGenerator.importer.db.TableImporter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SchemaImporter {
	TableImporter tableImporter = new TableImporter() ;
	private String exludedPrefix;
	
	private String driverClass;

	private String url;

	private String userName;

	private String password;

	private String catalog;

	private String schema;

	private String tablePattern;

	List importedTableName = new ArrayList();

	List importedResources= new ArrayList();

	private Connection openConnection() throws Exception {
		Class.forName(driverClass);
		Connection cnn = DriverManager.getConnection(url, userName, password);
		return cnn;
	}

	private void closeConnection(Connection cnn) throws Exception {
		cnn.close();
	}
	public DbTableDescriptor importTable(String tableName) throws Exception {
       Connection cnn = openConnection();
       DatabaseMetaData metadata = cnn.getMetaData();
        getTableImporter().clean();
		getTableImporter().setCatalog(catalog);
		getTableImporter().setSchema(schema);
		getTableImporter().setTable(tableName);
		getTableImporter().setMetadata(metadata);
		Map res = getTableImporter().importResource();
		DbTableDescriptor tabella =(DbTableDescriptor)res.get("table");
	  
   
    closeConnection(cnn);
    
    
    return tabella;
}
	

	public void importAll() throws Exception {
                importedTableName = new ArrayList();
                importedResources= new ArrayList();
		Connection cnn = openConnection();
		DatabaseMetaData metadata = cnn.getMetaData();
		ResultSet tableNames = metadata.getTables(catalog, schema,	tablePattern, new String[] { "TABLE", "VIEW", "SYNONYM","ALIAS" });
		while (tableNames.next()) {
			String tabName = tableNames.getString("TABLE_NAME");
			if (!(tabName.startsWith("BIN$"))) {
			
				getTableImporter().clean();
				getTableImporter().setCatalog(catalog);
				getTableImporter().setSchema(schema);
				getTableImporter().setTable(tabName);
				getTableImporter().setMetadata(metadata);
				Map res = getTableImporter().importResource();
				importedResources.add(res);
				importedTableName.add(tabName);
			}
		}
		tableNames.close();
		closeConnection(cnn);
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public List getImportedTableName() {
		return importedTableName;
	}

	public void setImportedTableName(List importedFile) {
		this.importedTableName = importedFile;
	}

	public List getImportedResources() {
		return importedResources;
	}

	public void setImportedResources(List importedResources) {
		this.importedResources = importedResources;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getTablePattern() {
		return tablePattern;
	}

	public void setTablePattern(String tablePattern) {
		this.tablePattern = tablePattern;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExludedPrefix() {
		return exludedPrefix;
	}

	public void setExludedPrefix(String exludedPrefix) {
		this.exludedPrefix = exludedPrefix;
	}

	public TableImporter getTableImporter() {
		return tableImporter;
	}

	public void setTableImporter(TableImporter importer) {
		this.tableImporter = importer;
	}
	
	public List getImportedTableDescriptor(){
  	  List listaTabelle=getImportedResources();
  	  ArrayList retVal =new ArrayList();
  	  for (Iterator iterator = listaTabelle.iterator(); iterator.hasNext();) {
  		HashMap risorse = (HashMap) iterator.next();
		DbTableDescriptor tabella =(DbTableDescriptor)risorse.get("table");
		retVal.add(tabella);
	}
	  
          
          return retVal;
    }
}
