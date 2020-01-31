package multi_shape;

import java.awt.*;

abstract class Shape2D {
    protected int x, y, width, height;
    protected Color shapeColor;
    protected boolean isFill;

    public Shape2D(int x, int y, int width, int height, Color shapeColor, boolean isFill) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.shapeColor = shapeColor;
        this.isFill = isFill;
    }

    public Shape2D(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        shapeColor = Color.BLACK;
        isFill = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(Color shapeColor) {
        this.shapeColor = shapeColor;
    }

    public boolean isFill() {
        return isFill;
    }

    public void setFill(boolean isFill) {
        this.isFill = isFill;
    }


}

