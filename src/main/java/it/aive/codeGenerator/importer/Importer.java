/**
 * 
 */
package it.aive.codeGenerator.importer;

import java.util.Map;

/**
 * Importer
 *
 * <b> Storia delle Modifiche </b>
 * <br>
 * 10/mag/07 15:20:38 Prima versione
 * Gli tableImporter si occupano di leggere strutture dati tra di loro eteteogenee (file xml
 * stutture di database ecc..)e di trasformarli in opportuni classi java che rappresentano
 * il contenuto informativo delle strutture.
 * <br>
 * 
 * @author mbaraldi
 * @version 1.0
 *
 */
public abstract class Importer {

    public abstract Map importResource()  throws Exception;
    public abstract void clean()  throws Exception;
}
