import java.awt.*;

public class GreenGoo extends GameObject {

    //------------------------------------------------------------------------------
    GreenGoo (int x , int y) {
        //super ( "G" , x , y , width , height );
        super ( "G" , x , y , ".//images//fireboy_small.png");
    }

    public void draw (Graphics g) {
        g.setColor ( Color.green );
        super.draw ( g );
    }

}