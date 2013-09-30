package com_wulph_torrganizer;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;


import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.io.File;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JScrollPane;

public class TorrWindow {

	private JFrame frmTorrganizer;
	private JTextField textField_homeDirectory;
	private JTextField textField_destinationDirectory;

	private File homeDir;
	private File destDir;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TorrWindow window = new TorrWindow();
					window.frmTorrganizer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TorrWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTorrganizer = new JFrame();
		frmTorrganizer.setTitle("Torrganizer");
		frmTorrganizer.setBounds(100, 100, 800, 600);
		frmTorrganizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTorrganizer.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frmTorrganizer.getContentPane().add(panel);
		
		JButton button_chooseDestinationDir = new JButton("Choose");
		button_chooseDestinationDir.setBounds(601, 78, 121, 23);
		button_chooseDestinationDir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				final JFileChooser fc = new JFileChooser(new File("D:\\workspace-java\\torrganizer"));
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(frmTorrganizer);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            destDir = fc.getSelectedFile();
		            textField_destinationDirectory.setText(destDir.getAbsolutePath());
		        } else {
		        }
			}
		});
		
		JButton button_chooseHomeDir = new JButton("Choose");
		button_chooseHomeDir.setBounds(601, 53, 121, 23);
		button_chooseHomeDir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				final JFileChooser fc = new JFileChooser(new File("D:\\workspace-java\\torrganizer"));
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(frmTorrganizer);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            homeDir = fc.getSelectedFile();
		            textField_homeDirectory.setText(homeDir.getAbsolutePath());
		        } else {
		        }
			}
		});
		panel.setLayout(null);
		
		JLabel label_homeDirectory = new JLabel("Home Directory");
		label_homeDirectory.setBounds(68, 57, 121, 14);
		label_homeDirectory.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(label_homeDirectory);
		label_homeDirectory.setLabelFor(textField_homeDirectory);
		
		textField_homeDirectory = new JTextField();
		textField_homeDirectory.setBounds(219, 54, 372, 20);
		panel.add(textField_homeDirectory);
		textField_homeDirectory.setColumns(30);
		panel.add(button_chooseHomeDir);
		
		JLabel label_destinationDirectory = new JLabel("Destination Directory");
		label_destinationDirectory.setBounds(68, 82, 121, 14);
		label_destinationDirectory.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(label_destinationDirectory);
		
		textField_destinationDirectory = new JTextField();
		textField_destinationDirectory.setBounds(219, 82, 372, 20);
		textField_destinationDirectory.setColumns(30);
		panel.add(textField_destinationDirectory);
		panel.add(button_chooseDestinationDir);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(23, 135, 738, 375);
				panel.add(scrollPane);
		
				final JTextArea textArea_output = new JTextArea();
				scrollPane.setViewportView(textArea_output);
				textArea_output.setEditable(false);
				textArea_output.setLineWrap(true);
				textArea_output.setFont(new Font("Consolas", Font.PLAIN, 13));
				textArea_output.setTabSize(4);
				textArea_output.setColumns(80);
		
		JButton button_run = new JButton("Run");
		button_run.setBounds(351, 521, 82, 23);
		button_run.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(homeDir == null) {
					homeDir = new File("D:\\workspace-java\\torrganizer\\homeDir");
				}
				if(destDir == null) {
					destDir = new File("D:\\workspace-java\\torrganizer\\sortDir");
				}
				Torrganizer.run(textArea_output, homeDir, destDir);
			}
		});
		panel.add(button_run);
	}
}
