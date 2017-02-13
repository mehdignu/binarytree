package int_baum16;

import static Prog1Tools.IOTools.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;


public class Baumrtest extends JFrame implements ActionListener{
	//main global variables
	JTextField t1, t2;
	JLabel l1, l2;
	JButton b1,b2,b3,b4,b5,b6;
	static Baumr T;
	int r=0, e=0,j=0;

	
	
	int x[] = new int[50]; //the array of the x-achse coordinations
	int y[] = new int[50];//the array of the y-achse coordinations
	String n[] = new String[50]; //the array of values
	
	
	/**
	 * set all the values of the arrays of the coordination and the values to default
	 */
	public void initi(){
		for(int i=0;i<n.length;i++){
			x[i] = 0;
			y[i] = 0;
			n[i] = "";
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		super.paintComponents(g);
		
	    
	    g.setColor(Color.black);
	    g.drawOval(320, 100, 40, 40);
	
	    
	    if(T.p == 0){
	    	 g.drawString("null",330,125);
	    }else{
	    	g.drawString(n[0],335,125); //wurezel
	    	
	    	for(int i = 1; i < T.p; i++) { 
	    	
	    		
	    		g.drawOval(x[i], y[i], 40, 40);
	    		g.drawString(n[i],x[i] + 15,y[i] +22);
	    		//g.drawLine(x.get(i-1), y.get(i-1), x.get(i), y.get(i));
	    	
	    	}
	   
	    }

	}


	/**
	 * @param c is the value to add to the tree and the values array
	 * while adding the values i store the coordinations to it's tables and increment the arrays p vriable to one
	 */
	public void add(String c){
        
		T.einfuegen(c);	

		x[T.p] = T.k;
		y[T.p] = T.m;
		n[T.p] = c;
	
		
		T.p = T.p +1;
		
		
	}
	public void export(String name){
		if(T.p > 0 ){
	    	
	    	try{
	    	    PrintWriter writer = new PrintWriter(name+".txt", "UTF-8");
	    	    
	    	    for(int j=0;j<T.p;j++){
	    	    	
	    	    		writer.print(n[j]+",");
	    	    	
	    	    }
	    	    writer.print("#");
	    	    writer.close();
	    	} catch (IOException e1) {
	    	   //TODO
	    	}
	    	
	    	}else{
	    		System.out.println("no baum");
	    	}
	}
	
	public void empty(){
		 T.wurzel = null;
	        for(int j=0;j<x.length;j++){
	        	x[j]=0;
	        	y[j]=0;
	        	n[j]="";
	        	
	        }
	        T.p = 0;
	       repaint();
	}
	
	public void rewrite(String name){

    	//regraphic
    	
    	BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(name+".txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
            StringBuilder sb = new StringBuilder();
            String line = null;
			try {
				line = br.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                try {
					line = br.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            String s = sb.toString();
            
            String k = null; 
            boolean b = false;
        
            int j=0;
            while(j < s.length() ){
            	if(s.charAt(j) == ','){
            		if(k != "" ) {
            			
            			if(k.startsWith("null")){
            				int g = 4 + (k.length()-4);
            				add(k.substring(4, g));
            			}
            			else
            				add(k);
            		}
            		k = "";
            		j++;
            }else{
            	k+=s.charAt(j);
            	j++;
            }
            	
            }
            

        	T.ausgabe();
        	repaint();
            
            try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        
    	
    	
	}
	
	  /* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		  
		  
		  if(e.getSource() == b1){
	        	String s;
	        int z,ex;
	        s = t1.getText();
	        
	        if(s.isEmpty() || s.length() > 3){
	        	JOptionPane.showMessageDialog(null, "unvalid entry !");
	        }else{
	        	
	        
	        
	        
	        r=0;
	        add(s);
			
	        
	        T.ausgabe();
			t1.setText("");
			
			

			repaint();
			
	        }

	        }
	        
	        if(e.getSource() == b2){ //button of delete the knoten
	        	String s;
	        int z=-1,ex,o=-1;
	       
	        
	         String i =t2.getText(); //get the value to be deleted
	         
	         if(i.isEmpty() || i.length() >3){
	         JOptionPane.showMessageDialog(null, "unvalid entry");
	         }else{
	         
	         boolean b=false;
	         for(int j = 0;j<T.p && b==false;j++){
	        	 if ( i.compareTo(n[j]) == 0 ){
	        		 z = j;
	        		 b=true;
	        	 }
	         }
	         
	         T.del(i); //delete Knoten logisch
	         
	         
	       
	         
	         //delete Knoten im graphic
	     
	       
	         for(int j=z;j<T.p-1;j++){
	        	 n[j] = n[j+1];
	         
	        }
	 
	       T.p = T.p-1;
	       
	       export("tree");
	       empty();
	       rewrite("tree");
	         
	         T.ausgabe();
	         repaint();
	         
	         }

	        }
	        if(e.getSource() == b3){ //button to empty the tree
	        empty();
	        }
	        
	        if(e.getSource() == b4){ //button to export tree

	        	export("baum1");
	        }
	        if(e.getSource() == b5){ //button to import tree
	        	empty();
	        BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader("baum1.txt"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	            StringBuilder sb = new StringBuilder();
	            String line = null;
				try {
					line = br.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	            while (line != null) {
	                sb.append(line);
	                sb.append(System.lineSeparator());
	                try {
						line = br.readLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	            String s = sb.toString();
	            
	            String k = null; 
	            boolean b = false;
	        
	            int j=0;
	            while(j < s.length() ){
	            	if(s.charAt(j) == ','){
	            		if(k != "" ) {
	            			
	            			if(k.startsWith("null")){
	            				int g = 4 + (k.length()-4);
	            				add(k.substring(4, g));
	            			}
	            			else
	            				add(k);
	            		}
	            		k = "";
	            		j++;
	            }else{
	            	k+=s.charAt(j);
	            	j++;
	            }
	            	
	            }
	            
	            
            	T.ausgabe();
            	repaint();
	            
	            try {
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        
	        
	        }
	        
	        if(e.getSource() == b6){ //button to import tree
	        	empty();
		        BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader("baum2.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		            StringBuilder sb = new StringBuilder();
		            String line = null;
					try {
						line = br.readLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

		            while (line != null) {
		                sb.append(line);
		                sb.append(System.lineSeparator());
		                try {
							line = br.readLine();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            }
		            String s = sb.toString();
		            
		            String k = null; 
		            boolean b = false;
		       
		            int j=0;
		            while(j < s.length() ){
		            	if(s.charAt(j) == ','){
		            		if(k != "" ) {
		            			
		            			if(k.startsWith("null"))
		            				add(k.substring(4, 7));
		            			else
		            				add(k);
		            		}
		            		k = "";
		            		j++;
		            }else{
		            	k+=s.charAt(j);
		            	j++;
		            }
		            	repaint();
		            	
		            }
		            
		            
		            try {
						br.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        
		        
		        }
	        
	        
	    }  
	

	
	
	/**
	 *  menu bar have only the exit option
	 */
	private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        ImageIcon pause = new ImageIcon("pause.png");

        ImageIcon iconExit = new ImageIcon("exit.png");
        
        JMenu fileMenu = new JMenu("File");
        
       
        JMenuItem exitMi = new JMenuItem("Exit", iconExit);
        exitMi.setToolTipText("Exit application");
 
        exitMi.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        
        fileMenu.addSeparator();
        fileMenu.add(exitMi);
        menubar.add(fileMenu);
        setJMenuBar(menubar);
    }
	
	
	
	/**
	 * main parameters for swing window
	 */
	Baumrtest() {
		// Angaben zum Fenster
		initi();
		T = new Baumr();
		setTitle("Tree");
		setSize(700, 600);
		createMenuBar();
		setLocation(200, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		//addMouseListener(new CMeinMausAdapter());
		setVisible(true);
		wur();
	}
	
	/**
	 *  add the action performed to every button on the screen
	 */
	private void wur(){
		//first text field
		add(t1 = new JTextField(""));
		t1.setBounds (3,3, 60, 20);
		//second text field
		add(t2 = new JTextField(""));
		t2.setBounds (128,3, 60, 20);
		//add button
	    add(b1 = new JButton("add"));
	    b1.setBounds (65,3, 60,20);
	    b1.addActionListener(this);
	    //delete button
	    add(b2 = new JButton("Delete"));
	    b2.setBounds (190,3, 80,20);
	    b2.addActionListener(this);
	    //empty button
	    add(b3 = new JButton("empty"));
	    b3.setBounds (290,3, 80,20);
	    b3.addActionListener(this);
	  //export button
	    add(b4 = new JButton("export"));
	    b4.setBounds (390,3, 80,20);
	    b4.addActionListener(this);
	  //iport button
	    add(b5 = new JButton("Beispiel1"));
	    b5.setBounds (480,3, 100,20);
	    b5.addActionListener(this);
	    //second exemple
	    add(b6 = new JButton("Beispiel2"));
	    b6.setBounds (590,3, 100,20);
	    b6.addActionListener(this);
	}
	/**
	 * @param args
	 * starting Tree software
	 */
	public static void main(String[] args) {
		new Baumrtest();
	
	}
	

	
}
