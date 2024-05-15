import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate {
  private int numTasks;
  private int taskSpeed;

  public static final int DEFAULT_SUS_LEVEL = 15;
  public static final int DEFAULT_NUM_TASKS = 6;
  public static final int DEFAULT_TASK_SPEED = 10;

  public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
    super(name, susLevel);
    this.numTasks = numTasks;
    this.taskSpeed = taskSpeed;
  }
  public BlueAstronaut(String name) {
    super(name, DEFAULT_SUS_LEVEL);
    this.numTasks = DEFAULT_NUM_TASKS;
    this.taskSpeed = DEFAULT_TASK_SPEED;
  }

  @Override
  public void emergencyMeeting() {
    if (this.isFrozen()) {
      // System.out.println("This player is frozen and cannot call emergencyMeeting!");
      return;
    }
    Player[] players = getPlayers();
    int notFrozen = 0;
    for (Player player : players) {
      if (!(player.isFrozen())) {
        notFrozen++;
      }
    }
    Player[] notFrozenPlayers = new Player[notFrozen];
    int index = 0;
    for (Player player : players) {
      if (!(player.isFrozen())) {
        notFrozenPlayers[index] = player;
        index++;
      }
    }
    Arrays.sort(notFrozenPlayers);
    Player highestSus = notFrozenPlayers[notFrozenPlayers.length - 1];
    Player secondHighestSus = notFrozenPlayers[notFrozenPlayers.length - 2];
    if (highestSus.compareTo(secondHighestSus) == 0) {
      // System.out.println("There are two or more players with the highest susLevel!");
      return;
    } 
    else {
      highestSus.setFrozen(true);
      this.gameOver();
      // System.out.println("Frozen name" + highestSus.getName() + " is frozen? " + highestSus.isFrozen());
    }
  }

  public void completeTask() {
    if (this.isFrozen()) {
      // System.out.println("Frozen! Can't complete task");
      return;
    }
    // if numTasks is already 0, return
    if (numTasks == 0) {
      return;
    }
    this.numTasks -= (this.taskSpeed > 20) ? 2 : 1;
    if (numTasks < 0) {
      this.numTasks = 0;
    }
    if (numTasks == 0) {
      System.out.println("I have completed all my tasks");
      this.setSusLevel((int) (this.getSusLevel() * 0.5));
    }
  }

  public boolean equals(Object o) {
      if (o instanceof BlueAstronaut) {
      BlueAstronaut player = (BlueAstronaut) o;
      return this.getName().equals(player.getName()) && this.isFrozen() == player.isFrozen() && this.getSusLevel() == player.getSusLevel() && this.numTasks == player.getNumTasks() && this.taskSpeed == player.getTaskSpeed();
    }
    return false;
  }

  @Override
  public String toString() {
      String frozenString = this.isFrozen() ? "frozen" : "not frozen";
      String outputString = "My name is " + this.getName() + ", and I have a susLevel of " + this.getSusLevel() + ". I am currently " + frozenString + "." + " I have " + this.numTasks + " leftover.";
      if (this.getSusLevel() > 15) {
        return outputString.toUpperCase();
      } 
      return outputString;
  }

  public int getNumTasks() {
    return this.numTasks;
  }

  public int getTaskSpeed() {
    return this.taskSpeed;
  }

  public void setNumTasks(int numTasks) {
    this.numTasks = numTasks;
  }

  public void setTaskSpeed(int taskSpeed) {
    this.taskSpeed = taskSpeed;
  }
}