import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> players;
    private Deck mainDeck;
    private Deck subDeck;
    private Scanner input;
    private boolean gameState;
    private Player player1;
    private Player player2;
    private Player dealer;
    private String lastAction;
    private String currentAction;
    private boolean roundOver;
    private int currentBet;
    private int currentTotalBet;
    private static int turnNumber;
    private Player winner;
    private int minimumBet;
    public static int cardsDrawn;
    private String background;
    private GameView window;
    private int playerTurn;

    public Game() {
        players = new ArrayList<Player>();
        String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
        int[] points = {14, 2, 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        mainDeck = new Deck(ranks, suits, points);
        subDeck = new Deck(ranks, suits, points);
        input = new Scanner(System.in);
        gameState = false;
        player1 = new Player("");
        player2 = new Player("");
        dealer = new Player("dealer");
        minimumBet = 50;
        background = "Welcome";
        window = new GameView(this);
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
        currentTotalBet = player1.getBet() + player2.getBet();
        System.out.println("\n\n");
        System.out.println("                              Raise - 'R'       Call - 'C'        Check - 'Check'        Fold - 'F'      Show - 'S'        Hide - 'H'\n");
        System.out.println("                                                     #########################################################");
        System.out.println("                                           ##########                                                         ########");
        System.out.println("                                       ###                                                                             ###");
        System.out.print("                                     ###                           Dealer: ");
        for (int i = 0; i < dealer.getHand().size(); i++) {
            System.out.print(dealer.getCard(i) + " ");
        }
        for (int i = 0; i < 5 - dealer.getHand().size(); i++) {
            System.out.print("__ ");
        }
        System.out.println("                               ###");
        System.out.println("                                   ###                                                                                     ###");
        System.out.println("                                 ###                                                                                         ###");
        System.out.println("                               ###                                                                                             ###");
        System.out.println("                              ##                                                                                                 ##");
        System.out.println("                              ##                                                                                                 ##");
        System.out.print("                              ##                                     current pool: $" + currentTotalBet);
        for (int i = 0; i < 5 - checkLength(currentTotalBet); i++) {
            System.out.print(" ");
        }
        System.out.println("                                        ##");
        System.out.println("                              ##                                                                                                 ##");
        System.out.println("                              ##                                                                                                 ##");
        System.out.println("________________               ###                                                                                             ###                 ________________");
        System.out.println("|Player 1      |                 ###                                                                                         ###                   |Player 2      |");
        System.out.print("|Cash: $" + player1.getCash());
        for (int i = 0; i < 7 - checkLength(player1.getCash()); i++) {
            System.out.print(" ");
        }
        System.out.print("|                   ###                                                                                     ###                     |Cash: $" + player2.getCash());
        for (int i = 0; i < 7 - checkLength(player2.getCash()); i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        System.out.print("|Hand: ");
        if (player1.isHidden()) {
            System.out.print("?? ??");
        }
        else {
            System.out.print(player1.getCard(0) + " " + player1.getCard(1));
        }
        System.out.print("   |                     ###                                                                                 ###                       |Hand: ");
        if (player2.isHidden()) {
            System.out.println("?? ??   |");
        }
        else {
            System.out.println(player2.getCard(0) + " " + player2.getCard(1) + "   |");
        }
        System.out.print("|Action: " + player1.getAction());
        for (int i = 0; i < 6 - player1.getAction().length(); i++) {
            System.out.print(" ");
        }
        System.out.print("|                       ###                                                                             ###                         |Action: " + player2.getAction());
        for (int i = 0; i < 6 - player2.getAction().length(); i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        System.out.println("|              |                           ##########                                                         ########                             |              |");
        System.out.println("|______________|                                     #########################################################                                     |______________|");
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
        // Basically just runs a loop of constantly running rounds until the user decides to stop playing
        System.out.println("Would you like to begin? (Y/N)");
        String userInput = input.nextLine();
        while (userInput.toUpperCase().equals("Y")) {
            background = "game";
            window.repaint();
            round();
            System.out.println("Would you like to play again? (Y/N)");
            userInput = input.nextLine();
        }
        System.out.println("Bye bye!");
    }

    public Card draw() {
        // Decides which deck to draw from based on the current number of cards drawn
        // since I'm using two different decks to deal cards with
        cardsDrawn++;
        if (cardsDrawn % 2 == 1) {
            return mainDeck.deal();
        }
        else {
            return subDeck.deal();
        }
    }
    public void resetTable() {
        // Resets all the players' hands for the new round
        player1.resetHand();
        player2.resetHand();
        dealer.resetHand();
        // Resets bets back down to $0
        player1.setBet(0);
        player2.setBet(0);
        // Shuffles the decks which resets them
        mainDeck.shuffle();
        subDeck.shuffle();
        // Gives two cards to each player for their own hand for the next round
        player1.addCard(draw());
        player1.addCard(draw());
        player2.addCard(draw());
        player2.addCard(draw());
    }

    public int getCurrentPlayer() {
        return playerTurn;
    }
    // Returns a value corresponding to the players hand to use to check for the winner
    public int checkValue(Player player) {
        boolean isFlush = false; // Whether the current hand contains a flush
        boolean isStraight = false; // Whether the current hand contains a straight
        int maxPair = 0; // Highest number of cards with the same point value
        int maxPairPoints = 0; // Highest value of paired cards
        int flushCount = 0; // Counter variable used to determine isFlush
        int straightCount = 0; // Counter variable used to determine isStraight
        int tempCount = 0;

        ArrayList<Card> allCards = new ArrayList<Card>();
        ArrayList<Card> uniqueCards = new ArrayList<Card>();
        // Adding the dealers 5 cards into hand to check overall score
        for (int i = 0; i < 5; i++) {
            allCards.add(dealer.getHand().get(i));
        }
        for (int i = 0; i < 5; i++) {
            tempCount = 0;
            if (uniqueCards.size() == 0) {
                uniqueCards.add(dealer.getHand().get(i));
            }
            for (int j = 0; j < uniqueCards.size(); j++) {
                if (dealer.getHand().get(i).getPoint() != uniqueCards.get(j).getPoint()) {
                    tempCount++;
                }
            }
            if (tempCount == uniqueCards.size()) {
                uniqueCards.add(dealer.getHand().get(i));
            }
        }
        // Adding the players 2 cards into hand to check overall score
        for (int i = 0; i < 2; i++) {
            allCards.add(player.getHand().get(i));
        }
        for (int i = 0; i < 2; i++) {
            tempCount = 0;
            for (int j = 0; j < uniqueCards.size(); j++) {
                if (player.getHand().get(i).getPoint() != uniqueCards.get(j).getPoint()) {
                    tempCount++;
                }
            }
            if (tempCount == uniqueCards.size()) {
                uniqueCards.add(dealer.getHand().get(i));
            }
        }

        // Bubble sort to make sure the cards are in order (by points)
        for (int i = 0; i < allCards.size(); i++) {
            for (int j = 0; j < allCards.size() - 1; j++) {
                if (allCards.get(j).getPoint() > allCards.get(j + 1).getPoint()) {
                    allCards.add(j, allCards.remove(j+1));
                }
            }
        }
        // Bubble sort for the uniqueCards arraylist doing the same thing
        for (int i = 0; i < uniqueCards.size(); i++) {
            for (int j = 0; j < uniqueCards.size() - 1; j++) {
                if (uniqueCards.get(j).getPoint() > uniqueCards.get(j + 1).getPoint()) {
                    uniqueCards.add(j, uniqueCards.remove(j+1));
                }
            }
        }

        // Checking the deck for a flush or straight and highest # of pairs
        for (int i = 0; i < allCards.size(); i++) {
            int currentPair = 1;
            int pairPoints = allCards.get(0).getPoint();
            flushCount = 0;
            straightCount = 0;
            for (int j = 1; j < allCards.size(); j++) {
                if (allCards.get(j).getSuit().equals(allCards.get(j-1).getSuit())) {
                    flushCount++;
                }
                if (allCards.get(j).getPoint() == allCards.get(j-1).getPoint() + 1) {
                    straightCount++;
                }
                if (allCards.get(i).getPoint() == allCards.get(j).getPoint() && j != i) {
                    currentPair++;
                    pairPoints += allCards.get(j).getPoint();
                }
            }
            if (flushCount >= 5)
                isFlush = true;
            if (straightCount >= 5)
                isStraight = true;
            if (currentPair > maxPair)
                maxPair = currentPair;
            if (pairPoints > maxPairPoints)
                maxPairPoints = pairPoints;
        }

        // Check for royal flush
        if (uniqueCards.size() == 5 && isFlush && uniqueCards.get(0).getPoint() == 10) {
            return 110;
        }
        // Check for straight flush
        else if (isStraight && isFlush) {
            return 100;
        }
        // Check for Four of a Kind
        else if (maxPair == 4) {
            return 90;
        }
        // Check for Full House
        else if (uniqueCards.size() == 4 && maxPair == 3) {
            return 80;
        }
        // Checking for a flush
        else if (isFlush) {
            return 70;
        }
        // Checking for a straight
        else if (isStraight) {
            return 60;
        }
        // Checking for Three of a Kind
        else if (maxPair == 3) {
            return 50;
        }
        // Checking for multiple pairs
        else if (maxPair == 2 && uniqueCards.size() <= 5) {
            return 40;
        }
        // Checking for a single pair
        else if (maxPair == 2) {
            return 20 + maxPairPoints / 2;
        }
        // Returning highCard
        else {
            return allCards.get(6).getPoint();
        }
    }

    public void round() {
        // Represents a single "round" of poker
        roundOver = false;
        // Resets the table
        resetTable();
        // Begings the first set of turns
        turnCycle();
        // As long as the game isn't over due to someone folding, continue
        if (!roundOver) {
            // Have the dealer draw 3 cards and present them on the table
            dealer.addCard(draw());
            dealer.addCard(draw());
            dealer.addCard(draw());
            // Continue allowing the players to have their turn and afterwards have the dealer draw until all 5 cards are drawn
            for (int i = 0; i < 2; i++) {
                if (roundOver)
                    break;
                turnCycle();
                dealer.addCard(draw());
            }
        }
        // Print the final board and if someone didn't fold, check for the winner
        printTable();
        if (!roundOver) {
            // Player 1 wins
            if (checkValue(player1) > checkValue(player2)) {
                System.out.println("Player 1 wins!");
                player1.addCash(currentTotalBet);
            }
            // Player 2 wins
            else if (checkValue(player1) < checkValue(player2)) {
                System.out.println("Player 2 wins!");
                player2.addCash(currentTotalBet);
            }
            // Tie
            else if (checkValue(player1) == checkValue(player2)) {
                System.out.println("It's a tie!");
                player1.addCash(currentTotalBet / 2);
                player2.addCash(currentTotalBet / 2);
            }
            else {
                System.out.println(checkValue(player1));
                System.out.println(checkValue(player2));
                System.out.println("Something went wrong");
            }
        }
    }

    public int checkLength(int num) {
        // Finds the length of a number (used for printing purposes)
        int value = 10;
        int digits = 1;
        while (num >= value){
            digits++;
            value *= 10;
        }
        return digits;
    }

    // Represents a single set of turns/actions for the players
    public void turnCycle() {
        // Continue to allow players to have their turn until an acceptable conclusion is reached (both checking etc.)
        while (!player1.getTurnEnd() || !player2.getTurnEnd()) {
            if (turnNumber % 2 == 0) {
                printTable();
                System.out.println("Player 1's Turn:");
                playerTurn = 1;
                turn(player1, player2);
            }
            else {
                printTable();
                System.out.println("Player 2's Turn:");
                playerTurn = 2;
                turn(player2, player1);
            }
        }
        // Reset turnEnd booleans used for checking whether the turns are over or not
        player1.setTurnEnd(false);
        player2.setTurnEnd(false);
    }

    // Represents a single turn for a player
    public void turn(Player player, Player otherPlayer) {
        // Increased turn number by 1
        turnNumber++;
        boolean currentTurn = true;
        String userInput = input.nextLine();
        // Asks user for their input for the turn and based on the input, perform actions
        while (currentTurn) {
            switch (userInput.toUpperCase()) {
                case "C":
                    // Checks if the player can actually call or not and if so proceeds to perform call action
                    if (lastAction.equals("Raise")) {
                        player.setAction("Call");
                        actions(player, otherPlayer, player.getAction());
                        currentTurn = false;
                        break;
                    }
                    // If the player can't call, let them redo their turn
                    turnNumber--;
                    currentTurn = false;
                    System.out.println("You can't do that");
                    break;
                case "R":
                    // Allows the player to increase their starting bet
                    player.setAction("Raise");
                    actions(player, otherPlayer, player.getAction());
                    currentTurn = false;
                    break;
                case "F":
                    // Lets the player quit a hand if they don't think they're going to win
                    player.setAction("Fold");
                    actions(player, otherPlayer, player.getAction());
                    currentTurn = false;
                    break;
                    // Lets the player check
                case "CHECK":
                    // Checks if the player can check or whether the other player has raised previously
                    if (!otherPlayer.getTurnEnd() || otherPlayer.getAction().equals("Check")) {
                        player.setAction("Check");
                        actions(player, otherPlayer, player.getAction());
                        currentTurn = false;
                        break;
                    }
                    // If the other player raised then don't allow the player to check
                    turnNumber--;
                    currentTurn = false;
                    System.out.println("You can't do that");
                    break;
                case "S":
                    player.show();
                    turnNumber--;
                    currentTurn = false;
                    break;
                case "H":
                    player.hide();
                    turnNumber--;
                    currentTurn = false;
                    break;
                default:
                    System.out.println(otherPlayer.getBet());
                    System.out.println("Something went horribly wrong");
                    System.exit(0);
                    break;
            }
        }
    }

    public void actions(Player player, Player otherPlayer, String action) {
        switch (action) {
            case "Call":
                lastAction = "Call";
                player.setBet(currentBet);
                player.setTurnEnd(true);
                break;
            case "Raise":
                System.out.println("How much would you like to raise by?");
                int userInput = input.nextInt();
                input.nextLine();
                currentBet += userInput;
                lastAction = "Raise";
                player.setBet(currentBet);
                player.setTurnEnd(true);
                otherPlayer.setTurnEnd(false);
                break;
            case "Fold":
                player.setTurnEnd(true);
                otherPlayer.setTurnEnd(true);
                lastAction = "Fold";
                roundOver = true;
                otherPlayer.addCash(currentTotalBet);
                System.out.println(otherPlayer.getName() + "has won! Congratulations.");
                break;
            case "Check":
                player.setAction("Check");
                player.setTurnEnd(true);
                lastAction = "Check";
                break;
        }
    }

    public boolean checkRound() {
        if (player1.getTurnEnd() && player2.getTurnEnd()) {
            roundOver = true;
            player1.setTurnEnd(false);
            player2.setTurnEnd(false);
            return true;
        }
        return false;
    }

    public String getBackground() {
        return background;
    }

    public void play() {
        clearScreen();
        printWelcome();
        String userInput = input.nextLine();
        while (!gameState) {
            if (userInput.equals("Y") || userInput.equals("y")) {
                background = "Welcome";
                window.repaint();
                gameState = true;
                clearScreen();
                System.out.println("Please enter the number of players (1 or 2)");
                userInput = input.nextLine();
                if (userInput.equals("1")) {
                    playBot();
                }
                else if (userInput.equals("2")) {
                    playPlayer();
                    background = "table";
                    window.repaint();
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
                background = "Instructions";
                window.repaint();
                printInstructions();
                userInput = input.nextLine();
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

// Note: Switch gameState to a string variable that tracks the player's actions so that the front end knows
// What background to switch to and what to do in general.


