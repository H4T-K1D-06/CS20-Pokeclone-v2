import java.util.*;

class Main {

  public static void gameOverText() {//Fun (and somewhat cryptic) text that displays at the end of the game, then ends the program
    try {// Delays next output
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } // end try-catch block

    String text = "At last I am free... for a time. I'll keep coming back, just like you. Until we meet again, farewell.";

    for (int i = 0; i < text.length(); i++) {
      try {// Delays next output
        Thread.sleep(30);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } // end try-catch block
      System.out.print(text.charAt(i));
    }

    for (int i = 0; i < text.length(); i++) {
      System.out.print("\b");
    }

    try {// Delays next output
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } // end try-catch block

    for (int i = 0; i < text.length(); i++) {
      try {// Delays next output
        Thread.sleep(30);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } // end try-catch block
      System.out.print(" ");
    }

    System.out.println("");

    System.exit(0);
  } //End gameOverText function

  public static void main(String[] args) {//Main Method

    Scanner input = new Scanner(System.in); // Creation of a Scanner

    try {// Delays next output
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } // end try-catch block

    System.out.println("What is your name?");// Custom name
    Settings config = new Settings();// Creates settings
    Player playerOne = new Player(input.nextLine()); // Human Player
    System.out.println("What is your rival's name?");// Custom name
    Player playerTwo = new Player(input.nextLine()); // Computer Player
    if (playerTwo.getName().equals("Supreme Calamitas")) {
      System.out.println("Alright, let's get started. Not sure why you're bothering.");
      System.out.println();
    }
    boolean gameOver = false; // Gamestate
    int cardChoice = -1; // Player's current card choice.
    boolean coinToss = true; // Determines who goes first
    int openingChoice = -1; // Used to navigate opening menus
    int settingsChoice = -1; // Used to navigate settings menu
    int playerChoice = -1;// Used to navigate Turn Menu
    int itemChoice = -1;// Used to navigate Bag
    int itemTarget = -1;// Used to navigate item targets
    int cardCount = 6;//Keeps track of that Computer's card count. Used for specialized messages.

    /*if (Math.random() > 0.5) {// Randomly determines who goes first
      coinToss = true;
    } else {
      coinToss = false;
    } */

    // Opening menu
    while (openingChoice != 1) {
      System.out.println("Welcome to yet another Pokemon Clone v2.3, " + playerOne.getName() + "!");
      System.out.println("[1] Play game");
      System.out.println("[2] Change game settings");
      System.out.println("[3] Quit");
      System.out.println(" ??? 20XX");
      openingChoice = input.nextInt();
      while (openingChoice == 2) {// Opens settings menu
        System.out.print(config + "\n");
        settingsChoice = input.nextInt();
        if (settingsChoice == 1) {// Goes back to the main menu
          openingChoice = -1;
        } else if (settingsChoice == 2) {// Cycles text speeds
          config.cycleTextSpeed();
        } else if (settingsChoice == 3) {// Toggles Turn-Based Combat
          config.toggleTurns();
        } else if (settingsChoice == 4) {// Toggles Player vs. Player
          config.togglePve();
        }
        // End Else/if
      } // End inner while
      if (openingChoice == 3) {// Ends the program
        System.exit(0);
      } // End if
    } // End while

    // Player One Card Choice
    while (cardChoice > 5 || cardChoice < 0) {// While the card choice is invalid
      System.out.println(playerOne.getName() + "'s Hand:");
      playerOne.showHand();
      System.out.println("\nWhich Card do you want to play?");
      cardChoice = input.nextInt(); // Asks the user for desired card

      if (cardChoice >= 0 && cardChoice <= 5) {// If player makes valid choice
        playerOne.activateCard(cardChoice);
        if (playerOne.getActiveCard().getHp() != 0) {// If card is alive
          System.out.println(playerOne.getName() + " sent out " + playerOne.getActiveCard().getName() + "!\n");
        } else {
          System.out.println(playerOne.getActiveCard().getName() + " Has no HP and cannot fight!");
        }
      } else {
        System.out.println("\ninvalid entry! You must pick a number between 0 and 5!\n");
      } // End if/else
    } // End while loop

    // Player Two Card Choice
    cardChoice = -1;
    if (config.getPve() == false) {
      while (cardChoice > 5 || cardChoice < 0) {// While the card choice is invalid
        System.out.println(playerTwo.getName() + "'s Hand:");
        playerTwo.showHand();
        System.out.println("\nWhich Card do you want to play?");
        cardChoice = input.nextInt(); // Asks the user for desired card

        if (cardChoice >= 0 && cardChoice <= 5) {// If player makes valid choice
          playerTwo.activateCard(cardChoice);
          if (playerTwo.getActiveCard().getHp() != 0) {// If card is alive
            System.out.println(playerTwo.getName() + " sent out " + playerTwo.getActiveCard().getName() + "!\n");
          } else {
            System.out.println(playerTwo.getActiveCard().getName() + " Has no HP and cannot fight!");
          }
        } else {
          System.out.println("\ninvalid entry! You must pick a number between 0 and 5!\n");
        } // End if/else
      } // End while loop
    } else {
      playerTwo.activateCard(0);
      System.out.println(playerTwo.getName() + " sent out " + playerTwo.getActiveCard().getName() + "!\n");
    }

    // game starts

    if (coinToss == true) { // Declares who goes first
      System.out.println(playerOne.getName() + " attacks first!\n");
    } else {
      System.out.println(playerTwo.getName() + " attacks first!\n");
    }

    while (playerOne.getActiveCard().getHp() > 0 && playerTwo.getActiveCard().getHp() > 0 && gameOver == false) {//While either pokemon is still alive
      if (coinToss == true) {// Player 1 Goes first------------------------------------------------------------------
        if (playerOne.getActiveCard().getHp() != 0) {// Checks if pokemon is alive to prevent actions Post Mortem
          if (config.getFastCombat() == false) {
            playerChoice = -1;// Resets player choice
            while (playerChoice < 0 || playerChoice > 4) {
              System.out.println(playerOne.getName() + "'s Turn: ");
              System.out.println("What do you do?");
              System.out.println("[1] Fight");
              System.out.println("[2] Switch");
              System.out.println("[3] Item");
              System.out.println("[4] Forfeit");
              playerChoice = input.nextInt();// Asks the player for their choice
              if (playerChoice == 1) {// Attack
                playerOne.attack(playerTwo.getActiveCard());
              } else if (playerChoice == 2) {// Switch out
                cardChoice = -1;// Resets card choice
                while (cardChoice > 5 || cardChoice < 0) {// While choice is invalid
                  System.out.println("Who shall be sent in?");
                  playerOne.showHand();
                  cardChoice = input.nextInt();
                  if (cardChoice >= 0 && cardChoice <= 5) {// Valid choice check
                    playerOne.activateCard(cardChoice);
                    if (playerOne.getActiveCard().getHp() != 0) {// Pokemon alive
                      System.out
                          .println(playerOne.getName() + " sent out " + playerOne.getActiveCard().getName() + "!\n");
                    } else {
                      System.out.println(playerOne.getActiveCard().getName() + " Has no HP and cannot fight!");
                      cardChoice = -1;
                    } // End inner inner if/else
                  } else {
                    System.out.println("invalid entry! You must pick a number between 0 and 5!");
                  } // End inner if/else
                } // End while
              } else if (playerChoice == 3) {// If the player wants to use an item
                itemChoice = -1;// Resets variables
                itemTarget = -1;
                while (itemChoice < 0 || itemChoice > 5) {// While Choice is invalid...
                  System.out.println("What item will you use?");
                  playerOne.showBag();
                  System.out.println("[5] Back");
                  itemChoice = input.nextInt();
                  if (itemChoice >= 0 && itemChoice <= 4) {// If a valid selection is made
                    playerOne.activateItem(itemChoice);
                  } else if (itemChoice == 5){//Returns player to previous menu
                    playerChoice = -1;
                    break;
                  } else {//If invalid choice
                    System.out.println("invalid entry!");
                  }

                  if (playerOne.getActiveItem().getAmount() != 0) {
                    if (playerOne.getActiveItem().getEffectType().equals("Nuke")) {//Unique item check. Name is self-explanatory
                      playerOne.useItem(playerTwo.getActiveCard());
                      playerOne.useItem(playerOne.getActiveCard());
                    } else {
                      while (itemTarget < 0 || itemTarget > 7) {// While Choice is invalid...
                        System.out.println("Use " + playerOne.getActiveItem() + " On...");
                        playerOne.showHand();
                        System.out.println("[6] Enemy Pokemon");
                        System.out.println("[7] Back");
                        itemTarget = input.nextInt();
                        if (itemTarget >= 0 && itemTarget <= 5) {// If a valid selection is made
                          playerOne.setItemTarget(itemTarget);
                          playerOne.useItem(playerOne.getItemTarget());
                        } else if (itemTarget == 6) {//Uses item on enemy
                          playerOne.useItem(playerTwo.getActiveCard());
                        } else if(itemTarget == 7) {
                          itemChoice = -1;
                          break;
                        }else {
                          System.out.println("invalid entry!");
                        } // End if
                      } // End While
                    }
                  } else {
                    System.out.println("You've ran out of " + playerOne.getActiveItem().getName() + "s!");
                  }
                } // End while
              } else if (playerChoice == 4 && playerTwo.getName().equals("Supreme Calamitas")) {
                System.out.println("...Please don't waste my time");
                System.exit(0);
              } else if (playerChoice == 4) {
                System.out.println(playerOne.getName() + " Forfeited!");
                System.out.println(playerTwo.getName() + " Wins!");
                System.exit(0);
              } else {
                System.out.println("Invalid Choice!");
              }
              System.out.println();
            }
          } else {
            playerOne.attack(playerTwo.getActiveCard());
          }
        } // End inner if

        try {// Delays next output
          Thread.sleep(config.getTextSpeed());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // end try-catch block
        
        //Player Two's turn
        if (playerTwo.getActiveCard().getHp() != 0 && config.getPve() == false) {// Pokemon alive and PvP Check
          if (config.getFastCombat() == false) {
            playerChoice = -1;// Resets player choice
            while (playerChoice < 0 || playerChoice > 4) {
              System.out.println(playerTwo.getName() + "'s Turn: ");
              System.out.println("What do you do?");
              System.out.println("[1] Fight");
              System.out.println("[2] Switch");
              System.out.println("[3] Item");
              System.out.println("[4] Forfeit");
              playerChoice = input.nextInt();// Asks the player for their choice
              if (playerChoice == 1) {// Attack
                playerTwo.attack(playerOne.getActiveCard());
              } else if (playerChoice == 2) {// Switch out
                cardChoice = -1;// Resets card choice
                while (cardChoice > 5 || cardChoice < 0) {// While choice is invalid
                  System.out.println("Who shall be sent in?");
                  playerTwo.showHand();
                  cardChoice = input.nextInt();
                  if (cardChoice >= 0 && cardChoice <= 5) {// Valid choice check
                    playerTwo.activateCard(cardChoice);
                    if (playerTwo.getActiveCard().getHp() != 0) {// Pokemon alive
                      System.out
                          .println(playerTwo.getName() + " sent out " + playerTwo.getActiveCard().getName() + "!\n");
                    } else {
                      System.out.println(playerTwo.getActiveCard().getName() + " Has no HP and cannot fight!");
                      cardChoice = -1;
                    } // End inner inner if/else
                  } else {
                    System.out.println("invalid entry! You must pick a number between 0 and 5!");
                  } // End inner if/else
                } // End while
              } else if (playerChoice == 3) {// If the player wants to use an item
                itemChoice = -1;// Resets variables
                itemTarget = -1;
                while (itemChoice < 0 || itemChoice > 5) {// While Choice is invalid...
                  System.out.println("What item will you use?");
                  playerTwo.showBag();
                  itemChoice = input.nextInt();
                  if (itemChoice >= 0 && itemChoice <= 4) {// If a valid selection is made
                    playerTwo.activateItem(itemChoice);
                  } else if (itemChoice == 7) {
                    playerChoice = -1;
                    break;
                  } else {
                    System.out.println("invalid entry!");
                  } // End if
                  if (playerOne.getActiveItem().getAmount() != 0) {
                    if (playerTwo.getActiveItem().getEffectType().equals("Nuke")) {
                      playerTwo.useItem(playerTwo.getActiveCard());
                      playerTwo.useItem(playerOne.getActiveCard());
                    } else {
                      while (itemTarget < 0 || itemTarget > 7) {// While Choice is invalid...
                        System.out.println("On which Pokemon?");
                        playerTwo.showHand();
                        System.out.println("[6] Enemy Pokemon");
                        itemTarget = input.nextInt();
                        if (itemTarget >= 0 && itemTarget <= 5) {// If a valid selection is made
                          playerTwo.setItemTarget(itemTarget);
                          playerTwo.useItem(playerTwo.getItemTarget());
                        } else if (itemTarget == 6) {
                          playerOne.useItem(playerTwo.getActiveCard());
                        } else if (itemTarget == 7) {
                          itemChoice = -1;
                          break;
                        }else {
                          System.out.println("invalid entry!");
                        } // End if
                      } // End While
                    } // End If
                  } else {
                    System.out.println("You've ran out of " + playerTwo.getActiveItem().getName() + "s!");
                  }
                } // End while
              } else if (playerChoice == 4) {
                System.out.println(playerTwo.getName() + " Forfeited!");
                System.out.println(playerOne.getName() + " Wins!");
                System.exit(0);

              } else {
                System.out.println("Invalid Choice!");
              }
              System.out.println();
            }
          } else {
            playerTwo.attack(playerOne.getActiveCard());
          }
        } else if (playerTwo.getActiveCard().getHp() != 0 && config.getPve() == true) {// Checks if pokemon is alive to prevent actions Post Mortem
          System.out.println("\n" + playerTwo.getName() + "'s Turn: ");
          playerTwo.attack(playerOne.getActiveCard());
          System.out.println();
        } // end inner if

        try {// Delays next output
          Thread.sleep(config.getTextSpeed());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // end try-catch block





      } else if (coinToss == false) {// Player 2 Goes first---------------------------------------------------------
        //Player Two's turn
        if (playerTwo.getActiveCard().getHp() != 0 && config.getPve() == false) {// Pokemon alive and PvP Check
          if (config.getFastCombat() == false) {
            playerChoice = -1;// Resets player choice
            while (playerChoice < 0 || playerChoice > 4) {
              System.out.println(playerTwo.getName() + "'s Turn: ");
              System.out.println("What do you do?");
              System.out.println("[1] Fight");
              System.out.println("[2] Switch");
              System.out.println("[3] Item");
              System.out.println("[4] Forfeit");
              playerChoice = input.nextInt();// Asks the player for their choice
              if (playerChoice == 1) {// Attack
                playerTwo.attack(playerOne.getActiveCard());
              } else if (playerChoice == 2) {// Switch out
                cardChoice = -1;// Resets card choice
                while (cardChoice > 5 || cardChoice < 0) {// While choice is invalid
                  System.out.println("Who shall be sent in?");
                  playerTwo.showHand();
                  cardChoice = input.nextInt();
                  if (cardChoice >= 0 && cardChoice <= 5) {// Valid choice check
                    playerTwo.activateCard(cardChoice);
                    if (playerTwo.getActiveCard().getHp() != 0) {// Pokemon alive
                      System.out
                          .println(playerTwo.getName() + " sent out " + playerTwo.getActiveCard().getName() + "!\n");
                    } else {
                      System.out.println(playerTwo.getActiveCard().getName() + " Has no HP and cannot fight!");
                      cardChoice = -1;
                    } // End inner inner if/else
                  } else {
                    System.out.println("invalid entry! You must pick a number between 0 and 5!");
                  } // End inner if/else
                } // End while
              } else if (playerChoice == 3) {// If the player wants to use an item
                itemChoice = -1;// Resets variables
                itemTarget = -1;
                while (itemChoice < 0 || itemChoice > 5) {// While Choice is invalid...
                  System.out.println("What item will you use?");
                  playerTwo.showBag();
                  itemChoice = input.nextInt();
                  if (itemChoice >= 0 && itemChoice <= 4) {// If a valid selection is made
                    playerTwo.activateItem(itemChoice);
                  } else if (itemChoice == 7) {
                    playerChoice = -1;
                    break;
                  } else {
                    System.out.println("invalid entry!");
                  } // End if
                  if (playerOne.getActiveItem().getAmount() != 0) {
                    if (playerTwo.getActiveItem().getEffectType().equals("Nuke")) {
                      playerTwo.useItem(playerTwo.getActiveCard());
                      playerTwo.useItem(playerOne.getActiveCard());
                    } else {
                      while (itemTarget < 0 || itemTarget > 7) {// While Choice is invalid...
                        System.out.println("On which Pokemon?");
                        playerTwo.showHand();
                        System.out.println("[6] Enemy Pokemon");
                        itemTarget = input.nextInt();
                        if (itemTarget >= 0 && itemTarget <= 5) {// If a valid selection is made
                          playerTwo.setItemTarget(itemTarget);
                          playerTwo.useItem(playerTwo.getItemTarget());
                        } else if (itemTarget == 6) {
                          playerOne.useItem(playerTwo.getActiveCard());
                        } else if (itemTarget == 7) {
                          itemChoice = -1;
                          break;
                        }else {
                          System.out.println("invalid entry!");
                        } // End if
                      } // End While
                    } // End If
                  } else {
                    System.out.println("You've ran out of " + playerTwo.getActiveItem().getName() + "s!");
                  }
                } // End while
              } else if (playerChoice == 4) {
                System.out.println(playerTwo.getName() + " Forfeited!");
                System.out.println(playerOne.getName() + " Wins!");
                System.exit(0);

              } else {
                System.out.println("Invalid Choice!");
              }
              System.out.println();
            }
          } else {
            playerTwo.attack(playerOne.getActiveCard());
          }
        } else if (playerTwo.getActiveCard().getHp() != 0 && config.getPve() == true) {// Checks if pokemon is alive to prevent actions Post Mortem
          System.out.println("\n" + playerTwo.getName() + "'s Turn: ");
          playerTwo.attack(playerOne.getActiveCard());
          System.out.println();
        } // end inner if

        try {// Delays next output
          Thread.sleep(config.getTextSpeed());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // end try-catch block

        if (playerOne.getActiveCard().getHp() != 0) {// Checks if pokemon is alive to prevent actions Post Mortem
          if (config.getFastCombat() == false) {
            playerChoice = -1;// Resets player choice
            while (playerChoice < 0 || playerChoice > 4) {
              System.out.println(playerOne.getName() + "'s Turn: ");
              System.out.println("What do you do?");
              System.out.println("[1] Fight");
              System.out.println("[2] Switch");
              System.out.println("[3] Item");
              System.out.println("[4] Forfeit");
              playerChoice = input.nextInt();// Asks the player for their choice
              if (playerChoice == 1) {// Attack
                playerOne.attack(playerTwo.getActiveCard());
              } else if (playerChoice == 2) {// Switch out
                cardChoice = -1;// Resets card choice
                while (cardChoice > 5 || cardChoice < 0) {// While choice is invalid
                  System.out.println("Who shall be sent in?");
                  playerOne.showHand();
                  cardChoice = input.nextInt();
                  if (cardChoice >= 0 && cardChoice <= 5) {// Valid choice check
                    playerOne.activateCard(cardChoice);
                    if (playerOne.getActiveCard().getHp() != 0) {// Pokemon alive
                      System.out
                          .println(playerOne.getName() + " sent out " + playerOne.getActiveCard().getName() + "!\n");
                    } else {
                      System.out.println(playerOne.getActiveCard().getName() + " Has no HP and cannot fight!");
                      cardChoice = -1;
                    } // End inner inner if/else
                  } else {
                    System.out.println("invalid entry! You must pick a number between 0 and 5!");
                  } // End inner if/else
                } // End while
              } else if (playerChoice == 3) {// If the player wants to use an item
                itemChoice = -1;// Resets variables
                itemTarget = -1;
                while (itemChoice < 0 || itemChoice > 5) {// While Choice is invalid...
                  System.out.println("What item will you use?");
                  playerOne.showBag();
                  System.out.println("[5] Back");
                  itemChoice = input.nextInt();
                  if (itemChoice >= 0 && itemChoice <= 4) {// If a valid selection is made
                    playerOne.activateItem(itemChoice);
                  } else if (itemChoice == 5){//Returns player to previous menu
                    playerChoice = -1;
                    break;
                  } else {//If invalid choice
                    System.out.println("invalid entry!");
                  }

                  if (playerOne.getActiveItem().getAmount() != 0) {
                    if (playerOne.getActiveItem().getEffectType().equals("Nuke")) {//Unique item check. Name is self-explanatory
                      playerOne.useItem(playerTwo.getActiveCard());
                      playerOne.useItem(playerOne.getActiveCard());
                    } else {
                      while (itemTarget < 0 || itemTarget > 7) {// While Choice is invalid...
                        System.out.println("Use " + playerOne.getActiveItem() + " On...");
                        playerOne.showHand();
                        System.out.println("[6] Enemy Pokemon");
                        System.out.println("[7] Back");
                        itemTarget = input.nextInt();
                        if (itemTarget >= 0 && itemTarget <= 5) {// If a valid selection is made
                          playerOne.setItemTarget(itemTarget);
                          playerOne.useItem(playerOne.getItemTarget());
                        } else if (itemTarget == 6) {//Uses item on enemy
                          playerOne.useItem(playerTwo.getActiveCard());
                        } else if(itemTarget == 7) {
                          itemChoice = -1;
                          break;
                        }else {
                          System.out.println("invalid entry!");
                        } // End if
                      } // End While
                    }
                  } else {
                    System.out.println("You've ran out of " + playerOne.getActiveItem().getName() + "s!");
                  }
                } // End while
              } else if (playerChoice == 4 && playerTwo.getName().equals("Supreme Calamitas")) {
                System.out.println("...Please don't waste my time");
                System.exit(0);
              } else if (playerChoice == 4) {
                System.out.println(playerOne.getName() + " Forfeited!");
                System.out.println(playerTwo.getName() + " Wins!");
                System.exit(0);
              } else {
                System.out.println("Invalid Choice!");
              }
              System.out.println();
            }
          } else {
            playerOne.attack(playerTwo.getActiveCard());
          }
        } // End inner if

        try {// Delays next output
          Thread.sleep(config.getTextSpeed());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // end try-catch block
      } // End if------------------------------------------------------------------------------------------------------







      if (playerTwo.getActiveCard().getHp() <= 0 && config.getPve() == false) {// If the active card has fainted
        boolean playerTwoGameOver = true;
        for (int i = 0; i < 6; i++) {// Checks if all Pokemon are fainted
          if (playerTwo.getHand()[i].getHp() > 0) {
            playerTwoGameOver = false;
          } // end if
        } // end for loop
        if (playerTwoGameOver) {// If the player has all their Pokemon die
          gameOver = true;
          System.out.println("Game Over!\n" + playerOne.getName() + " wins!");
        } // end inner if
        else {// Pokemon Fecking Dies
          playerTwo.getActiveCard().setActive(false); // De-selects pokemon as Active
          System.out.println(playerTwo.getActiveCard().getName() + " Fainted!");
          cardChoice = -1;

          while (cardChoice > 5 || cardChoice < 0) {// While choice is invalid
            System.out.println(playerTwo.getName() + ", Who shall be sent in?");
            playerTwo.showHand();
            cardChoice = input.nextInt();

            if (cardChoice >= 0 && cardChoice <= 5) {// Valid choice check
              playerTwo.activateCard(cardChoice);
              if (playerTwo.getActiveCard().getHp() != 0) {// Pokemon alive
                System.out.println(playerTwo.getName() + " sent out " + playerTwo.getActiveCard().getName() + "!\n");
              } else {
                System.out.println(playerTwo.getActiveCard().getName() + " Has no HP and cannot fight!");
                cardChoice = -1;
              } // End inner if
            } else {
              System.out.println("invalid entry! You must pick a number between 0 and 5!");
            } // End if/else
          } // End while loop
        } // end else
      } else if (playerTwo.getActiveCard().getHp() <= 0 && config.getPve() == true) {// If the enemy card faints and PvE is on
        playerTwo.getActiveCard().setActive(false);
        System.out.println(playerTwo.getActiveCard().getName() + " Fainted!");
        if ((playerTwo.getActiveCardIndex() + 1) < 6) {// Tells the computer to keep playing cards if it has any
          playerTwo.activateCard(playerTwo.getActiveCardIndex() + 1);
          cardCount--;
          System.out.println(playerTwo.getName() + " sent out " + playerTwo.getActiveCard().getName() + "!");
          System.out.println(playerTwo.getName() + " has " + (cardCount) + " Pokemon left!\n");
          if (playerTwo.getName().equals("Supreme Calamitas")) {
            if (cardCount == 5) {
              System.out.println("You seem so confident, even though you are painfully ignorant of what has yet to transpire...");
            } else if (cardCount == 4) {
              System.out.println("Everything was going well until you came along.");
            } else if (cardCount == 3) {
              System.out.println("Don't worry, I still have plenty of tricks left");
            } else if (cardCount == 2) {
              System.out.println("Impressive...but still not good enough!");
            } else if (cardCount == 1) {
              System.out.println("Well... I suppose this is the end...");
            }
            System.out.println();
          }
        } else {
          System.out.println("Game Over!\n" + playerOne.getName() + " wins!");
          gameOverText();
        }
      } // end if

      if (playerOne.getActiveCard().getHp() <= 0) {// If the active card has fainted
        boolean playerOneGameOver = true;
        for (int i = 0; i < 6; i++) {// Checks if all Pokemon are fainted
          if (playerOne.getHand()[i].getHp() > 0) {
            playerOneGameOver = false;
          } // end if
        } // end for loop
        if (playerOneGameOver) {// If the player has all their Pokemon die
          gameOver = true;
          System.out.println("Game Over!\n" + playerTwo.getName() + " wins!");
        } // end inner if
        else {// Pokemon Fecking Dies
          playerOne.getActiveCard().setActive(false); // De-selects pokemon as Active
          System.out.println(playerOne.getActiveCard().getName() + " Fainted!");
          cardChoice = -1;

          while (cardChoice > 5 || cardChoice < 0) {// While choice is invalid
            System.out.println(playerOne.getName() + ", Who shall be sent in?");
            playerOne.showHand();
            cardChoice = input.nextInt();

            if (cardChoice >= 0 && cardChoice <= 5) {// Valid choice check
              playerOne.activateCard(cardChoice);
              if (playerOne.getActiveCard().getHp() != 0) {// Pokemon alive
                System.out.println(playerOne.getName() + " sent out " + playerOne.getActiveCard().getName() + "!\n");
              } else {
                System.out.println(playerOne.getActiveCard().getName() + " Has no HP and cannot fight!");
                cardChoice = -1;
              } // End inner if
            } else {
              System.out.println("invalid entry! You must pick a number between 0 and 5!");
            } // End if/else
          } // End while loop
        } // end else
      } // end outer if
    } // end while
  }// End Main
}// End main
// End of Reasoning, Carnage and pain...