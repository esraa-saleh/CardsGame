/**
 * Esra'a Saleh,  2016-07-23
 * This class represents a hand or a player (basically cards of player) in the Game class. It allows for a player to play and get cards from the deck
 * or sort their cards. It also allows for changing the suit when an 8 is played.
 * Compilation and usage instructions: Dr Java for compilation.
 * Examples: not applicable, not a program.
 */
import java.util.Arrays;


public class Hand implements HandActions {

  private Card[] handCards;
    private int score;

  public Hand(Card[] cards) {
    // initialize hand with the given input cards
    this.handCards=cards;
  }
   public Card playCard(Card[] pile) {
     
  /* removes and returns a card from the current hand
    * such that the card is a valid card in the game
    * based on the provided pile of cards
    */
    //THIS WORKS =================================================TESTED EXTENSIVELY=================================

    for(int i=0; i<this.handCards.length;i++){
      int suitStrength = Arrays.asList(Card.SUITS).indexOf(this.handCards[i].getSuit());
      int suitStrengthOther=Arrays.asList(Card.SUITS).indexOf(pile[0].getSuit());
      if(this.handCards[i].getRank()==pile[0].getRank() || suitStrength==suitStrengthOther
              || this.handCards[i].getRank()==8 || this.handCards[i].getRank()==1){
        Card validCard= this.handCards[i];
        this.handCards[i]=this.handCards[this.handCards.length-1];
        this.handCards[this.handCards.length-1]=validCard;
        this.handCards=Arrays.copyOfRange(this.handCards,0,this.handCards.length-1 );

        return validCard;
      }
    }
   return null;
  }
  //================================================================================================================

  /* adds one or more cards to the current hand
   * (given by the input argument) and
   * possibly discards one or more cards from the
   * hand (returned)
   */
//===============================================================WORKS============================
  public Card[] getCard(Card[] cards){
    return this.addCards(cards);
    //puts cards in handcards
    //make a new array to fit cards and handcards
    //
  }

  public Card[] addCards(Card[] cards){
    int counter=0;
    //Count how many are not null to get the real length
    for(int i=0; i<cards.length; i++){
      if(cards[i]!=null){
        counter++;
      }
    }

    Card[] tempCards = new Card[counter];
    int currentIndex=0;
    for(int i=0; i<cards.length; i++){
      if(cards[i]!=null){
        tempCards[currentIndex++]=cards[i];

      }
    }

    if(cards.length != 0) {

      Card[] tempHandCards = new Card[cards.length + handCards.length]; //Arrays.copyOfRange(this.handCards,0,this.handCards.length);
      //int totalLength=cards.length+handCards.length;
      //this.handCards=new Card[totalLength];
      int totalCounter = 0;
      for (int handCardsCount = 0; handCardsCount < this.handCards.length; handCardsCount++) {
        tempHandCards[handCardsCount] = this.handCards[handCardsCount];
        totalCounter++;
      }
      for (int cardsCount = 0; cardsCount < cards.length; cardsCount++) {
        tempHandCards[totalCounter] = cards[cardsCount];
        totalCounter++;
      }
      this.handCards = tempHandCards;
    }
    /*MUST RETURN NULL FOR THIS ASSIGNMENT*/
    return null;
  }



//================================================================================================================
//
//  /* returns an array of all cards in the current hand */
  public Card[] displayHand(){return this.handCards;}

    /* returns an array of all cards in the current hand
    * with the cards in sorted order ("smallest first") based
    * on the sorting order of the cards used.
    * (for MyCard cards, diamonds come first, clubs next,
    * hearts next, spades next and jokers last. Each suit
    * is also sorted as described in the Cards problem)
    */
    //==========================================================WORKS
  public Card[] sortHand(){
    Arrays.sort(this.handCards);
    return this.handCards;
  }
//================================================================WORKS
  /* allows user to pass a message to the game */
  public String message(String question){
    System.out.println(question);
    int i=0;
    while(handCards[i].getSuit().equals("None")){

      i++;
    }
    return this.handCards[i].getSuit();
  }



  public static final boolean simpleLogic = true;

}
