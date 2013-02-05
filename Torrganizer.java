//=============================================================================
// Torrganizer
//
//             Author:  James Mack
// Project Start Date:  2013-02-04
//
//=============================================================================

//=================
// Imports
//=================

import java.io.File;


public class Torrganizer
{
    private static final String DEFAULT_PATH = "/home/james/";

    public static void main(String [] args)
    {
        File homeDir = new File(DEFAULT_PATH);
        String [] files = homeDir.list();
        File [] filePaths = homeDir.listFiles();

        for(String thisFile: files) {
            System.out.println(thisFile);
        }
    }

    public void printFiles(File [] files, int indentLevel)
    {
        String indent = "";
        for(int i = 0; i < indentLevel; i++) 
            indent += "    ";

        for(File thisFile: files) {
            System.out.print(indent);
            System.out.println(thisFile.getName());
            
            if(thisFile.isDirectory()) {
                System.out.println("isDirectory");
                printFiles(thisFile.listFiles(), indentLevel + 1);
            }
        }
    }
}
