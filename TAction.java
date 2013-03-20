//=============================================================================
// TAction.java
//=============================================================================

//===============================
// Imports
//===============================
import java.io.File;


public class TAction
{
    //===============================================================
    //      Instance Variables
    //===============================================================
    private TFile   file;
    private String action;
    private String current;
    private String destination;

    //===============================================================
    //      Constructors
    //===============================================================

    public TAction(TFile file, String action)
    {
        this(file, action, "", "");
    }

    public TAction(TFile file, String action, String current, String destination)
    {
        this.file        = file;
        this.action      = action;
        this.current     = current;
        this.destination = destination;
    }

    //===============================================================
    //      getters / setters
    //===============================================================

    //=================================
    //      file
    //=================================

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    //=================================
    //      action
    //=================================

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    //=================================
    //      current
    //=================================

    public String getCurrent()
    {
        return current;
    }


    public void setCurrent(String current)
    {
        this.current = current;
    }
    
    //=================================
    //      destination
    //=================================

    public String getDestination()
    {
        return destination;
    }


    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    //===============================================================
    //      toString
    //===============================================================
    
    public String toString()
    {
        String actionString = "";

        if(action.equals("move")
            actionString = "Move";
        else if(action.equals("rename")
            actionString = "Rename";

        String returnVal = actionString + " " + current + " to " + destination;

        return returnVal;

    }
}
