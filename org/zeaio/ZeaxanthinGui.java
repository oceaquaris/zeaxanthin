package org.zeaio;

/*
 * Standard Java libraries
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.Exception;
import java.lang.RuntimeException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileFilter;
import org.w3c.dom.Document;

/*
 * Our libraries
 */
import org.zeaio.FileFilterZXT;
import org.zeatrace.*;

/*
 * The mxGraph libraries
 */
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.io.mxCodec;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;


public class ZeaxanthinGui extends JFrame implements ActionListener, MouseListener
{
    /**
     * Zeaxanthin version
     */
    public static final String VERSION = "0.1.0";

    
    /*================================================
     * Menubar Components */
    private JMenuBar menubar;
    //Menus for the menubar
    private JMenu file, node, update;
    //Menu Items for "File"
    private JMenuItem file_exit, file_new, file_open, file_save, file_saveAs;
    //Menu Items for "Node"
    private JMenu node_new;
    private JMenuItem node_new_cob, node_new_plant, node_edit;
    //Menu Items for "Update"
    private JMenuItem updatePedigree;
    /* End of Menubar Components *
     *================================================*/
    
    
    /*================================================
     * Popup Menu Components */
    private JPopupMenu popup_new, popup_edit;
    //Menus Items for the popup menu
    private JMenu popup_new_node;
    private JMenuItem popup_edit_node, popup_new_node_cob, popup_new_node_plant;
    //passing the coordinates to ActionListener
    private int click_x = 0, click_y = 0;
    /* End of Popup Menu Components *
     *================================================*/
    
    
    /*================================================
     * File Choosing Components */
    private JFileChooser fileNavGui;
    private FileFilter[] fileFilters;
    /* End of File Choosing Components *
     *================================================*/
    
    
    /*================================================
     * mxGraph environment Components */
    private mxGraph graph;
    private Object graphParent;
    private mxGraphComponent graphComponent;
    /* Endo of mxGraph environment Components *
     *================================================*/
    
    
    /*================================================
     * loaded file Components */
    private File loadedFile;
    //private boolean loadedFile_isSaved;
    /* Endo of loaded file Components *
     *================================================*/
    
    public ZeaxanthinGui()
    {
        createMenuBar();
        createPopupMenus();
        createFileChooser();
        //createNewGraph();
    }
    
    /**
     * Creates a Menu Bar along the top of the window/JFrame and contains
     * multiple options.
     */
    private void createMenuBar()
    {
        menubar = new JMenuBar();
        
            /*
             * Menus
             */
            this.file = new JMenu("File");
            file.setMnemonic(KeyEvent.VK_F);
            this.node = new JMenu("Node");
            node.setMnemonic(KeyEvent.VK_N);
            this.update = new JMenu("Update");
            update.setMnemonic(KeyEvent.VK_U);
            
                /*
                 * Menus Items for "File"
                 */
                this.file_exit = new JMenuItem("Exit", KeyEvent.VK_E);
                file_exit.setToolTipText("Exit Zeaxanthin");
                file_exit.addActionListener(this);
                
                this.file_open = new JMenuItem("Open...", KeyEvent.VK_O);
                file_open.setToolTipText("Open a File");
                file_open.addActionListener(this);
                
                this.file_new = new JMenuItem("New", KeyEvent.VK_N);
                file_new.setToolTipText("Create a New Maize Inheritance Model file");
                file_new.addActionListener(this);
                
                this.file_save = new JMenuItem("Save", KeyEvent.VK_S);
                file_save.setToolTipText("Save File");
                file_save.addActionListener(this);
                
                this.file_saveAs = new JMenuItem("Save As...");
                //file_saveAs.setMnemonic(KeyEvent.VK_O);
                file_saveAs.setToolTipText("Save File");
                file_saveAs.addActionListener(this);
                
                
                /*
                 * Menu Items for "Node"
                 */
                this.node_new = new JMenu("New...");
                    this.node_new_plant = new JMenuItem("Plant Node", KeyEvent.VK_P);
                    node_new_plant.setToolTipText("Create a New Plant Node");
                    node_new_plant.addActionListener(this);
                    
                    this.node_new_cob = new JMenuItem("Cob Node", KeyEvent.VK_C);
                    node_new_cob.setToolTipText("Create a New Cob Node");
                    node_new_cob.addActionListener(this);
                    
                node_new.add(node_new_plant);
                node_new.add(node_new_cob);
                
                this.node_edit = new JMenuItem("Edit...", KeyEvent.VK_E);
                node_edit.setToolTipText("Edit a Node");
                node_edit.addActionListener(this);
                
                /*
                 * Menu Items for "Update"
                 */
                this.updatePedigree = new JMenuItem("Update Pedigree");
                updatePedigree.setMnemonic(KeyEvent.VK_A);
                updatePedigree.setToolTipText("Update all Pedigree Graphics");
            
            //Add all the menu items
            file.add(file_new);
            file.add(file_open);
            file.add(file_save);
            file.add(file_saveAs);
            file.add(file_exit);
            
            node.add(node_new);
            node.addSeparator();
            node.add(node_edit);
            
            update.add(updatePedigree);
        
        menubar.add(file);
        menubar.add(node);
        menubar.add(update);
        
        setJMenuBar(menubar);
    }
    
    /**
     * Creates Popup Menu options for the opened window/JFrame.
     */
    private void createPopupMenus()
    {
        //initialize the first popup menu
        this.popup_new = new JPopupMenu();
        
            //create a submenu that will contain menu items within itself
            this.popup_new_node = new JMenu("New...");
            
                //create a menu item for "New..."
                this.popup_new_node_plant = new JMenuItem("Plant Node");
                popup_new_node_plant.setToolTipText("Create a New Plant Node");
                popup_new_node_plant.addActionListener(this); //'this' listens for when this menu is selected
                
                //create another menu item for "New..."
                this.popup_new_node_cob = new JMenuItem("Cob Node");
                popup_new_node_cob.setToolTipText("Create a New Cob Node");
                popup_new_node_cob.addActionListener(this); //'this' listens for when this menu is selected
                
            //add menu items created above to the "New..." popup submenu
            popup_new_node.add(popup_new_node_plant);
            popup_new_node.add(popup_new_node_cob);
            
        
        //add submenu(s) and menu items to popup
        popup_new.add(popup_new_node);
        
        
        this.popup_edit = new JPopupMenu();
        
        //create a menu item for popup menu
        this.popup_edit_node = new JMenuItem("Edit...");
        popup_edit_node.setToolTipText("Create a New Maize Inheritance Model file");
        popup_edit_node.addActionListener(this); //'this' listens for when this menu is selected
        
        popup_edit.add(popup_edit_node);
        
        //'this' listens for when the mouse is right clicked
        addMouseListener(this);
        return;
    }
    
    private void createFileChooser()
    {
        //initialize JFileChooser
        fileNavGui = new JFileChooser();
        //we only want files of type *.zxt
        fileNavGui.setAcceptAllFileFilterUsed(false);
        
        //adding file filter(s) for desired file types
        fileFilters = new FileFilter[1]; //create array...
        fileFilters[0] = new FileFilterZXT(); //filter for *.zxt
        
        for(FileFilter filter : fileFilters) { //add 'em!
            fileNavGui.addChoosableFileFilter(filter);
        }
    }
    
    private void createNewGraph()
    {
        this.graph = new mxGraph();
        this.graphParent = graph.getDefaultParent();
        
        graph.getModel().beginUpdate();
        
        try
        {
            Object v1 = graph.insertVertex(graphParent, null, "Hello", 20, 20, 80, 30);
            Object v2 = graph.insertVertex(graphParent, null, "World!", 240, 150, 80, 30);
            graph.insertEdge(graphParent, null, "Edge", v1, v2);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        this.graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
        
        graphComponent.getGraphControl().addMouseListener(this);
        
        //display it!
        setVisible(true);
    }
    
    /**
     * Precondition: 
     */
    private void displayLoadedGraph()
    {
        this.graphParent = graph.getDefaultParent();
        
        graph.getModel().beginUpdate();
        
        try {
        
        }
        finally {
            graph.getModel().endUpdate();
        }
        
        this.graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
        
        graphComponent.getGraphControl().addMouseListener(this);
        
        //display it!
        setVisible(true);
    }
    
    /**
     * Safely closes any objects that need to be closed.
     * Ex: Files
     */
    private void pre_kill_protocols()
    {
        
    }

    /**
     * Implementation of the ActionListener interface.
     */
    public void actionPerformed(ActionEvent e)
    {
        /*
         * Exit the program
         */
        if(e.getSource() == file_exit) {
            pre_kill_protocols();
            System.exit(0);
        }
        /*
         * Create a new mxGraph
         */
        if(e.getSource() == file_new) {
            createNewGraph();
        }
        /*
         * Open a file
         */
        if(e.getSource() == file_open) {
            int returnValue = fileNavGui.showOpenDialog(this);
            
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                loadedFile = fileNavGui.getSelectedFile();
                
                try {
                    Document document = mxXmlUtils.parseXml( mxUtils.readFile( loadedFile.getAbsolutePath() ) );
                    mxCodec codec = new mxCodec(document);
                    this.graph = new mxGraph();
                    codec.decode(document.getDocumentElement(), graph.getModel());
                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                
                displayLoadedGraph();
            }
        }
        /*
         * Save the mxGraph
         */
        if(e.getSource() == file_save) {
            int returnValue = fileNavGui.showSaveDialog(this);
            
            File saveFile;
            if(returnValue == JFileChooser.APPROVE_OPTION) {
            
                //may in the future allow for more save options
                if(fileNavGui.getFileFilter() == fileFilters[0]) {
                    // The following if-else statement forces the file extension
                    // to become .zxt
                    if(FileFilterZXT.getExtension( fileNavGui.getSelectedFile() )
                                    .equalsIgnoreCase(FileFilterZXT.ZEAXANTHIN_FILE_EXTENSION)) {
                        // filename is OK as is
                        saveFile = fileNavGui.getSelectedFile();
                    }
                    else {
                        //append .zxt to name
                        saveFile = new File(fileNavGui.getSelectedFile().toString()
                                            + "." + FileFilterZXT.ZEAXANTHIN_FILE_EXTENSION);
                    }
                    
                    //retrieve the path to save to
                    String savePath = saveFile.getAbsolutePath();
                    //Attempt to save the file
                    try {
                        mxCodec codec = new mxCodec();
                        String xml = mxXmlUtils.getXml(codec.encode(graph.getModel()));
                        mxUtils.writeFile(xml, savePath);
                    }
                    catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    
                    System.out.println("File saved to: " + savePath);
                    //System.exit(0);
                    
                }
                
            }
        }
        /*
         * Save the mxGraph as/save a copy.
         */
        if(e.getSource() == file_saveAs) {
            fileNavGui.setDialogType(JFileChooser.SAVE_DIALOG);
            fileNavGui.setDialogTitle("Save File As...");
            int returnValue = fileNavGui.showDialog(this, "Save As");
            
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                String saveAsPath = fileNavGui.getSelectedFile().getAbsolutePath();
                //TODO save Java object in serializable form.
                //System.out.println(path);
                //System.exit(0);
            }
        }
        if(e.getSource() == node_new_cob ||
           e.getSource() == popup_new_node_cob) {
            try {
                graph.insertVertex(graphParent, null, new Cob(), click_x, click_y, 80, 30);
            }
            catch (Exception err) {
                err.printStackTrace();
            }
        }
        if(e.getSource() == node_new_plant ||
           e.getSource() == popup_new_node_plant) {
            try {
                graph.insertVertex(graphParent, null, new Plant(), click_x, click_y, 80, 30);
            }
            catch (Exception err) {
                err.printStackTrace();
            }
        }
        if(e.getSource() == node_edit ||
           e.getSource() == popup_edit_node) {
            
        }
        if(e.getSource() == updatePedigree) {
        
        }
//         if(e.getSource() == ) {
//             
//         }
    }
    
    /**
     * Implementation(s) of the MouseListener interface.
     */
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON3) {
            Object cellSelected = graphComponent.getCellAt(e.getX(), e.getY());
            
            if(cellSelected != null) {
                popup_edit.show(e.getComponent(), e.getX(), e.getY());
            }
            else {
                click_x = e.getX();
                click_y = e.getY();
                popup_new.show(e.getComponent(), click_x, click_y);
            }
        }
    }

    public static void main(String[] args)
    {
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
                gui.setTitle("Zeaxanthin");
            }
        });
    }
}