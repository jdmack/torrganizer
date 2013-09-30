package com_wulph_torrganizer;
//=============================================================================
// TFile.java
//=============================================================================

//===============================
// Imports
//===============================
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class GInterface //implements TorrInterface
{
    //===============================================================
    //      Constants
    //===============================================================


    //===============================================================
    //      Instance Variables
    //===============================================================
    Scanner strInput;
    Scanner intInput;
    JTextArea outputTextArea;

    //===============================================================
    //      Constructors
    //===============================================================

    public GInterface(JTextArea output)
    {
    	outputTextArea = output;
        outputTextArea.append("Torrganizer\n");

        strInput = new Scanner(System.in);
        intInput = new Scanner(System.in);
    }

    //===============================================================
    //      Input Methods
    //===============================================================

    public boolean getDirectoryProceed(String dirName)
    {
        int answer = JOptionPane.showConfirmDialog(
        		null,
        	    "Processing directory:\n\n" + "\t" + dirName + "\n\n" + "Proceed?\n", 
        	    "Yo", 
        	    JOptionPane.YES_NO_OPTION,
        	    JOptionPane.QUESTION_MESSAGE);

        if(answer == JOptionPane.NO_OPTION) {
            outputTextArea.append("Skipping directory\n");
            return false;
        }

        return true;
    }

    public String getMoveToPath(String destinationPath, String fileName)
    {
        String inputValue = JOptionPane.showInputDialog(
        	"Enter missing path information:\n\n"
        	+ "\t" + destinationPath + "/???/" + fileName + "\n"
        );

        return inputValue;
    }

    public int getProcessOption()
    {
    	
    	Object[] possibleValues = { 
	        "(1) Process as Show\n",
	        "(2) Process as Movie\n",
	        "(3) Process Manually\n",
	        "(4) Skip\n"
    	};

    	Object selectedValue = JOptionPane.showInputDialog(null,
    		"Select option: \n",
	    	"Input",
	    	JOptionPane.INFORMATION_MESSAGE, 
	    	null,
	    	possibleValues, 
	    	possibleValues[0]);	
    	
    	if(selectedValue.equals("(1) Process as Show\n")) {
    		return 1;
    	}
    	else if(selectedValue.equals("(2) Process as Movie\n")) {
    		return 2;
    	}
    	else if(selectedValue.equals("(3) Process Manually\n")) {
    		return 3;
    	}
    	else if(selectedValue.equals("(4) Skip\n")) {
    		return 4;
    	}
    	else {
    		return 4;
    	}
    }

    public String getShowTitle()
    {
        String inputValue = JOptionPane.showInputDialog("Enter show title:\n", " ");
        return inputValue;
    }

    public int getShowSeason()
    {
        String inputValue = JOptionPane.showInputDialog("Enter show season: ", 0);
        return Integer.parseInt(inputValue);
    }

    public int getShowEpisode()
    {
        String inputValue = JOptionPane.showInputDialog("Enters show episode: ", 0);
        return Integer.parseInt(inputValue);
    }

    public String getMovieTitle()
    {
        String inputValue = JOptionPane.showInputDialog("Enter movie title: ", " ");
        return inputValue;
    }

    public int getMovieYear()
    {
        String inputValue = JOptionPane.showInputDialog("Enter movie year: ", 0);
        return Integer.parseInt(inputValue);
    }

    public String getFileName()
    {
        String inputValue = JOptionPane.showInputDialog("Enter file name: ", " ");
        return inputValue;
    }

    public String getEpisodeTitle()
    {
        String inputValue = JOptionPane.showInputDialog("Enter episode title: ", " ");
        return inputValue;
    }

    public boolean getPerformAction()
    {
        int answer = JOptionPane.showConfirmDialog(
        		null,
        		"Perform Action?",
        	    "Yo", 
        	    JOptionPane.YES_NO_OPTION,
        	    JOptionPane.QUESTION_MESSAGE);

        if(answer == JOptionPane.NO_OPTION) {
            outputTextArea.append("Skipping action\n");
            return false;
        }

        return true;
    }


    //===============================================================
    //      Output Methods
    //===============================================================
    
    public void outputDownloadDirectory(String path)
    {
        outputTextArea.append("Download directory set to: '" + path + "'\n");
    }

    public void outputDestinationDirectory(String path)
    {
        outputTextArea.append("Destination directory set to: '" + path + "'\n");
    }

    public void outputProcessingDirectory(String dir)
    {
        outputTextArea.append("\nProcessing directory: " + dir + "\n");
    }

    public void outputProcessingFile(String file)
    {
        outputTextArea.append("\nProcessing file: " + file + "\n");
    }

    public void outputDirectoryEmpty(String dir)
    {
        outputTextArea.append(dir + ": directory empty\n");
    }

    public void outputDirectoryNotExist(String dir)
    {
        outputTextArea.append(dir + ": directory does not exist\n");
    }

    public void outputDirectoryCreateSuccess(String dir)
    {
        outputTextArea.append(dir + ": directory created successfully\n");
    }

    public void outputDirectoryCreateError(String dir)
    {
        outputTextArea.append(dir + ": Error creating directory\n");
    }

    public void outputFileAttemptRename(String path)
    {
        outputTextArea.append("Attempting to rename/move to: " + path + "\n");
    }

    public void outputFileRenameSuccess()
    {
        outputTextArea.append("Rename completed successfully\n");
    }

    public void outputFileRenameError()
    {
        outputTextArea.append("Error renaming/moving file\n");
    }

    public void outputNoMatch(String extension)
    {
        outputTextArea.append("Unable to match file, found extension: " + extension + "\n");
    }

    public void outputSkipFile()
    {
        outputTextArea.append("Skipping file\n");
    }

    public void outputProcessError()
    {
        outputTextArea.append("Error processing file\n");
    }

    public void outputObject(TFile thisFile)
    {
        outputTextArea.append(thisFile.toString() + "\n");
    }

    public void outputDebug(String message)
    {
        outputTextArea.append("[DEBUG] " + message + "\n");
    }

    //===============================================================
    //      getters / setters
    //===============================================================

    //=================================
    //      varName
    //=================================



}
