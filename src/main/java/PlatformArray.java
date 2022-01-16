//import java.awt.Graphics; 
//import java.awt.Color; 
import java.awt.Rectangle; 
 
public class PlatformArray{ 
    private int rowNum; 
    private char[] line; 

//------------------------------------------------------------------------------     
    PlatformArray(int rowNum) {
         
        this.rowNum = rowNum; 
      
    } 
//setters and getters 
    public void setRowNum(int rowNum) { 
        this.rowNum = rowNum; 
    } 
    public int getRowNum() { 
        return this.rowNum; 
    } 
    public void setLine(char[] line) { 
        //this.line = new char[line.length]; 
        this.line = line; 
    } 
    public char[] getLine() { 
        return this.line; 
    } 
}