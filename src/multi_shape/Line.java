package multi_shape;

import java.awt.*;

class Line {
    private int x, x1, y, y1;
    private Color lineColor;

    public Line(int x, int x1, int y, int y1, Color lineColor) {
        this.x = x;
        this.x1 = x1;
        this.y = y;
        this.y1 = y1;
        this.lineColor = lineColor;
    }


    public Line(int x, int x1, int y, int y1) {
        this.x = x;
        this.x1 = x1;
        this.y = y;
        this.y1 = y1;
        lineColor = Color.BLACK;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

}

