/*  A Breif overview of the Object Structure
 *
 *  +-----------------------------------+
 *  |             JFrame                |   ZeaxanthinGui
 *  |+---------------------------------+|
 *  ||          JTabbedPane            ||
 *  ||+-------------------------------+||
 *  |||         JTabbedPane           |||
 *  |||+-----------------------------+|||
 *  ||||        JScrollPane          ||||
 *  ||||+---------------------------+||||
 *  |||||         JTable            |||||   ZeaTable
 *  |||||+-------------------------+|||||
 *  ||||||    DefaultTableModel    ||||||   ZeaTableModel
 *  |||||+-------------------------+|||||
 *  ||||+---------------------------+||||
 *  |||+-----------------------------+|||
 *  ||+-------------------------------+||
 *  |+---------------------------------+|
 *  +-----------------------------------+
 *
 */

/**
 * Write a description of class ZeaxanthinGui here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

package com.zeaxanthin;

/*
 * Standard Java Libraries
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.util.List;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import org.w3c.dom.Document;

/*
 * Opencsv Parser Libraries
 */
import com.opencsv.CSVReader;

/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.gui.ZeaTable;
import com.zeaxanthin.io.FileTypeCSV;
import com.zeaxanthin.io.FileTypeCSVHW;
import com.zeaxanthin.io.ZeaFileIO;




@SuppressWarnings("serial")     // this class is not intended to be serialized.
public class ZeaxanthinGui extends JFrame 
                           implements ActionListener, MouseListener
{
    @SuppressWarnings("serial")     // this class is not intended to be serialized.

    /**
     * The name of the program: Zeaxanthin
     */
    public static final String ZEAXANTHIN = "Zeaxanthin";
    
    
    
    /**
     * Zeaxanthin version
     */
    public static final String VERSION = "0.1.1";
    
    
    
    /****************************************************************
     * Menubar and Popup Components
     *  Includes:
     *      The menubar
     *      The popup menu
     *      Menus for the menubar and popup menu
     *      Menuitems for menus
     *      Accelerator constants
     */
    
    /**
     * The menubar across the top
     */
    private JMenuBar menubar;
    
    /**
     * The popup menu
     */
    private JPopupMenu popup;

    /**
     * Menus for the menubar and popup
     */
    private JMenu m_file,
                  m_new,
                  p_new,
                  m_update;
    
    /**
     * Menu Items for the menubar and popup
     */
    private JMenuItem m_exit,
                      m_new_HW,
                      p_new_HW,
                      m_open,
                      m_save,
                      m_saveAs,
                      m_updateScreen;
    
    /**
     * Accelerators (shortcuts) for the menus
     */
    private static final KeyStroke
        CTRL_O = KeyStroke.getKeyStroke(KeyEvent.VK_O,
                                        Toolkit.getDefaultToolkit()
                                               .getMenuShortcutKeyMask()),
        CTRL_S = KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                        Toolkit.getDefaultToolkit()
                                               .getMenuShortcutKeyMask()),
        CTRL_SHIFT_S = KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                              InputEvent.CTRL_DOWN_MASK+InputEvent.SHIFT_DOWN_MASK),
        CTRL_E = KeyStroke.getKeyStroke(KeyEvent.VK_E,
                                        Toolkit.getDefaultToolkit()
                                               .getMenuShortcutKeyMask());
    /* 
     * End of Menubar and Popup Components *
     ****************************************************************/
    
    
    
    /****************************************************************
     * Table Components
     */
    private ZeaTable table = null;
    private JScrollPane scrollTable = null;
    /*
     * End of Table Components
     ****************************************************************/
    
    
    
    /****************************************************************
     * File Choosing Components
     */
    private JFileChooser fileChooser;
    private FileTypeCSV csvFilter;
    /*
     * End of File Choosing Components
     ****************************************************************/
    
    
    
    /****************************************************************
     * loaded file Components
     */
    private File loadedFile = null;
    private boolean loadedFile_isSaved = true;
    /* 
     * Endo of loaded file Components
     ****************************************************************/
    
    
    
    /**
     * Create the default Zeaxanthin GUI. This is the only option.
     */
    public ZeaxanthinGui() {
        createMenus();
        createFileChooser();
    }
    
    
    
    /**
     * Creates a Menu Bar along the top of the window/JFrame and contains
     * multiple options. Also creates a popup menu
     */
    private void createMenus() {        
        /*
         * Create JMenuItems for the JMenuBar and JPopupMenu
         */
        //Create the "Exit" item for the JMenuBar
        this.m_exit = new JMenuItem("Exit", KeyEvent.VK_E);
             m_exit.setToolTipText("Exit Zeaxanthin");
             m_exit.setAccelerator(CTRL_E);
             m_exit.addActionListener(this);
        
        //Create the "Open" item for the JMenuBar
        this.m_open = new JMenuItem("Open...", KeyEvent.VK_O);
             m_open.setToolTipText("Open a File");
             m_open.setAccelerator(CTRL_O);
             m_open.addActionListener(this);
        
        //Create the "Hardy-Weinburg" item for the JMenuBar
        this.m_new_HW = new JMenuItem("Hardy-Weinburg", KeyEvent.VK_H);
             m_new_HW.setToolTipText("Create a New Hardy-Weinburg Simulation");
             m_new_HW.addActionListener(this);
        
        //Create the "Hardy-Weinburg" item for the JPopupMenu
        this.p_new_HW = new JMenuItem("Hardy-Weinburg", KeyEvent.VK_H);
             p_new_HW.setToolTipText("Create a New Hardy-Weinburg Simulation");
             p_new_HW.addActionListener(this);
        
        //Create the "Save" item for the JMenuBar
        this.m_save = new JMenuItem("Save", KeyEvent.VK_S);
             m_save.setToolTipText("Save File");
             m_save.setAccelerator(CTRL_S);
             m_save.addActionListener(this);
        
        //Create the "Save As" item for the JMenuBar
        this.m_saveAs = new JMenuItem("Save As...", KeyEvent.VK_A);
             m_saveAs.setToolTipText("Save File As");
             m_saveAs.setAccelerator(CTRL_SHIFT_S);
             m_saveAs.addActionListener(this);
        
        //Create the "Update Pedigree" item for the JMenuBar
        this.m_updateScreen = new JMenuItem("Update Screen", KeyEvent.VK_U);
             m_updateScreen.setToolTipText("Update the Screen Graphics");
             m_updateScreen.addActionListener(this);
        
        
        /*
         * Create higher level JMenus (Order dependent)
         */
        //Create the "New Simulation..." menu for the JMenuBar
        this.m_new = new JMenu("New Simulation...");
             m_new.setMnemonic(KeyEvent.VK_N);
             m_new.add(m_new_HW);
        
        //Create the "New Simulation..." menu for the JPopupMenu
        this.p_new = new JMenu("New Simulation...");
             p_new.add(p_new_HW);
        
        //Create the "File" menu for the JMenuBar
        this.m_file = new JMenu("File");
             m_file.setMnemonic(KeyEvent.VK_F);
             //populate with submenus/menu items
             m_file.add(m_new);
             m_file.add(m_open);
             m_file.add(m_save);
             m_file.add(m_saveAs);
             m_file.add(m_exit);
        
        //Create the "Update" menu for the JMenuBar
        this.m_update = new JMenu("Update");
             m_update.setMnemonic(KeyEvent.VK_U);
             m_update.add(m_updateScreen);
        
        
        /*
         * Create highest level JMenuBar and JPopupMenu
         */
        this.menubar = new JMenuBar();
             menubar.add(m_file);
             menubar.add(m_update);
        this.setJMenuBar(menubar);
        
        //initialize the first popup menu
        this.popup = new JPopupMenu();
             popup.add(p_new);
        
        //'this' listens for when the mouse is right clicked
        addMouseListener(this);
        
        //update the screen
        setVisible(true);
        return;
    }
    
    
    
    /**
     * Create the JFileChooser for selecting and opening files;
     * create filter options for files to load.
     */
    private void createFileChooser() {
        //initialize JFileChooser
        fileChooser = new JFileChooser();
        
        //we only want files of types we recognize
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        //create file filters for desired file types
        csvFilter = new FileTypeCSV(); //filter for *.csv
        
        //add file filters for desired file types
        fileChooser.addChoosableFileFilter(csvFilter);
    }
    
    
    
    /**
     * Briefly open an dialog box telling the user that there are unsaved
     * changes that have been made.
     */
    private void openDialog_unsavedChanges() {
        //TODO: probably add a warning "don't overwrite old data"
        //TODO: If file is not saved
        int pane = 
            JOptionPane.showOptionDialog
                        (this,
                         "You have unsaved changes. Would you like to save these changes?",
                         "Unsaved Changes",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.WARNING_MESSAGE,
                         null,
                         null,
                         null);
        if(pane == JOptionPane.YES_OPTION) {
            saveTable();
        }
        else if(pane == JOptionPane.NO_OPTION) {
            return;
        }
        else {
            return;
        }
    }
    
    
    
    /**
     *
     */
    private void createTable(int row, int col) {
        //test to see if table has been previously created
        if(this.scrollTable != null) {
            //TODO: probably add a warning "don't overwrite old data"
            //remove old panel
            remove(scrollTable);
        }
        //create the table
        this.table = new ZeaTable(row, col);
        this.scrollTable = new JScrollPane( this.table );
        
        //add it to the gui
        add( this.scrollTable );
        
        //update the screen
        setVisible(true);
    }
/*    private void createTable(Object[][] data, Object[] headers) {
        //test to see if table has been previously created
        if(this.table == null && this.scrollTable == null) {
            //create the table
            this.table = new ZeaTable(data, headers);
            this.scrollTable = new JScrollPane( this.table );
            
            //add it to the gui
            add( this.scrollTable );
            
            //update the screen
            setVisible(true);
        }
        //table has already been created
        else{
            //TODO: probably add a warning "don't overwrite old data"
            //remove old panel
            remove(scrollTable);
            
            //make new pointers
            this.table = new ZeaTable(data, headers);
            this.scrollTable = new JScrollPane( this.table );
            
            //add it to the gui
            add( this.scrollTable );
            
            //update the screen
            setVisible(true);
        }
    }*/
    private void createTable(ZeaTable table) {
        //determine if there is already a table active
        if(this.scrollTable != null) {
            openDialog_unsavedChanges();
            
            //remove old panel
            remove(scrollTable);
        }
        
        //add the table
        this.table = table;
        
        //create and add the JScrollPane
        this.scrollTable = new JScrollPane( this.table );
        
        //add the scrollTable to the gui
        add(this.scrollTable);
        
        //update screen
        setVisible(true);
        return;
    }
    
    
    
    /**
     * Save the table opened up to a file.
     */
    public void saveTable() {
        if(this.loadedFile != null) {
            if(ZeaFileIO.getExtension(loadedFile)
                        .equalsIgnoreCase(FileTypeCSV.CSV_FILE_EXT) /*.csv*/) {
                csvFilter.write(this.table, this.loadedFile);
                this.setSaveStatus(true);
            }
        }
        else if(this.table != null){
            int returnValue = fileChooser.showSaveDialog(this);
            
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                //get the name of the file we're saving to
                this.loadedFile = fileChooser.getSelectedFile();
                

                if(fileChooser.getFileFilter() == csvFilter /*.csv*/) {
                    //make sure filename has the right ending (.csv)
                    this.loadedFile = csvFilter.processFilename(this.loadedFile);
                    csvFilter.write(this.table, this.loadedFile);
                    this.setSaveStatus(true);
                    return;
                }
                
            }
        }
        else {
            return;
        }
    }
    
    
    
    /**
     * Determines if the file has been modified.
     *
     * @return 'true' if the file has been modified, 'false' otherwise.
     */
    public boolean fileHasBeenModified() {
        return !loadedFile_isSaved;
    }
    
    
    
    /**
     * Sets the save status variable (loadedFile_isSaved) to isSaved.
     * Changes the title of the Window to reflect the file which is open
     * and whether it has been modified. The title will appear in the
     * format: "Zeaxanthin - <*><Filename>" The <*> means the file has
     * been modified.
     */
    public void setSaveStatus(boolean isSaved) {
        this.loadedFile_isSaved = isSaved;
        
        if(!isSaved) {
            if(this.loadedFile != null) {
                if(getTitle().equals(ZEAXANTHIN + " - *" + loadedFile.getName())) {
                    return;
                }
                else {
                    setTitle(ZEAXANTHIN + " - *" + loadedFile.getName());
                    return;
                }
            }
            else {
                if(getTitle().equals(ZEAXANTHIN + " - *" + "[Untitled]")) {
                    return;
                }
                else {
                    setTitle(ZEAXANTHIN + " - *" + "[Untitled]");
                    return;
                }
            }
        }
        else {
            if(this.loadedFile != null) {
                if(getTitle().equals(ZEAXANTHIN + " - " + loadedFile.getName())) {
                    return;
                }
                else {
                    setTitle(ZEAXANTHIN + " - " + loadedFile.getName());
                    return;
                }
            }
            else {
                if(getTitle().equals(ZEAXANTHIN + " - " + "[Untitled]")) {
                    return;
                }
                else {
                    setTitle(ZEAXANTHIN + " - " + "[Untitled]");
                    return;
                }
            }
        }
    }
    
    
    
    /**
     * Safely closes any objects that need to be closed.
     * Ex: Files
     */
    private void pre_kill_protocols() {
        
    }

    
    
    /**
     * Implementation of the ActionListener interface.
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
        /*
         * Exit the program
         */
        if(e.getSource() == m_exit) {
            //if the loaded file has not been saved
            if(!loadedFile_isSaved) {
                openDialog_unsavedChanges();
            }
            pre_kill_protocols();
            System.exit(0);
        }
        
        
        
        /*
         * Create a table
         */
        if(e.getSource() == m_new_HW || e.getSource() == p_new_HW) {            
            createTable(7, 7);
            setSaveStatus(true);
        }
        
        
        
        /*
         * Open a file
         */
        if(e.getSource() == m_open) {
            int returnValue = fileChooser.showOpenDialog(this);
            
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                this.loadedFile = fileChooser.getSelectedFile();
                
                if(ZeaFileIO.getExtension( loadedFile )
                            .equalsIgnoreCase(FileTypeCSV.CSV_FILE_EXT) /*.csv*/) {
                    createTable( csvFilter.read(loadedFile));   //call csv filter
                                                                //and create table
                    setSaveStatus(true);                        //update save status,
                                                                //update gui title
                }
                else {
                    return;
                }
            }
        }
        
        
        
        /*
         * Save the data
         */
        if(e.getSource() == m_save) {
            saveTable();
        }
        
        
        
        /*
         * Save the data as/save a copy.
         */
        if(e.getSource() == m_saveAs) {
            fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
            fileChooser.setDialogTitle("Save File As...");
            int returnValue = fileChooser.showDialog(this, "Save As");
            
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                this.loadedFile = fileChooser.getSelectedFile();
                
                if(fileChooser.getFileFilter() == csvFilter /*.csv*/) {
                    //make sure filename has the right ending (.csv)
                    this.loadedFile = csvFilter.processFilename(this.loadedFile);
                    
                    //write the file
                    csvFilter.write(this.table, this.loadedFile);
                    
                    //update save status and window header
                    this.setSaveStatus(true);
                    return;
                }
            }
        }
        
        
        
        /*
         * Update
         */
        if(e.getSource() == m_updateScreen) {
            this.setVisible(true);
        }
    }
    
    
    
    /**
     * Implementation(s) of the MouseListener interface.
     */
    public void mouseClicked(MouseEvent e) { return; }
    public void mouseEntered(MouseEvent e) { return; }
    public void mouseExited(MouseEvent e)  { return; }
    public void mousePressed(MouseEvent e) { return; }
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            popup.show(e.getComponent(), e.getX(), e.getY());
            
        }
    }

    
    
    /**
     * 'main' method; begins Zeaxanthin
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ZeaxanthinGui gui = new ZeaxanthinGui();

                gui.getContentPane().setBackground( Color.BLACK );
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //gui.pack();
                gui.setSize(500, 500);
                gui.setVisible(true);
                gui.setTitle(ZEAXANTHIN);
            }
        });
    }
    
    
    
    /**
     * In the event that someone tries to serialize this object, throw an exception.
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        throw new IOException("The class: " + this.getClass().getSimpleName() +
                              " is NOT serializable.");
    }
}
