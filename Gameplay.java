public class Gameplay {
  public static void main(String[] args) {
    BlueAstronaut bob = new BlueAstronaut("Bob", 20, 6, 30);
    BlueAstronaut heath = new BlueAstronaut("Heath", 30, 3, 21);
    BlueAstronaut albert = new BlueAstronaut("Albert", 44, 2, 0);
    BlueAstronaut angel = new BlueAstronaut("Angel", 0, 1, 0);

    RedAstronaut liam = new RedAstronaut("Liam", 19, "experienced");
    RedAstronaut susPerson = new RedAstronaut("Suspicious Person", 100, "expert");

    System.out.println("1");
    liam.sabotage(bob);
    System.out.println(bob);
    System.out.println();
    
    System.out.println("2");
    liam.freeze(susPerson);
    System.out.println(susPerson);
    System.out.println();
    
    System.out.println("3");
    liam.freeze(albert);
    System.out.println(albert);
    System.out.println(liam);
    System.out.println();
    
    System.out.println("4");
    albert.emergencyMeeting();
    System.out.println();
    
    System.out.println("5");
    susPerson.emergencyMeeting();
    System.out.println(bob);
    System.out.println(heath);
    System.out.println();
    
    System.out.println("6");
    bob.emergencyMeeting();
    System.out.println(susPerson);
    System.out.println();
    
    System.out.println("7");
    heath.completeTask();
    System.out.println(heath);
    System.out.println();
    
    System.out.println("8");
    heath.completeTask();
    System.out.println(heath);
    System.out.println();
    
    System.out.println("9");
    heath.completeTask();
    System.out.println(heath);
    System.err.println();

    System.out.println("10");
    liam.freeze(angel);
    System.out.println(angel);
    System.out.println(liam);
    System.err.println();

    System.out.println("11");
    liam.sabotage(bob);
    System.out.println(bob);
    liam.sabotage(bob);
    System.out.println(bob);
    System.out.println(liam);
    System.err.println();

    System.out.println("12");
    liam.freeze(bob);
    System.out.println(bob);
    System.err.println();
    
    // System.out.println("13");
    // angel.emergencyMeeting();
    // System.out.println(liam);
    // System.err.println();
    
    System.out.println("14");
    for (int i = 0; i < 5; i++) {
      liam.sabotage(heath);
      System.out.println(heath);
    }
    System.err.println();

    System.out.println("15");
    liam.freeze(heath);

  }
}