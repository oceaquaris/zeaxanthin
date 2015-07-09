package org.zeaio;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Program
{
    /*public static void main(String[] args)
    {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        createAndShowGUI();
    }
    
    private static void createAndShowGUI()
    {
        //Create and set up the window.
        JFrame frame = new JFrame("Zeaxanthin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(400, 400));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }*/
    /*public static void main(String[] args) {
        JFrame frame = new JFrame("Zeaxanthin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        
        JLabel label = new JLabel("This is a label!");
        
        JButton button = new JButton();
        button.setText("Press me");
        
        panel.add(label);
        panel.add(button);
        
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }*/
    public static void main(String[] args) {
        //Create and set up the window.
        JFrame frame = new JFrame("TopLevelDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create the menu bar.  Make it have a green background.
        JMenuBar greenMenuBar = new JMenuBar();
        greenMenuBar.setOpaque(true);
        greenMenuBar.setBackground(new Color(154, 165, 127));
        greenMenuBar.setPreferredSize(new Dimension(200, 20));

        //Create a yellow label to put in the content pane.
        JLabel yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(248, 213, 131));
        yellowLabel.setPreferredSize(new Dimension(200, 180));

        //Set the menu bar and add the label to the content pane.
        frame.setJMenuBar(greenMenuBar);
        frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}