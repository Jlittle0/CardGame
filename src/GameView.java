import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
public class GameView extends JFrame {
    private static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int WIDTH = 1480,
                                HEIGHT = 878;
    private static final String TITLE = "POKER";
    private Image background;
    private Image foreground;
    private Game a;

    public GameView(Game g) {
        this.a = g;
        this.background = new ImageIcon("Resources/Background.png").getImage();
        this.setTitle(TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
//        createBufferStrategy(2);
    }

    public void paint(Graphics g) {
        switch (a.getBackground()) {
            case ("Welcome"):
                background = new ImageIcon("Resources/Menu/Menu.PNG").getImage();
                // Setting the font and increasing the size of the letters
                int fontSize = 25;
                Font stringFont = new Font( "Times", Font.PLAIN, fontSize );
                g.setFont(stringFont);
                // Clear the screen via painting a white rectangle
                clearBoard(g);
                // Drawing a string and dynamically placing it on the screen based on its length
                String str = "Welcome to Poker";
                customStringPrinter(g, str, WIDTH / 2 - (str.length() * 26) / 2, HEIGHT / 8, 1);
                // Prints out the string using pixel art font
                customStringPrinter(g, "Play(Y)", 250, 340, 0.8);
                customStringPrinter(g, "Instructions(I)", 50 ,385, 0.8);
                customStringPrinter(g, "Quit(Q)", 195 ,430, 0.8);
                g.drawImage(new ImageIcon("Resources/Standing.png").getImage(), 1150, 100, 300, 711, this);
                // Adds any foreground that's required that normally wouldn't appear on the page
                // without additional user input (such as the possible modes that the user can play)
                if (a.getForeground().equals("Modes")) {
                    a.setForeGround("");
                    customStringPrinter(g, ">> 1 Player (currently unavailable)", 420, 385, 0.5);
                    customStringPrinter(g, ">> 2 Player (2)", 395, 420, 0.5);
                }
                break;
            // Hopefully this case does everything that is already in the repaint statement
            // Basically just print the screen with instructions on it and allow the user to go back to home screen
            case ("Instructions"):
                background = new ImageIcon("Resources/Instructions.png").getImage();
                clearBoard(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, WIDTH, HEIGHT);
                /*
                Welcome to Josh’s poker game! To start enter Y (to play), 2 (to select 2 players), and Y again to officially start.
                For this game, you will be playing with the standard
                 texas holdem ruleset which will be summarized below. In texas holdem, each player
                  is dealt a hand containing 2 random cards from separate decks. Each player
                  doesn’t know the others hand and before anything else happens, each player can
                   choose to bet as much money as they wish. For the game to progress, the other
                    player must accept this amount or increase it. After the game officially begins,
                    each of the players' bets goes into the pot and the dealer places two cards on
                    the table. In total, the dealer will place a total of 5 cards down and in between
                    ach of them the players have the option to fold (give up) - F, raise (increase their bet) - R,
                    call - C, or check (keep the bet the same) - check. At the end of the game when all cards are down
                    and one of the players hasn’t resigned, the player with the best hand wins.

                 */
                customStringPrinter(g, "Instructions", 510, 40, 1.5);
                customStringPrinter(g, "Welcome to Joshs poker game. To start enter Y (to play) 2 (to select 2", 30, 120, 0.75);
                customStringPrinter(g, "players), and Y again to officially start. For this game, you will be playing", 30, 160, 0.75);
                customStringPrinter(g, "with the standard texas holdem ruleset which will be summarized below.", 30, 200, 0.75);
                customStringPrinter(g, " In texas holdem, each player is dealt a hand containing 2 random cards", 30, 240, 0.75);
                customStringPrinter(g, " from separate decks. Each player doesn't know the others hand and", 30, 280, 0.75);
                customStringPrinter(g, "before anything else happens, each player can choose to bet as much ", 30, 320, 0.75);
                customStringPrinter(g, "money as they wish. Forthe game to progress, the other player must ", 30, 360, 0.75);
                customStringPrinter(g, "accept this amount or increase it. After the gameofficially begins, each ", 30, 400, 0.75);
                customStringPrinter(g, "of the players bets goes into the pot and the dealer places three cards", 30, 440, 0.75);
                customStringPrinter(g, " on the table. In total, the dealer will place a total of five cards down ", 30, 480, 0.75);
                customStringPrinter(g, "and in between each of them the players have the option to fold (give up)", 30, 520, 0.75);
                customStringPrinter(g, " F, raise (icrease their bet) R, call C, or check (keep the bet the same)", 30, 560, 0.75);
                customStringPrinter(g, "check. At the end of the game when all cards are down and one of the ", 30, 600, 0.75);
                customStringPrinter(g, "players hasnot resigned, the player with the best hand wins.", 30,  640, 0.75);

                break;
            // First instance of the table that is just a table PNG and would be used for before the game
            // actually begins. Also going to hopefulYly be used for the dealer.
            case ("table"):
                background = new ImageIcon("Resources/Table.png").getImage();
                clearBoard(g);
                customStringPrinter(g, "Would you like to begin (Y<>N)", WIDTH / 2 -350, HEIGHT / 2, 1);
                break;
            // Instance of table where the game has already started and where another if statement will be nested
            // inside to check whose turn it is and what animations to play for said turns.
            case ("game"):
                background = new ImageIcon("Resources/Table.png").getImage();
                clearBoard(g);
                drawProfiles(g);
                drawHand(g);
                checkWin(g);

                /*
                    TODO - Write everything for the project
                    // Have each piece be in its own method that's called here, one for putting the
                    player profiles up, another for clearing the board, a third for presenting the
                    player move screens etc.
               */
                break;
            default:
                System.out.println("Something went wrong with the backgrounds");
        }
    }

    public void drawProfiles(Graphics g) {
        g.setColor(Color.WHITE);
        // Player 1s information
        customStringPrinter(g, "Player 1", 10, 42, 0.5);
        String temp = "" + a.getPlayers(1).getCash();
        g.drawString("Cash: $" + temp, 10, 195);
        temp = "" + a.getPlayers(1).getBet();
        g.drawString("Bet: $" + temp, 10, 215);
        temp = a.getCards(1);
        g.drawString("Hand: " + temp, 10, 235);
        temp = a.getAction(1);
        g.drawString("Action: " + temp, 10, 255);
        // Player 2s information
        customStringPrinter(g, "Player 2", 1270, 42, 0.5);
        temp = "" + a.getPlayers(2).getCash();
        g.drawString("Cash: $" + temp, 1270, 195);
        temp = "" + a.getPlayers(2).getBet();
        g.drawString("Bet: $" + temp, 1270, 215);
        temp = a.getCards(2);
        g.drawString("Hand: " + temp, 1270, 235);
        temp = a.getAction(2);
        g.drawString("Action: " + temp, 1270, 255);
        // General Information
        customStringPrinter(g, "Raise(R)   Check(CHECK)    Call(C)   Fold(F)    Hide(H)   Show (S)", 300, 50, 0.5);
    }

    public void drawHand(Graphics g) {
        // Draws default cards in the positions if they're hidden
        // Player 1's back cards
        g.drawImage(new ImageIcon("Resources/Cards/back.png").getImage(), 30, 300, 50, 65, this);
        g.drawImage(new ImageIcon("Resources/Cards/back.png").getImage(), 100, 300, 50, 65, this);
        // Player 2's back cards
        g.drawImage(new ImageIcon("Resources/Cards/back.png").getImage(), 1300, 300, 50, 65, this);
        g.drawImage(new ImageIcon("Resources/Cards/back.png").getImage(), 1370, 300, 50, 65, this);
        // Dealer's back cards
//        for (int i = 0; i < a.getDealer().getHand().size(); i++) {
//            g.drawImage(a.getDealer().getHand().get(i).getCardImage(), 600 + (60 * i), 760, 40, 52, this);
//        }
        // Draws player 1's cards if they're shown
        if (!a.getPlayers(1).isHidden()) {
            g.drawImage(new ImageIcon("Resources/Cards/" + a.getPlayers(1).getHand().get(0).getNum() +  ".png").getImage(), 30, 300, 50, 65, this);
            g.drawImage(new ImageIcon("Resources/Cards/" + a.getPlayers(1).getHand().get(1).getNum() +  ".png").getImage(), 100, 300, 50, 65, this);
        }
        // Draws player 2's cards if they're shown
        if (!a.getPlayers(2).isHidden()) {
            g.drawImage(new ImageIcon("Resources/Cards/" + a.getPlayers(2).getHand().get(0).getNum() +  ".png").getImage(), 1300, 300, 50, 65, this);
            g.drawImage(new ImageIcon("Resources/Cards/" + a.getPlayers(2).getHand().get(1).getNum() +  ".png").getImage(), 1370, 300, 50, 65, this);
        }
        // Draws the dealer's cards once they are dealt
        for (int i = 0; i < a.getDealer().getHand().size(); i++) {
            g.drawImage(new ImageIcon("Resources/Cards/" + a.getDealer().getHand().get(i).getNum() +  ".png").getImage(), 545 + 65 * i, 790, 50, 65, this);
        }

    }

    public void clearBoard(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, this);
    }

    public void checkWin(Graphics g) {
        String str = a.getWinnerString();
        customStringPrinter(g, str, WIDTH / 2 - (str.length() * 26) / 2, HEIGHT / 2, 1);
        if (str != "") {
            str = "Would you like to play again(Y<>N)";
            customStringPrinter(g, str, WIDTH / 2 - (str.length() * 26) / 2, HEIGHT / 2 + 38, 1);
        }
    }

    public void customStringPrinter(Graphics g, String input, int x, int y, double size) {
        input = input.toUpperCase();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                g.drawImage(new ImageIcon("Resources/Characters/Space.png").getImage(), x + (int)(size * 26 * i), y,  (int)(28 * size), (int)(32 * size), this);
            }
            else if (input.charAt(i) == '.') {
                g.drawImage(new ImageIcon("Resources/Characters/" + input.charAt(i) + ".png").getImage(), x + (int)(size * 26 * i), y + 11, (int)(28 * size), (int)(32 * size), this);
            }
            else if (input.charAt(i) == ',') {
                g.drawImage(new ImageIcon("Resources/Characters/" + input.charAt(i) + ".png").getImage(), x + (int)(size * 26 * i), y + 16, (int)(14 * size), (int)(16 * size), this);
            }
            else if (input.charAt(i) == '(' || input.charAt(i) == ')') {
                g.drawImage(new ImageIcon("Resources/Characters/" + input.charAt(i) + ".png").getImage(), x + (int)(size * 26 * i) + 2, y + 2, (int)(20 * size), (int)(28 * size), this);
            }
            else {
                g.drawImage(new ImageIcon("Resources/Characters/" + input.charAt(i) + ".png").getImage(), x + (int)(size * 26 * i), y, (int)(28 * size), (int)(32 * size), this);
            }
        }
    }

//    public void paint(Graphics g) {
//        BufferStrategy bf = this.getBufferStrategy();
//        if (bf == null)
//            return;
//        Graphics g2 = null;
//        try {
//            g2 = bf.getDrawGraphics();
//            myPaint(g2);
//        }
//        finally {
//            g2.dispose();
//        }
//        bf.show();
//        Toolkit.getDefaultToolkit().sync();
//    }

}
