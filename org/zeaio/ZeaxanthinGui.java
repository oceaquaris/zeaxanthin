package org.zeaio;

//import java.awt.EventQueue;
//import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;

//import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
//import javax.swing.JTextField;
//import javax.swing.ImageIcon;



public class ZeaxanthinGui extends JFrame implements ActionListener, MouseListener
{
//     private JLabel label;
//     private JButton button;
//     private JTextField field;
//     private ImageIcon image1, image2;
    
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
    private JPopupMenu popup;
    //Menus Items for the popup menu
    private JMenu popup_node_new;
    private JMenuItem popup_node_edit, popup_node_new_cob, popup_node_new_plant;
    /* End of Popup Menu Components *
     *================================================*/
    
    /*================================================
     * File Choosing Components */
    private JFileChooser fileNavGui;
    /* End of File Choosing Components *
     *================================================*/
    
    File loadedFile;
    
    public ZeaxanthinGui()
    {
        createMenuBar();
        createPopupMenu();
        //initialize JFileChooser
        fileNavGui = new JFileChooser();
        
        
//         setLayout(new FlowLayout());
//         
//         image1 = new ImageIcon(getClass().getResource("corn-kernel-diagram.jpg"));
//         label1 = new JLabel(image1);
//         add(label1);
//         
//         image2 = new ImageIcon(getClass().getResource("corn-kernel-anatomy.png"));
//         label2 = new JLabel(image2);
//         add(label2);
        
//         setLayout(new FlowLayout());
//         
//         label = new JLabel("Label");
//         add(label);
//         
//         field = new JTextField("Field");
//         add(field);
//         
//         button = new JButton("Button");
//         add(button);
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
                node_edit.setToolTipText("Create a New Maize Inheritance Model file");
                node_edit.addActionListener(this);
                
                /*
                 * Menu Items for "Update"
                 */
                this.updatePedigree = new JMenu("Update Pedigree");
                updatePedigree.setMnemonic(KeyEvent.VK_A);
                updatePedigree.setToolTipText("Update all Pedigree Graphics");
                update.add(updatePedigree);
            
            //Add all the menu items
            file.add(file_new);
            file.add(file_open);
            file.add(file_save);
            file.add(file_saveAs);
            file.add(file_exit);
            
            node.add(node_new);
            node.addSeparator();
            node.add(node_edit);
        
        menubar.add(file);
        menubar.add(node);
        
        setJMenuBar(menubar);
    }
    
    /**
     * Creates Popup Menu options for the opened window/JFrame.
     */
    private void createPopupMenu()
    {
        this.popup = new JPopupMenu();
        
            this.popup_node_new = new JMenu("New...");
                this.popup_node_new_plant = new JMenuItem("Plant Node", KeyEvent.VK_P);
                popup_node_new_plant.setToolTipText("Create a New Plant Node");
                popup_node_new_plant.addActionListener(this);
                
                this.popup_node_new_cob = new JMenuItem("Cob Node", KeyEvent.VK_C);
                popup_node_new_cob.setToolTipText("Create a New Cob Node");
                popup_node_new_cob.addActionListener(this);
                
            popup_node_new.add(popup_node_new_plant);
            popup_node_new.add(popup_node_new_cob);
            
            this.popup_node_edit = new JMenuItem("Edit...", KeyEvent.VK_E);
            popup_node_edit.setToolTipText("Create a New Maize Inheritance Model file");
            popup_node_edit.addActionListener(this);
        
        popup.add(popup_node_new);
        popup.addSeparator();
        popup.add(node_edit);
        
        addMouseListener(this);
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
        if(e.getSource() == file_exit) {
            pre_kill_protocols();
            System.exit(0);
        }
        if(e.getSource() == file_new) {
            
        }
        if(e.getSource() == file_open) {
            int returnValue = fileNavGui.showOpenDialog(this);
            
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                loadedFile = fileNavGui.getSelectedFile();
            }
        }
        if(e.getSource() == file_save) {
            int returnValue = fileNavGui.showSaveDialog(this);
            
            if(returnValue == JFileChooser.APPROVE_OPTION) {
                String savePath = fileNavGui.getSelectedFile().getAbsolutePath();
                //TODO save Java object in serializable form.
                System.out.println(savePath);
                //System.exit(0);
            }
        }
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
           e.getSource() == popup_node_new_cob) {
            
        }
        if(e.getSource() == node_new_plant ||
           e.getSource() == popup_node_new_plant) {
            
        }
        if(e.getSource() == node_edit ||
           e.getSource() == popup_node_edit) {
            
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
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public static void main(String[] args)
    {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ZeaxanthinGui gui = new ZeaxanthinGui();

                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //gui.pack();
                gui.setSize(500, 500);
                gui.setVisible(true);
                gui.setTitle("Zeaxanthin");
            }
        });
        
        
        
    }
}