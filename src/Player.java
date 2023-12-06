import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int points;
    private int cash;
    private boolean isHidden;
    private String action;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<Card>();
        points = 0;
        cash = 1000;
        isHidden = true;
        action = "Idle";
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
        points = 0;
        cash = 1000;
        isHidden = true;
        action = "Idle";
    }

    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }

    public int getCash() {
        return cash;
    }

    public boolean isHidden() {
        if (isHidden == true)
            return true;
        return false;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getHand(int index) {
        return hand.get(index).getRank().substring(0,1) + hand.get(index).getSuit().substring(0,1);
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void addCard(Card card) {
        hand.add(new Card(card.getRank(), card.getSuit(), card.getPoint()));
    }

    public void hide() {
        isHidden = true;
    }

    public void show() {
        isHidden = false;
    }

    public String toString() {
        return name + " has " + points + " points\n" +
                name + "'s cards: " + hand;
    }

}
