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
    private static final String DEFAULT_PATH = "/home/james";

    // Regex
    private static final String REGEX_SHOW1 = "([a-zA-Z0-9\\.\\ ]+)(S\\d\\dE\\d\\d).*\\.(mp4|mpeg4|mp3|avi|mkv|wmv)";
    private static final String REGEX_SHOW2 = "([a-zA-Z0-9\\.\\ ]+)(\\d\\d\\d).*\\.(mp4|mpeg4|avi|mkv)";
    private static final String REGEX_MOVIE = "([a-zA-Z0-9\\.\\ ]+).*[\\(|\\]]?(19|20\\d\\d)[\\(|\\]]?.*\\.(mp4|mpeg4|avi|mkv)";


    //===============================================================
    //      main
    //===============================================================

    public static void main(String [] args)
    {
        File homeDir = new File(DEFAULT_PATH);
        String [] files = homeDir.list();
        File [] filePaths = homeDir.listFiles();


        MovieFile movie = new MovieFile("The", "mp4", 2014);

        printFiles(filePaths, 0);
    }


    //===============================================================
    //      matchFile
    //===============================================================

    public static void matchFile(File thisFile)
    {
        //Pattern show1 = Pattern.compile();

        //Matcher matcher = pattern.matcher(thisFile.getName());

    }    


    /****************************************************************
     
    Video
    ([a-zA-Z0-9\.\ ]+)(S\d\dE\d\d).*\.(mp4|mpeg4|avi|mkv)

    MP3


     
    
     
     
     
     
     
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
