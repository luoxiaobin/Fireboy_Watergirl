import java.awt.*;

public class MovingPlatform extends GameObject {
    private int Vy;
    private int movingDistance;
    private final int originalY;


    //------------------------------------------------------------------------------
    MovingPlatform(int x, int y) {
        this.originalY = y;
        super ("G", x, y, ".//images//platform.png");
        this.Vy = 0;
    }

    public int getVy() {
        return this.Vy;
    }
        
    public void setVy(int Vy) {
        this.Vy = Vy;
    }

    public void setMovingDistance(int movingDistance) {
        this.movingDistance = movingDistance; 
    }
  
    public void movePlatformDown() {
        if ((this.getY()-this.originalY) < movingDistance) 
            this.setY(this.Vy++);
    }
    
    public void movePlatformUp() {
        if ((this.originalY-this.getY()) < movingDistance) 
            this.setY(this.Vy--);
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        super.draw(g);
    }

}