import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class GameObject {
    private final String ObjectType;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private BufferedImage picture;
    private final Rectangle box;

    public GameObject (String ObjectType ,
                       int x ,
                       int y
                       ) {
        this.ObjectType = ObjectType;
        this.x = x;
        this.y = y;
        this.width = 0;
        this.height = 0;
        this.box = new Rectangle (x, y, width , height );
    }

    public GameObject(String ObjectType ,
                      int x ,
                      int y ,
                      String picName){
        this.ObjectType = ObjectType;
        this.x = x;
        this.y = y;

        try {
            this.picture = ImageIO.read(new File(picName));
        }
        catch (IOException ex){};

        //we still need a Rectangle object to be able to detect collision etc. but let's not draw it
        this.width = this.picture.getWidth ();
        this.height =  this.picture.getHeight ();
        this.box = new Rectangle ( x , y , this.width , this.height );

    }


    public String ObjectType() {
        return ObjectType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public Rectangle getBox() {
        return this.box;
    }

    //------------------------------------------------------------------------------
    public void draw (Graphics g) {
        //g.fillRect ( this.x , this.y , this.width , this.height );
        if (this.ObjectType.equals("B")) {
            g.drawImage ( this.picture, this.x, this.y, null);
        }
        else {
            //g.drawImage(this.picture, this.x-this.width, this.y-this.height, null);
            g.drawImage(this.picture, this.x, this.y, null);
        }

    }


/*
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GameObject) obj;
        return Objects.equals(this.ObjectType, that.ObjectType) &&
                this.x == that.x &&
                this.y == that.y &&
                this.width == that.width &&
                this.height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ObjectType, x, y, width, height);
    }

    @Override
    public String toString() {
        return "GameObject[" +
                "ObjectType=" + ObjectType + ", " +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "width=" + width + ", " +
                "height=" + height + ']';
    }

*/
}