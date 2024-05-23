import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class PaintControl extends JToolBar {
    private JLabel strokeLabel;

    private final DrawFrame frame;

    PaintControl(DrawFrame frame) {
        this.frame = frame;

        // create a color chooser
        ColorChooser colorChooser = new ColorChooser(frame);
        DrawingTools drawingTools = new DrawingTools();
        DrawingShapes drawingShapes = new DrawingShapes();
        UndoRedo undoRedo = new UndoRedo();


//        add(new Separator());
        add(undoRedo);
        add(drawingTools);
        add(new ManageStroke());
        add(drawingShapes);
        add(colorChooser);
        

        setBackground(Color.WHITE);
        setLayout(new GridLayout(6, 1));
        setFloatable(false);
    }


    class DrawingTools extends JPanel {
        DrawingTools() {

            JButton pencil = new JButton("");
            ImageIcon pencilPic = new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/pencil.jpg")));
            pencil.setBackground(Color.white);
            pencil.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPaintPanel().active_tool = 0;
                }
            });
            pencil.setIcon(pencilPic);

            JButton eraser = new JButton("");
            ImageIcon eraserPic = new ImageIcon(Objects.requireNonNull(getClass().getResource("icons\\eras.jpg")));
            eraser.setBackground(Color.WHITE);
            eraser.setIcon(eraserPic);
            eraser.setSize(new Dimension(pencil.getWidth(),pencil.getHeight()));
            eraser.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPaintPanel().active_tool = 4;
                }
            });

            add(pencil);
            add(eraser);

            setBorder(new TitledBorder("Tools"));

        }
    }

    class DrawingShapes extends JPanel {
        DrawingShapes() {
            JButton circleBtn = new JButton("");
            circleBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/circle.png"))));
            circleBtn.setPreferredSize(new Dimension(30, 30));
            circleBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPaintPanel().active_tool = 3;
                }
            });
            add(circleBtn);

            JButton rectangleBtn = new JButton("");
            rectangleBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/rectangle.png"))));
            rectangleBtn.setPreferredSize(new Dimension(30, 30));
            rectangleBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPaintPanel().active_tool = 2;
                }
            });
            add(rectangleBtn);

            JButton lineBtn = new JButton("");
            lineBtn.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/line.png"))));
            lineBtn.setPreferredSize(new Dimension(30, 30));
            lineBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPaintPanel().active_tool = 1;
                }
            });
            add(lineBtn);


            setBorder(new TitledBorder("Shapes"));

        }

    }

    class ManageStroke extends JPanel {
        ManageStroke() {
            JSlider chooseLineStroke = new JSlider(HORIZONTAL, 1, 15, 2);

            strokeLabel = new JLabel("Size: " + chooseLineStroke.getValue(), SwingConstants.CENTER);
            frame.getPaintPanel().stroke = new BasicStroke((float) chooseLineStroke.getValue());
            chooseLineStroke.addChangeListener(e -> {
                strokeLabel.setText("Size: " + chooseLineStroke.getValue());
                frame.getPaintPanel().stroke = new BasicStroke((float) chooseLineStroke.getValue());

            });

            add(new Separator());
            add(chooseLineStroke);
            add(strokeLabel);
            add(new Separator());
            setLayout(new GridLayout(4, 1));

        }

    }

    class UndoRedo extends JPanel {
        UndoRedo() {
            JPanel panel = new JPanel();

            JButton undoBtn = new JButton("Undo", new ImageIcon(Objects.requireNonNull(getClass().getResource("icons\\undo_.jpg"))));
            undoBtn.setBackground(Color.WHITE);
            undoBtn.setBorder(new LineBorder(Color.BLACK));
            undoBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPaintPanel().undo();
                }
            });

            JButton redoBtn = new JButton("Redo", new ImageIcon(Objects.requireNonNull(getClass().getResource("icons\\redo_.jpg"))));
//            redoBtn.setPreferredSize(new Dimension(70 , 25));
            redoBtn.setBackground(Color.white);
            redoBtn.setBorder(new LineBorder(Color.BLACK));
            redoBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPaintPanel().redo();
                }
            });
            panel.add(undoBtn);
            panel.add(redoBtn);


            JPanel panelClear = new JPanel();
            JButton clear = new JButton("Clear", new ImageIcon(Objects.requireNonNull(getClass().getResource("icons\\deleted.jpg"))));
            clear.setBackground(Color.WHITE);
            clear.setBorder(new LineBorder(Color.BLACK));
            clear.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getPaintPanel().clear();
                }
            });
            panelClear.add(clear);

            add(panel);
            add(panelClear);
            setLayout(new GridLayout(2, 1));
        }
    }

}
