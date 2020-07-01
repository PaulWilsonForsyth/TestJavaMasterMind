import java.util.ArrayList;

public class MasterMind
{
  private int secretLength;
  private int maxAttempts;
  private String validLetters;
  private String secretCode;
  private ArrayList<String> guessHistory;
  private ArrayList<String> hintHistory;

  public MasterMind() {
    secretLength = 4; // TODO Option
    maxAttempts = 12; // TODO Option
    validLetters = "RGBYOPKW ".toUpperCase(); // TODO Option
    secretCode = generateSecret();
    guessHistory = new ArrayList<String>();
    hintHistory = new ArrayList<String>();
  }

  public String checkGuess(String userGuess) {
    if (!validGuess(userGuess)) {
      // TODO throw new Exception("ERROR: Invalid Guess Submitted");
    }

    String newHint = calculateHint(userGuess);
    guessHistory.add(userGuess);
    hintHistory.add(newHint);
    return newHint;
  }

  public boolean validGuess(String userGuess) {
    if (userGuess.length() != secretLength) {
      return false;
    }
    for (int index = 0; index < secretLength; index++) {
      String letter = userGuess.substring(index, index + 1);
      if (validLetters.indexOf(letter.toUpperCase()) < 0) {
        return false;
      }
    }
    return true;
  }

  public boolean correctGuess(String userGuess) {
    if (userGuess.equalsIgnoreCase(secretCode)) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean attemptsMaxed() {
    if (guessHistory.size() >= maxAttempts) {
      return true;
    }
    else {
      return false;
    }
  }

  private String calculateHint(String userGuess) {
    int countExact = 0;
    int countPartial = 0;

    String codeHint = "";
    String codeLeft = secretCode;
    for (int index = 0; index < secretLength; index++) {
      String guessLetter = userGuess.substring(index, index + 1);
      String codeLetter = secretCode.substring(index, index + 1);
      if (guessLetter.equalsIgnoreCase(codeLetter)) {
        countExact++;
        codeHint += "!";
        codeLeft = codeLeft.substring(0, index) + "!"
          + codeLeft.substring(index + 1);
      }
      else {
        codeHint += "-";
      }
    }

    for (int index = 0; index < secretLength; index++) {
      String guessLetter = userGuess.substring(index, index + 1);
      String indexHint = codeHint.substring(index, index + 1);
      if (!indexHint.equals("!")) {
        int position = codeLeft.indexOf(guessLetter.toUpperCase());
        if (position >= 0) {
          countPartial++;
          codeHint = codeHint.substring(0, index) + "?"
            + codeHint.substring(index + 1);
          codeLeft = codeLeft.substring(0, position) + "?"
            + codeLeft.substring(position + 1);
        }
      }
    }

    String jumbleHint = "";
    for (int index = 0; index < countExact; index++) {
      jumbleHint += "b";
    }
    for (int index = 0; index < countPartial; index++) {
      jumbleHint += "w";
    }
    while (jumbleHint.length() < secretLength) {
      jumbleHint += "-";
    }

    return jumbleHint; 
  }

  private String generateSecret() {
    String newSecret = "";
    for (int index = 0; index < secretLength; index++) {
      int randomPosition = (int) (validLetters.length() * Math.random());
      String randomLetter = validLetters.substring(randomPosition, randomPosition + 1);
      newSecret += randomLetter;
    }
    return newSecret;
  }
}