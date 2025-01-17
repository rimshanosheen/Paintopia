import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ToolBar extends JToolBar implements ActionListener {

    private JMenuItem openFile;
    private JFileChooser fileChooser;
    private DrawFrame frame;
    private File file;
    private JMenuItem save;

    ToolBar(DrawFrame frame) {
        this.frame = frame;
        setBackground(new Color(166, 75, 97));
        fileChooser = new JFileChooser(new File("."));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image", "jpg", "png", "jpeg"));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        //JMenuItem newPage = new JMenuItem("New Page");

       openFile = new JMenuItem("Open File");
       openFile.addActionListener(this);

        save = new JMenuItem("Save");
        save.addActionListener(this);

        //menu.add(newPage);
        //menu.add(openFile);
        menu.add(save);

        menuBar.add(menu);

        add(menuBar);
        setFloatable(false);
    }

    public JToolBar getToolBar() {
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem) e.getSource();

        /* 
        if (item == openFile) {
            if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                System.out.println(file.toString());
                //openSelectedFile(file);
            }
        } */ 
        if (item == save) {
            if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                file = new File(fileChooser.getSelectedFile() + ".png");
                try {
                    saveImage(file);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private void saveImage(File f) throws IOException {
        BufferedImage bi = makePanel(frame.getPaintPanel());
        // Specify the image format as PNG
        ImageIO.write(bi, "PNG", f);
    }

    private BufferedImage makePanel(JPanel panel) {
        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.print(g);
        return bi;
    }
 
    private void openSelectedFile(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            frame.getPaintPanel().setImage(image);
            Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
            setDimensions(dimension.width, dimension.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDimensions(int width, int height) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        if (height > dim.height - 160 && width > dim.width) {
            frame.getScrollPane().setSize(dim.width - 300, dim.height - 160);
        } else if (width > dim.width - 300) {
            frame.getScrollPane().setSize(dim.width - 300, height);
        } else if (height > dim.height - 160) {
            frame.getScrollPane().setSize(width, dim.height - 160);
        } else {
            frame.getScrollPane().setSize(width, height);
        }
    }
}
