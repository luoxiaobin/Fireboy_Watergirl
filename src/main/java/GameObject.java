import java.util.Objects;

public final class GameObject {
    private final String ObjectType;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public GameObject(String ObjectType,
                      int x,
                      int y,
                      int width,
                      int height) {
        this.ObjectType = ObjectType;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String ObjectType() {
        return ObjectType;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

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


}