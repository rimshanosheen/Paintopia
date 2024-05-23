import java.awt.*;

public class Paint {
    public Paint(){
        DrawFrame frame = new DrawFrame();
        frame.setTitle("Paintopia");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Paint.class.getResource("icons\\icon.jpeg")));
        frame.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}