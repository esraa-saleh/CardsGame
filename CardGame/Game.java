/**
 *
 * Esra'a Saleh, 2016-07-23
 * This class represents a card in the Game class. It allows for a card to have a suit and a rank.
 * Compilation and usage instructions: Dr Java for compilation.
 * Examples:
 * if card on top of pile was 13C
 * Player 1 hand: [J, 8D, 10C, 11S, 9C, 3H, 13S, 11H]
 * Player 1 hand: [J, 8D, 10C, 11S, 9C, 3H, 13S, 11H, 4S] ---->one card taken from deck
 * Player 1 played J
 * Player 2 hand: [2S, 3C, 9S, 11H, 2S, 8H, 14C, 8C]
 * Player 2 hand: [2S, 3C, 9S, 11H, 2S, 8H, 14C, 8C, 11D]
 * Player 2 played 3C

 */
import java.util.Arrays;

public class Game {
    private static final int INITIAL_CARDS = 8;
    private Hand[] hands;
    private Card[] discardPile;
    private Deck deck;
    //EMPTY CONSTRUCTOR
    public Game(){
      /*EMPTY ON PURPOSE*/
    }


    //=================================================CHECKS IF A PLAYER WON==========================================
    /*
    * input: Card array of current hand and deck
    * return type: int
    * contract:-preconditions: parameters entered as stated
    * -postconditions: returns a different int depending on whther the deck is empty(0), someone won(1), or if none of that happened (-1)
    * side-effects:none
    *
    * */
    private int winningChecker(Card[] currentHand, Deck deck){
        if(currentHand.length==0 || deck.getCards().length==0){
            if(deck.getCards().length==0){
                return 0;
            }
            else if(currentHand.length==0) {
                return 1;
            }
        }
            return -1;
    }
    //================================================CHECK FOR EXISTENCE OF SPECIAL CARDS (2,4,8)=====================
    /*
    * input:Card array which is the discard pile
    * return type:boolean
    * contract:-preconditions: top card must be index 0 of discard pile
    * -postconditions: returns true if there is a special card at index 0, false other wise
    * side-effects:none
    *
    * */
    private boolean specialCardsChecker(Card[] discardPile){

        if(
                discardPile[0].getRank()==2 ||
                discardPile[0].getRank()==4 ||
                discardPile[0].getRank()==8 ||
                discardPile[0].getRank()==1){
            return true;
        }
        return false;
    }
    //====================================================Updates pile==========================================
    //simple method for making sure that the card played is on the top
    private void updatePile(Card[] discardPile,Card newCard){
        Card oldCard=discardPile[0];
        discardPile[0]=newCard;
        discardPile[1]=oldCard;

    }




    //================================================GAME LOOP========================================================
    /*
    * input:none
    * return type:none
    * contract:-preconditions: to be embedded in this game class
    * -postconditions:varied output depending on player actions
    * side-effects: changing all attributes stated on the top
    *
    * */
    private void gameLoop(){

        /*Declare variables to be initialized officially inside the loop*/
        int currentPlayerIndex = 0;
        Hand currentHand = null;
        //1 turn
         do{
             currentHand = this.hands[currentPlayerIndex];
             System.out.println("Player " + (currentPlayerIndex + 1) +" hand: "+
                     Arrays.toString(hands[currentPlayerIndex].displayHand()));
             Card potentialPlay = null;
             potentialPlay = currentHand.playCard(discardPile);
             if(currentHand.displayHand().length>0) {
                 do {

                     if (potentialPlay == null && deck.getCards().length > 0) {

                         currentHand.addCards(new Card[]{deck.pop()});
                         System.out.println("Player " + (currentPlayerIndex + 1) +" hand: "+
                                 Arrays.toString(hands[currentPlayerIndex].displayHand()));
                         potentialPlay = currentHand.playCard(discardPile);
                     } else if (deck.getCards().length == 0) {
                         break;
                     }
                 } while (potentialPlay == null);
             }

             if(winningChecker(currentHand.displayHand(),this.deck)==1) {
                 System.out.println("Player " + (currentPlayerIndex + 1) + " won!");
             }else if(winningChecker(currentHand.displayHand(),this.deck)==0){

                 potentialPlay = currentHand.playCard(discardPile);
                 if(potentialPlay!= null){

                     updatePile(discardPile,potentialPlay);
                     System.out.println("Player "+ (currentPlayerIndex + 1) +" played "+potentialPlay);
                     System.out.println("Player " + (currentPlayerIndex + 1) +" hand: "+
                             Arrays.toString(hands[currentPlayerIndex].displayHand()));
                 }


             }
             else {

                 updatePile(discardPile,potentialPlay);

                 System.out.println("Player "+ (currentPlayerIndex + 1) +" played "+potentialPlay);
                 System.out.println("Player " + (currentPlayerIndex + 1) +" hand: "+
                         Arrays.toString(hands[currentPlayerIndex].displayHand()));


                 int nextPlayerIndex = 0;
                 if(specialCardsChecker(discardPile)){
                     if(discardPile[0].getRank()==2){
                         //this will actually be the next player
                         nextPlayerIndex=currentPlayerIndex+1;
                         if(nextPlayerIndex==this.hands.length){
                             nextPlayerIndex=0;
                         }
                         this.hands[nextPlayerIndex].addCards(new Card[]{deck.pop(),deck.pop()});
                         System.out.println("A card of rank 2 was played. Player "+(nextPlayerIndex+1)+
                                 " receives two cards and misses their turn.");
                         //Skip turn, done once here because default increment happens after this
                         currentPlayerIndex++;
                         if(currentPlayerIndex==this.hands.length){
                             nextPlayerIndex=0;
                         }

                     }else if(discardPile[0].getRank()==4){
                         nextPlayerIndex=currentPlayerIndex+1;
                         if(nextPlayerIndex==this.hands.length){
                             nextPlayerIndex=0;
                         }
                         System.out.println("A card of rank 4 was played. Player "+(nextPlayerIndex+1)+" misses their turn.");
                         //Skip turn, done once here because default increment happens after this
                         currentPlayerIndex++;
                         if(currentPlayerIndex==this.hands.length){
                             nextPlayerIndex=0;
                         }
                     }else if(discardPile[0].getRank()==8){
                                String changedSuit=currentHand.message("What suit do you want the 8 you just played to be ?");
                                discardPile[0]= new MyCard(8,changedSuit);
                                System.out.println("Changed suit of 8: "+changedSuit);
                     }else if(discardPile[0].getRank()==1){
                         discardPile[0]=discardPile[1];
                     }
                 }
             }
             currentPlayerIndex++;
         if (currentPlayerIndex >= this.hands.length) {
                 currentPlayerIndex = 0;
             }

        }while(this.winningChecker(currentHand.displayHand(),this.deck)==-1);

        if(winningChecker(currentHand.displayHand(),this.deck)==0){

            System.out.println("The deck is now empty. The winner is the player who has cards with the lowest sum of ranks.");


            int[] scores = new int[hands.length];
            for(int i=0; i<hands.length;i++){
                for(int x=0; x<hands[i].displayHand().length;x++) {

                    scores[i] += hands[i].displayHand()[x].getRank();
                }
            }

            int winnerIndex[] = new int[hands.length];
            for (int i=0;i<winnerIndex.length;i++){
                winnerIndex[i]=-1;
            }
            int lowestScore = Integer.MAX_VALUE;
            int winnerCounter=0;
            for (int playerCounter = 0; playerCounter < hands.length; playerCounter++){

                System.out.println("Player "+(playerCounter+1)+" score: "+scores[playerCounter]);
                if(lowestScore>scores[playerCounter]){
                    winnerCounter=0;
                    winnerIndex = new int[hands.length];
                    for (int i=0;i<winnerIndex.length;i++){
                        winnerIndex[i]=-1;
                    }
                    lowestScore=scores[playerCounter];
                    winnerIndex[winnerCounter++]=playerCounter;
                }
                else if(lowestScore==scores[playerCounter]){
                    winnerIndex[winnerCounter++]=playerCounter;
                }
            }

            if(winnerCounter > 1){
                System.out.print("The winners are:");
                for(int playerCounter=0; playerCounter<winnerIndex.length;playerCounter++) {
                    if (winnerIndex[playerCounter] == -1) {
                        break;
                    } else {
                        System.out.print(" Player " + (winnerIndex[playerCounter] + 1));
                    }


                }
            }else{
                System.out.println("The winner is: Player "+ (winnerIndex[0]+1));
            }




        }


    }
    /*
    * input: array of string type
    * return type:none
    * contract:-preconditions: args must be an array of strings obtained from command prompt args
    * -postconditions:no return value
    * side-effects: displays the set up of the game, changes attributes above.
    *
    * */
    private void start(String[] args){
        //========================================SETTING UP THE GAME ==================================================
        if(args[0].equals("2")|| args[0].equals("3")||args[0].equals("4")||args[0].equals("5") ||
                args[0].equals("6")|| args[0].equals("7")){
            deck=new Deck(0,0);
            if(args[0].equals("2")|| args[0].equals("3")||args[0].equals("4")||args[0].equals("5")){
            System.out.println("Setting up a game for "+args[0]+" players.");
                deck= new Deck();

            }else if(args[0].equals("6")|| args[0].equals("7")){
            System.out.println("Setting up a game for "+args[0]+" players.");
                deck= new Deck(2,4);

            }

            int numPlayers=Integer.parseInt(args[0]);
            System.out.println("Deck: "+Arrays.toString(deck.getCards()));
            do {

                deck.shuffleDeck();


            }while(this.specialCardsChecker(new Card[]{deck.getCards()[INITIAL_CARDS*numPlayers]}));

            System.out.println("Deck after shuffling: "+Arrays.toString(deck.getCards()));
            //create hands

            hands = new Hand[numPlayers];

            for(int handCounter=0; handCounter<numPlayers; handCounter++) {
                //create a hand at the specified index
                this.hands[handCounter]=new Hand(new Card[0]);
            }
            //deal cards
            for(int cardCounter=0; cardCounter<INITIAL_CARDS; cardCounter++){
            for(int handCounter=0; handCounter<numPlayers; handCounter++){

                    this.hands[handCounter].addCards(new Card[]{deck.pop()});
                }
            }

        System.out.println(Arrays.toString(hands[0].displayHand()));
        discardPile= new Card[2];//{new MyCard(poppedCard.getRank(),poppedCard.getSuit())};
        discardPile[0]= deck.pop();
        System.out.println("Card on top of pile: "+Arrays.toString(discardPile));
            //========================================================================================================
        gameLoop();




        }else{
            System.out.println("You have entered invalid command line arguments. " +
                    "Please re-run the program and enter an integer from 2-7 for the number of players.");
        }

        //==============================================================================================================

    }


    public static void main(String[] args){

        new Game().start(args);
    }
}
