package multi_shape;

import java.awt.*;

class Rectangle extends Shape2D {

    public Rectangle(int x, int y, int width, int height, Color shapeColor, boolean isFill) {
        super(x, y, width, height, shapeColor, isFill);
    }


    public Rectangle(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    public Color getRectColor() {
        return shapeColor;
    }

    public void setRecColor(Color recColor) {
        this.shapeColor = recColor;
    }

}
