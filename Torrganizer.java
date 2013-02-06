//=============================================================================
// Torrganizer
//
//             Author:  James Mack
// Project Start Date:  2013-02-04
//
//=============================================================================

//===============================
// Imports
//===============================

// Library
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Classes
//import TFile;
//import MovieFile;
//import ShowFile;


public class Torrganizer
{
    //===============================================================
    //      Constants
    //===============================================================

    // Directories
    private static final String DOWNLOAD_PATH = "/home/james/Torrganizer/Downloads";

    // Regex
    private static final String REGEX_SHOW1 = "([a-zA-Z0-9\\.\\ ]+)(S\\d\\dE\\d\\d).*\\.(mp4|mpeg4|mp3|avi|mkv|wmv)";
    private static final String REGEX_SHOW2 = "([a-zA-Z0-9\\.\\ ]+)(\\d\\d\\d).*\\.(mp4|mpeg4|avi|mkv)";
    private static final String REGEX_MOVIE = "([a-zA-Z0-9\\.\\ ]+).*[\\(|\\]]?(19|20\\d\\d)[\\(|\\]]?.*\\.(mp4|mpeg4|avi|mkv)";


    //===============================================================
    //      main
    //===============================================================

    public static void main(String [] args)
    {
        File homeDir = new File(DOWNLOAD_PATH);
        String [] files = homeDir.list();
        File [] files = homeDir.listFiles();

        for(File thisFile: filePaths) {
            processFile(thisFile);
        }

        //printFiles(filePaths, 0);
    }

    //===============================================================
    //      processFile
    //===============================================================

    private static void processFile(File thisFile)
    {
        // match and create TFile representation   
        TFile tFile = matchFile(thisFile);
        
        // move file
    }    

    //===============================================================
    //      matchFile
    //===============================================================

    private static TFile matchFile(File thisFile)
    {
        Pattern show1Pattern = Pattern.compile(REGEX_SHOW1);
        Matcher show1Matcher = show1Pattern.matcher(thisFile.getName());

        Pattern show2Pattern = Pattern.compile(REGEX_SHOW2);
        Matcher show2Matcher = show2Pattern.matcher(thisFile.getName());

        Pattern moviePattern = Pattern.compile(REGEX_MOVIE);
        Matcher movieMatcher = moviePattern.matcher(thisFile.getName());

        // Show1
        if(show1Matcher.matches()) {
            

        }


    }    
    private static final String REGEX_SHOW1 = "([a-zA-Z0-9\\.\\ ]+)(S\\d\\dE\\d\\d).*\\.(mp4|mpeg4|mp3|avi|mkv|wmv)";
    private static final String REGEX_SHOW2 = "([a-zA-Z0-9\\.\\ ]+)(\\d\\d\\d).*\\.(mp4|mpeg4|avi|mkv)";
    private static final String REGEX_MOVIE = "([a-zA-Z0-9\\.\\ ]+).*[\\(|\\]]?(19|20\\d\\d)[\\(|\\]]?.*\\.(mp4|mpeg4|avi|mkv)";


    /****************************************************************
     


     
    
     
     
     
     
     
    ****************************************************************/






    //===============================================================
    //      printFiles
    //===============================================================

    public static void printFiles(File [] files, int indentLevel)
    {
        String indent = "";
        for(int i = 0; i < indentLevel; i++) 
            indent += "    ";

        for(File thisFile: files) {
            if(thisFile.getName().startsWith("."))
                continue;

            System.out.print(indent);
            System.out.println(thisFile.getName());
            
            if(thisFile.isDirectory()) {
                System.out.println(thisFile.getPath());
                printFiles(thisFile.listFiles(), indentLevel + 1);
            }
        }
    }
}
