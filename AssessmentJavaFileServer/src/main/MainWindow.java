package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MainWindow {

	private JFrame frame;
	
	//I can make a array list of hashmaps
	//The string can hold the ip addresss while the address hold the thread of the ip address and call methods on them
	static HashMap<String,ClientThread> mythreads;
	private JList list_1 = new JList();
	
	private JTextField textField;
	private JTextField txtBrian;
	static DefaultListModel<File> model = new DefaultListModel<File>();
	static ServerThread1 server;
	static Thread t;
	final JComboBox comboBox = new JComboBox();
	
	
	//static DefaultListModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				
			}
		});
		
		try {
			MainWindow window = new MainWindow();
			window.frame.setVisible(true);
			
			server = new ServerThread1();
			t = new Thread(server);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 645, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{217, 217, 0};
		gbl_panel_1.rowHeights = new int[]{23, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_1.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton Download = new JButton("Download");
		Download.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_Download = new GridBagConstraints();
		gbc_Download.gridwidth = 3;
		gbc_Download.insets = new Insets(0, 0, 5, 5);
		gbc_Download.gridx = 1;
		gbc_Download.gridy = 0;
		panel.add(Download, gbc_Download);
		
		//Connect
		final JButton btnNewButton = new JButton("Connect");
		//When the connection is successful
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				//Connect to socket ip address
			
				try {
					Socket socket = new Socket(textField.getText(),10170);
					System.out.println(socket.getPort() + "test");
					
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				btnNewButton.setEnabled(false);
//				ClientThread client = new ClientThread(soc);
//				Thread thread = new Thread(client);
//				
				//mythreads.put(textField.getText(),client);
				

				comboBox.addItem(textField.getText());
				
				if(t.isAlive())
				{
					System.out.println("Server is alive");
				}
				
				else
				{
					System.out.println("I'm Dead");
				}
				
				
				for(ClientThread tl: server.map.values())
				{
					try {
						System.out.println(tl.testConnection());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
				
				
					
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("GetFileList");
		
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				File folder = new File("C:/Users/FT9");
				if(!folder.isDirectory())
				 {
				   		//add the folder to the string if is not a directory
						model.addElement(folder);
				 }
				  else{
				   		
				   		//gets the amount of files for indexing
				   		
					  int number_of_files = folder.listFiles().length;
				   	//for each item in file
				  for (final File fileEntry : folder.listFiles()) {
					  //if it is put the directory then all its file into the string follow by a comma
					  model.addElement(fileEntry);
					  System.out.println(fileEntry.getName());
					  //list_1.setModel(model);
				  }
				  }		
				list_1.setModel(model);
			}
		});
		
		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, BorderLayout.EAST);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		textField = new JTextField();
		textField.setText("10.1.1.159");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		panel_4.add(textField, gbc_textField);
		textField.setColumns(20);
		
		txtBrian = new JTextField();
		txtBrian.setEnabled(false);
		txtBrian.setText("Brian");
		GridBagConstraints gbc_txtBrian = new GridBagConstraints();
		gbc_txtBrian.insets = new Insets(0, 0, 5, 0);
		gbc_txtBrian.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBrian.gridx = 0;
		gbc_txtBrian.gridy = 1;
		panel_4.add(txtBrian, gbc_txtBrian);
		txtBrian.setColumns(10);
		
		
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"PlaceHolder"}));
		//comboBox.setSelectedIndex(0);
		comboBox.setEditable(true);
	
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 2;
		panel_4.add(comboBox, gbc_comboBox);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 3;
		panel_4.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{135, 258, 0};
		gbl_panel_5.rowHeights = new int[]{50, 0, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panel_5.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{258, 0};
		gbl_panel_6.rowHeights = new int[]{50, 0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
				File file = (File)list_1.getSelectedValue();
				
			}
		});
		
		
		//panel_6.add(list);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(list_1);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_6.add(scrollPane, gbc_scrollPane);
		
		JLabel lblAvailableFiles = new JLabel("Available Files");
		GridBagConstraints gbc_lblAvailableFiles = new GridBagConstraints();
		gbc_lblAvailableFiles.gridx = 0;
		gbc_lblAvailableFiles.gridy = 1;
		panel_6.add(lblAvailableFiles, gbc_lblAvailableFiles);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 0;
		panel_5.add(panel_7, gbc_panel_7);
		JList list2 = new JList();
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane_1 = new JScrollPane(list2);
		panel_7.add(scrollPane_1);
		
		JLabel lblNewLabel = new JLabel("Your Files");
		scrollPane_1.setColumnHeaderView(lblNewLabel);
	}

}
