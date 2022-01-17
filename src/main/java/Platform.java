import java.awt.*;

public class Platform extends GameObject {

    //------------------------------------------------------------------------------
    Platform (int x , int y ) {
        //super ( "P" , x , y , width , height );
        super ( "G" , x , y , ".//images//platform.png");
    }

    public void draw (Graphics g) {
        g.setColor ( Color.blue );
        super.draw ( g );
    }

}