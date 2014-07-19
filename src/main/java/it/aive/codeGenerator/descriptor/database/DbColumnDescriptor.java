/**
 * 
 */
package it.aive.codeGenerator.descriptor.database;


/**
 * DbColumnDescriptor
 *
 * <b> Storia delle Modifiche </b>
 * <br>
 * 18/mag/07 15:08:28 Prima versione
 * <br>
 * 
 * @author mbaraldi
 * @version 1.0
 *
 */
public class DbColumnDescriptor {
      String columnName ; 
          // Data Type nativo
	  String dataType;
	  // Data Type convertito
	  String dataTypeConvertito;
          // Data Type convertito ulteriore, di comodità
          String dataTypeConvertito2;
          int datasize; 
	  int digits ; 
	  DbTableDescriptor table; 
	  boolean isNull;

	/**
	 * @param columnName
	 * @param datatype
	 * @param datasize
	 * @param digits
	 * @param isNull
	 */
	public DbColumnDescriptor(String columnName, String datatype, int datasize, int digits, boolean isNull) {
	    super();
	    this.columnName = columnName;
	    this.dataType = datatype;
	    this.datasize = datasize;
	    this.digits = digits;
	    this.isNull = isNull;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
	    return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
	    this.columnName = columnName;
	}

	/**
	 * @return the datasize
	 */
	public int getDatasize() {
	    return datasize;
	}

	/**
	 * @param datasize the datasize to set
	 */
	public void setDatasize(int datasize) {
	    this.datasize = datasize;
	}

	/**
	 * @return the datatype
	 */
	public String getDatatype() {
	    return dataType;
	}

	/**
	 * @param datatype the datatype to set
	 */
	public void setDatatype(String datatype) {
	    this.dataType = datatype;
	}

	/**
	 * @return the digits
	 */
	public int getDigits() {
	    return digits;
	}

	/**
	 * @param digits the digits to set
	 */
	public void setDigits(int digits) {
	    this.digits = digits;
	}

	/**
	 * @return the isNull
	 */
	public boolean isNull() {
	    return isNull;
	}

	/**
	 * @param isNull the isNull to set
	 */
	public void setNull(boolean isNull) {
	    this.isNull = isNull;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + ((columnName == null) ? 0 : columnName.hashCode());
	    result = PRIME * result + datasize;
	    result = PRIME * result + ((dataType == null) ? 0 : dataType.hashCode());
	    result = PRIME * result + digits;
	    result = PRIME * result + (isNull ? 1231 : 1237);
	    return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    final DbColumnDescriptor other = (DbColumnDescriptor) obj;
	    if (columnName == null) {
		if (other.columnName != null)
		    return false;
	    } else if (!columnName.equals(other.columnName))
		return false;
	    if (datasize != other.datasize)
		return false;
	    if (dataType == null) {
		if (other.dataType != null)
		    return false;
	    } else if (!dataType.equals(other.dataType))
		return false;
	    if (digits != other.digits)
		return false;
	    if (isNull != other.isNull)
		return false;
	    return true;
	}

	/**
	 * @return the table
	 */
	public DbTableDescriptor getTable() {
	    return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(DbTableDescriptor table) {
	    this.table = table;
	}

	public String getDataTypeConvertito() {
		return dataTypeConvertito;
	}

	public void setDataTypeConvertito(String dataTypeConvertito) {
		this.dataTypeConvertito = dataTypeConvertito;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

    public String getDataTypeConvertito2() {
        return dataTypeConvertito2;
    }

    public void setDataTypeConvertito2(String dataTypeConvertito2) {
        this.dataTypeConvertito2 = dataTypeConvertito2;
    } 
}
