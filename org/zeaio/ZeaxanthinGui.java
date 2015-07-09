package org.zeaio;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;



public class ZeaxanthinGui extends JFrame
{
//     private JLabel label;
//     private JButton button;
//     private JTextField field;
//     private ImageIcon image1, image2;
    
    JMenuBar menubar;
    JPopupMenu popup;
    
    public ZeaxanthinGui()
    {
        createMenuBar();
        
        
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
    
    private void createMenuBar()
    {
        menubar = new JMenuBar();
        
        /*
         * Menus
         */
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        JMenu node = new JMenu("Node");
        file.setMnemonic(KeyEvent.VK_N);
        
        /*
         * Menus Items for "File"
         */
        JMenuItem file_exit = new JMenuItem("Exit", KeyEvent.VK_E);
        file_exit.setToolTipText("Exit Zeaxanthin");
        file_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JMenuItem file_open = new JMenuItem("Open...", KeyEvent.VK_O);
        file_open.setToolTipText("Open a File");
        file_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        JMenuItem file_new = new JMenuItem("New", KeyEvent.VK_N);
        file_new.setToolTipText("Create a New Maize Inheritance Model file");
        file_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        JMenuItem file_save = new JMenuItem("Save", KeyEvent.VK_S);
        file_save.setToolTipText("Save File");
        file_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        JMenuItem file_saveAs = new JMenuItem("Save As...");
        //file_saveAs.setMnemonic(KeyEvent.VK_O);
        file_saveAs.setToolTipText("Save File");
        file_saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        
        /*
         * Menu Items for "Node"
         */
        JMenu node_new = new JMenu("New...");
        node_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
            JMenuItem node_new_plant = new JMenuItem("Plant Node", KeyEvent.VK_P);
            node_new_plant.setToolTipText("Create a New Plant Node");
            node_new_plant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });
            JMenuItem node_new_cob = new JMenuItem("Cob Node", KeyEvent.VK_C);
            node_new_cob.setToolTipText("Create a New Cob Node");
            node_new_cob.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                }
            });
            node_new.add(node_new_plant);
            node_new.add(node_new_cob);
        JMenuItem node_edit = new JMenuItem("Edit...", KeyEvent.VK_E);
        node_edit.setToolTipText("Create a New Maize Inheritance Model file");
        node_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        
        
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
    
    public void createPopupMenu()
    {
        popup = new JPopupMenu();
    }


    public static void main(String[] args)
    {
        ZeaxanthinGui gui = new ZeaxanthinGui();
        
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //gui.pack();
        gui.setSize(500, 500);
        gui.setVisible(true);
        gui.setTitle("Zeaxanthin");
        
    }
}