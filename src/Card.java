import javax.swing.*;
import java.awt.*;

public class Card {
    private String rank;
    private String suit;
    private int point;
    private Image cardImage;

    public Card(String rank, String suit, int point, int num) {
        this.rank = rank;
        this.suit = suit;
        this.point = point;
        this.cardImage = new ImageIcon("Resources/Cards/" + num + ".png").getImage();
    }
    public Card(String rank, String suit, int point, Image cardImage) {
        this.rank = rank;
        this.suit = suit;
        this.point = point;
        this.cardImage = cardImage;
    }


    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getPoint() {
        return point;
    }

    public Image getCardImage() {
        return cardImage;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setRank(int point) {
        this.point = point;
    }

    public String toString() {
        return rank + " of " + suit;
    }

}
