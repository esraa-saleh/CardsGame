/**
 *
 * Esra'a Saleh, 2016-07-23
 * This class represents a deck in the Game class. It allows for a deck to decrease in size, to be sorted, to peek and to shuffle..
 * Compilation and usage instructions: Dr Java for compilation.
 * Examples: not applicable, not a program.
 */


import java.util.Arrays;
import java.util.Random;

public class Deck{
  private final int MAX_CARDS = 54;
  private final int MAX_CARDS_PER_SUIT = 13;
  private final int MAX_SUITS = 4;

//  private int[][] cardArray;
  private Card[] cards;
  
  /* creates a deck of 54 cards as described in the Cards problem 
   * (52 cards that are in the standard deck of cards plus two jokers)
   */
  public Deck(){
    //I will create an int array that represents the cards

    this.cards=new Card[MAX_CARDS];
    int totalCardCounter = 0;
    for(int suitCounter=0; suitCounter< MAX_SUITS; suitCounter++){
       for(int cardCounter = 2; cardCounter < MAX_CARDS_PER_SUIT +2; cardCounter++){
         this.cards[totalCardCounter++] = new MyCard(cardCounter, Card.SUITS[suitCounter]);
       }
    }
    //make jokers
    this.cards[MAX_CARDS-2] = new MyCard(1,"None");
    this.cards[MAX_CARDS-1] = new MyCard(1,"None");


  }


  /* create a deck with specified number of copies of the 
   * standard deck (52 cards) and with the specified 
   * number of jokers 
   * 
   *
   */
  public Deck(int numStandard, int numJokers){

      populateDeck(numStandard,numJokers);



  }

  private void populateDeck(int numStandard){
      this.populateDeck(numStandard,2*numStandard);
  }

  private void populateDeck(int numStandard, int numJokers){
      this.cards=new Card[numStandard*(MAX_CARDS-2)+numJokers];
      int totalCardCounter = 0;
      for(int standardDeckCount=0; standardDeckCount<numStandard; standardDeckCount++) {
          for (int suitCounter = 0; suitCounter < MAX_SUITS; suitCounter++) {
              for (int cardCounter = 2; cardCounter < MAX_CARDS_PER_SUIT + 2; cardCounter++) {
                  this.cards[totalCardCounter++] = new MyCard(cardCounter, Card.SUITS[suitCounter]);
              }
          }
      }
      for(int jokerCount=0; jokerCount<numJokers; jokerCount++){
          this.cards[totalCardCounter++] = new MyCard(1, "None");

      }


  }
  
  
  /* returns the number of cards in the deck */
  public int numCards(){

      return this.cards.length ;}
  
  
  /* returns all the cards in the deck without modifying the deck 
   * the ordering in the array is the order of the cards at this
   * given time */
  public Card[] getCards(){return this.cards;}
  
  
  /* return the top card of the deck without modifying the deck */
  public Card peek(){
      return this.cards[this.cards.length-1];
  }
  
  
  /* remove the top card from the deck and return it */
  public Card pop(){
      Card poppedCard =  null;
      if(this.cards.length>0) {
          poppedCard = this.cards[this.cards.length - 1];
          this.cards = Arrays.copyOfRange(this.cards, 0, this.cards.length - 1);

      }
      return poppedCard;
  }
  

  /* randomly shuffle the order of the cards in the deck */
  public void shuffleDeck(){
    //will have to use random class
      Random randomIndexMaker= new Random();
      for(int cardCount=0; cardCount<this.cards.length-1;cardCount++){
          Card swappedCard= this.cards[cardCount];
          int randomInt=randomIndexMaker.nextInt(this.cards.length);
          this.cards[cardCount]=this.cards[randomInt];
          this.cards[randomInt]=swappedCard;
      }
  }
  
  /* sorts the deck so that cards are in the order 
   * specified in the Cards problem (low cards first)
   */
  public void sortDeck(){
      Arrays.sort(this.cards);
  }


}
