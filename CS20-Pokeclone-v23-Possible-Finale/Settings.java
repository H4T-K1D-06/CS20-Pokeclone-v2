public class Settings{
  private int textSpeed;//Output delay for try-catch block in milliseconds
  private String textSpeedPhrase;//Text speed represented as a word
  private int speedChoice;//Variable to cycle through speed settings
  private boolean fastCombat;//Toggles Turn-by-turn combat
  private boolean pve;//Toggles Player vs. Computer/Player vs. Player

  public Settings() {//Constructor
    textSpeed = 500;
    textSpeedPhrase = "Medium";
    speedChoice = 2;
    fastCombat = false;//Off by Default
    pve = true;//On by Default
  }

  //Returns the textSpeed variable
  public int getTextSpeed() {
    return textSpeed;
  }

  //Returns textSpeed as a word
  public String getTextSpeedPhrase() {
    return textSpeedPhrase;
  }//End method

  //Changes text speed
  /* in milliseconds:
  Instant = 0 (Instant)
  Fast = 250 (0.25 seconds)
  Medium = 500 (0.5 seconds)
  Slow = 750 (0.75 seconds)
  Snail = 1000 (1 second)
  Heat-Death of The Universe = 2147483647 (About 25 days)
  */
  public void cycleTextSpeed() {
    speedChoice++;
    if (speedChoice > 5) {//Resets speedChoice if it goes above the slowest setting
      speedChoice = 0;
    }
    if (speedChoice == 0) {
      textSpeedPhrase = "Instant";
      textSpeed = 0;
    } else if (speedChoice == 1) {
      textSpeedPhrase = "Fast";
      textSpeed = 250;
    } else if (speedChoice == 2) {
      textSpeedPhrase = "Medium";
      textSpeed = 500;
    } else if (speedChoice == 3) {
      textSpeedPhrase = "Slow";
      textSpeed = 750;
    } else if (speedChoice == 4) {
      textSpeedPhrase = "Snail";
      textSpeed = 1000;
    } else if (speedChoice == 5) {
      textSpeedPhrase = "Heat-Death of The Universe";
      textSpeed = 2147483647;
    }//end else if
  }

  //Returns the fastCombat variable
  public boolean getFastCombat() {
    return fastCombat;
  }

  //Toggles the fastCombat variable
  public void toggleTurns() {
    if (fastCombat == true) {
      fastCombat = false;
    } else {
      fastCombat = true;
    }
  }

  //Returns the pve variable
  public boolean getPve() {
    return pve;
  }

  //Toggles the pve variable
  public void togglePve() {
    if (pve == true) {
      pve = false;
    } else {
      pve = true;
    }
  }

  //Tostring
  @Override
  public String toString() {
    return "Select settings to cycle through them\n[1] Back\n[2] Text Speed: " + textSpeedPhrase + "\n[3] Quick Combat: " + fastCombat + "\n[4] PvE: " + pve;
  }
}//End class
//And be remembered through the years...