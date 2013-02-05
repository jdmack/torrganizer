//=============================================================================
// TFile.java
//=============================================================================

//===============================
// Imports
//===============================


public class TFile
{
    //===============================================================
    //      Constants
    //===============================================================


    //===============================================================
    //      Instance Variables
    //===============================================================
    private String fileName;
    private String fileExtension;

    //===============================================================
    //      Constructors
    //===============================================================

    public TFile(String name, String extension)
    {
        fileName      = name;
        fileExtension = extension;
    }

    //===============================================================
    //      getters / setters
    //===============================================================

    //=================================
    //      fileName
    //=================================

    public String getName()
    {
        return fileName;
    }

    public void setName(String name)
    {
        fileName = name;
    }

    //=================================
    //      fileExtension
    //=================================

    public String getExtension()
    {
        return fileName;
    }


    public void setExtension(String extension)
    {
        fileExtension = extension;
    }
    
    //===============================================================
    //      conditionals
    //===============================================================

    public boolean isMovie() { return false; }
    public boolean isShow()  { return false; }





}
