package multi_shape;

import java.awt.*;

class Oval extends Shape {

    public Oval(int x, int y, int width, int height, Color shapeColor, boolean isFill) {
        super(x, y, width, height, shapeColor, isFill);
    }

    public Oval(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Color getOvalColor() {
        return shapeColor;
    }

    public void setShapeColor(Color ovalColor) {
        this.shapeColor = ovalColor;
    }


}
