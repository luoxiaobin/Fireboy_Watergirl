import java.awt.*;

public class Platform extends GameObject {

    //------------------------------------------------------------------------------
    Platform (int x , int y , int width , int height) {
        super ( "P" , x , y , width , height );
    }

    public void draw (Graphics g) {
        g.setColor ( Color.blue );
        super.draw ( g );
    }

}