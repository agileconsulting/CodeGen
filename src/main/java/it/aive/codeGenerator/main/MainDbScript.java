package it.aive.codeGenerator.main;

import it.aive.codeGenerator.descriptor.database.DbTableDescriptor;

import java.util.Iterator;
import java.util.List;

public class MainDbScript extends MainDb {

	/**
	 * @param args
	 */
	public void addTrigger(DbTableDescriptor table){
		
	}
  public void addSequence(DbTableDescriptor table){
		
	}
    public void generateCode(){
	
   }
	public static void main(String[] args) {
		try {
			MainDbScript me = new MainDbScript("dbScript.xml");
			me.getSchema().importAll();
			List listaTabelle=	me.getSchema().getImportedTableDescriptor();
		    for (Iterator iterator = listaTabelle.iterator(); iterator.hasNext();) {
		    	DbTableDescriptor table = (DbTableDescriptor) iterator.next();
				String colName="ID_"+table.getTableName();
		    	if (table.findColumn(colName)!=null) {
		    		me.addTrigger(table);
					me.addSequence(table);	
				}
		    	
			    
		    }
		   me.generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	public MainDbScript(String fileName) {
		super(fileName);
	}
}
