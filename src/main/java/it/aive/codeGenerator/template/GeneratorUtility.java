/*
 * Created on 5-ott-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package it.aive.codeGenerator.template;

import it.aive.utility.DateUtil;

/**
 * @author root
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GeneratorUtility {
	
	public static String firstToUpperCase(String string) {
	if (string==null) {
		return"";
	}
	if (string.length()<2) {
		return"";
	}
		
		String post = string.substring(1,string.length());
		String first = (""+string.charAt(0)).toUpperCase();
		return first+post;
	  }
	  
	public static String  firstToLowerCase(String string) {
	if (string==null) {
		return"";
	}
	if (string.length()<2) {
		return"";
	}
	
	String post = string.substring(1,string.length());
	String first = (""+string.charAt(0)).toLowerCase();
	return first+post;
  }
	public static String UpperCase(String datum) {
			if (datum!=null) {
				datum=datum.toUpperCase();
			}
			return datum;
	}
	
	public static String LowerCase(String datum) {
				if (datum!=null) {
					datum=datum.toLowerCase();
				}
				return datum;
		}
	public static String getActualData(){
		return DateUtil.getActualData();
	}
	
	// Converte la stringa aggiungendo alla lettera maiuscola _ es nomeSoggetto in nome_soggetto
	public static String UpperToUnderscore(String nomeProp) {
		 String retVal="";
		   for (int i = 0; i < nomeProp.length(); i++) {
			   char f = nomeProp.charAt(i);
			   String tmp=""+f;
			   // il primo carattere min-mas e' lo stesso
			   if (i!=0){
				 if(tmp.toUpperCase().equals(tmp)) {
				   retVal += "_";
			   }  
			   }
            
           
				   retVal += tmp.toUpperCase();
        
		   } 
		return retVal;
	  }         
        public static String Maxleng(String nomeProp, int lunghezza) {
            if(nomeProp.length()<lunghezza)
                return nomeProp;
            else return nomeProp.substring(0, 29);
        }

}
