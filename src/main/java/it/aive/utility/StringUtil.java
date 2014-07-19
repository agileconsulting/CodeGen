package it.aive.utility;

import java.util.StringTokenizer;


/**
 * Questa classe contiene delle funzioni di utilita per la
 * gestione delle stringhe
 * <br>
 * <b> Storia delle Modifiche </b> 
 * <br>
 * 01/12/2002 Prima Versione  <br>
 *
 * @author Baraldi Marco
 * @version 1.0
 */
public class StringUtil {
    //~ Methods ----------------------------------------------------------------

    /**
     * Divide la stringa in un vettore di stringhe in base al parametro
     * carattere passato
     * @param: str - stringa da dividere <br> c - carattere
     *
     * @return: un vettore di stringhe
     *
     */
    static public String[] getStrToken(String str, String separatore) {
        StringTokenizer t = new StringTokenizer(str, separatore);
        int i = 0;
        String[] vStr = new String[t.countTokens()];

        while (t.hasMoreTokens()) {
            vStr[i++] = t.nextToken();
        }

        return vStr;
    }



    /**
     * Crea una stringa di lunghezza <i>lunghezza</i> formata dal carattere
     * <i>c</i>
     * @param: c - carattere che, ripetuto, andrà a formare la stringa<br>
     *        lunghezza - lunghezza della stringa che si desidera ottenere
     *
     * @return: una stringa
     *
     */
    static public String createFromChar(char c, int lunghezza) {
        int i = 0;
        String ret_string = "";

        for (i = 0; i < lunghezza; i++) {
            ret_string = ret_string + c;
        }

        return ret_string;
    }



    /**
     * Esegue il Padding a lunghezza di una stringa value con il carattere c
     * allienato a tipoPadding
     *
     * <P></p>
     *
     * @param: c - carattere del padding<br>
     *        lunghezza - intero che indica lunghezza del padding desiderato<br>
     *        tipoPadding - tipo del padding desiderato: R per right, L per left<br>
     *        value - stringa sulla quale eseguire il pudding
     *
     * @return stringa formattata
     *
     * @exception
     */
    static public String padding(char c, int lunghezza, char tipoPadding, String value) {
        String ret_string = "";
        String valore = value;

        for (int i = 0; i < (lunghezza - valore.length()); i++) {
            ret_string = ret_string + c;
        }

        if (valore.length() < lunghezza) {
            if (tipoPadding == 'R') {
                valore = valore + ret_string;
            } else if (tipoPadding == 'L') {
                valore = ret_string + valore;
            }
        } else if (value.length() > lunghezza) {
            valore = valore.substring(0, lunghezza);
        }

        return valore;
    }



    /**
     * Sostituisce il carattere <i>vecchio</i> cole carattere <i>nuovo</i>
     * nella stringa <i>originale</i>
     * @param: vecchio - carattere da sostituire <br>
     *        nuovo - carattere da inserire come sostituto <br>
     *        originale - stringa da eleborare
     *
     * @return: la stringa formattata
     *
     */
    static public String substitute(String vecchio, String nuovo, String originale) {
        int i = originale.indexOf(vecchio);
        String ret_string = originale;

        while (i >= 0) {
            ret_string = ret_string.substring(0, i) + nuovo + ret_string.substring(i + vecchio.length());
            i = ret_string.indexOf(vecchio, i + nuovo.length());
        }

        return ret_string;
    }

static public String EliminaDaStringa(String inizio, String fine,String StringaDaPulire) {
       int primo = StringaDaPulire.indexOf(inizio);
         int ultimo= StringaDaPulire.indexOf(fine);
         String inizioStringa=  StringaDaPulire.substring(1,primo);
         String fineStringa= StringaDaPulire.substring(ultimo+fine.length());
         return (inizioStringa+fineStringa);
        }

    /**
     * conta il numero di volte che nella stringa
     * <i>originale</i> appare il carattere <i>c</i>
     * @param: originale - stringa che si vuole controllare<br>
     *        carattere - char che dev'essere ricercato
     *
     * @return: un intero
     *
     * @exception
     */
    static public int occurrenceCount(String originale, char carattere) {
        int i = originale.indexOf(carattere);
        int ret = 0;

        while (i > 0) {
            i++;
            ret++;
            i = originale.indexOf(carattere, i);
        }

        return ret;
    }



 public static boolean isEmptyString(String str) {
        if ((str == null) || (str.length() == 0)) {
            return true;
        } else {
            return false;
        }
    }


public static   String   NotNull(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }

public static   String   NotNull(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }



    /**
     * Elimina il punto dall'importo <i>originale</i>
     * che è espresso in lire italiane
     *
     * <P></p>
     *
     * @param: originale - valore in lire
     *
     * @return: importo formattato
     *
     * @exception
     */
    static public String valutaToNumber(String originale) {
        String finale = "";

        for (int i = 0; i < originale.length(); i++) {
            if (originale.charAt(i) != '.') {
                finale += originale.charAt(i);
            }
        }

        return finale;
    }
}

/* End of File */
