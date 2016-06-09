package com.zeaxanthin.gui;

/*
 * Standard Java Libraries
 */
import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ZeaTableModel extends DefaultTableModel {
    /**
     * Column class identifiers. Ex:"String","Integer","Double","Boolean"
     */
    protected Vector<String> columnClassIdentifiers;
    
  
  
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * Constructs a default ZeaTableModel which is a table of zero columns
     * and zero rows. No column class identifiers are specified.
     */
    public ZeaTableModel() {
        super();                        //use super-class constructor
        this.columnClassIdentifiers =   //make an empty Vector
             new Vector<String>();
    }
    
    
    
    /**
     * Constructs a ZeaTableModel with rowCount and columnCount of null 
     * object values. The default column class identifier for each column is
     * a String.
     */
    public ZeaTableModel(int rowCount, int columnCount) {
        super(rowCount, columnCount);   //use super-class constructor
        
        //make Vector and populate it with the default String class specification
        this.columnClassIdentifiers = new Vector<String>();
        for(int i = 0; i < columnCount; i++) {
            columnClassIdentifiers.add(new String("String"));
        }
    }
    
    
    
    /**
     * Constructs a ZeaTableModel and initializes the table by passing data
     * and columnNames to the setDataVector method. The first index in the
     * Object[][] array is the row index and the second is the column index.
     * Column names are specified in the second Object[] array. Column class
     * identifiers are specified in the third Object[] array.
     */
    public ZeaTableModel(Object[][] data, Object[] columnNames, Object[] columnClasses) {
        //Call another constructor
        this(processData_convertDataToVector(data, columnClasses),
             convertColumnNamesToVector(columnNames),
             convertColumnClassesToVector(columnClasses));
    }
    
    
    
    /**
     * Constructs a ZeaTableModel with as many columns as there are elements in
     * columnNames and rowCount of null object values. Each column's name will
     * be taken from the columnNames array. Column classes are specified by the
     * second Object[] array. Empty rows (specified by 'rowCount') are inserted.
     */
    public ZeaTableModel(Object[] columnNames, Object[] columnClasses, int rowCount) {
        //Call another constructor
        this(convertColumnNamesToVector(columnNames),
             convertColumnClassesToVector(columnClasses),
             rowCount);
    }
    
    
    
    /**
     * Constructs a ZeaTableModel with as many columns as there are elements in
     * columnNames and rowCount of null object values. Each column's name will be
     * taken from the columnNames vector. Column classes are specified by the
     * second Vector of Strings. Empty rows (specified by 'rowCount') are inserted.
     */
    public ZeaTableModel(Vector columnNames, Vector<String> columnClasses, int rowCount) {
        super(columnNames, rowCount);//use super-class constructor
        this.columnClassIdentifiers = columnClasses;
    }
    
    
    
    /**
     * Constructs a ZeaTableModel and initializes the table by passing data and
     * columnNames to the setDataVector method. Column class specifiers are added.
     */
    public ZeaTableModel(Vector data, Vector columnNames, Vector<String> columnClasses) {
        super(data, columnNames);//use super-class constructor
        this.columnClassIdentifiers = columnClasses;
    }

    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * Retrieve the Vector of Strings containing the column class identifiers.
     */
    public Vector<String> getColumnClassIdentifiers() {
        return this.columnClassIdentifiers;
    }
    
    
    
    /**
     * Set the Vector of Strings containing the column class identifiers.
     */
    public void setColumnClassIdentifiers(Vector<String> columnClassIdentifiers) {
        this.columnClassIdentifiers = columnClassIdentifiers;
        return;
    }
    
    
    
    /**
     * Move a column class identifier to another column index.
     */
    public void moveColumnClassIdentifiers(int column, int targetColumn) {
        String str = columnClassIdentifiers.get(column),
               tmp;
        tmp = columnClassIdentifiers.set(targetColumn, str);
        columnClassIdentifiers.setElementAt(tmp, column);
        return;
    }
    
    
    
    /**
     * Add another column class identifier to the end of the Vector containing them.
     */
    public boolean addColumnClassIdentifiers(String str) {
        return columnClassIdentifiers.add(str);
    }
    
    
    
    /**
     * Return the specified class of a column.
     */
    @Override
    public Class getColumnClass(int col) {
        if(columnClassIdentifiers == null) {
            return null;
        }
        if(col < columnClassIdentifiers.size()) {
            String s = columnClassIdentifiers.get(col);
            if(s.equalsIgnoreCase("Integer") || s.equalsIgnoreCase("int")) {
                return Integer.class;
            }
            if(s.equalsIgnoreCase("Double")) {
                return Double.class;
            }
            if(s.equalsIgnoreCase("Boolean") || s.equalsIgnoreCase("bool")) {
                return Boolean.class;
            }
            if(s.equalsIgnoreCase("Long")) {
                return Long.class;
            }
            if(s.equalsIgnoreCase("Character") || s.equalsIgnoreCase("char")) {
                return Character.class;
            }
        }
        return String.class;
    }
    
    
    
    /**
     * Inserts an empty row in the model.
     */
    public void insertEmptyRowAbove(int row) {
        this.insertRow(row, (Object[])null);
    }
    
    
    
    /**
     * Inserts an empty row below the given row index.
     */
    public void insertEmptyRowBelow(int row) {
        this.insertRow(row+1, (Object[])null);
    }
    
    
    
    /**
     * Static methods needed by some of the constructors.
     */
    /**
     * Determines the object 
     */
    private static Class getColumnClassClass(Object[] columnClasses, int col) {
        if(col >= columnClasses.length) {
            return null;
        }
        
        Object obj = columnClasses[col];
        if(obj.toString().equalsIgnoreCase("Integer") ||
           obj.toString().equalsIgnoreCase("int")) {
            return Integer.class;
        }
        if(obj.toString().equalsIgnoreCase("Double")) {
            return Double.class;
        }
        if(obj.toString().equalsIgnoreCase("Boolean") ||
           obj.toString().equalsIgnoreCase("bool")) {
            return Boolean.class;
        }
        if(obj.toString().equalsIgnoreCase("Long")) {
            return Long.class;
        }
        return String.class;
    }
    
    private static Vector<Vector<Object>> processData_convertDataToVector(Object[][] data, Object[] columnClasses) {
        /*
         * This section converts 'data' to a Vector<Vector<Object>>. All objects in
         * 'data' will be forced to convert to Objects (but will retain their values).
         */
        Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>();   //create the Vector<Vector>
        for(int i = 0; i < data.length; i++) {  //Add Vector<Object> objects
            dataVector.add( new Vector<Object>( Arrays.asList(data[i]) ) );
        }
        
        /* 
         * This section detects if an Object within 'dataVector' is recognized as one
         * of the primative object types (String, Double, Boolean, Integer), which
         * are defined in columnClasses.
         * It forces a conversion to the defined class and replaces the object with
         * a converted version.
         * Assuming that the object is a derivative of a primative type or a String,
         * the toString method will return a String which can be parsed properly to
         * a String, Double, Boolean, or Integer.
         */
        for(int i = 0; i < columnClasses.length; i++) {
            Class colClass = getColumnClassClass(columnClasses, i);
            if(colClass.equals(Integer.class)) {
                for(int j = 0; j < dataVector.size(); j++) {
                    dataVector.get(j)
                              .setElementAt( new Integer(dataVector.get(j)
                                                                   .get(i)
                                                                   .toString()),
                                             i); 
                }
            }
            else if(colClass.equals(Double.class)) {
                for(int j = 0; j < dataVector.size(); j++) {
                    dataVector.get(j)
                              .setElementAt( new Double(dataVector.get(j)
                                                                  .get(i)
                                                                  .toString()),
                                             i);
                }
            }
            else if(colClass.equals(Boolean.class)) {
                for(int j = 0; j < dataVector.size(); j++) {
                    dataVector.get(j)
                              .setElementAt( new Boolean(dataVector.get(j)
                                                                   .get(i)
                                                                   .toString()),
                                             i); 
                }
            }
            else /*(colClass.equals(String.class))*/ {
                for(int j = 0; j < dataVector.size(); j++) {
                    dataVector.get(j)
                              .setElementAt( new String(dataVector.get(j)
                                                                  .get(i)
                                                                  .toString()),
                                             i); 
                }
            }
        }
        
        return dataVector;
    }
    
    private static Vector convertColumnNamesToVector(Object[] columnNames) {
        return new Vector<Object>( Arrays.asList(columnNames) );
    }
    
    private static Vector<String> convertColumnClassesToVector(Object[] columnClasses) {
        Vector<String> columnClassesVector = new Vector<String>();
        for(int i = 0; i < columnClasses.length; i++) {
            columnClassesVector.add(columnClasses[i].toString());
        }
        return columnClassesVector;
    }
}
