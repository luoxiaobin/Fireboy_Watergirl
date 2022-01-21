import java.awt.*;

public class Door extends GameObject {

    private String name;
    
    //------------------------------------------------------------------------------
    Door (int x , int y, String name) {
        super ("G", x, y, ".//images//door.png");
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    public void draw (Graphics g) {
        g.setColor(Color.white);
        super.draw(g);
    }
}