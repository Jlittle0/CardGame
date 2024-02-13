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

        /* TODO - Make sure to use this switch statement for the entirety of the repaint method so that
            whenever the method is run, it prints the correct screen and all the actions associated with
            that background can also be run rather than constantly painting over based on if statements
            for each individual background after the "base" paint method is called.
         */
        switch (a.getBackground()) {
            // Hopefully this case does everything that is already in the repaint statement
            case ("home"):
                background = new ImageIcon("Resources/Home.png").getImage();
                break;
            // Basically just print the screen with instructions on it and allow the user to go back to home screen
            case ("instructions"):
                background = new ImageIcon("Resources/Instructions.png").getImage();
                break;
            // First instance of the table that is just a table PNG and would be used for before the game
            // actually begins. Also going to hopefully be used for the dealer.
            case ("table"):
                background = new ImageIcon("Resources/Table.png").getImage();
                break;
            // Instance of table where the game has already started and where another if statement will be nested
            // inside to check whose turn it is and what animations to play for said turns.
            case ("table2"):
                background = new ImageIcon("Resources/Table2.png").getImage();
                break;
            default:
                System.out.println("Something went wrong with the backgrounds");
        }
    }

}
