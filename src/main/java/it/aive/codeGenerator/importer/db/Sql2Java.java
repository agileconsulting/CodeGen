/**
 * 
 */
package it.aive.codeGenerator.importer.db;

import java.sql.Types;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class has static methods for mapping SQL types to Java types. Both
 * supported and preferred types are supported.
 *
 * @author <a href="mailto:aslak.hellesoy@bekk.no">Aslak Helles�y</a>
 * @created 3. oktober 2001
 * @todo mark non-serializable types with a warning. they will call an exception
 *      in cmr (and maybe other cases too) (at least on wls 6.1). This is
 *      InputStream, Reader, Clob, Blob, Array, Ref (at least I think it's a
 *      problem. Have to test it)
 * @version $Id: Sql2Java.java,v 1.3 2004/02/04 12:09:57 ludovicc Exp $
 */
public class Sql2Java {

   /**
    * Get static reference to Log4J Logger
    */
   private static org.apache.log4j.Category _log = org.apache.log4j.Category.getInstance(Sql2Java.class.getName());

   // see resultset.gif (25 SQL types)
   // see java.sql.Types (28 SQL types in JDK1.3, 30 SQL types in JDK1.4)
   // JDK 1.3 java.sql.Types missing from table: DISTINCT, NULL, OTHER
   // JDK 1.4 java.sql.Types missing from table: DISTINCT, NULL, OTHER, DATALINK, BOOLEAN

   /**
    * @todo-javadoc Describe the column
    */
   private final static IntStringMap _javaTypesForSqlType = new IntStringMap();
   /**
    * @todo-javadoc Describe the column
    */
   private final static IntStringMap _preferredJavaTypeForSqlType = new IntStringMap();

   /**
    * @todo-javadoc Describe the column
    */
   private final static Comparator _typeComparator =
      new Comparator() {
         public int compare(Object o1, Object o2) {
            String s1 = (String)o1;
            String s2 = (String)o2;
            boolean isS1Class = s1.indexOf('.') != -1;
            boolean isS2Class = s2.indexOf('.') != -1;
            if ((isS1Class && isS2Class) || (!isS1Class && !isS2Class)) {
               // Both are class or both are primitive. Compare normally
               return s1.compareTo(s2);
            }
            else {
               // One is primitive and one is class. Primitive always first
               return isS1Class ? 1 : -1;
            }
         }


         public boolean equals(Object o1, Object o2) {
            return o1.equals(o2);
         }
      };

   /**
    * @todo-javadoc Describe the column
    */
   private final static HashMap _primitiveToClassMap = new HashMap();

   /**
    * @todo-javadoc Describe the column
    */
   private final static String[] _allJavaTypes = new String[]{
         "boolean",
         "byte",
         "byte[]",
         "double",
         "float",
         "int",
         "long",
         "short",
         "java.io.InputStream",
         "java.io.Reader",
         "java.lang.Boolean",
         "java.lang.Byte",
         "java.lang.Double",
         "java.lang.Float",
         "java.lang.Integer",
         "java.lang.Long",
         "java.lang.Short",
         "java.lang.String",
         "java.lang.Object",
         "java.math.BigDecimal",
         "java.math.BigInteger",
         "java.sql.Array",
         "java.sql.Blob",
         "java.sql.Clob",
         "java.sql.Date",
         "java.sql.Ref",
         "java.sql.Time",
         "java.sql.Timestamp",
         "java.util.Date"
         };

   /**
    * @todo-javadoc Describe the field
    */
   private final static Set _numericClasses = new HashSet();


   /**
    * Gets the TypeComparator attribute of the Sql2Java object
    *
    * @return The TypeComparator value
    */
   public static Comparator getTypeComparator() {
      return _typeComparator;
   }


   /**
    * Gets the preferred Java Type for an SQL type. It has special logic for
    * handling DECIMAL and NUMERICs with zero decimal places to return the most
    * apppropriate type.
    *
    * @param sqlType Describe what the parameter does
    * @param size Describe what the parameter does
    * @param decimalDigits Describe what the parameter does
    * @return The PreferredJavaType value
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    */
   public static String getPreferredJavaType(int sqlType, int size, int decimalDigits) {
      if ((sqlType == Types.DECIMAL || sqlType == Types.NUMERIC) && decimalDigits == 0) {
         if (size == 1) {
            // https://sourceforge.net/tracker/?func=detail&atid=415993&aid=662953&group_id=36044
            return "boolean";
         }
         else if (size < 3) {
            return "byte";
         }
         else if (size < 5) {
            return "short";
         }
         else if (size < 10) {
            return "int";
         }
         else if (size < 19) {
            return "long";
         }
         else {
            return "java.math.BigDecimal";
         }
      }
      String result = _preferredJavaTypeForSqlType.getString(sqlType);
      if (result == null) {
         result = "java.lang.Object";
      }
      return result;
   }


   /**
    * Gets the preferred Java Type for non-key columns. It has special coding
    * for handling DECIMAL and NUMERICs with zero decimal places.
    *
    * @param sqlType The SQL type code. Must be one of java.sql.Types
    * @param size Describe what the parameter does
    * @param decimalDigits Describe what the parameter does
    * @return The Java Type for this non-key column
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    * @author Jonathan O'Connor
    * @created 03 April 2002
    */
   public static String getPreferredJavaTypeNoPrimitives(int sqlType, int size, int decimalDigits) {
      String pjt = getPreferredJavaType(sqlType, size, decimalDigits);
      String pjtc = getClassForPrimitive(pjt);
      if (pjtc != null) {
         return pjtc;
      }
      else {
         return pjt;
      }
   }


   /**
    * Gets the JavaTypes attribute of the Sql2Java class
    *
    * @param sqlType Describe what the parameter does
    * @return The JavaTypes value
    * @todo-javadoc Write javadocs for method parameter
    */
   public static String[] getJavaTypes(int sqlType) {
      String[] result = _javaTypesForSqlType.getStrings(sqlType);
      if (result == null) {
         // we're dealing with non SQL'92 types, like for example MSSQL NVARCHAR
         // return all types so the user can select.
         result = _allJavaTypes;
      }
      return result;
   }


   /**
    * Gets the ClassForPrimitive attribute of the Sql2Java class
    *
    * @param primitive Describe what the parameter does
    * @return The ClassForPrimitive value
    * @todo-javadoc Write javadocs for method parameter
    */
   public static String getClassForPrimitive(String primitive) {
      return (String)_primitiveToClassMap.get(primitive);
   }


   /**
    * Gets the Primitive attribute of the Sql2Java class
    *
    * @param type Describe what the parameter does
    * @return The Primitive value
    * @todo-javadoc Write javadocs for method parameter
    */
   public static boolean isPrimitive(String type) {
      return getClassForPrimitive(type) != null;
   }


   /**
    * Gets the NumericClass attribute of the Sql2Java class
    *
    * @param type Describe what the parameter does
    * @return The NumericClass value
    * @todo-javadoc Write javadocs for method parameter
    */
   public static boolean isNumericClass(String type) {
      return _numericClasses.contains(type);
   }


   /**
    * Describe what the method does
    *
    * @param sqlType Describe what the parameter does
    * @param javaType Describe what the parameter does
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    */
   public static void overridePreferredJavaTypeForSqlType(int sqlType, String javaType) {
      _preferredJavaTypeForSqlType.put(sqlType, javaType);
   }


   /**
    * Describe what the method does
    *
    * @param sqlType Describe what the parameter does
    * @param javaTypes Describe what the parameter does
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    */
   public static void overrideAllowedJavaTypesForSqlType(int sqlType, String[] javaTypes) {
      _javaTypesForSqlType.put(sqlType, javaTypes);
   }


   /**
    * Describe what this class does
    *
    * @author Aslak Helles�y
    * @created 24. mars 2002
    * @todo-javadoc Write javadocs
    */
   private static class IntStringMap extends HashMap {

      /**
       * Gets the String attribute of the IntStringMap object
       *
       * @param i Describe what the parameter does
       * @return The String value
       * @todo-javadoc Write javadocs for method parameter
       */
      public String getString(int i) {
         return (String)get(new Integer(i));
      }


      /**
       * Gets the Strings attribute of the IntStringMap object
       *
       * @param i Describe what the parameter does
       * @return The Strings value
       * @todo-javadoc Write javadocs for method parameter
       */
      public String[] getStrings(int i) {
         return (String[])get(new Integer(i));
      }


      /**
       * Describe what the method does
       *
       * @param i Describe what the parameter does
       * @param s Describe what the parameter does
       * @todo-javadoc Write javadocs for method
       * @todo-javadoc Write javadocs for method parameter
       * @todo-javadoc Write javadocs for method parameter
       */
      public void put(int i, String s) {
         put(new Integer(i), s);
      }


      /**
       * Describe what the method does
       *
       * @param i Describe what the parameter does
       * @param sa Describe what the parameter does
       * @todo-javadoc Write javadocs for method
       * @todo-javadoc Write javadocs for method parameter
       * @todo-javadoc Write javadocs for method parameter
       */
      public void put(int i, String[] sa) {
         put(new Integer(i), sa);
      }
   }
   static {
      _primitiveToClassMap.put("byte", "java.lang.Byte");
      _primitiveToClassMap.put("short", "java.lang.Short");
      _primitiveToClassMap.put("int", "java.lang.Integer");
      _primitiveToClassMap.put("long", "java.lang.Long");
      _primitiveToClassMap.put("float", "java.lang.Float");
      _primitiveToClassMap.put("boolean", "java.lang.Boolean");
      _primitiveToClassMap.put("double", "java.lang.Double");
   }

   static {
      _numericClasses.add("java.lang.Byte");
      _numericClasses.add("java.lang.Short");
      _numericClasses.add("java.lang.Integer");
      _numericClasses.add("java.lang.Long");
      _numericClasses.add("java.lang.Float");
      _numericClasses.add("java.lang.Boolean");
      _numericClasses.add("java.lang.Double");
   }

   static {
      _javaTypesForSqlType.put(Types.TINYINT, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.String",
            "java.lang.Object",
            "java.math.BigDecimal",
            "java.math.BigInteger"
            });
      _javaTypesForSqlType.put(Types.SMALLINT, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.String",
            "java.lang.Object",
            "java.math.BigDecimal",
            "java.math.BigInteger"
            });
      _javaTypesForSqlType.put(Types.INTEGER, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.String",
            "java.lang.Object",
            "java.math.BigDecimal",
            "java.math.BigInteger"
            });
      _javaTypesForSqlType.put(Types.BIGINT, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.String",
            "java.lang.Object",
            "java.math.BigDecimal",
            "java.math.BigInteger"
            });
      _javaTypesForSqlType.put(Types.REAL, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.String",
            "java.lang.Object",
            "java.math.BigDecimal",
            "java.math.BigInteger"
            });
      _javaTypesForSqlType.put(Types.FLOAT, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.String",
            "java.lang.Object",
            "java.math.BigDecimal",
            "java.math.BigInteger"
            });
      _javaTypesForSqlType.put(Types.DOUBLE, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.String",
            "java.lang.Object",
            "java.math.BigDecimal",
            "java.math.BigInteger"
            });
      _javaTypesForSqlType.put(Types.DECIMAL, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.String",
            "java.lang.Object",
            "java.math.BigDecimal",
            "java.math.BigInteger"
            });
      _javaTypesForSqlType.put(Types.NUMERIC, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.String",
            "java.lang.Object",
            "java.math.BigDecimal",
            "java.math.BigInteger"
            });
      _javaTypesForSqlType.put(Types.BIT, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.String",
            "java.lang.Object",
            "java.math.BigDecimal",
            "java.math.BigInteger"
            });
      _javaTypesForSqlType.put(Types.CHAR, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.io.InputStream",
            "java.io.Reader",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Object",
            "java.lang.Short",
            "java.lang.String",
            "java.math.BigDecimal",
            "java.math.BigInteger",
            "java.sql.Date",
            "java.sql.Time",
            "java.sql.Timestamp",
            "java.util.Date"
            });
      _javaTypesForSqlType.put(Types.VARCHAR, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.io.InputStream",
            "java.io.Reader",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Object",
            "java.lang.Short",
            "java.lang.String",
            "java.math.BigDecimal",
            "java.math.BigInteger",
            "java.sql.Date",
            "java.sql.Time",
            "java.sql.Timestamp",
            "java.util.Date"
            });
      _javaTypesForSqlType.put(Types.LONGVARCHAR, new String[]{
            "boolean",
            "byte",
            "double",
            "float",
            "int",
            "long",
            "short",
            "java.io.InputStream",
            "java.io.Reader",
            "java.lang.Boolean",
            "java.lang.Byte",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Object",
            "java.lang.Short",
            "java.lang.String",
            "java.math.BigDecimal",
            "java.math.BigInteger",
            "java.sql.Date",
            "java.sql.Time",
            "java.sql.Timestamp",
            "java.util.Date"
            });
      _javaTypesForSqlType.put(Types.BINARY, new String[]{
            "byte[]",
            "java.lang.String",
            "java.lang.Object",
            "java.io.InputStream",
            "java.io.Reader"
            });
      _javaTypesForSqlType.put(Types.VARBINARY, new String[]{
            "byte[]",
            "java.lang.String",
            "java.lang.Object",
            "java.io.InputStream",
            "java.io.Reader"
            });
      _javaTypesForSqlType.put(Types.LONGVARBINARY, new String[]{
            "byte[]",
            "java.lang.String",
            "java.lang.Object",
            "java.io.InputStream",
            "java.io.Reader",
            });
      _javaTypesForSqlType.put(Types.DATE, new String[]{
            "java.lang.String",
            "java.lang.Object",
            "java.sql.Date",
            "java.sql.Timestamp",
            "java.util.Date",
            });
      _javaTypesForSqlType.put(Types.TIME, new String[]{
            "java.lang.String",
            "java.lang.Object",
            "java.sql.Time",
            "java.sql.Timestamp",
            "java.util.Date",
            });
      _javaTypesForSqlType.put(Types.TIMESTAMP, new String[]{
            "java.lang.String",
            "java.lang.Object",
            "java.sql.Date",
            "java.sql.Time",
            "java.sql.Timestamp",
            "java.util.Date",
            });
      _javaTypesForSqlType.put(Types.CLOB, new String[]{
            "java.lang.Object",
            "java.sql.Clob"
            });
      _javaTypesForSqlType.put(Types.BLOB, new String[]{
            "java.lang.Object",
            "java.sql.Blob"
            });
      _javaTypesForSqlType.put(Types.ARRAY, new String[]{
            "java.lang.Object",
            "java.sql.Array"
            });
      _javaTypesForSqlType.put(Types.REF, new String[]{
            "java.lang.Object",
            "java.sql.Ref"
            });
      _javaTypesForSqlType.put(Types.STRUCT, new String[]{
            "java.lang.Object"
            });
      _javaTypesForSqlType.put(Types.JAVA_OBJECT, new String[]{
            "java.lang.Object"
            });
   }

   static {
      _preferredJavaTypeForSqlType.put(Types.TINYINT, "byte");
      _preferredJavaTypeForSqlType.put(Types.SMALLINT, "short");
      _preferredJavaTypeForSqlType.put(Types.INTEGER, "int");
      _preferredJavaTypeForSqlType.put(Types.BIGINT, "long");
      _preferredJavaTypeForSqlType.put(Types.REAL, "float");
      _preferredJavaTypeForSqlType.put(Types.FLOAT, "double");
      _preferredJavaTypeForSqlType.put(Types.DOUBLE, "double");
      _preferredJavaTypeForSqlType.put(Types.DECIMAL, "java.math.BigDecimal");
      _preferredJavaTypeForSqlType.put(Types.NUMERIC, "java.math.BigDecimal");
      _preferredJavaTypeForSqlType.put(Types.BIT, "boolean");
      _preferredJavaTypeForSqlType.put(Types.CHAR, "java.lang.String");
      _preferredJavaTypeForSqlType.put(Types.VARCHAR, "java.lang.String");
      // according to resultset.gif, we should use java.io.Reader, but String is more convenient for EJB
      _preferredJavaTypeForSqlType.put(Types.LONGVARCHAR, "java.lang.String");
      _preferredJavaTypeForSqlType.put(Types.BINARY, "byte[]");
      _preferredJavaTypeForSqlType.put(Types.VARBINARY, "byte[]");
      _preferredJavaTypeForSqlType.put(Types.LONGVARBINARY, "java.io.InputStream");
      _preferredJavaTypeForSqlType.put(Types.DATE, "java.sql.Date");
      _preferredJavaTypeForSqlType.put(Types.TIME, "java.sql.Time");
      _preferredJavaTypeForSqlType.put(Types.TIMESTAMP, "java.sql.Timestamp");
      _preferredJavaTypeForSqlType.put(Types.CLOB, "java.sql.Clob");
      _preferredJavaTypeForSqlType.put(Types.BLOB, "java.sql.Blob");
      _preferredJavaTypeForSqlType.put(Types.ARRAY, "java.sql.Array");
      _preferredJavaTypeForSqlType.put(Types.REF, "java.sql.Ref");
      _preferredJavaTypeForSqlType.put(Types.STRUCT, "java.lang.Object");
      _preferredJavaTypeForSqlType.put(Types.JAVA_OBJECT, "java.lang.Object");
   }

}

