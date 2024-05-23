import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class firstwindowmain extends JFrame implements ActionListener {
    private Clip clip;

    public firstwindowmain() {
        setTitle("Welcome to Paintopia");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load and play music
        loadMusic("queen.wav");
        playMusic();

        ImageIcon imageIcon = new ImageIcon("icon.jpg");
        FirstWindow panel = new FirstWindow(imageIcon);
        add(panel);

        JButton jb = new JButton("Welcome to Paintopia <3");
        jb.addActionListener(this);

        JPanel jp = new JPanel();
        jp.add(jb);
        add(jp, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Paint();
    }

    private void loadMusic(String filepath) {
        try {
            File musicFile = new File(filepath);
            if (musicFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
            } else {
                System.out.println("File not found: " + filepath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void playMusic() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music continuously
        }
    }

    private void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}