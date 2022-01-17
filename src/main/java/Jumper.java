import java.awt.*;
 
public class Jumper {
    private final int width;
    private final int height;
    private final Rectangle box;
    private int x;
    private int y;
    private int Vx;
    private int Vy;

    //------------------------------------------------------------------------------
    Jumper (int x , int y , int width , int height) {
        //super ("J", x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.box = new Rectangle ( x , y , width , height );
        this.Vx = 0;
        this.Vy = 0;
    }

    //------------------------------------------------------------------------------
    public int getHeight ( ) {
        return this.height;
    }

    public int getVy ( ) {
        return this.Vy;
    }

    public void setVy (int Vy) {
        this.Vy = Vy;
    }

    public int getVx ( ) {
        return this.Vx;
    }

    public void setVx (int Vx) {
        this.Vx = Vx;
    }

    public Rectangle getBox ( ) {
        return this.box;
    }

    public int getX ( ) {
        return this.x;
    }

    public void setY (int y) {
        this.y = y;
    }

    public void setBox ( ) {
        this.box.setLocation ( this.x , this.y );
    }

    //------------------------------------------------------------------------------
    public void draw (Graphics g) {
        g.setColor ( Color.red );
        g.fillRect ( this.x , this.y , this.width , this.height );
    }

    public void accelerate ( ) {
        this.Vy += Const.GRAVITY;
    }

    public void moveX ( ) {
        this.x += this.Vx;
        this.setBox ( );
    }

    public void moveY (int bottomLimit) {
        this.y += this.Vy;
        if (this.y + this.height >= bottomLimit) {
            this.y = bottomLimit - this.height;
            this.Vy = 0; 
        } 
        this.setBox(); 
    } 
    
    public boolean collides(GameObject other){
        return this.getBox().intersects(other.getBox()); 
    }     
    public boolean isOnLevel(int level){ 
        return (this.y + this.height == level); 
    } 
}