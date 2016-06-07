package com.zeaxanthin.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Point;
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

public class ZeaTable extends JTable implements ActionListener, MouseListener
{
    /*
     * A custom TableModel. Make this instance data to keep track of
     * where the pointer is.
     */
    protected ZeaTableModel zeaTableModel;
    
    /****************************************************************
     * Popup menus
     */
    private JPopupMenu popup;
    private JMenu popup_insertRow, popup_insertCol;
    private JMenuItem popup_insertRow_up, popup_insertRow_down,
                      popup_insertCol_L, popup_insertCol_R;
    /*
     * End of Popup menus
     ****************************************************************/
    
    /*
     * Coordinates of the selected cell.
     */
    private int coord_row,
                coord_col;
    
    
    
    /*
     * Constructors
     */
    public ZeaTable() {
        //call constructor that uses a ZeaTableModel
        //this constructor will handle everything
        this(new ZeaTableModel());  
    }
    public ZeaTable(int numRows, int numColumns) {
        //call constructor that uses a ZeaTableModel
        //this constructor will handle everything
        this(new ZeaTableModel(numRows, numColumns));
    }
    public ZeaTable(Object[][] rowData, Object[] columnNames, Object[] columnClass) {
        //call constructor that uses a ZeaTableModel
        //this constructor will handle everything
        this(new ZeaTableModel(rowData, columnNames, columnClass));
    }
    public ZeaTable(ZeaTableModel dm) {
        super(dm);                      //call super-class constructor
        
        this.zeaTableModel = dm;        //save the object pointer
        
                                        //create a row sorter
        this.setRowSorter( new TableRowSorter(dm) );
        
        createPopupMenus();             //create popup
        
        setCellSelectionEnabled(true);  //Allow cells in this table to be selected
    }
    public ZeaTable(ZeaTableModel dm, TableColumnModel cm) {
        super(dm, cm);                  //call super-class constructor
        
        this.zeaTableModel = dm;        //save the object pointer

                                        //create a row sorter
        this.setRowSorter( new TableRowSorter(dm) );
        
        createPopupMenus();             //create popup
        
        setCellSelectionEnabled(true);  //Allow cells in this table to be selected
    }
    public ZeaTable(ZeaTableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm, cm, sm);              //call super-class constructor
        
        this.zeaTableModel = dm;        //save the object pointer
        
                                        //create a row sorter
        this.setRowSorter( new TableRowSorter(dm) );
        
        createPopupMenus();             //create popup
        
        setCellSelectionEnabled(true);  //Allow cells in this table to be selected
    }
    public ZeaTable(Vector rowData, Vector columnNames, Vector<String> columnClass) {
        //call constructor that uses a ZeaTableModel
        //this constructor will handle everything
        this(new ZeaTableModel(rowData, columnNames, columnClass));
    }
    
    
    
    /*
     * MouseListener implementations
     */
    public void mouseClicked(MouseEvent e) {
        //if right clicked
        if(e.getButton() == MouseEvent.BUTTON3) {
            clearSelection();             //clear selected cells
            Point p = e.getPoint();       //get where clicked
            coord_row = rowAtPoint(p);    //get table row where clicked
            coord_col = columnAtPoint(p); //get table column where clicked
            
            //select single cell where right clicked
            addRowSelectionInterval(coord_row, coord_row);
            setColumnSelectionInterval(coord_col, coord_col);
            
            //display the popup menu
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    public void mouseEntered(MouseEvent e)  { return; }
    public void mouseExited(MouseEvent e)   { return; }
    public void mousePressed(MouseEvent e)  { return; }
    public void mouseReleased(MouseEvent e) { return; }
    
    
    
    /*
     * ActionListener implementations
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == popup_insertRow_up) {
            
        }
        if(e.getSource() == popup_insertRow_down) {
            
        }
        if(e.getSource() == popup_insertCol_L) {
            
        }
        if(e.getSource() == popup_insertCol_R) {
            
        }
    }
    
    
    
    @Override
    public void moveColumn(int column, int targetColumn) {
        super.moveColumn(column, targetColumn);
        this.zeaTableModel.moveColumnClassIdentifiers(column, targetColumn);
        return;
    }
    
    
    
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);

        //Go up 6 Layers!!!
        Container parent = this.getParent()
                               .getParent()
                               .getParent()
                               .getParent()
                               .getParent()
                               .getParent();

        //alternative to the above (if the position heritage of the ZeaTable
        //is not known)
        /*Component parent = this;
        while( !(parent instanceof JFrame) && parent instanceof Component) {
            parent = parent.getParent();
        }*/
        if(parent instanceof ZeaxanthinGui) {
            ((ZeaxanthinGui)parent).setSaveStatus(false);
        }
        return;
    }
    
    
    
    /*
     * Retrieve our ZeaTableModel pointer
     */
    public ZeaTableModel getZeaTableModel() { return this.zeaTableModel; }
    
    
    
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
        
        //create a menu item for "New..."
        this.popup_insertCol_L = new JMenuItem("Left Current Column");
             popup_insertCol_L.setToolTipText("Insert a New Column Left of Current Column");
             popup_insertCol_L.addActionListener(this); //'this' listens for when this menu is selected
        
        //create another menu item for "New..."
        this.popup_insertCol_R = new JMenuItem("Right Current Column");
             popup_insertCol_R.setToolTipText("Insert a New Column Right of Current Column");
             popup_insertCol_R.addActionListener(this); //'this' listens for when this menu is selected
        
        /*
         * Create JMenus
         */
        //create a submenu that will contain menu items within itself
        this.popup_insertRow = new JMenu("Insert Row..."); 
             //Add options to the "Insert Row..." menu
             popup_insertRow.add(popup_insertRow_up);
             popup_insertRow.add(popup_insertRow_down);
            
        //create another submenu that will contain menu items within itself
        this.popup_insertCol = new JMenu("Insert Column...");
             //add menu items created above to the "New..." popup submenu
             popup_insertCol.add(popup_insertCol_L);
             popup_insertCol.add(popup_insertCol_R);
        
        /*
         * Create JPopupMenu
         */
        //initialize the popup menu
        this.popup = new JPopupMenu();
             //add submenus and menu items to popup
             popup.add(popup_insertRow);
             popup.add(popup_insertCol);
        
        
        //'this' listens for when the mouse is right clicked
        addMouseListener(this);
        
        
        return;
    }
}
