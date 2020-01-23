package multi_shape;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author mashael
 */
public class MultiShapes extends Applet {

    private final static int LINE = 0, REC = 1, OVAL = 2, RED = 0, BLUE = 1, GREEN = 2;

    private Vector<Line> lines;
    private Vector<Rectangle> rectangles;
    private Vector<Oval> ovals;
    private int shapeSelector = 0, colorSelector = 0;
    private boolean isFill = false;
    private int refY;
    private int refX;

    @Override
    public void init() {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        addBtns();

        lines = new Vector<>(10);
        rectangles = new Vector<>(10);
        ovals = new Vector<>(10);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

                switch (shapeSelector) {
                    case LINE:
                        lines.add(new Line(e.getX(), e.getX(), e.getY(), e.getY(), getColor()));
                        break;

                    case REC:
                        rectangles.add(new Rectangle(e.getX(), e.getY(), 0, 0, getColor(), isFill));
                        refX = e.getX();
                        refY = e.getY();

                        break;

                    case OVAL:
                        ovals.add(new Oval(e.getX(), e.getY(), 0, 0, getColor(), isFill));
                        refX = e.getX();
                        refY = e.getY();

                        break;
                }

            }

            private Color getColor() {
                Color color;
                switch (colorSelector) {
                    case BLUE:
                        color = Color.BLUE;
                        break;
                    case GREEN:
                        color = Color.GREEN;
                        break;
                    case RED:
                    default:
                        color = Color.RED;
                }
                return color;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                switch (shapeSelector) {
                    case LINE:
                        Line l = lines.get(lines.size() - 1);
                        l.setX1(e.getX());
                        l.setY1(e.getY());
                        break;

                    case REC:
                        Rectangle rectangle = rectangles.get(rectangles.size() - 1);
                        setWidth(e, rectangle);
                        setHeight(e, rectangle);
                        break;

                    case OVAL:
                        Oval oval = ovals.get(ovals.size() - 1);
                        setWidth(e, oval);
                        setHeight(e, oval);
                        break;
                }
                repaint();
            }

            private void setHeight(MouseEvent e, Shape shape) {
                int height;

                if (shape.getY() > e.getY()) {
                    height = refY - e.getY();
                    shape.setY(e.getY());
                } else {
                    height = e.getY() - shape.getY();

                }
                shape.setHeight(height);
            }

            private void setWidth(MouseEvent e, Shape shape) {
                int width;

                if (shape.getX() > e.getX()) {
                    width = refX - e.getX();
                    shape.setX(e.getX());
                } else {
                    width = e.getX() - shape.getX();
                }
                shape.setWidth(width);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    @Override
    public void resize(int width, int height) {
        super.resize(960, 540); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paint(Graphics g) {
        for (Line line : lines) {
            g.setColor(line.getLineColor());
            g.drawLine(line.getX(), line.getY(), line.getX1(), line.getY1());
        }

        for (Rectangle rectangle : rectangles) {
            g.setColor(rectangle.getRectColor());
            if (rectangle.isFill()) {
                g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            } else {
                g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }

        for (Oval oval : ovals) {
            g.setColor(oval.getOvalColor());
            if (oval.isFill()) {
                g.fillOval(oval.x, oval.y, oval.width, oval.height);
            } else {
                g.drawOval(oval.x, oval.y, oval.width, oval.height);
            }
        }
    }

    private void addBtns() {
        addShapeButton("line", LINE);
        addShapeButton("oval", OVAL);
        addShapeButton("Rectangle", REC);


        addFillBtn("Filled", true);
        addFillBtn("Not Filled", false);

        addColorBtn("Red", RED);
        addColorBtn("Green", GREEN);
        addColorBtn("Blue", BLUE);
        Button btn = new Button("Reset All ");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lines.clear();
                rectangles.clear();
                ovals.clear();
                repaint();

            }
        });

        add(btn);


    }

    private void addColorBtn(String red, int red2) {
        Button btn;
        btn = new Button(red);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorSelector = red2;
            }
        });
        add(btn);
    }

    private void addFillBtn(String filled, boolean b) {
        Button btn;
        btn = new Button(filled);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFill = b;
            }
        });
        add(btn);
    }

    private void addShapeButton(String line, int line2) {
        Button btn;
        btn = new Button(line);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeSelector = line2;
            }
        });
        add(btn);
    }

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

    abstract class Shape {
        protected int x, y, width, height;
        protected Color shapeColor;
        protected boolean isFill;

        public Shape(int x, int y, int width, int height, Color shapeColor, boolean isFill) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.shapeColor = shapeColor;
            this.isFill = isFill;
        }

        public Shape(int x, int y, int width, int height) {
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

    class Rectangle extends Shape {

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


}
