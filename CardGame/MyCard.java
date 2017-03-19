/**
 *
 * Esra'a Saleh, 101014397, 2016-07-23
 * This class represents a card in the Game class. It allows for a card to have a suit and a rank.
 * Compilation and usage instructions: Dr Java for compilation.
 *
 */


import java.util.Arrays;

public class MyCard extends Card {
    /*The string representation taken from Ranks array in the Card class*/
    private String rank;
    /*The string representation taken from Suits array in the Card class*/
    private String suit;



    /*Int representation (value) of the rank defined as a calculated attribute*/

    public MyCard(String rank, String suit) {
        // purpose: creates a card with given rank and suit
        // preconditions: suit must be a string found in Cards.SUITS
        // rank must be a string found in Cards.RANKS
        super();
        this.rank = rank;
        this.suit = suit;
    }

    public MyCard(int value, String suit) {
        // purpose: creates a card with the given rank and suit
        // preconditions: suit must be a string found in Cards.SUITS
        // rank is an integer satisfying 1 <= rank <= 14, where
        // 1 for joker, 2 for 2, 3 for 3, .., 10 for 10
        // 11 for jack, 12 for queen, 13 for king, 14 for ace
        /*Call to parent constructor*/
        super();
        /**/
        this.rank = convertRank(value);
        this.suit = suit;

    }

    /**
     * @return int value of this card (rank)
     */
    @Override
    public int getRank() {
//         Purpose: Get the current card’s rank as an integer
//         Output: the rank of the card
//         joker -> 1, 2 -> 2, 3 -> 3, ..., 10 -> 10
//         jack -> 11, queen -> 12, king -> 13, ace -> 14
        int rankCount = 0;
//        for(; rankCount<RANKS.length; rankCount++){
//            if(RANKS[rankCount].equals(this.rank)) {
//                break;
//            }
//        }
        for (; rankCount < RANKS.length && !RANKS[rankCount].equals(this.rank); rankCount++) ;

        return rankCount;
    }

    public String getRankString() {

        return this.rank;
    }

    @Override
    public String getSuit() {
        return this.suit;
    }

    @Override
    public int compareTo(Card other) {
        int suitStrength = Arrays.asList(Card.SUITS).indexOf(this.getSuit());
        int suitStrengthOther = Arrays.asList(Card.SUITS).indexOf(other.getSuit());



        if (suitStrength == suitStrengthOther) {

            if(this.getRank()==other.getRank()){
                return 0;
            }else{
                return this.getRank()-other.getRank();
            }

        }
        else {
            return suitStrength - suitStrengthOther;
        }
    }

    /*HELPER METHODS*/

    /**
     * Converts a numerical (int) value into a rank
     *
     * @param value
     * @return
     */
    private String convertRank(int value) {

        return RANKS[value];
    }






    

}
