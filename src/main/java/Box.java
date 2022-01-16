import java.awt.Graphics; 
import java.awt.Color; 
import java.awt.Rectangle; 
 
public class Box{ 
    private int width; 
    private int y;
    private int x;
    private Rectangle box; 
//------------------------------------------------------------------------------     
    public void Box(int y, int x, int width) {
        this.width = width;
        this.y = y;
        this.x = x;
        this.box = new Rectangle(x, y, width, 1); 
    } 
//------------------------------------------------------------------------------ 
    public void setWidth(int width) { 
        this.width = width; 
    } 
    public int getWidth() { 
        return this.width; 
    } 
    public void setY(int y) { 
        this.y = y; 
    } 
    public int getY() { 
        return this.y; 
    } 
    public void setX(int x) { 
        this.x = x; 
    } 
    public int getX() { 
        return this.x; 
    } 
    public void setBox(Rectangle box) { 
        this.box = box; 
    } 
    public Rectangle getBox() { 
        return this.box; 
    } 
//------------------------------------------------------------------------------     
    public void draw(Graphics g){ 
        g.setColor(Color.blue); 
        g.fillRect(this.x, this.y, this.width, 1); 
    } 
}