import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ColorChooser extends JPanel implements ActionListener {
    JButton red, black, green, orange, blue, pink, brown, yellow, white, cyan, purple, gray, indigo,beige,Colors;
    DrawFrame frame;

    ColorChooser(DrawFrame frame) {
        this.frame = frame;

        red = new JButton("");
        red.setSize(25, 25);
        red.setBackground(Color.red);
        red.addActionListener(this);

        blue = new JButton("");
        blue.setSize(25, 25);
        blue.setBackground(Color.BLUE);
        blue.addActionListener(this);

        black = new JButton("");
        black.setSize(25, 25);
        black.setBackground(Color.BLACK);
        black.addActionListener(this);

        green = new JButton("");
        green.setSize(25, 25);
        green.setBackground(Color.GREEN);
        green.addActionListener(this);

        orange = new JButton("");
        orange.setSize(25, 25);
        orange.setBackground(Color.ORANGE);
        orange.addActionListener(this);

        pink = new JButton("");
        pink.setSize(25, 25);
        pink.setBackground(Color.pink);
        pink.addActionListener(this);

        yellow = new JButton("");
        yellow.setSize(25, 25);
        yellow.setBackground(Color.yellow);
        yellow.addActionListener(this);

        brown = new JButton("");
        brown.setSize(25, 25);
        brown.setBackground(new Color(49, 14, 14));
        brown.addActionListener(this);

        white = new JButton("");
        white.setSize(25, 25);
        white.setBackground(new Color(255, 255, 255));
        white.addActionListener(this);

        cyan = new JButton("");
        cyan.setSize(25, 25);
        cyan.setBackground(Color.cyan);
        cyan.addActionListener(this);

        purple = new JButton("");
        purple.setSize(25, 25);
        purple.setBackground(new Color(163, 73, 164));
        purple.addActionListener(this);

        gray = new JButton("");
        gray.setSize(25, 25);
        gray.setBackground(Color.gray);
        gray.addActionListener(this);

        indigo = new JButton("");
        indigo.setSize(25, 25);
        indigo.setBackground(Color.darkGray);
        indigo.addActionListener(this);


        beige = new JButton("");
        beige.setSize(25, 25);
        beige.setBackground(new Color( 245, 245,  220));
        beige.addActionListener(this);

        Colors = new JButton("",new ImageIcon("icons\\abcdE.png"));
        Colors.setBounds(50, 50, 25, 25);
        Colors.setFocusable(false);

        add(red);
        add(black);
        add(green);
        add(orange);
        add(pink);
        add(brown);
        add(yellow);
        add(white);
        add(blue);
        add(cyan);
        add(purple);
        add(gray);
        add(indigo);
        add(beige);
        add(Colors);


        Colors.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {    
            JColorChooser jc = new JColorChooser();

                try{
                Color color = JColorChooser.showDialog(null, " Pick a Colour ", Color.BLACK);
                if(color != null){
                    Colors.setBackground(color);
                    frame.getPaintPanel().currentColor =Colors.getBackground();
                }}
                catch(NullPointerException n){
                    System.err.println("NUll pointer exception"+n);
                }
        }});

        setLayout(new GridLayout(3, 3, 5, 5));
        setBorder(new TitledBorder("Colors"));
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        frame.getPaintPanel().currentColor = btn.getBackground();
    }
}