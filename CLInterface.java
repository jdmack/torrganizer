//=============================================================================
// TFile.java
//=============================================================================

//===============================
// Imports
//===============================
import java.util.Scanner;

public class CLInterface //implements TorrInterface
{
    //===============================================================
    //      Constants
    //===============================================================


    //===============================================================
    //      Instance Variables
    //===============================================================
    Scanner strInput;
    Scanner intInput;

    //===============================================================
    //      Constructors
    //===============================================================

    public CLInterface()
    {
        System.out.println("\nTorrganizer");

        strInput = new Scanner(System.in);
        intInput = new Scanner(System.in);
    }

    //===============================================================
    //      Input Methods
    //===============================================================

    public String getDownloadDirectory()
    {
        System.out.print("\nEnter download directory: ");        
        return strInput.nextLine();
    }

    public String getDestinationDirectory()
    {
        System.out.print("\nEnter destination directory: ");        
        return strInput.nextLine();
    }

    public boolean getDirectoryProceed()
    {
        System.out.print("Proceed with directory (y/n)? ");


        if(!strInput.nextLine().startsWith("y")) {
            System.out.println("Skipping directory");
            return false;
        }

        return true;
    }

    public String getMoveToPath(String destinationPath, String fileName)
    {
        System.out.println(destinationPath + "/???/" + fileName);
        System.out.print("Enter missing path information: ");

        return strInput.nextLine();
    }

    public int getProcessOption()
    {
        System.out.println("Select option: ");
        System.out.println("(1) Process as Show");
        System.out.println("(2) Process as Movie");
        System.out.println("(3) Process Manually");
        System.out.println("(4) Skip");
        System.out.print("Selection? ");

        return intInput.nextInt();
    }

    public String getShowTitle()
    {
        System.out.print("Enter show title: ");
        return strInput.nextLine();
    }

    public int getShowSeason()
    {
        System.out.print("Enter show season: ");
        return intInput.nextInt();
    }

    public int getShowEpisode()
    {
        System.out.print("Enters show episode: ");
        return intInput.nextInt();
    }

    public String getMovieTitle()
    {
        System.out.print("Enter movie title: ");
        return strInput.nextLine();
    }

    public int getMovieYear()
    {
        System.out.print("Enter movie year: ");
        return intInput.nextInt();
    }

    public String getFileName()
    {
        System.out.print("Enter file name: ");
        return strInput.nextLine();
    }


    //===============================================================
    //      Output Methods
    //===============================================================
    
    public void outputDownloadDirectory(String path)
    {
        System.out.println("Download directory set to: '" + path + "'");
    }

    public void outputDestinationDirectory(String path)
    {
        System.out.println("Destination directory set to: '" + path + "'");
    }

    public void outputProcessingDirectory(String dir)
    {
        System.out.println("\nProcessing directory: " + dir);
    }

    public void outputProcessingFile(String file)
    {
        System.out.println("\nProcessing file: " + file);
    }

    public void outputDirectoryEmpty(String dir)
    {
        System.out.println(dir + ": directory empty");
    }

    public void outputDirectoryNotExist(String dir)
    {
        System.out.println(dir + ": directory does not exist");
    }

    public void outputDirectoryCreateSuccess(String dir)
    {
        System.out.println(dir + ": directory created successfully");
    }

    public void outputDirectoryCreateError(String dir)
    {
        System.out.println(dir + ": Error creating directory");
    }

    public void outputFileAttemptRename(String path)
    {
        System.out.println("Attempting to rename/move to: " + path);
    }

    public void outputFileRenameSuccess()
    {
        System.out.println("Rename completed successfully");
    }

    public void outputFileRenameError()
    {
        System.out.println("Error renaming/moving file");
    }

    public void outputNoMatch(String extension)
    {
        System.out.println("Unable to match file, found extension: " + extension);
    }

    public void outputSkipFile()
    {
        System.out.println("Skipping file");
    }

    public void outputProcessError()
    {
        System.out.println("Error processing file");
    }

    public void outputObject(TFile thisFile)
    {
        System.out.println(thisFile.toString());
    }

    public void outputDebug(String message)
    {
        System.out.println("[DEBUG] " + message);
    }

    //===============================================================
    //      getters / setters
    //===============================================================

    //=================================
    //      varName
    //=================================



}
