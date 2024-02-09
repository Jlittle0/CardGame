import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
public class GameView extends JFrame {
    private static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int width = 1480,
                             height = 878;
    private static final String TITLE = "POKER";
    private Image background;
    private Game a;

    public GameView(Game g) {
        this.a = g;
        this.background = new ImageIcon("Resources/Background.png").getImage();
        this.setTitle(TITLE);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        // Setting the font and increasing the size of the letters
        int fontSize = 25;
        Font stringFont = new Font( "Serif", Font.PLAIN, fontSize );
        g.setFont(stringFont);
        // Clear the screen via painting a white rectangle
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // Setting the background
        g.drawImage(background, 0, 0, width, height, this);
        // Drawing a string and dynamically placing it on the screen based on its length
        String str = "Welcome to Poker!";
        g.drawString(str, width / 2 - (str.length() * fontSize) / 4, height / 8);

        switch (a.getBackground()) {
            case ("game"):
                background =
        }
    }

}
