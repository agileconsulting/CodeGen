/**
 * 
 */
package it.aive.codeGenerator.descriptor.database;



import java.util.ArrayList;
import java.util.HashMap;

/**
 * DbTableDescriptor
 *
 * <b> Storia delle Modifiche </b>
 * <br>
 * 18/mag/07 15:10:35 Prima versione
 * <br>
 * 
 * @author mbaraldi
 * @version 1.0
 *
 */
public class DbTableDescriptor {
 ArrayList column = new ArrayList();
 HashMap  columnMap= new HashMap();
 ArrayList primaryKey = new ArrayList();
 ArrayList foreignKey = new ArrayList();
 String tableName;
 String catalog; 
 String schema;
 
public DbColumnDescriptor findColumn (String colName){
    return (DbColumnDescriptor) columnMap.get(colName);
}

  /**
 * @param tableName
 */
public DbTableDescriptor(String tableName) {
  
    this.tableName = tableName;
}
public DbTableDescriptor() {
   
}
public void addColumn (DbColumnDescriptor col){
      column.add(col);
  }
/**
 * @return the column
 */
public ArrayList getColumn() {
    return column;
}
/**
 * @param column the column to set
 */
public void setColumn(ArrayList column) {
    this.column = column;
}
/**
 * @return the tableName
 */
public String getTableName() {
    return tableName;
}
/**
 * @param tableName the tableName to set
 */
public void setTableName(String tableName) {
    this.tableName = tableName;
}
/**
 * @return the columnMap
 */
public HashMap getColumnMap() {
    return columnMap;
}
/**
 * @param columnMap the columnMap to set
 */
public void setColumnMap(HashMap allColumn) {
    this.columnMap = allColumn;
}
/**
 * @return the catalog
 */
public String getCatalog() {
    return catalog;
}
/**
 * @param catalog the catalog to set
 */
public void setCatalog(String catalog) {
    this.catalog = catalog;
}
/**
 * @return the schema
 */
public String getSchema() {
    return schema;
}
/**
 * @param schema the schema to set
 */
public void setSchema(String schema) {
    this.schema = schema;
}

/**
 * @return the primaryKey
 */
public ArrayList getPrimaryKey() {
    return primaryKey;
}

/**
 * @param primaryKey the primaryKey to set
 */
public void setPrimaryKey(ArrayList primaryKey) {
    this.primaryKey = primaryKey;
}

/**
 * @return the foreignKey
 */
public ArrayList getForeignKey() {
    return foreignKey;
}

/**
 * @param foreignKey the foreignKey to set
 */
public void setForeignKey(ArrayList foreignKey) {
    this.foreignKey = foreignKey;
}
}
