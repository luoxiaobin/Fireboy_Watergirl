import java.awt.Graphics; 
import java.awt.Color; 
import java.awt.Rectangle; 
 
public class Jumper{ 
    private int x; 
    private int y; 
    private int width; 
    private int height; 
    private Rectangle box;     
    private int Vx; 
    private int Vy; 
//------------------------------------------------------------------------------     
    Jumper(int x, int y, int width, int height){ 
        this.x = x; 
        this.y = y; 
        this.width = width; 
        this.height = height; 
        this.box = new Rectangle(x, y, width, height);         
        this.Vx = 0; 
        this.Vy = 0;  
    } 
//------------------------------------------------------------------------------ 
    public int getHeight(){ 
        return this.height; 
    } 
    public int getVy(){ 
        return this.Vy; 
    } 
    public Rectangle getBox(){ 
        return this.box; 
    } 
    public void setY(int y){ 
        this.y = y; 
    } 
    public void setVx(int Vx){ 
        this.Vx = Vx; 
    } 
    public void setVy(int Vy){ 
        this.Vy = Vy; 
    } 
    public void setBox(){ 
        this.box.setLocation(this.x, this.y);  
    }     
//------------------------------------------------------------------------------     
    public void draw(Graphics g){ 
        g.setColor(Color.red); 
        g.fillRect(this.x, this.y, this.width, this.height); 
    } 
    public void accellerate(){ 
        this.Vy += Const.GRAVITY; 
    } 
    public void moveX(){ 
        this.x += this.Vx; 
        this.setBox(); 
    } 
    public void moveY(int bottomLimit){ 
        this.y += this.Vy; 
        if (this.y + this.height >= bottomLimit){ 
            this.y = bottomLimit - this.height; 
            this.Vy = 0; 
        } 
        this.setBox(); 
    } 
    
    public boolean collides(Platform other){ 
        return this.getBox().intersects(other.getBox()); 
    }     
    public boolean isOnLevel(int level){ 
        return (this.y + this.height == level); 
    } 
}