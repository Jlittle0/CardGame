import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards;
    private static int cardsLeft;
    private static int numCards;
    private boolean isHidden;

    public Deck(String[] ranks, String[] suits, int[] points) {
       cards = new ArrayList<Card>();
       for (int i = 0; i < suits.length; i++) {
           for (int j = 0; j < ranks.length; j++) {
               numCards++;
               cards.add(new Card(ranks[j], suits[i], points[j], numCards));
           }
       }
       cardsLeft = cards.size();
       isHidden = true;
       numCards = 0;
    }
    public Deck() {
        cards = new ArrayList<Card>();
        cardsLeft = cards.size();
        isHidden = true;
    }

    public boolean isEmpty() {
        if (cardsLeft == 0)
            return true;
        return false;

    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if (isEmpty())
            return null;
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int randomIndex = (int)(Math.random() * cards.size());
            cards.add(i, cards.remove(randomIndex));
            cards.add(randomIndex, cards.remove(i - 1));
        }
        cardsLeft = cards.size();
    }


    public String toString() {
        String stuff = "";
        for (int i = 0; i < cards.size(); i++) {
            stuff += "[" + cards.get(i).toString() + "], \n";
        }
        return stuff + "total cards: " + cards.size() + "\n";
    }

}
