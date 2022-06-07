package Main;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static String ver = "Alpha 2.0";

    public static void main(String[] args) {

        // Opening arguments to create the visual elements of the game
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes the close button work
        window.setResizable(false); // Disables resizing of the window
        window.setTitle("Spooky Puzzle Game (" + ver + ")");

        GamePanel gamePanel = new GamePanel(); // Calls initializer
        window.add(gamePanel);

        /*
        CardLayout cards = new CardLayout();
        window.setLayout(cards);

        JPanel titleScreen = new JPanel();
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            cards.previous(window.getContentPane());
        });
        titleScreen.setPreferredSize(new Dimension(gamePanel.screenWidth, gamePanel.screenHeight));
        titleScreen.add(startButton);
        window.add(titleScreen, "Title");
        cards.show(window.getContentPane(), "Title");
        window.add(gamePanel, "Game");
        */

        window.pack(); // Resolves preferred size error

        window.setLocationRelativeTo(null); // Ensures window is created in centre

        window.setVisible(true); // Displays game window

        gamePanel.setObjects(); // Sets objects
        gamePanel.startGamaThread(); // Starts the Game

    }
}
