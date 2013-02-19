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
    private static String DOWNLOAD_PATH = "/home/james/Torrganizer/files";
    private static String SORT_PATH     = "/home/james/Torrganizer/Sort";

    // Regex
    private static final String REGEX_EXTENSION = ".*\\.([a-zA-Z0-9]{3,4})$";
    private static final String REGEX_SHOW1     = "([a-zA-Z0-9\\.\\ ]+)S(\\d\\d)E(\\d\\d).*\\.(mp4|mpeg4|avi|mkv|wmv|mov)";
    private static final String REGEX_SHOW2     = "([a-zA-Z0-9\\.\\ ]+)(\\d\\d\\d).*\\.(mp4|mpeg4|avi|mkv|wmv|mov)";
    private static final String REGEX_MOVIE     = "([a-zA-Z0-9\\.\\ ]+).*[\\(|\\]]?(19|20\\d\\d)[\\(|\\]]?.*\\.(mp4|mpeg4|avi|mkv|wmv|mov)";


    //===============================================================
    //      main
    //===============================================================

    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Torrganizer\n");

        String inputStr;
        System.out.print("Enter download directory: ");
        inputStr  = input.nextLine();
        if(!inputStr.equals(""))
            DOWNLOAD_PATH = inputStr;
        System.out.println("Download directory set to '" + DOWNLOAD_PATH + "'");

        System.out.print("Enter destination directory: ");
        inputStr  = input.nextLine();
        if(!inputStr.equals(""))
            SORT_PATH = inputStr;
        System.out.println("Destination directory set to '" + SORT_PATH + "'");

        File homeDir = new File(DOWNLOAD_PATH);

        Vector<TFile> allFiles = new Vector<TFile>();

        allFiles = processDirectory(homeDir);

    }

    public static Vector<TFile> processDirectory(File thisDir)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\nProcessing directory: " + thisDir.getName());

        File [] files = thisDir.listFiles();

        if(files.length <= 0) {
            System.out.println("Directory empty: " + thisDir.getName());
            return new Vector<TFile>();
        }
        else {
            System.out.print("Proceed with directory (y/n)? ");

            if(!input.nextLine().startsWith("y")) {
                System.out.println("Skipping directory");
                return new Vector<TFile>();
            }

        }

        Vector<TFile> allFiles = new Vector<TFile>();

        for(File thisFile: files) {
            allFiles.addAll(processFile(thisFile));
        }

        return allFiles;
    }


    //===============================================================
    //      processFile
    //===============================================================

    private static Vector<TFile> processFile(File thisFile)
    {
        System.out.println("\nProcessing file: " + thisFile.getName());

        Scanner input = new Scanner(System.in);

        // if directory, enter directory
        if(thisFile.isDirectory()) {
            return processDirectory(thisFile);
        }

        // match and create TFile representation   
        TFile thisTFile = matchFile(thisFile);
        if(thisTFile == null)
            return new Vector<TFile>();

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
    
        Vector<TFile> returnVector = new Vector<TFile>();
        returnVector.addElement(thisTFile);

        return returnVector;
    }    

    //===============================================================
    //      matchFile
    //===============================================================

    private static TFile matchFile(File thisFile)
    {
        Pattern extensionPattern = Pattern.compile(REGEX_EXTENSION);
        Matcher extensionMatcher = extensionPattern.matcher(thisFile.getName());

        Pattern show1Pattern = Pattern.compile(REGEX_SHOW1);
        Matcher show1Matcher = show1Pattern.matcher(thisFile.getName());

        Pattern show2Pattern = Pattern.compile(REGEX_SHOW2);
        Matcher show2Matcher = show2Pattern.matcher(thisFile.getName());

        Pattern moviePattern = Pattern.compile(REGEX_MOVIE);
        Matcher movieMatcher = moviePattern.matcher(thisFile.getName());

        TFile newFile = null;
    
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
        else if(show2Matcher.matches()) {

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

        else if(extensionMatcher.matches()) {
            String extension = extensionMatcher.group(1);
            System.out.println("Unable to match file, found extension '" + extension + "'");

            Scanner input = new Scanner(System.in);

            System.out.println("Select option: ");
            System.out.println("(1) Process as Movie");
            System.out.println("(2) Process as Show");
            System.out.println("(3) Process Manually");
            System.out.println("(4) Skip");
            int choice;

            do {
                System.out.print("Selection? ");
                choice = input.nextInt();
            } while((choice != 1) && (choice != 2) && (choice != 3) &&  (choice != 4));

            String title, name;

            switch(choice) {
                case 1:
                    System.out.print("Enter show title: ");
                    title = input.nextLine();

                    System.out.print("Enter the season: ");
                    int season = input.nextInt();

                    System.out.print("Enter the episode: ");
                    int episode = input.nextInt();

                    name = title + " - S" + String.format("%02d", season) + "E" + String.format("%02d", episode) + "." + extension;

                    newFile = new ShowFile(thisFile, name, extension, season, episode, title);
                    break;

                case 2:
                    System.out.print("Enter movie title: ");
                    title = input.nextLine();

                    System.out.print("Enter the year: ");
                    int year = input.nextInt();

                    name = title + " (" + String.format("%4d", year) + ")." + extension;

                    newFile = new MovieFile(thisFile, name, extension, year);
                    break;

                case 3:
                    System.out.print("Enter file name: ");
                    name = input.nextLine();

                    newFile = new TFile(thisFile, name, extension);
                    break;

                case 4:
                    System.out.println("Skipping file");
                    return null;

                default:
            }
        }

        else {
            System.out.println("Error: Unable to process file\n");
            return null;
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
