import java.awt.Graphics; 
import java.awt.Color; 
import java.awt.Rectangle; 
 
public class Rectangle_old{ 
    private int x;
    private int y;
    private int width; 
    private int height;
    
//------------------------------------------------------------------------------     
    void Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    } 
    
    void Rectangle(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    } 

//------------------------------------------------------------------------------ 
    public void setXY(int x, int Y) { 
        this.x = x;
        this.y = y;
    } 
    
    
    public void setHeight(int height) { 
        this.height = height; 
    } 
    public int getheight() { 
        return this.height; 
    } 

    public void setWidth(int width) { 
        this.width = width; 
    } 
    public int getWidth() { 
        return this.width; 
    } 

    public boolean intersects(Rectangle anotherBox){
        //to be improved
        return false;
    }
//------------------------------------------------------------------------------     
    public void draw(Graphics g){ 
        g.setColor(Color.blue); 
        g.fillRect(this.x, this.y, this.width, this.height); 
    } 
}