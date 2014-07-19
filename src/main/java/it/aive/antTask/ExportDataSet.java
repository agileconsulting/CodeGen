package it.aive.antTask;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.ForwardOnlyResultSetTableFactory;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class ExportDataSet {

	/** 
	 * Test
	 */
	public static void main(String[] args) {
		// database connection
		try {
			    Class driverClass = Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection jdbcConnection = DriverManager.getConnection("jdbc:oracle:thin:@netshareve:1521:orcl", "becubic", "becubic");
		        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection,"BECUBIC");
		        // Forward only per lavorare con grosse dimensioni di dati
		        DatabaseConfig config = connection.getConfig();
                config.setProperty(DatabaseConfig.PROPERTY_RESULTSET_TABLE_FACTORY, new ForwardOnlyResultSetTableFactory());

		          
		        // dependent tables database export: export table X and all tables that
		        // have a PK which is a FK on X, in the right order for insertion
		        String[] depTableNames = 
		          TablesDependencyHelper.getAllDependentTables( connection, "BECUBIC.RELAZIONE" );
		        IDataSet depDataset = connection.createDataSet( depTableNames );
		        
		        FlatXmlDataSet.write(depDataset, new FileOutputStream("dependents.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
            

	}

}
