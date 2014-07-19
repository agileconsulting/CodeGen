package it.aive.utility;


import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * DateUtil
 * Descrizione
 * Contiene delle funzioni di utilita per la gestione delle stringhe
 * <br>
 * <b> Storia delle Modifiche </b>
 * <br>
 * 02/12/2002 Prima versione 1.0
 * 03/04/2007 Aggiunta la versione con il locale e default date e time format versione 1.1
 * 
 * <br>
 * @author Marco Baraldi
 * @version 1.1
 *
 */
public class DateUtil {
    //~ Methods ----------------------------------------------------------------
    
    //
    public static final String DEFAULT_DATE_FORMAT="dd/MM/yyyy";
    public static final String DEFAULT_TIME_FORMAT="HH:mm:ss";
    public static final String DEFAULT_DATE_AND_TIME_FORMAT="dd/MM/yyyy HH:mm:ss";
    /**
     * Ritorna una oggetto di tipo Date ricevendo in input
     * una stringa contenente il valore di una data secondo il locale ITALY
     * DEFAULT_DATE_FORMAT
     * @param asDate Stringa contenete una data
     *
     * @return Data
     *
     * @throws Exception
     */
    public static java.util.Date getDate(String asDate) throws Exception {
        java.util.Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT) ;
        df.setLenient(false);
        dt = df.parse(asDate);

        return (dt);
    }
    /**
     * Ritorna un oggetto date ricivendo in ingresso una stringa del tipo
     * DEFAULT_DATE_AND_TIME_FORMAT
     * @param asTime Stringa che definisce il tempo
     *
     * @return
     *
     * @throws Exception
     */
   

    public static java.util.Date getDateAndTime(String asDate) throws Exception {
        java.util.Date dt = null;
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_TIME_FORMAT) ;
        df.setLenient(false);
        dt = df.parse(asDate);

        return (dt);
    }
    
    
    
    /**
     * Data una stringa che rappresenta la data, una stringa che rappresenta il formato
     * ed un locale, ritorna la data parsando la stringa con il dato formato e il dato locale
     *
     * @throws Exception
     */
   
    
    public static java.util.Date getDateTime(String asDate, String format, Locale loc) throws Exception {
	 SimpleDateFormat sd = new   SimpleDateFormat(format,loc);
	 sd.setLenient(false);
         java.util.Date dt = sd.parse(asDate);
         return (dt);
    }
    
    /**
     * Data una data che rappresenta , una stringa che rappresenta il formato
     * ed un locale, ritorna la stringa parsando la data con il dato formato e il dato locale
     *
     * @throws Exception
     */ 
    
    public static String getDateTimeAsString(java.util.Date aDate, String format,Locale loc) throws Exception {
	SimpleDateFormat sd = new   SimpleDateFormat(format,loc);
	sd.setLenient(false);
        String dt = sd.format(aDate);
        return (dt);
   }
  
     /**
       * Ritorna una stringa contenente la data attuale nel Formato DEFAULT_DATE_FORMAT
       *
       * @return
       */
    public static String getActualData() {
        java.util.Date now = new java.util.Date();
        return dateToString(now);
    }
    /**
     * Ritorna una stringa contenente la data attuale nel Formato DEFAULT_TIME_FORMAT
     *
     * @return
     */
    public static String getActualTime() {
        java.util.Date now = new java.util.Date();
        return timeToString(now);
    }
    /**
     * Ritorna una stringa contenente la data attuale nel Formato DEFAULT_DATE_AND_TIME_FORMAT
     *
     * @return
     */
    public static String getActualDateAndTime() {
        java.util.Date now = new java.util.Date();
        return dateAndTimeToString(now);
    } 
    
    /**
     * Data una data ritorna una stringa contenente la data attuale nel Formato DEFAULT_TIME_FORMAT
     *
     * @return
     */
   public static  String dateToString( java.util.Date dato){
    SimpleDateFormat sd = new   SimpleDateFormat(DEFAULT_DATE_FORMAT);
    return sd.format(dato);
   }
   /**
    * Ritorna una stringa contenente la data attuale nel Formato DEFAULT_DATE_AND_TIME_FORMAT
    *
    * @return
    */
  public static  String dateAndTimeToString( java.util.Date dato){
	   SimpleDateFormat sd = new   SimpleDateFormat(DEFAULT_DATE_AND_TIME_FORMAT);
	    return sd.format(dato);
  }
  /**
   * Ritorna una stringa contenente la data attuale nel Formato DEFAULT_TIME_FORMAT
   *
   * @return
   */
 public static  String timeToString( java.util.Date dato){
	   SimpleDateFormat sd = new   SimpleDateFormat(DEFAULT_TIME_FORMAT);
	    return sd.format(dato);
  }  
  
  
}

/* End of File */
