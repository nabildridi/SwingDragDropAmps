package com.wxyz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainClazz extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final String label1="Alfresco war or folder :";
	private static final String label2="Amps for Alfresco :";
	private static final String label3="share war or folder :";
	private static final String label4="Amps for Share :";
	
	JTextArea dropAreaAlf;
	JTextArea dropAreaAmpAlf;
	JTextArea dropAreaShare;
	JTextArea dropAreaAmpShare;
	
	
	JLabel labelAlf;
	JLabel labelAmpAlf;
	JLabel labelShare;
	JLabel labelAmpShare;
	
	JButton goButton;
	JButton initButton;
	
	String alfrescoWarPath="";
	String shareWarPath="";
	
	Set<String> ampAlfList=new HashSet<String>();
	Set<String> ampShareList=new HashSet<String>();
	private JTextArea console;
	
	public static void main( String[] args )
    {
   	
    	MainClazz frame = new MainClazz();
    	frame.setVisible(true);
    	
    	
    }   // end main


	public MainClazz() {
		
		
		
		this.setTitle("Amps deployment utility");
		this.setSize(621, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//--------------------------------------------
		labelAlf = new JLabel(label1);
		labelAlf.setForeground(Color.blue);
		
		dropAreaAlf = new JTextArea();
		//-------------------------------------------------
		labelAmpAlf = new JLabel(label2);
		labelAmpAlf.setForeground(Color.blue);
		
		dropAreaAmpAlf = new JTextArea();
		//-------------------------------------------
		labelShare = new JLabel(label3);
		labelShare.setForeground(Color.blue);
		
		dropAreaShare = new JTextArea();
		//--------------------------------------
		labelAmpShare = new JLabel(label4);
		labelAmpShare.setForeground(Color.blue);
		
		dropAreaAmpShare = new JTextArea();
		
		
		goButton = new JButton("Apply");
		goButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			callMmt();  
			  
		  }
		});
		
		initButton = new JButton("Reinit");
        initButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  clearAll();
		  }
		});
        
        
        console = new JTextArea("");
        JTextAreaOutputStream out = new JTextAreaOutputStream (console);
        System.setOut (new PrintStream (out));
        System.setErr(new PrintStream (out));
        
        console.setEditable(false);
        console.setFont(new Font("Monospaced",Font.PLAIN,15));
        console.setBackground(Color.BLACK);
        console.setForeground(Color.LIGHT_GRAY);
       
        
        JScrollPane sp= new JScrollPane(console);
        
		this.setVisible(true);
		
		
        
		
		new FileDrop( System.out, dropAreaAlf, new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   for( int i = 0; i < files.length; i++ )
                {   try
                    {   dropAreaAlf.setText( files[i].getCanonicalPath() );
                    	alfrescoWarPath=files[i].getCanonicalPath();
                    } 
                    catch( java.io.IOException e ) {}
                }   
            }   
        }); 

        
        new FileDrop( System.out, dropAreaShare, new FileDrop.Listener()
        {
			public void filesDropped(java.io.File[] files) {
				for (int i = 0; i < files.length; i++) {
					try {						
						dropAreaShare.setText( files[i].getCanonicalPath());
						shareWarPath = files[i].getCanonicalPath();
					} catch (java.io.IOException e) {
					}
				}
			}   
        });
        
        new FileDrop( System.out, dropAreaAmpAlf, new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   for( int i = 0; i < files.length; i++ )
                {   try
                    {   
                		ampAlfList.add(files[i].getCanonicalPath());
                    } 
                    catch( java.io.IOException e ) {}
                }
            	
            	String txt="";
            	for(String file:ampAlfList){
            		txt=txt+file+"\n";
            	}
            	dropAreaAmpAlf.setText(txt);
            }   
        }); 
        
        new FileDrop( System.out, dropAreaAmpShare, new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   for( int i = 0; i < files.length; i++ )
                {   try
                    {   
                		ampShareList.add(files[i].getCanonicalPath());
                    } 
                    catch( java.io.IOException e ) {}
                }
            	
            	String txt="";
            	for(String file:ampShareList){
            		txt=txt+file+"\n";
            	}
            	dropAreaAmpShare.setText(txt);
            }   
        }); 
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(12)
        			.addComponent(labelAlf, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        			.addGap(37)
        			.addComponent(labelShare, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        			.addGap(16))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(47)
        			.addComponent(goButton, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        			.addGap(117)
        			.addComponent(initButton, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        			.addGap(41))
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addGap(12)
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(sp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(dropAreaAmpAlf, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        						.addComponent(dropAreaAlf, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        						.addComponent(labelAmpAlf, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
        					.addGap(37)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(dropAreaShare, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        						.addComponent(dropAreaAmpShare, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        						.addComponent(labelAmpShare, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))))
        			.addGap(16))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(labelAlf, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        				.addComponent(labelShare, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
        			.addGap(5)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(95)
        					.addComponent(dropAreaAmpAlf, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
        				.addComponent(dropAreaAlf, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(35)
        					.addComponent(labelAmpAlf, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
        				.addComponent(dropAreaShare, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(95)
        					.addComponent(dropAreaAmpShare, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(35)
        					.addComponent(labelAmpShare, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
        			.addGap(30)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(goButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        				.addComponent(initButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
        			.addGap(12)
        			.addComponent(sp, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        			.addContainerGap())
        );
        getContentPane().setLayout(groupLayout);
		
	}
	
	
	private void callMmt(){
		
		
		
		goButton.setEnabled(false);
		initButton.setEnabled(false);
		console.setText("");

		
		
			SystemExitControl.forbidSystemExitCall();
			
				
			//alfresco part
			if(ampAlfList.size()>0){
				if(alfrescoWarPath.length()>0){
					
					boolean isFolder=false;
					if((new File(alfrescoWarPath).isDirectory()))isFolder=true;
		
					for(String ampFilePath:ampAlfList){
						LinkedList<String> arguments=new LinkedList<String>();
						
						System.out.println("Processing " + ampFilePath + "..........");
						
						arguments.add("install");
						arguments.add(ampFilePath);
						arguments.add(alfrescoWarPath);
						//arguments.add("-verbose");
						arguments.add("-force");
						if(isFolder)arguments.add("-nobackup");
						
						
						try {
							org.alfresco.repo.module.tool.ModuleManagementTool.main(arguments.toArray(new String [arguments.size()]));
						} catch (Exception e) {							
							
						}
						
						System.out.println("--->End");


					}
				}
			}

			//share part
			if(ampShareList.size()>0){
				if(shareWarPath.length()>0){
					
					boolean isFolder=false;
					if((new File(shareWarPath).isDirectory()))isFolder=true;

					for(String ampFilePath:ampShareList){
						
						System.out.println("Processing " + ampFilePath + "..........");
						
						LinkedList<String> arguments=new LinkedList<String>();
						arguments.add("install");
						arguments.add(ampFilePath);
						arguments.add(shareWarPath);
						//arguments.add("-verbose");
						arguments.add("-force");
						if(isFolder)arguments.add("-nobackup");
						
						try {
							org.alfresco.repo.module.tool.ModuleManagementTool.main(arguments.toArray(new String [arguments.size()]));
						} catch (Exception e) {

						}
						
						System.out.println("--->End");

					}
				}
			}

		SystemExitControl.enableSystemExitCall();
		
		goButton.setEnabled(true);
		initButton.setEnabled(true);

	}
	
	private void clearAll(){
		//clear all
		  alfrescoWarPath="";
		  shareWarPath="";				
		  ampAlfList=new HashSet<String>();
		  ampShareList=new HashSet<String>();
		  
		  dropAreaAlf.setText("");
		  dropAreaAmpAlf.setText("");
		  
		  dropAreaShare.setText("");
		  dropAreaAmpShare.setText("");
		  
		  console.setText("");
		  
	}
}