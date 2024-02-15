import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
public class GameView extends JFrame {
    private static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int width = 1480,
                             height = 878;
    private static final String TITLE = "POKER";
    private Image background;
    private Image foreground;
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
        switch (a.getBackground()) {
            case ("Welcome"):
                background = new ImageIcon("Resources/Menu.png").getImage();
                // Setting the font and increasing the size of the letters
                int fontSize = 25;
                Font stringFont = new Font( "Times", Font.PLAIN, fontSize );
                g.setFont(stringFont);
                // Clear the screen via painting a white rectangle
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, width, height);
                // Setting the background
                g.drawImage(background, 0, 0, width, height, this);
                // Drawing a string and dynamically placing it on the screen based on its length
                String str = "Welcome to Poker!";
                g.drawString(str, width / 2 - (str.length() * fontSize) / 4, height / 8);
                break;
            // Hopefully this case does everything that is already in the repaint statement
            case ("Menu"):
                background = new ImageIcon("Resources/Menu.png").getImage();
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, width, height);
                g.drawImage(background, 0, 0, width, height, this);
                break;
            // Basically just print the screen with instructions on it and allow the user to go back to home screen
            case ("Instructions"):
                background = new ImageIcon("Resources/Instructions.png").getImage();
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, width, height);
                // Setting the background
                g.drawImage(background, 0, 0, width, height, this);
                break;
            // First instance of the table that is just a table PNG and would be used for before the game
            // actually begins. Also going to hopefully be used for the dealer.
            case ("table"):
                background = new ImageIcon("Resources/Table.png").getImage();
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, width, height);
                g.drawImage(background, 0, 0, width, height, this);
                break;
            // Instance of table where the game has already started and where another if statement will be nested
            // inside to check whose turn it is and what animations to play for said turns.
            case ("game"):
                background = new ImageIcon("Resources/Table2.png").getImage();
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, width, height);
                g.drawImage(background, 0, 0, width, height, this);
                foreground = new ImageIcon("Resources/Player" + a.getCurrentPlayer() + "Enter.png").getImage();
                // Fix the location of this so that it's centered in the screen
                // Also add a timer to remove this added foreground image appropriately.
                g.drawImage(foreground, 0, 0, width, height, this);
                /*
                    TODO - Write everything for the project
               */
                break;
            default:
                System.out.println("Something went wrong with the backgrounds");
        }
    }

}
