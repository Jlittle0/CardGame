import java.util.ArrayList;

public class Game {

    private ArrayList<Player> players;
    private Deck mainDeck;
    private Deck subDeck;

    public Game() {
        players = new ArrayList<Player>();
        String[] ranks = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
        int[] points = {14, 2, 3 , 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        mainDeck = new Deck(ranks, suits, points);
        subDeck = new Deck(ranks, suits, points);
        // test
    }

    public String printDeck() {
        mainDeck.shuffle();
        subDeck.shuffle();
        return mainDeck.toString() + subDeck.toString();
    }
    public static void main(String[] args) {
        Game g = new Game();
        System.out.println(g.printDeck());
    }
}


