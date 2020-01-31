package multi_shape;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

/**
 * @author mashael
 * GitHub Repo: https://github.com/abdomashael/MultiShape
 */
public class MultiShapes extends Applet {

    private final static int LINE = 0, REC = 1, OVAL = 2, RED = 0, BLUE = 1, GREEN = 2;

    private Vector<Line> lines;
    private Vector<Shape2D> shape2DS;
    private int shapeSelector = 0, colorSelector = 0;
    private boolean isFill = false;
    private int refY;
    private int refX;

    @Override
    public void init() {
        super.init();
        addBtns();

        lines = new Vector<>(10);
        shape2DS = new Vector<>(10);

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
                        shape2DS.add(new Rectangle(e.getX(), e.getY(), 0, 0, getColor(), isFill));
                        refX = e.getX();
                        refY = e.getY();

                        break;

                    case OVAL:
                        shape2DS.add(new Oval(e.getX(), e.getY(), 0, 0, getColor(), isFill));
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
                        Rectangle rectangle = (Rectangle) shape2DS.get(shape2DS.size() - 1);
                        setWidth(e, rectangle);
                        setHeight(e, rectangle);
                        break;

                    case OVAL:
                        Oval oval = (Oval) shape2DS.get(shape2DS.size() - 1);
                        setWidth(e, oval);
                        setHeight(e, oval);
                        break;
                }
                repaint();
            }

            private void setHeight(MouseEvent e, Shape2D shape2D) {
                int height;

                if (shape2D.getY() > e.getY()) {
                    height = Math.abs(refY - e.getY());
                    shape2D.setY(e.getY());
                } else {
                    height = e.getY() - shape2D.getY();

                }
                shape2D.setHeight(height);
            }

            private void setWidth(MouseEvent e, Shape2D shape2D) {
                int width;

                if (shape2D.getX() > e.getX()) {
                    width = Math.abs(refX - e.getX());
                    shape2D.setX(e.getX());
                } else {
                    width = e.getX() - shape2D.getX();
                }
                shape2D.setWidth(width);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    @Override
    public void resize(int width, int height) {
        super.resize(960, 540);
    }

    @Override
    public void paint(Graphics g) {
        for (Line line : lines) {
            g.setColor(line.getLineColor());
            g.drawLine(line.getX(), line.getY(), line.getX1(), line.getY1());
        }

        for (Shape2D shape2D : shape2DS) {
            if (shape2D instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape2D;
                g.setColor(rectangle.getRectColor());
                if (rectangle.isFill()) {
                    g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                } else {
                    g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                }
            } else if (shape2D instanceof Oval) {
                Oval oval = (Oval) shape2D;
                g.setColor(oval.getOvalColor());
                if (oval.isFill()) {
                    g.fillOval(oval.x, oval.y, oval.width, oval.height);
                } else {
                    g.drawOval(oval.x, oval.y, oval.width, oval.height);
                }
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
        btn.addActionListener(actionEvent -> {
            lines.clear();
            shape2DS.clear();
            repaint();

        });

        add(btn);


    }

    private void addColorBtn(String color, int colorName) {
        Button btn;
        btn = new Button(color);
        btn.addActionListener(e -> colorSelector = colorName);
        add(btn);
    }

    private void addFillBtn(String filled, boolean b) {
        Button btn;
        btn = new Button(filled);
        btn.addActionListener(e -> isFill = b);
        add(btn);
    }

    private void addShapeButton(String shape, int shapeName) {
        Button btn;
        btn = new Button(shape);
        btn.addActionListener(e -> shapeSelector = shapeName);
        add(btn);
    }
}