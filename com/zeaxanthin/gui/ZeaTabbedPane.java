/**
 * ZeaTabbedPane.
 *
 * This class is derived off of 6dc's 'JTabbedPaneCloseButton' class which can
 * be found at https://gist.github.com/6dc/0c8926f85d701a869bb2.
 * The 'JTabbedPaneCloseButton' class is "a class which creates a JTabbedPane
 * and auto sets a close button when you add a tab."
 * 
 */
package com.zeaxanthin.gui;



/*
 * Standard Java Libraries
 */
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.metal.MetalIconFactory;

/*
 * Zeaxanthin Libraries
 */
import com.zeaxanthin.io.SaveStatus;
import com.zeaxanthin.io.SaveStatusListener;



@SuppressWarnings("serial")     // this class is not intended to be serialized.
public class ZeaTabbedPane extends JTabbedPane implements SaveStatusListener {
    /**
     * All SaveStatus children this object responds to.
     */
    protected Vector<SaveStatus> saveStatusChildren = null;
    
    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * Creates an empty TabbedPane with a default tab placement of JTabbedPane.TOP.
     */
    public ZeaTabbedPane() {
        super();
    }
    
    
    
    /**
     * Creates an empty TabbedPane with the specified tab placement of either:
     * JTabbedPane.TOP, JTabbedPane.BOTTOM, JTabbedPane.LEFT, or JTabbedPane.RIGHT.
     */
    public ZeaTabbedPane(int tabPlacement) {
        super(tabPlacement);
    }
    
    
    
    /**
     * Creates an empty TabbedPane with the specified tab placement and tab layout
     * policy.
     */
    public ZeaTabbedPane(int tabPlacement, int tabLayoutPolicy) {
        super(tabPlacement, tabLayoutPolicy);
    }

    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * Add a child SaveStatus object.
     */
    public boolean addSaveStatusChild(final SaveStatus statusChild) {
        return this.saveStatusChildren.add(statusChild);
    }
    
    
    
    /**
     * Adds a component and tip represented by a title and/or icon, either of
     * which can be null.
     *
     * Override Addtab in order to add the close Button everytime.
     */
    @Override
    public void addTab(String title, Icon icon, Component component, String tip) {
        //create the default tab as defined in the super-class.
        super.addTab(title, icon, component, tip);
        
        //find the current tab.
        int count = this.getTabCount() - 1;
        this.setTabComponentAt(count, new TabCloseButton(component, title, icon));
    }

    
    
    /**
     * Adds a tab to the ZeaTabbedPane.
     */
    @Override
    public void addTab(String title, Icon icon, Component component) {
        this.addTab(title, icon, component, null);
    }

    
    
    /**
     * Adds a tab to the ZeaTabbedPane.
     */
    @Override
    public void addTab(String title, Component component) {
        this.addTab(title, null, component);
    }

    
    
    /**
     * Add a tab without adding a close button.
     */
    public void addTabNoExit(String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
    }

    
    
    /**
     * Add a tab without adding a close button.
     */
    public void addTabNoExit(String title, Icon icon, Component component) {
        this.addTabNoExit(title, icon, component, null);
    }

    
    
    /**
     * Add a tab without adding a close button.
     */
    public void addTabNoExit(String title, Component component) {
        this.addTabNoExit(title, null, component);
    }

    
    
    /**
     * Get a Vector of SaveStatus children.
     */
    public Vector<SaveStatus> getSaveStatusChildren() {
        return this.saveStatusChildren;
    }
    
    
    
    /**
     * Update the SaveStatusListener when a child has been modified.
     */
    public void updateSaveStatusListener(final Object source, boolean isSaved) {
        return;
    }

    
    
    /*
     **********************************************************************************************
     **********************************************************************************************
     **********************************************************************************************
     */
    
    
    
    /**
     * A Component to make ZeaTabbedPane have closeable tabs.
     */
    public class TabCloseButton extends JPanel implements MouseListener {
        /**
         * The tab on the ZeaTabbedPane this TabCloseButton is associated with.
         */
        private Component tab;

        
        /*
         **********************************************************************************************
         **********************************************************************************************
         **********************************************************************************************
         */
        
        
        /**
         * Constructs a Component to put use in ZeaTabbedPane.
         */
        public TabCloseButton(final Component tab, String title, Icon icon) {
            //Store the pointer of the tab that this TabCloseButton is associated with.
            this.tab = tab;
            
            //Do not paint all the pixels of this TabCloseButton
            setOpaque(false);
            
            //Set the LayoutManager of this TabCloseButton
            setLayout( new FlowLayout(FlowLayout.CENTER, 3, 3) );
            
            
            JLabel jLabel = new JLabel(title);  //Create a JLabel to serve as a title for the tab.
            jLabel.setIcon(icon);               //Set the icon as 'icon'
            this.add(jLabel);                   //Add the JLabel to the TabCloseButton
            
            
            JButton button = new JButton(MetalIconFactory.getInternalFrameCloseIcon(15));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.addMouseListener(this);
            this.add(button);
        }
        
        
        
        /**
         * Implementation of the MouseListener interface.
         *
         * Invoked when the mouse button has been clicked (pressed and released)
         * on a component.
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() instanceof JButton){
                JButton clickedButton = (JButton) e.getSource();
                
                // Edit
                int layer = 0;
                Component parent = clickedButton;
                while(parent instanceof Component) {
                    System.out.printf("%d: %s\n", layer, parent.getClass().getSimpleName());
                    
                    layer++;
                    parent = parent.getParent();
                }
                // End edit
                
                //JTabbedPane tabbedPane = (JTabbedPane) clickedButton.getParent().getParent().getParent();
                JTabbedPane tabbedPane = (JTabbedPane)(clickedButton.getParent().getParent().getParent().getParent().getParent());
                tabbedPane.remove(tab);
            }
        }

        
        
        /**
         * Implementation of the MouseListener interface.
         *
         * Invoked when the mouse enters a component.
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            return;
        }

        
        
        /**
         * Implementation of the MouseListener interface.
         *
         * Invoked when the mouse exits a component.
         */
        @Override
        public void mouseExited(MouseEvent e) {
            return;
        }
        
        
        
        /**
         * Implementation of the MouseListener interface.
         *
         * Invoked when a mouse button has been pressed on a component.
         */
        @Override
        public void mousePressed(MouseEvent e) {
            return;
        }

        
        
        /**
         * Implementation of the MouseListener interface.
         *
         * Invoked when a mouse button has been released on a component.
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            return;
        }

    }
}
