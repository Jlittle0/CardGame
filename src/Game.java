import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> players;
    private Deck mainDeck;
    private Deck subDeck;
    private Scanner input;
    private boolean gameState;

    public Game() {
        players = new ArrayList<Player>();
        String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
        int[] points = {14, 2, 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        mainDeck = new Deck(ranks, suits, points);
        subDeck = new Deck(ranks, suits, points);
        input = new Scanner(System.in);
        gameState = false;
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
        System.out.println("                                       PPPPPPP          OOO             OOO  KKK KKK             EEE                RRRRRRRR");
        System.out.println("                                       PPP              OOO             OOO  KKK   KKK           EEE                RRR   RRR");
        System.out.println("                                       PPP              OOO             OOO  KKK     KKK         EEE                RRR      RRR");
        System.out.println("                                       PPP               OOO           OOO   KKK       KKK       EEE                RRR        RRR");
        System.out.println("                                       PPP                 OOO       OOO     KKK         KKK     EEEEEEEE           RRR          RRR");
        System.out.println("                                       PPP                    OOOOOO         KKK           KKK   EEEEEEEEEEEEEEEEE  RRR            RRR");
        System.out.println("\n\n\n");
        System.out.println("                                                       If you would like to begin playing the game, press (Y)");
        System.out.println("                                                           if you are finished playing, press (Q)");
    }

    public void printTable() {
        System.out.println("                                                          #########################################################");
        System.out.println("                                                ##########                                                         ########");
        System.out.println("                                            ###                                                                             ###");
        System.out.println("                                          ###                                                                                 ###");
        System.out.println("                                        ###                                                                                     ###");
        System.out.println("                                      ###                                                                                         ###");
        System.out.println("                                    ###                                                                                             ###");
        System.out.println("                                   ##                                                                                                 ##");
        System.out.println("                                   ##                                                                                                 ##");
        System.out.println("                                   ##                                                                                                 ##");
        System.out.println("                                   ##                                                                                                 ##");
        System.out.println("                                   ##                                                                                                 ##");
        System.out.println("                                    ###                                                                                             ###");
        System.out.println("                                      ###                                                                                         ###");
        System.out.println("                                        ###                                                                                     ###");
        System.out.println("                                          ###                                                                                 ###");
        System.out.println("                                            ###                                                                             ###");
        System.out.println("                                                ##########                                                         ########");
        System.out.println("                                                          #########################################################");
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


