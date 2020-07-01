import java.util.Scanner;

public class MMGame
{
  private MasterMind mm;
  private Scanner scanner;

  public MMGame() {
    mm = new MasterMind();
  }

  public void startRunning() {
    instructions();
    
    scanner = new Scanner(System.in);
    while (true) {
      String userGuess = getUserGuess();
      String codeHint = mm.checkGuess(userGuess);
      displayHint(codeHint);

      if (mm.correctGuess(userGuess) || mm.attemptsMaxed()) {
        break;
      }
    }
    scanner.close();

    displayResults();
  }

  private void displayResults() {
    System.out.println();
    if (mm.attemptsMaxed()) {
      System.out.println("Game Over!");
    }
    else {
      System.out.println("You Win!");
    }
  }

  private String getUserGuess() {
    System.out.println("Enter your guess: ");
    String userGuess;
    while (true) {
      userGuess = scanner.nextLine();
      if (mm.validGuess(userGuess)) {
        break;
      }
      else {
        System.out.println("Invalid guess - try again: ");
      }
    }
    return userGuess;
  }

  private void instructions() {
    System.out.println("----- Welcome to MasterMind! -----");
    System.out.println("Guess the 4-Character Secret Code!");
    System.out.println("The Secret Code will be made from:");
    System.out.println("\tR (Red), G (Green), B (Blue),");
    System.out.println("\tY (Yellow), O (Orange), P (Purple),");
    System.out.println("\tK (Black), W (White), or ' ' (Blank)");
    System.out.println("It may or may not contain duplicates.");
    System.out.println("You will get 12 Attempts to Guess it.");
    System.out.println("Each time you will get a hint like:");
    System.out.println("\tb (black) for correct in exact spot");
    System.out.println("\tw (white) for correct in wrong spot");
    System.out.println("\t- (blank) for not correct at all");
    System.out.println("The hint will not be in any order");
    System.out.println("");
  }

  private void displayHint(String codeHint) {
    System.out.println(codeHint);
  }
}
/*
R Red
G Green
B Blue
Y Yellow
O Orange
P Purple
K Black
W White
  Blank

X Secret
b Hint Exact
w Hint Partial
- Hint None
*/