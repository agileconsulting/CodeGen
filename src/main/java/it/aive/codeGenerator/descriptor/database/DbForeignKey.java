/**
 * 
 */
package it.aive.codeGenerator.descriptor.database;

/**
 * DbForeignKey
 *
 * <b> Storia delle Modifiche </b>
 * <br>
 * 21/mag/07 15:33:26 Prima versione
 * <br>
 * 
 * @author mbaraldi
 * @version 1.0
 *
 */
public class DbForeignKey {
private String sourceColumn;
private String sourceTable;
private String destColumn;
private String destTable;
/**
 * @return the destColumn
 */
public String getDestColumn() {
    return destColumn;
}
/**
 * @param destColumn the destColumn to set
 */
public void setDestColumn(String destColumn) {
    this.destColumn = destColumn;
}
/**
 * @return the destTable
 */
public String getDestTable() {
    return destTable;
}
/**
 * @param destTable the destTable to set
 */
public void setDestTable(String destTable) {
    this.destTable = destTable;
}
/**
 * @return the sourceColumn
 */
public String getSourceColumn() {
    return sourceColumn;
}
/**
 * @param sourceColumn the sourceColumn to set
 */
public void setSourceColumn(String sourceColumn) {
    this.sourceColumn = sourceColumn;
}
/**
 * @return the sourceTable
 */
public String getSourceTable() {
    return sourceTable;
}
/**
 * @param sourceTable the sourceTable to set
 */
public void setSourceTable(String sourceTable) {
    this.sourceTable = sourceTable;
}
}
