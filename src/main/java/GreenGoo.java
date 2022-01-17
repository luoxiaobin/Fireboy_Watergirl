import java.awt.*;

public class GreenGoo extends GameObject {

    //------------------------------------------------------------------------------
    GreenGoo (int x , int y , int width , int height) {
        super ( "G" , x , y , width , height );
    }

    public void draw (Graphics g) {
        g.setColor ( Color.green );
        super.draw ( g );
    }

}