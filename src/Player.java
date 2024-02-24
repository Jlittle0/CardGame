import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int points;
    private int cash;
    private boolean isHidden;
    private String action;
    private int bet;
    private int previousBet;
    private boolean turnEnd;

    public Player(String name) {
        turnEnd = false;
        this.name = name;
        hand = new ArrayList<Card>();
        points = 0;
        cash = 1000;
        isHidden = true;
        action = "Idle";
        bet = 0;
        previousBet = 0;
    }

    public Player(String name, ArrayList<Card> hand) {
        turnEnd = false;
        this.name = name;
        this.hand = hand;
        points = 0;
        cash = 1000;
        isHidden = true;
        action = "Idle";
        bet = 0;
        previousBet = 0;
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

    public void addCash(int cash) {
        this.cash += cash;
    }

    public boolean isHidden() {
        if (isHidden == true)
            return true;
        return false;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getCard(int index) {
        if (hand.get(index).getPoint() < 10) {
            return hand.get(index).getPoint() + hand.get(index).getSuit().substring(0,1);
        }
        return hand.get(index).getRank().substring(0,1) + hand.get(index).getSuit().substring(0,1);
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void addCard(Card card) {
        hand.add(card);
//        hand.add(new Card(card.getRank(), card.getSuit(), card.getPoint(), card.getCardImage()));
    }

    public void hide() {
        isHidden = true;
    }

    public void show() {
        isHidden = false;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
        if (bet != 0) {
            cash -= (bet - previousBet);
            previousBet = bet;
        }
    }

    public boolean getTurnEnd() {
        return turnEnd;
    }

    public void setTurnEnd(boolean bool) {
        turnEnd = bool;
    }

    public void resetHand() {
        hand = new ArrayList<Card>();
    }

    public String toString() {
        return name + " has " + points + " points\n" +
                name + "'s cards: " + hand;
    }

}
