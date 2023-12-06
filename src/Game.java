import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> players;
    private Deck mainDeck;
    private Deck subDeck;
    private Scanner input;
    private boolean gameState;
    private int player1Cash;
    private int player2Cash;
    private Deck player1Hand;
    private Deck player2Hand;

    public Game() {
        players = new ArrayList<Player>();
        String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
        int[] points = {14, 2, 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        mainDeck = new Deck(ranks, suits, points);
        subDeck = new Deck(ranks, suits, points);
        input = new Scanner(System.in);
        gameState = false;
        player1Cash = 1000;
        player2Cash = 1000;
        player1Hand = new Deck();
        player2Hand = new Deck();
        // test
    }

    public void printWelcome() {
        System.out.println("                                                                             Welcome to Poker!");
        System.out.println("\n\n");
        System.out.println("                                        PPPPPPP              OOOOOOO         KKK            KKK  EEEEEEEEEEEEEEEEE   RRRRRRRRRRR");
        System.out.println("                                       PPP      PPP       OOO       OOO      KKK           KKK   EEEEEEEE           RRR        RRR");
        System.out.println("                                       PPP       PPP    OOO           OOO    KKK         KKK     EEE                RRR          RRR");
        System.out.println("                                       PPP         PPP  OOO             OOO  KKK       KKK       EEE                RRR           RRR");
        System.out.println("                                       PPP        PPP   OOO             OOO  KKK     KKK         EEE                RRR           RRR");
        System.out.println("                                       PPP      PPP     OOO             OOO  KKK   KKK           EEE                RRR          RRR   ");
        System.out.println("                                       PPP     PPP      OOO             OOO  KKK KKK             EEEEEEE            RRR      RRR");
        System.out.println("                                       PPPPPPP          OOO             OOO  KKK   KKK           EEE                RRRRRRRR");
        System.out.println("                                       PPP              OOO             OOO  KKK     KKK         EEE                RRR   RRR");
        System.out.println("                                       PPP              OOO             OOO  KKK       KKK       EEE                RRR      RRR");
        System.out.println("                                       PPP               OOO           OOO   KKK         KKK     EEE                RRR        RRR");
        System.out.println("                                       PPP                 OOO       OOO     KKK           KKK   EEEEEEEE           RRR          RRR");
        System.out.println("                                       PPP                    OOOOOO         KKK            KKK  EEEEEEEEEEEEEEEEE  RRR            RRR");
        System.out.println("\n\n");
        System.out.println("                                                      If you would like to begin playing the game, press (Y)");
        System.out.println("                                                            if you are finished playing, press (Q)");
        System.out.println("                                                                      Instructions: (I)");
    }

    public void printTable() {
        System.out.println("\n\n\n\n\n");
        System.out.println("                                                     #########################################################");
        System.out.println("                                           ##########                                                         ########");
        System.out.println("                                       ###                           P2 Hand:                                          ###");
        System.out.println("                                     ###                                                                                 ###");
        System.out.println("                                   ###                                                                                     ###");
        System.out.println("                                 ###                                                                                         ###");
        System.out.println("                               ###                                                                                             ###");
        System.out.println("                              ##                                                                                                 ##");
        System.out.println("                              ##                                                                                                 ##");
        System.out.println("                              ##                                                                                                 ##");
        System.out.println("                              ##                                                                                                 ##");
        System.out.println("                              ##                                                                                                 ##");
        System.out.println("________________               ###                                                                                             ###                 ________________");
        System.out.println("|Player 1      |                 ###                                                                                         ###                   |Player 2      |");
        if (player1Cash / 1000 >= 1) {
            System.out.println("|Cash: " + player1Cash + "    |                   ###                                                                                     ###                     |Cash:         |");
        }
        else {
            System.out.println("|Cash:" + player1Cash + "      |                   ###                                                                                     ###                     |Cash:         |");
        }
        if (player1Hand.isHidden()) {
            System.out.println("|Hand: ?? ??   |                     ###                                                                                 ###                       |Hand:         |");
        }
        else {
            System.out.println("|Hand:         |                     ###                                                                                 ###                       |Hand:         |");
        }
        System.out.println("|Action:       |                       ###                           P1 Hand:                                          ###                         |Action:       |");
        System.out.println("|              |                           ##########                                                         ########                             |              |");
        System.out.println("|_____________ |                                     #########################################################                                     |______________|");
        System.out.println("\n");
    }

    public void printInstructions() {
        System.out.println("Welcome to Josh's poker game. At this table, you will be playing Texas Holdem, one of the classic styles of poker.");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public String printDeck() {
        mainDeck.shuffle();
        subDeck.shuffle();
        return mainDeck.toString() + subDeck.toString();
    }

    public void playBot() {
        // Do later

    }

    public void playPlayer() {
        if (player1Hand.isEmpty()) {

        }
        printTable();


    }

    public void play() {
        clearScreen();
        printWelcome();
        String userInput = input.nextLine();
        while (!gameState) {
            if (userInput.equals("Y") || userInput.equals("y")) {
                gameState = true;
                clearScreen();
                System.out.println("Please enter the number of players (1 or 2)");
                userInput = input.nextLine();
                if (userInput.equals("1")) {
                    playBot();
                }
                else if (userInput.equals("2")) {
                    playPlayer();
                }
                else {
                    System.out.println("Bruh");
                }
            }
            else if (userInput.equals("Q") || userInput.equals("q")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            else if (userInput.equals("I") || userInput.equals("i")) {
                printInstructions();
            }
            else {
                System.out.println("Please enter a valid character");
                userInput = input.nextLine();
            }
        }
    }


    public static void main(String[] args) {
        Game g = new Game();
        System.out.println(g.printDeck());
        g.play();
    }
}


