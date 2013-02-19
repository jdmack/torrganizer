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
import java.util.Vector;
import java.util.Scanner;

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
    private static final String DOWNLOAD_PATH = "/home/james/Torrganizer/files";
    private static final String SORT_PATH     = "/home/james/Torrganizer/Sort";

    // Regex
    private static final String REGEX_SHOW1 = "([a-zA-Z0-9\\.\\ ]+)S(\\d\\d)E(\\d\\d).*\\.(mp4|mpeg4|mp3|avi|mkv|wmv)";
    private static final String REGEX_SHOW2 = "([a-zA-Z0-9\\.\\ ]+)(\\d\\d\\d).*\\.(mp4|mpeg4|avi|mkv)";
    private static final String REGEX_MOVIE = "([a-zA-Z0-9\\.\\ ]+).*[\\(|\\]]?(19|20\\d\\d)[\\(|\\]]?.*\\.(mp4|mpeg4|avi|mkv)";


    //===============================================================
    //      main
    //===============================================================

    public static void main(String [] args)
    {
        File homeDir = new File(DOWNLOAD_PATH);
        //String [] fileStrings = homeDir.list();

        File [] files = homeDir.listFiles();
        if(files.length <= 0) {
            System.out.println("Target Directory empty: " + DOWNLOAD_PATH);
            System.exit(0);
        }

        Vector<TFile> allFiles = new Vector<TFile>();

        for(File thisFile: files) {
            allFiles.addElement(processFile(thisFile));
        }

        for(TFile thisFile: allFiles) {
            System.out.println("new file: " + thisFile.getFileName());
        }

        //printFiles(filePaths, 0);
    }

    //===============================================================
    //      processFile
    //===============================================================

    private static TFile processFile(File thisFile)
    {
        System.out.println("Processing file: " + thisFile.getName());


        Scanner input = new Scanner(System.in);

        // match and create TFile representation   
        TFile thisTFile = matchFile(thisFile);

        // rename and move file
        String newPath = SORT_PATH;
        String newAbsoluteName = "";

        if(thisTFile.isShow()) {
            newPath = newPath + "/TV/" + ((ShowFile) thisTFile).getShowTitle() + "/" + ((ShowFile) thisTFile).getShowTitle() + " - Season " + ((ShowFile) thisTFile).getSeasonNum();
            newAbsoluteName = newPath + "/" + thisTFile.getFileName();
        }


        else if(thisTFile.isMovie()) {
            newPath = newPath + "/Movies";
            newAbsoluteName = newPath + "/" + thisTFile.getFileName();
        }

        else {
            System.out.println(SORT_PATH + "/???/" + thisTFile.getFileName());
            System.out.print("Enter missing path information: ");
            String inputString = input.nextLine();

            newPath = newPath + inputString;
            newAbsoluteName = newPath + "/" + thisTFile.getFileName();
        }

        System.out.println("newPath: " + newPath);
        System.out.println("newAbsoluteName: " + newAbsoluteName);

        //System.out.println("Old: " + thisTFile.getFile().getName());
        //System.out.println("New: " + newPath);

        File dir = new File(newPath);
        if(!dir.exists()) {
            System.out.println("Directory does not exist, creating");
            boolean dirResult = dir.mkdirs();

            if(dirResult) 
                System.out.println("Directory created successfully");
            else
                System.out.println("Error creating directory");

        }

        File newFile = new File(newAbsoluteName);
        System.out.println("Attempting to rename/move to: " + newFile.getName());
        boolean result = thisTFile.getFile().renameTo(newFile);

        if(result) {
            System.out.println("Successfully renamed and moved!");
            thisTFile.setFile(newFile);
        }
        else
            System.out.println("Error renaming or moving file");
    
        System.out.println();

        return thisTFile;
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

        TFile newFile;
    
        // Show1
        if(show1Matcher.matches()) {

            String title = show1Matcher.group(1).replace(".", " ");
            title = title.replace("  ", " ");
            title = title.trim();

            int season = Integer.parseInt(show1Matcher.group(2));
            int episode = Integer.parseInt(show1Matcher.group(3));

            String extension = show1Matcher.group(4);
            String name = title + " - S" + String.format("%02d", season) + "E" + String.format("%02d", episode) + "." + extension;

            newFile = new ShowFile(thisFile, name, extension, season, episode, title);
        }

        // Show1
        else if(show1Matcher.matches()) {

            String title = show1Matcher.group(1).replace(".", " ");
            title = title.replace("  ", " ");
            title = title.trim();

            int episodeNumber = Integer.parseInt(show1Matcher.group(2));
            int episode = episodeNumber % 100;
            int season = (episodeNumber / 100) % 10;

            String extension = show1Matcher.group(3);
            String name = title + " - S" + String.format("%02", season) + "E" + String.format("%02", episode) + "." + extension;

            newFile = new ShowFile(thisFile, name, extension, season, episode, title);
        }

        // Movie
        else if(movieMatcher.matches()) {

            String title = movieMatcher.group(1).replace(".", " ");
            title = title.replace("  ", " ");
            title = title.trim();

            int year = Integer.parseInt(movieMatcher.group(2));

            String extension = movieMatcher.group(3);
            String name = title + " (" + String.format("%4d", year) + ")." + extension;

            newFile = new MovieFile(thisFile, name, extension, year);
        }

        else {

            System.out.println("Unable to match file: " + thisFile.getName());

            Scanner input = new Scanner(System.in);

            System.out.print("Enter the extension: ");
            String extension = input.nextLine();

            System.out.print("Is this (1) show or (2) movie? ");
            String choice = input.nextLine();

            if(choice.equals("1")) {

                System.out.print("Enter show title: ");
                String title = input.nextLine();

                System.out.print("Enter the season: ");
                int season = input.nextInt();

                System.out.print("Enter the episode: ");
                int episode = input.nextInt();

                String name = title + " - S" + String.format("%02d", season) + "E" + String.format("%02d", episode) + "." + extension;

                newFile = new ShowFile(thisFile, name, extension, season, episode, title);
                
            }
            else {

                System.out.print("Enter movie title: ");
                String title = input.nextLine();

                System.out.print("Enter the year: ");
                int year = input.nextInt();

                String name = title + " (" + String.format("%4d", year) + ")." + extension;

                newFile = new MovieFile(thisFile, name, extension, year);
            }
        }

        return newFile;

    }    


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
