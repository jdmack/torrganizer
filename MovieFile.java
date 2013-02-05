//=============================================================================
// MovieFile.java
//=============================================================================

//===============================
// Imports
//===============================


public class MovieFile extends TFile
{
    //===============================================================
    //      Constants
    //===============================================================


    //===============================================================
    //      Instance Variables
    //===============================================================
    private int releaseYear;

    //===============================================================
    //      Constructors
    //===============================================================

    public MovieFile(String name, String extension, int year)
    {
        super(name, extension);

        releaseYear = year;
    }

    //===============================================================
    //      getters / setters
    //===============================================================

    //=================================
    //      yearYear
    //=================================

    public int getReleaseYear()
    {
        return releaseYear;
    }

    public void setReleaseYear(int year)
    {
        releaseYear = year;
    }
    
    //===============================================================
    //      conditionals
    //===============================================================

    public boolean isMovie() { return true; }

}