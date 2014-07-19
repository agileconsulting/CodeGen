package it.aive.codeGenerator.converter;

import it.aive.codeGenerator.descriptor.database.DbColumnDescriptor;
import it.aive.codeGenerator.descriptor.database.DbTableDescriptor;
import it.aive.codeGenerator.descriptor.java.AttributeDescriptor;
import it.aive.codeGenerator.descriptor.java.ClassDescriptor;
import it.aive.utility.StringUtil;

import java.util.ArrayList;
import java.util.Iterator;

public class Table2ClassConverter {
ClassDescriptor classe;
DbTableDescriptor tabella;
private String generaNomeAttributo(String nomeColonna){

	  nomeColonna=nomeColonna.toLowerCase();
	  String[] token= StringUtil.getStrToken(nomeColonna,"_");
	  String nomeNuovo="";
	  for(int i=0;i<token.length;i++){
	    char f= token[i].charAt(0);
	  String tmp="";
	  tmp=tmp+f;
	   if(i==0){
	      nomeNuovo+=tmp+token[i].substring(1);
	   } else {
	      tmp= tmp.toUpperCase();
	      nomeNuovo+=tmp+token[i].substring(1);
	   }

	  }

	  return nomeNuovo;
	 }

public String convertiTipo(String tipoDb) throws Exception {
    String retVal = "";

    if (tipoDb.equalsIgnoreCase("VARCHAR2") || tipoDb.equalsIgnoreCase("LONG") || tipoDb.equalsIgnoreCase("VARCHAR") || tipoDb.equalsIgnoreCase("CHAR")) {
        retVal = "String";
    } else if (tipoDb.equalsIgnoreCase("Date") || tipoDb.equalsIgnoreCase("smalldatetime") || tipoDb.equalsIgnoreCase("datetime") || tipoDb.equalsIgnoreCase("TIMESTAMP")) {
        retVal = "java.util.Date";
    } else if (tipoDb.equalsIgnoreCase("Number")) {
        retVal = "java.math.BigDecimal";
    } else  if (tipoDb.equalsIgnoreCase("INTEGER")) {
        retVal = "Integer";
    } else if (tipoDb.equalsIgnoreCase("FLOAT")) {
        retVal = "double";
    } else if (tipoDb.equalsIgnoreCase("FLOAT")) {
        retVal = "double";
    } else if (tipoDb.equalsIgnoreCase("INT")) {
        retVal = "int";
    }  else if (tipoDb.equalsIgnoreCase("RAW") || tipoDb.equalsIgnoreCase("BLOB") || tipoDb.equalsIgnoreCase("CLOB")    || tipoDb.equalsIgnoreCase("LONGRAW")  ) {
        retVal = "byte[]";
    }  else if (tipoDb.equalsIgnoreCase("DECIMAL") ||tipoDb.equalsIgnoreCase("money") ) {
    	retVal = "java.math.BigDecimal";
        
    } else {
      
      
      new Exception ("Errore dato non riconosciuto");
    }

    return retVal;
}

	private String generaNomeClasse(String nomeTabella){

	  nomeTabella=nomeTabella.toLowerCase();
	   
	  String[] token= StringUtil.getStrToken(nomeTabella,"_");
	  String nomeNuovo="";
	  for(int i=0;i<token.length;i++){
	    char f= token[i].charAt(0);
	    String tmp="";
	    tmp=tmp+f;
	    tmp= tmp.toUpperCase();
	    nomeNuovo+=tmp+token[i].substring(1);
	   }
	  return nomeNuovo;
	 }
 private AttributeDescriptor convertiColonna(DbColumnDescriptor colonna) throws Exception{
	 AttributeDescriptor retVal= new  AttributeDescriptor();
	 retVal.setAttName(generaNomeAttributo(colonna.getColumnName()));
	 retVal.setScope("private");
	 retVal.setAttType(convertiTipo(colonna.getDatatype()));
	 return retVal;
 }
public void  convert() throws Exception {
	classe= new ClassDescriptor();
	String nomeTabella=generaNomeClasse(tabella.getTableName());
	classe.setName(nomeTabella);
    ArrayList attributi = new ArrayList();
	for (Iterator iter = tabella.getColumn().iterator(); iter.hasNext();) {
		DbColumnDescriptor colonna = (DbColumnDescriptor) iter.next();
		AttributeDescriptor attributo=   convertiColonna(colonna);
		attributi.add(attributo);
	}
	classe.setAttributes(attributi);
}

public ClassDescriptor getClasse() {
	return classe;
}

public void setClasse(ClassDescriptor classe) {
	this.classe = classe;
}

public DbTableDescriptor getTabella() {
	return tabella;
}

public void setTabella(DbTableDescriptor tabella) {
	this.tabella = tabella;
}
}
