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
    private static final String DEFAULT_PATH = "/home/james";

    public static void main(String [] args)
    {
        File homeDir = new File(DEFAULT_PATH);
        String [] files = homeDir.list();
        File [] filePaths = homeDir.listFiles();

        printFiles(filePaths, 0);
    }

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
