import java.awt.*;

public class Door extends GameObject {

    //------------------------------------------------------------------------------
    Door (int x , int y , int width , int height) {
        super ( "D" , x , y , width , height );
    }

    public void draw (Graphics g) {
        g.setColor ( Color.white );
        super.draw ( g );
    }

}