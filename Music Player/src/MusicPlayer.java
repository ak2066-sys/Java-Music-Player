

import javazoom.jl.player.Player;
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.io.*;

public class MusicPlayer extends JFrame {
    private JButton playButton, pauseButton, stopButton, chooseButton;
    private JLabel songLabel;
    private File selectedFile;
    private Player player;
    private Thread playThread;
    private boolean isPlaying = false;

    public MusicPlayer() {
        setTitle("🎵 Simple Music Player");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        chooseButton = new JButton("Choose MP3");
        playButton = new JButton("Play");
        pauseButton = new JButton("Pause");
        stopButton = new JButton("Stop");
        songLabel = new JLabel("No song selected");

        add(chooseButton);
        add(playButton);
        add(pauseButton);
        add(stopButton);
        add(songLabel);

        chooseButton.addActionListener(e -> chooseFile());
        playButton.addActionListener(e -> playMusic());
        stopButton.addActionListener(e -> stopMusic());
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            songLabel.setText("Selected: " + selectedFile.getName());
        }
    }

    private void playMusic() {
        if (selectedFile != null && !isPlaying) {
            playThread = new Thread(() -> {
                try {
                    FileInputStream fileInputStream = new FileInputStream(selectedFile);
                    player = new Player(fileInputStream);
                    isPlaying = true;
                    player.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            playThread.start();
        }
    }

    private void stopMusic() {
        if (player != null) {
            player.close();
            isPlaying = false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MusicPlayer().setVisible(true));
    }
}
