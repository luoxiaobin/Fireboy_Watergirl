import java.awt.Graphics; 
import java.awt.Color; 
import java.awt.Rectangle; 
 
public class GreenGoo{
    private int rowNum; 
    private char[] line; 
     
    private int x; 
    private int y; 
    private int width; 
    private int height; 
    private Rectangle box; 
//------------------------------------------------------------------------------     
    GreenGoo(int x, int y, int width, int height) { 
         
        this.x = x; 
        this.y = y;
        this.width = width;
        this.height = height;
        this.box = new Rectangle(x, y, width, height);
    } 

    void GreenGoo(int rowNum) { 
        this.rowNum = rowNum; 
      
    } 
//setters and getters 
    public void setX(int x) { 
        this.x = x; 
    } 
    public int getX() { 
        return this.x; 
    }
    public void setY(int y) { 
        this.y = y; 
    } 
    public int getY() { 
        return this.y; 
    }  
    public void setWeight(int width) { 
        this.width = width; 
    } 
    public int getWidth() { 
        return this.width; 
    }
    public void setHeight(int height) { 
        this.height = height; 
    } 
    public int getHeight() { 
        return this.height; 
    }  

    public Rectangle getBox() {
        return this.box;
    }
        
    
    public void setLine(char[] line) { 
        //this.line = new char[line.length]; 
        this.line = line; 
    } 
    public char[] getLine() { 
        return this.line; 
    }
    
    //------------------------------------------------------------------------------     
    public void draw(Graphics g){ 
        g.setColor(Color.green); 
        g.fillRect(this.x, this.y, this.width, this.height); 
    } 

    
}