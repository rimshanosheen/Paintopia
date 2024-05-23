import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow extends JPanel implements ActionListener {
    private Timer timer;
    private Shape[] shapes;
    private ImageIcon imageIcon;

    private static class Shape {
        int x, y, speedX, speedY, size;
        Color color;
        ShapeType type;

        Shape(int x, int y, int speedX, int speedY, int size, Color color, ShapeType type) {
            this.x = x;
            this.y = y;
            this.speedX = speedX;
            this.speedY = speedY;
            this.size = size;
            this.color = color;
            this.type = type;
        }

        void move() {
            x += speedX;
            y += speedY;
        }

        void reverseX() {
            speedX = -speedX;
        }

        void reverseY() {
            speedY = -speedY;
        }
    }

    private enum ShapeType {
        CIRCLE, RECTANGLE, TRIANGLE
    }

    public FirstWindow(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
        this.shapes = new Shape[] {
            new Shape(0, 100, 2, 2, 20, Color.RED, ShapeType.CIRCLE),
            new Shape(0, 300, 2, 2, 20, Color.MAGENTA, ShapeType.CIRCLE),
            new Shape(300, 0, 2, 2, 20, Color.YELLOW, ShapeType.CIRCLE),
            new Shape(600, 0, 2, 2, 20, Color.CYAN, ShapeType.CIRCLE),
            new Shape(0, 600, 2, 2, 20, Color.ORANGE, ShapeType.CIRCLE),
            new Shape(200, 0, 2, 2, 20, Color.BLUE, ShapeType.RECTANGLE),
            new Shape(0, 400, 2, 2, 20, Color.PINK, ShapeType.RECTANGLE),
            new Shape(0, 670, 2, 2, 20, new Color(0, 255, 0), ShapeType.RECTANGLE),
            new Shape(370, 0, 2, 2, 20, Color.GREEN, ShapeType.RECTANGLE),
            new Shape(0, 200, 2, 2, 20, Color.MAGENTA, ShapeType.RECTANGLE),
            new Shape(500, 0, 1, 1, 20, Color.GREEN, ShapeType.TRIANGLE),
            new Shape(0, 900, 1, 1, 20, Color.RED, ShapeType.TRIANGLE),
            new Shape(800, 0, 1, 1, 20, Color.RED, ShapeType.TRIANGLE),
            new Shape(0, 700, 1, 1, 20, new Color(0, 0, 255), ShapeType.TRIANGLE),
            new Shape(0, 100, 1, 1, 20, Color.MAGENTA, ShapeType.TRIANGLE),
            new Shape(100, 100, 2, 2, 20, new Color(255, 165, 0), ShapeType.CIRCLE), // Orange
            new Shape(200, 300, 2, 2, 20, new Color(255, 255, 0), ShapeType.CIRCLE), // Yellow
            new Shape(400, 500, 2, 2, 20, new Color(255, 0, 255), ShapeType.CIRCLE), // Magenta
            new Shape(500, 100, 2, 2, 20, new Color(0, 255, 255), ShapeType.CIRCLE), // Cyan
            new Shape(600, 200, 2, 2, 20, new Color(255, 192, 203), ShapeType.RECTANGLE), // Pink
            new Shape(300, 400, 2, 2, 20, new Color(255, 255, 0), ShapeType.RECTANGLE), // Yellow
            new Shape(200, 600, 2, 2, 20, new Color(0, 255, 255), ShapeType.RECTANGLE), // Cyan
            new Shape(400, 200, 2, 2, 20, new Color(255, 0, 255), ShapeType.RECTANGLE), // Magenta
            new Shape(500, 400, 2, 2, 20, new Color(255, 165, 0), ShapeType.RECTANGLE), // Orange
            new Shape(600, 600, 1, 1, 20, new Color(255, 192, 203), ShapeType.TRIANGLE) // Pink
        };
        this.timer = new Timer(10, this);
        this.timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(imageIcon.getImage(), getWidth() / 2 - imageIcon.getIconWidth() / 2, 
                    getHeight() / 2 - imageIcon.getIconHeight() / 2, this);

        int pX = getWidth() / 2 - imageIcon.getIconWidth() / 2;
        int pY = getHeight() / 2 - imageIcon.getIconHeight() / 2;
        int pWidth = imageIcon.getIconWidth();
        int pHeight = imageIcon.getIconHeight();

        g.drawRect(pX, pY, pWidth, pHeight);

        for (Shape shape : shapes) {
            g.setColor(shape.color);
            switch (shape.type) {
                case CIRCLE:
                    g.fillOval(shape.x, shape.y, shape.size, shape.size);
                    break;
                case RECTANGLE:
                    g.fillRect(shape.x, shape.y, shape.size, shape.size);
                    break;
                case TRIANGLE:
                    int[] xPoints = {shape.x, shape.x + shape.size, shape.x + shape.size / 2};
                    int[] yPoints = {shape.y + shape.size, shape.y + shape.size, shape.y};
                    g.fillPolygon(xPoints, yPoints, 3);
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int pX = getWidth() / 2 - imageIcon.getIconWidth() / 2;
        int pY = getHeight() / 2 - imageIcon.getIconHeight() / 2;
        int pWidth = imageIcon.getIconWidth();
        int pHeight = imageIcon.getIconHeight();

        for (Shape shape : shapes) {
            shape.move();

            if (shape.x < 0 || shape.x > getWidth() - shape.size) {
                shape.reverseX();
            }
            if (shape.y < 0 || shape.y > getHeight() - shape.size) {
                shape.reverseY();
            }

            if (shape.x < pX + pWidth && shape.x + shape.size > pX &&
                shape.y < pY + pHeight && shape.y + shape.size > pY) {
                shape.reverseX();
                shape.reverseY();
            }
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("First Window");
        ImageIcon imageIcon = new ImageIcon("path/to/image.png");
        FirstWindow firstWindow = new FirstWindow(imageIcon);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(firstWindow);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
