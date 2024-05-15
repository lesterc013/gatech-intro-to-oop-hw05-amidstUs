import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor {
  private String skill;
  public static final int DEFAULT_SUS_LEVEL = 15;
  public static final String DEFAULT_EXP = "experienced";

  public RedAstronaut(String name, int susLevel, String skill) {
    super(name, susLevel);
    this.skill = skill.toLowerCase();
  }

  public RedAstronaut(String name) {
    super(name, DEFAULT_SUS_LEVEL);
    this.skill = DEFAULT_EXP;
  }

  @Override
  public void emergencyMeeting() {
    if (this.isFrozen()) {
      // System.out.println("This player is frozen and cannot call emergencyMeeting!");
      return;
    }
    Player[] players = getPlayers();
    // get the size of the array: find out how many are not frozen
    int notFrozen = 0;
    for (Player player : players) {
      if (!(player.isFrozen())) {
        notFrozen++;
      }
    }
    // create new array of length notFrozen
    Player[] notFrozenPlayers = new Player[notFrozen];
    int index = 0;
    // need to reuse abit of code but necessary because we need to specify the length of the array first
    for (Player player : players) {
      if (!(player.isFrozen())) {
        notFrozenPlayers[index] = player;
        index++;
      }
    }
    // ascending order
    Arrays.sort(notFrozenPlayers);
    Player highestSusPlayer = notFrozenPlayers[notFrozenPlayers.length - 1];
    Player secondHighestSusPlayer = notFrozenPlayers[notFrozenPlayers.length - 2];
    // if the highestSusLevel belongs to the Impostor calling this emergencyMeeting, replace highestSusLevel with the next highest value i.e. 1 index down. It may be the same value but it will be valid cos it no longer belongs to the Impostor calling this emergencyMeeting
    if (highestSusPlayer.equals(this)) {
      // check if this second highest susLevel is repeated even just once
      if (secondHighestSusPlayer.compareTo(notFrozenPlayers[notFrozenPlayers.length - 3]) == 0) {
        return;
      }
      else {
        // freeze the second highest suslevel
        secondHighestSusPlayer.setFrozen(true);
        // check gameover
        this.gameOver();
      }
    }
    else {
      // if the highestSusLevelPlayer is not this Impostor
      if (highestSusPlayer.compareTo(secondHighestSusPlayer) == 0) {
        return;
      }
      else {
        highestSusPlayer.setFrozen(true);
        this.gameOver();
      }
    }
  }
  
  public void freeze(Player p) {
      if (p instanceof Impostor || p.isFrozen() || this.isFrozen()) {
        // System.out.println("Player you're trying to freeze is Impostor or already frozen or you are frozen!");
        return;
      } 
      if (this.getSusLevel() < p.getSusLevel()) {
        p.setFrozen(true);
        this.gameOver();
      }
      else {
        this.setSusLevel(this.getSusLevel() * 2);
      }
  }

  public void sabotage(Player p) {
    if (p instanceof Impostor || p.isFrozen() || this.isFrozen()) {
      // System.out.println("Can't sabotage an Impostor or frozen or you are frozen");
      return;
    }
    if (this.getSusLevel() < 20) {
      p.setSusLevel((int) (p.getSusLevel() * 1.5));
    }
    else {
      p.setSusLevel((int) (p.getSusLevel() * 1.25));
    }
  }

  public boolean equals(Object o) {
        if (o instanceof RedAstronaut) {
        RedAstronaut player = (RedAstronaut) o;
        return this.getName().equals(player.getName()) && this.isFrozen() == player.isFrozen() && this.getSusLevel() == player.getSusLevel() && this.skill == player.getSkill();
    }
    return false;
  }
  
  @Override
  public String toString() {
      String frozenString = this.isFrozen() ? "frozen" : "not frozen";
      String outpuString = "My name is " + this.getName() + ", and I have a susLevel of " + this.getSusLevel() + ". I am currently " + frozenString + "." + " I am an " + this.skill + " player!";
      if (this.getSusLevel() > 15) {
        return outpuString.toUpperCase();
      }
      return outpuString;
  }

  public String getSkill() {
    return this.skill;
  }

  public void setSkill(String skill) {
    this.skill = skill.toLowerCase();
  }
}