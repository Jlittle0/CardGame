import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards;
    private static int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] points) {
       cards = new ArrayList<Card>();
       for (int i = 0; i < suits.length; i++) {
           for (int j = 0; j < ranks.length; j++) {
               cards.add(new Card(ranks[j], suits[i], points[j]));
           }
       }
       cardsLeft = cards.size();
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
        for (int i = cards.size() - 1; i >= 0; i--) {
            int randomIndex = (int)(Math.random() * cards.size());
            cards.add(i, cards.remove(randomIndex));
            cards.add(randomIndex, cards.remove(i - 1));
        }
        cardsLeft = cards.size();
    }

}
