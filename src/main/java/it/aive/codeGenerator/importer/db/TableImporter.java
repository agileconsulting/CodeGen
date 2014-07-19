/**
 * 
 */
package it.aive.codeGenerator.importer.db;

import it.aive.codeGenerator.descriptor.database.DbColumnDescriptor;
import it.aive.codeGenerator.descriptor.database.DbForeignKey;
import it.aive.codeGenerator.descriptor.database.DbTableDescriptor;
import it.aive.codeGenerator.importer.Importer;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * TableImporter
 * 
 * <b> Storia delle Modifiche </b> <br>
 * 18/mag/07 14:00:43 Prima versione. Importa il nome della tabella che si trova
 * nello schema e nel catalogo selezionato nell'opportuna struttura dati
 * DatabaseMetadata <br>
 * 
 * @author mbaraldi
 * @version 1.0
 * 
 */
public class TableImporter extends Importer {
	public static String TABLE = "table";

	private DatabaseMetaData metadata;

	private String catalog;

	private String schema;

	private String table;

	public void clean() throws Exception {

		catalog = null;
		metadata = null;
		catalog = null;
		schema = null;
		table = null;
	}

	private void loadForeignKeys(DbTableDescriptor table) throws Exception {

		String catalogName = table.getCatalog();
		String schemaName = table.getSchema();
		String tableName = table.getTableName();
		ResultSet cols = metadata.getImportedKeys(catalogName, schemaName,
				tableName);
		while (cols.next()) {
			DbForeignKey fk = new DbForeignKey();
			String colName = cols.getString("FKCOLUMN_NAME");
			fk.setSourceColumn(colName);
			fk.setDestTable(cols.getString(4));
			fk.setDestColumn(cols.getString(3));
			fk.setSourceTable(table.getTableName());
			table.getForeignKey().add(fk);

		}
		if (cols!=null) {
			cols.close();
		}
	}

	private void loadPrimaryKey(DbTableDescriptor table) throws Exception {
		ArrayList columns = new ArrayList();

		ResultSet primaryKeys = metadata.getPrimaryKeys(table.getCatalog(),
				table.getSchema(), table.getTableName());
		while (primaryKeys.next()) {
			String primaryKeyColumn = primaryKeys.getString("COLUMN_NAME");
			DbColumnDescriptor column = table.findColumn(primaryKeyColumn);
			columns.add(column);

		}
		table.setPrimaryKey(columns);
		if (primaryKeys!=null) {
			primaryKeys.close();
		}
		
		

	}

	// net.sf.hibernate.tool.ddl2hbm.JDBCUtil convert java type to hibernate

	public void readTable(String catalog, String schema, String tabName, Map map)
			throws Exception {

		DbTableDescriptor table = new DbTableDescriptor(tabName);
		System.out.println("loading... " + tabName);
		readTableColumns(table);
		map.put(TABLE, table);

		

		loadPrimaryKey(table);
		loadForeignKeys(table);
	}

	public void readTableColumns(DbTableDescriptor table) throws SQLException {
		ResultSet columns = metadata.getColumns(null, "%",
				table.getTableName(), "%");
		while (columns.next()) {
			String columnName = columns.getString("COLUMN_NAME");
			String datatype = columns.getString("TYPE_NAME");
			int datasize = columns.getInt("COLUMN_SIZE");
			int digits = columns.getInt("DECIMAL_DIGITS");
			int nullable = columns.getInt("NULLABLE");
			boolean isNull = (nullable == 1);
			DbColumnDescriptor newColumn = new DbColumnDescriptor(columnName,
					datatype, datasize, digits, isNull);
			newColumn.setTable(table);
			table.addColumn(newColumn);
			table.getColumnMap().put(columnName, newColumn);
		}
	if (columns!=null) {
		columns.close();
	}	
	}

	public Map importResource() throws Exception {
		HashMap map = new HashMap();

		readTable(catalog, schema, table, map);

		return map;

	}

	public String getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog
	 *            the catalog to set
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
	 * @param schema
	 *            the schema to set
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(String tablePattern) {
		this.table = tablePattern;
	}

	/**
	 * @return the metadata
	 */
	public DatabaseMetaData getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata
	 *            the metadata to set
	 */
	public void setMetadata(DatabaseMetaData metadata) {
		this.metadata = metadata;
	}

}
