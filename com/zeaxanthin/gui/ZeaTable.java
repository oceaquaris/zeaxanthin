package com.zeaxanthin.gui;


/*
 * Standard Java Libraries &
 * Standard Swing Libraries
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.gui.ZeaTableModel;
import com.zeaxanthin.io.SaveStatus;
import com.zeaxanthin.io.SaveStatusListener;
import com.zeaxanthin.ZeaxanthinGui;

// This class is not intended to be serialized.
@SuppressWarnings("serial")
public class ZeaTable extends JTable
                      implements ActionListener,
                                 MouseListener,
                                 SaveStatus,
                                 SaveStatusListener
{
    /**
     * A custom TableModel. This is instance data to make it easier to keep
     * track of the TableModel.
     */
    protected ZeaTableModel zeaTableModel;
    
    
    
    /**
     * All SaveStatus children this object responds to.
     */
    protected Vector<SaveStatus> saveStatusChildren = null;
    
    
    
    /**
     * A parent class that listens for when this object is modified.
     */
    protected SaveStatusListener statusParent = null;
    
    
    
    /**
     * The save status of this object. 'true' when this object has not been modified.
     * 'false' when this object has been modified.
     */
    protected boolean saveStatus = true;
    
    
    
    /****************************************************************
     * Popup menus
     */
    private JPopupMenu popup;
    private JMenu popup_insertRow; //popup_insertCol;
    private JMenuItem popup_insertRow_up, popup_insertRow_down;
                      //popup_insertCol_L, popup_insertCol_R;
    /*
     * End of Popup menus
     ****************************************************************/
    
    
    
    /**
     * Coordinates of the selected cell. Default is 0,0.
     *
     * coord_row: Row coordinates
     * coord_col: Column coordinates
     */
    private int coord_row = 0,
                coord_col = 0;
    
    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * Constructs a default ZeaTable that is initialized with a default data model,
     * a default column model, and a default selection model.
     */
    public ZeaTable() {
        //call constructor that uses a ZeaTableModel
        //this constructor will handle everything
        this( new ZeaTableModel() );
    }
    
    
    
    /**
     * Constructs a ZeaTable with numRows and numColumns of empty cells using
     * ZeaTableModel. The columns will have names of the form "A", "B", "C", etc.
     */
    public ZeaTable(int numRows, int numColumns) {
        //call constructor that uses a ZeaTableModel
        //this constructor will handle everything
        this( new ZeaTableModel(numRows, numColumns) );
    }
    
    
    
    /**
     * Constructs a ZeaTable to display the values in the two dimensional array,
     * rowData, with column names, columnNames. rowData is an array of rows. An
     * array of column class identifiers, columnClass, must also be provided to
     * specify the type of row data in the ZeaTable/ZeaTableModel.
     */
    public ZeaTable(Object[][] rowData, Object[] columnNames, Object[] columnClass) {
        //call constructor that uses a ZeaTableModel
        //this constructor will handle everything
        this( new ZeaTableModel(rowData, columnNames, columnClass) );
    }
    
    
    
    /**
     * Constructs a ZeaTable that is initialized with dm as the data model, a
     * ZeaTableModel column model, and a default selection model.
     */
    public ZeaTable(ZeaTableModel dm) {
        super(dm);                      //call super-class constructor
        
        this.zeaTableModel = dm;        //save the object pointer
        
        this.zeaTableModel.setSaveStatusListener(this);
        this.saveStatusChildren = new Vector<SaveStatus>();

                                        //create a row sorter
        this.setRowSorter( new TableRowSorter<ZeaTableModel>(dm) );
        
        createPopupMenus();             //create popup
        
        setCellSelectionEnabled(true);  //Allow cells in this table to be selected
    }
    
    
    
    /**
     * Constructs a ZeaTable that is initialized with dm as the data model,
     * cm as the column model, and a default selection model.
     */
    public ZeaTable(ZeaTableModel dm, TableColumnModel cm) {
        super(dm, cm);                  //call super-class constructor
        
        this.zeaTableModel = dm;        //save the object pointer

        //Instantiating this variable is ORDER DEPENDENT!!! DO THIS FIRST!!!
        this.saveStatusChildren = new Vector<SaveStatus>();
        
        //Set a SaveStatusListener
        this.zeaTableModel.setSaveStatusListener(this);

                                        //create a row sorter
        this.setRowSorter( new TableRowSorter<ZeaTableModel>(dm) );
        
        createPopupMenus();             //create popup
        
        setCellSelectionEnabled(true);  //Allow cells in this table to be selected
    }
    
    
    
    /**
     * Constructs a ZeaTable that is initialized with dm as the data model,
     * cm as the column model, and sm as the selection model. If any of the
     * parameters are null this method will initialize the table with the 
     * corresponding default model. The autoCreateColumnsFromModel flag is
     * set to false if cm is non-null, otherwise it is set to true and the
     * column model is populated with suitable TableColumns for the columns in dm.
     */
    public ZeaTable(ZeaTableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm, cm, sm);              //call super-class constructor
        
        this.zeaTableModel = dm;        //save the object pointer

        //Instantiating this variable is ORDER DEPENDENT!!! DO THIS FIRST!!!
        this.saveStatusChildren = new Vector<SaveStatus>();
        
        //Set a SaveStatusListener
        this.zeaTableModel.setSaveStatusListener(this);
        
                                        //create a row sorter
        this.setRowSorter( new TableRowSorter<ZeaTableModel>(dm) );
        
        createPopupMenus();             //create popup
        
        setCellSelectionEnabled(true);  //Allow cells in this table to be selected
    }
    
    
    
    /**
     * Constructs a ZeaTable to display the values in the Vector of Vectors, 
     * rowData, with column names, columnNames. The Vectors contained in rowData
     * should contain the values for that row. A Vector of Strings is also needed
     * to specify the column class in the ZeaTable/ZeaTableModel.
     */
    public ZeaTable(Vector<?> rowData, Vector<?> columnNames, Vector<String> columnClass) {
        //call constructor that uses a ZeaTableModel
        //this constructor will handle everything
        this(new ZeaTableModel(rowData, columnNames, columnClass));
    }
    
    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * ActionListener implementation.
     *
     * Invoked when an action occurs.
     * AKA: Invoked when JMenuItems in the popup are clicked on.
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == popup_insertRow_up) {
            this.insertEmptyRowAbove(coord_row);
        }
        if(e.getSource() == popup_insertRow_down) {
            this.insertEmptyRowBelow(coord_row);
        }
        /*
        if(e.getSource() == popup_insertCol_L) {
            
        }
        if(e.getSource() == popup_insertCol_R) {
            
        }
        */
    }
    
    
    
    /**
     * SaveStatusListener implementation
     *
     * Add a child SaveStatus object.
     */
    public boolean addSaveStatusChild(final SaveStatus statusChild) {
        return this.saveStatusChildren.add(statusChild);
    }
    
    
    
    /**
     * Update the SaveStatusListener when a child has been modified.
     * This function should be called by the child.
     */
    public void childModified(final Object source, boolean isSaved) {
        this.setSaveStatusNotifySaveStatusListener(isSaved);
        return;
    }
    
    
    
    /**
     * Search for a column titled 'title'. This must be an exact match.
     */
    public int findColumn(String title) {
        int numColumns = getColumnCount();
        if(numColumns > 0) {
            for(int i = 0; i < numColumns; i++) {
                if( getColumnName(i).equals(title) ) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    
    
    /**
     * Search for a column titled 'title'. This must be an exact match.
     */
    public int findColumnIgnoreCase(String title) {
        int numColumns = getColumnCount();
        if(numColumns > 0) {
            for(int i = 0; i < numColumns; i++) {
                if( getColumnName(i).equalsIgnoreCase(title) ) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    
    
    /**
     * SaveStatus implementation
     *
     * Return the saveStatus of this object.
     */
    public boolean getSaveStatus() {
        return this.saveStatus;
    }
    
    
    
    /**
     * SaveStatusListener implementation
     *
     * Get a Vector of SaveStatus children.
     */
    public Vector<SaveStatus> getSaveStatusChildren() {
        return this.saveStatusChildren;
    }
    
    
    
    /**
     * Retrieve our ZeaTableModel pointer
     */
    public ZeaTableModel getZeaTableModel() {
        return this.zeaTableModel;
    }
    
    
    
    /**
     * Insert a new empty row above a given row.
     */
    public void insertEmptyRowAbove(int row) {
        //let the ZeaTableModel class handle this.
        zeaTableModel.insertEmptyRowAbove(row);

        //table has been modified; statusParent needs to know this.
        this.setSaveStatusNotifySaveStatusListener(false);
        
        return;
    }
    
    
    
    /**
     * Insert a new empty row below a given row.
     */
    public void insertEmptyRowBelow(int row) {
        //let the ZeaTableModel class handle this.
        zeaTableModel.insertEmptyRowBelow(row);
        
        //table has been modified; statusParent needs to know this.
        this.setSaveStatusNotifySaveStatusListener(false);
        
        return;
    }
    
    
    
    /**
     * MouseListener implementation.
     *
     * Invoked when the mouse button has been clicked (pressed and released) on
     * a component.
     */
    public void mouseClicked(MouseEvent e) {
        //this.coord_row = getSelectedRow();
        //this.coord_col = getSelectedColumn();
        
        /*if(coord_row > -1 && coord_col > -1) {
            System.out.printf("r%d,c%d:%s", coord_row, coord_col, getValueAt(coord_row, coord_col));
        }*/
    
        //if right clicked
        if(e.getButton() == MouseEvent.BUTTON3) {
            clearSelection();                  //clear selected cells
            Point p = e.getPoint();            //get where clicked
            this.coord_row = rowAtPoint(p);    //get table row where clicked
            this.coord_col = columnAtPoint(p); //get table column where clicked
            
            //select single cell where right clicked
            addRowSelectionInterval(coord_row, coord_row);
            setColumnSelectionInterval(coord_col, coord_col);
            
            //display the popup menu
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    
    
    
    /**
     * MouseListener implementation.
     *
     * Invoked when the mouse enters a component.
     */
    public void mouseEntered(MouseEvent e)  { return; }
    
    
    
    /**
     * MouseListener implementation.
     *
     * Invoked when the mouse exits a component.
     */
    public void mouseExited(MouseEvent e)   { return; }
    
    
    
    /**
     * MouseListener implementation.
     *
     * Invoked when a mouse button has been pressed on a component.
     */
    public void mousePressed(MouseEvent e)  { return; }
    
    
    
    /**
     * MouseListener implementation.
     *
     * Invoked when a mouse button has been released on a component.
     */
    public void mouseReleased(MouseEvent e) { return; }
    
    
    
    /**
     * Moves the column 'column' to the position currently occupied by the column
     * 'targetColumn' in the view. The old column at 'targetColumn' is shifted left
     * or right to make room. The column class identifiers are also shifted left
     * in the same manner.
     */
    @Override
    public void moveColumn(int column, int targetColumn) {
        super.moveColumn(column, targetColumn);
        
        this.zeaTableModel.moveColumnClassIdentifiers(column, targetColumn);
        return;
    }
    
    
    
    /**
     * SaveStatus implementation
     *
     * Set the saveStatus of the SaveStatus object.
     * This DOES NOT notify the 'statusParent' of changes to this variable.
     */
    public void setSaveStatus(boolean isSaved) {
        this.saveStatus = isSaved;
        return;
    }
    
    
    
    /**
     * Notify the children of the SaveStatusListener that the file has been saved
     * ONLY IF 'saveStatus' and 'isSaved' are different.
     */
    public void setSaveStatusNotifySaveStatusChildren(boolean isSaved) {
        if(this.saveStatus != isSaved) {
            this.saveStatus = isSaved;
            for(SaveStatus ssobj : this.saveStatusChildren) {
                if(ssobj instanceof SaveStatusListener) {
                    ((SaveStatusListener)ssobj).setSaveStatusNotifySaveStatusChildren(isSaved);
                }
                else if(ssobj instanceof SaveStatus) {
                    ssobj.setSaveStatus(isSaved);
                }
                else {
                    return;
                }
            }
        }
    }
    
    
    
    /**
     * SaveStatus implementation
     *
     * Set the SaveStatusListener for this object.
     */
    public void setSaveStatusListener(SaveStatusListener statusParent) {
        this.statusParent = statusParent;
        
        this.statusParent.addSaveStatusChild(this);
        return;
    }
    
    
    
    /**
     * SaveStatus implementation
     *
     * Set the saveStatus of the SaveStatus object AND notify the SaveStatusListener
     * ONLY IF 'isSaved' and 'saveStatus' are different.
     */
    public void setSaveStatusNotifySaveStatusListener(boolean isSaved) {
        if( this.saveStatus != isSaved ) {
            this.saveStatus = isSaved;
            this.statusParent.childModified(this, this.saveStatus);
        }
        return;
    }
    
    
    
    /**
     * Sets the value for the cell in the table model at row and column. When
     * executed, the super-parent gui is notified that the table has been modified,
     * and the super-parent sets its save status accordingly.
     */
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);

        this.setSaveStatusNotifySaveStatusListener(false);
        return;
    }
    
    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * Creates Popup Menu options for the opened window/JFrame.
     * Adds 'this' as an ActionListener for each menu item.
     * Adds a 'this' as a MouseListener
     */
    private void createPopupMenus() {
        /*
         * Create JMenuItems
         */
        //create a menu item for "Insert Row..."
        this.popup_insertRow_up = new JMenuItem("Above Current Row");
             popup_insertRow_up.setToolTipText("Insert a New Row Above Current Row");
             popup_insertRow_up.addActionListener(this); //'this' listens for when this menu is selected
        
        //create another menu item for "Insert Row..."
        this.popup_insertRow_down = new JMenuItem("Below Current Row");
             popup_insertRow_down.setToolTipText("Create a New Cob Node");
             popup_insertRow_down.addActionListener(this); //'this' listens for when this menu is selected
        
        /*
        //create a menu item for "New..."
        this.popup_insertCol_L = new JMenuItem("Left Current Column");
             popup_insertCol_L.setToolTipText("Insert a New Column Left of Current Column");
             popup_insertCol_L.addActionListener(this); //'this' listens for when this menu is selected
        
        //create another menu item for "New..."
        this.popup_insertCol_R = new JMenuItem("Right Current Column");
             popup_insertCol_R.setToolTipText("Insert a New Column Right of Current Column");
             popup_insertCol_R.addActionListener(this); //'this' listens for when this menu is selected
        */
        
        /*
         * Create JMenus
         */
        //create a submenu that will contain menu items within itself
        this.popup_insertRow = new JMenu("Insert Row..."); 
             //Add options to the "Insert Row..." menu
             popup_insertRow.add(popup_insertRow_up);
             popup_insertRow.add(popup_insertRow_down);
        
        /*
        //create another submenu that will contain menu items within itself
        this.popup_insertCol = new JMenu("Insert Column...");
             //add menu items created above to the "New..." popup submenu
             popup_insertCol.add(popup_insertCol_L);
             popup_insertCol.add(popup_insertCol_R);
        */
        
        /*
         * Create JPopupMenu
         */
        //initialize the popup menu
        this.popup = new JPopupMenu();
             //add submenus and menu items to popup
             popup.add(popup_insertRow);
             //popup.add(popup_insertCol);
        
        
        //'this' listens for when the mouse is right clicked
        addMouseListener(this);
        
        
        return;
    }

    
    
    /**
     * In the event that someone tries to serialize this object, throw an exception.
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        throw new IOException("The class: " + this.getClass().getSimpleName() +
                              " is NOT serializable.");
    }
}
