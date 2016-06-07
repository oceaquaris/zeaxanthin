package com.zeaxanthin.gui;

/*
 * Standard Java Libraries
 */
import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ZeaTableModel extends DefaultTableModel {
    /*
     * Instance Data
     */
    protected Vector<String> columnClassIdentifiers;
    
    
    
    /*
     * Constructors (derived from super-class)
     */
    public ZeaTableModel() {
        super();                        //use super-class constructor
        this.columnClassIdentifiers =   //make an empty Vector
             new Vector<String>();
    }
    public ZeaTableModel(int rowCount, int columnCount) {
        super(rowCount, columnCount);   //use super-class constructor
        
        //make Vector and populate it with the default String class specification
        this.columnClassIdentifiers = new Vector<String>();
        for(int i = 0; i < columnCount; i++) {
            columnClassIdentifiers.add(new String("String"));
        }
    }
    public ZeaTableModel(Object[][] data, Object[] columnNames, Object[] columnClasses) {
        //Call another constructor
        this(processData_convertDataToVector(data, columnClasses),
             convertColumnNamesToVector(columnNames),
             convertColumnClassesToVector(columnClasses));
    }
    public ZeaTableModel(Object[] columnNames, Object[] columnClasses, int rowCount) {
        //Call another constructor
        this(convertColumnNamesToVector(columnNames),
             convertColumnClassesToVector(columnClasses),
             rowCount);
    }
    public ZeaTableModel(Vector columnNames, Vector<String> columnClasses, int rowCount) {
        super(columnNames, rowCount);//use super-class constructor
        this.columnClassIdentifiers = columnClasses;
    }
    public ZeaTableModel(Vector data, Vector columnNames, Vector<String> columnClasses) {
        super(data, columnNames);//use super-class constructor
        this.columnClassIdentifiers = columnClasses;
    }

    
    
    /*
     * Methods to manipulate columnClassIdentifiers
     */
    public Vector getColumnClassIdentifiers() {
        return columnClassIdentifiers;
    }
    public void setColumnClassIdentifiers(Vector<String> columnClassIdentifiers) {
        this.columnClassIdentifiers = columnClassIdentifiers;
    }
    public void moveColumnClassIdentifiers(int column, int targetColumn) {
        String str = columnClassIdentifiers.get(column),
               tmp;
        tmp = columnClassIdentifiers.set(targetColumn, str);
        columnClassIdentifiers.setElementAt(tmp, column);
        return;
    }
    public boolean addColumnClassIdentifiers(String str) {
        return columnClassIdentifiers.add(str);
    }
    
    
    
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
        }
        return String.class;
    }
    
    
    
    /*
     * Static methods needed by some of the constructors.
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