import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainBlooketConsoleQuiz {

    private static Scanner input = new Scanner(System.in);
    private static Random random = new Random();
    
    private static List<Question> questionBank;
    private static List<ChoiceEffect> allChoiceEffects; 
    private static double gold; 

    public static void main(String[] args) {
        
        initializeGameData(); 
        
        String playAgain;

        System.out.println("Welcome to the Better Version of Blooket!!!");
        System.out.println("Answer questions to earn gold, then pick a bonus! :)");
        System.out.println("------------------------------------");

        do {
            gold = 500.0;
            
            
            Collections.shuffle(questionBank); 
            
            int currentQuestionNumber = 0;

            for (Question q : questionBank) {
                currentQuestionNumber++;
                System.out.println("\n--- Current Gold: " + String.format("%.2f", gold) + " ---"); 
                System.out.println("Question " + currentQuestionNumber + ": " + q.getQuestionText());

                
                List<String> choices = q.getAnswerChoices();
                char choicePrefix = 'A';
                for (String choice : choices) {
                    System.out.println(choicePrefix + ". " + choice);
                    choicePrefix++;
                }

                System.out.print("Enter your answer (e.g., 'A', 'B', 'C', or 'D'): ");
                String userAnswerChar = input.nextLine().toUpperCase();

                String actualUserAnswer = "";
                int choiceIndex = userAnswerChar.charAt(0) - 'A';
                if (choiceIndex >= 0 && choiceIndex < choices.size()) {
                    actualUserAnswer = choices.get(choiceIndex);
                }

                if (q.isCorrect(actualUserAnswer)) {
                    System.out.println("Correct!");
                    gold += 100.0;
                    System.out.println("You earned 100 Gold! Current Gold: " + String.format("%.2f", gold));
                    
                    handleBonusChoice();
                } else {
                    System.out.println("Incorrect. The correct answer was: " + q.getCorrectAnswer());
                    gold -= 50.0;
                    System.out.println("You lost 50 Gold! Current Gold: " + String.format("%.2f", gold));
                    if (gold < 0) {
                        gold = 0;
                        System.out.println("Your gold cannot go below zero!");
                    }
                }
            }

            System.out.println("--- Game Over ---");
            System.out.println("Your final gold is: " + String.format("%.2f", gold));
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = input.nextLine();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thank you for playing!");
        input.close(); 
    }


    private static void initializeGameData() {
        questionBank = new ArrayList<>();
        allChoiceEffects = new ArrayList<>();

        // Algebra Questions (1-15)
        questionBank.add(new Question(
            "If 3x + 5 = 17, what is the value of x?",
            Arrays.asList("4", "12", "6", "5"),
            "4"
        ));
        questionBank.add(new Question(
            "Solve for x: 2(x - 3) = 10",
            Arrays.asList("8", "5", "6", "13"),
            "8"
        ));
        questionBank.add(new Question(
            "If 5x - 7 = 23, what is x ?",
            Arrays.asList("6", "3", "7", "4"),
            "6"
        ));
        questionBank.add(new Question(
            "What are the solutions to the equation x^2 - 4x + 3 = 0?",
            Arrays.asList("1 and 3", "-1 and -3", "2 and -2", "1 and -3"),
            "1 and 3"
        ));
        questionBank.add(new Question(
            "Solve the inequality 2x + 1 > 7",
            Arrays.asList("x > 3", "x < 3", "x > 4", "x < 4"),
            "x > 3"
        ));
        questionBank.add(new Question(
            "If a line passes through the points (2, 5) and (4, 9), what is its slope?",
            Arrays.asList("2", "4", "1", "3"),
            "2"
        ));
        questionBank.add(new Question(
            "What is the value of x in the equation \\frac{x}{2} + 4 = 10?",
            Arrays.asList("12", "3", "6", "8"),
            "12"
        ));
        questionBank.add(new Question(
            "Simplify the expression 3x + 2y - x + 5y",
            Arrays.asList("2x + 7y", "4x + 7y", "2x + 3y", "4x + 3y"),
            "2x + 7y"
        ));
        questionBank.add(new Question(
            "If f(x) = x^2 - 3, what is f(4)?",
            Arrays.asList("13", "1", "16", "7"),
            "13"
        ));
        questionBank.add(new Question(
            "Factor the expression x^2 - 9",
            Arrays.asList("(x-3)(x+3)", "(x-3)^2", "(x-9)(x+9)", "(x+3)^2"),
            "(x-3)(x+3)"
        ));
        questionBank.add(new Question(
            "Solve for x: 3x - 8 = 10 - 3x",
            Arrays.asList("3", "6", "18", "1"),
            "3"
        ));
        questionBank.add(new Question(
            "What is the vertex of the parabola y = (x-2)^2 + 3?",
            Arrays.asList("(2, 3)", "(-2, 3)", "(2, -3)", "(-2, -3)"),
            "(2, 3)"
        ));
        questionBank.add(new Question(
            "If a = 5 and b = -2, what is the value of a^2 + 2b?",
            Arrays.asList("21", "29", "25", "23"),
            "21"
        ));
        questionBank.add(new Question(
            "What is the slope of the line perpendicular to y = 3x + 4?",
            Arrays.asList("-\\frac{1}{3}", "3", "1", "-\\frac{1}{4}"),
            "-\\frac{1}{3}"
        ));
        questionBank.add(new Question(
            "Which of the following is an equivalent expression to x^5 \\cdot x^3?",
            Arrays.asList("x^8", "x^{15}", "2x^8", "x^2"),
            "x^8"
        ));

        // Basic Java Questions (16-30)
        questionBank.add(new Question(
            "Which of the following is a primitive data type in Java?",
            Arrays.asList("int", "String", "ArrayList", "Scanner"),
            "int"
        ));
        questionBank.add(new Question(
            "How do you declare an integer variable named 'score' and initialize it to 0?",
            Arrays.asList("int score = 0;", "score = 0;", "integer score = 0;", "declare int score = 0;"),
            "int score = 0;"
        ));
        questionBank.add(new Question(
            "What is the output of `System.out.println(10 / 3);`?",
            Arrays.asList("3", "3.333...", "3.0", "3.3"),
            "3"
        ));
        questionBank.add(new Question(
            "Which keyword is used to create a subclass in Java?",
            Arrays.asList("extends", "implements", "inherits", "subclass"),
            "extends"
        ));
        questionBank.add(new Question(
            "What is the correct syntax for a `for` loop that runs 5 times?",
            Arrays.asList("for (int i = 0; i < 5; i++)", "for (i = 0; i <= 5; i++)", "for (int i = 5; i > 0; i--)", "for (i = 1; i < 5; i++)"),
            "for (int i = 0; i < 5; i++)"
        ));
        questionBank.add(new Question(
            "What is the result of the expression `5 == 5`?",
            Arrays.asList("true", "false", "5", "Error"),
            "true"
        ));
        questionBank.add(new Question(
            "What is the purpose of the `main` method in a Java program?",
            Arrays.asList("It is the entry point of the program.", "It is used for creating new objects.", "It defines class variables.", "It handles exceptions."),
            "It is the entry point of the program."
        ));
        questionBank.add(new Question(
            "Which of the following is NOT a valid Java variable name?",
            Arrays.asList("1st_name", "myVariable", "user_name", "firstName"),
            "1st_name"
        ));
        questionBank.add(new Question(
            "What is the output of `System.out.println(\"Hello\" + 10);`?",
            Arrays.asList("Hello10", "Hello", "10", "Error"),
            "Hello10"
        ));
        questionBank.add(new Question(
            "Which access modifier makes a variable accessible only within its own class?",
            Arrays.asList("private", "public", "protected", "default"),
            "private"
        ));
        questionBank.add(new Question(
            "How do you create an object of a class named 'Car'?",
            Arrays.asList("Car myCar = new Car();", "new Car myCar;", "create Car myCar;", "Car myCar = Car.create();"),
            "Car myCar = new Car();"
        ));
        questionBank.add(new Question(
            "What is the keyword used to define a constant variable in Java?",
            Arrays.asList("final", "const", "static", "constant"),
            "final"
        ));
        questionBank.add(new Question(
            "What does the `else` block do in an `if-else` statement?",
            Arrays.asList("It executes when the `if` condition is false.", "It executes regardless of the `if` condition.", "It is always executed before the `if` block.", "It is used for error handling."),
            "It executes when the `if` condition is false."
        ));
        questionBank.add(new Question(
            "How do you get the length of a string named `myString`?",
            Arrays.asList("myString.length()", "myString.length", "length(myString)", "myString.size()"),
            "myString.length()"
        ));
        questionBank.add(new Question(
            "What is an `interface` in Java?",
            Arrays.asList("A blueprint of a class.", "A type of variable.", "A data structure.", "A special type of loop."),
            "A blueprint of a class."
        ));

        // Choice Effects
        allChoiceEffects.add(new ChoiceEffect("Bonus", ChoiceEffect.EffectType.PERCENTAGE_MULTIPLIER, 2.0));
        allChoiceEffects.add(new ChoiceEffect("Bonus", ChoiceEffect.EffectType.PERCENTAGE_MULTIPLIER, 3.0));
        allChoiceEffects.add(new ChoiceEffect("Bonus", ChoiceEffect.EffectType.PERCENTAGE_MULTIPLIER, 0.01)); 
        allChoiceEffects.add(new ChoiceEffect("Bonus", ChoiceEffect.EffectType.PERCENTAGE_MULTIPLIER, 0.8));
        allChoiceEffects.add(new ChoiceEffect("Bonus", ChoiceEffect.EffectType.PERCENTAGE_MULTIPLIER, 0.5));
        allChoiceEffects.add(new ChoiceEffect("Bonus", ChoiceEffect.EffectType.FLAT_AMOUNT, 250.0));
        allChoiceEffects.add(new ChoiceEffect("Bonus", ChoiceEffect.EffectType.FLAT_AMOUNT, 10.0));
        allChoiceEffects.add(new ChoiceEffect("Bonus", ChoiceEffect.EffectType.FLAT_AMOUNT, 500.0));
        allChoiceEffects.add(new ChoiceEffect("Bonus", ChoiceEffect.EffectType.FLAT_AMOUNT, -100.0));
        allChoiceEffects.add(new ChoiceEffect("Bonus", ChoiceEffect.EffectType.FLAT_AMOUNT, -200.0));
        allChoiceEffects.add(new ChoiceEffect("Bonus", ChoiceEffect.EffectType.FLAT_AMOUNT, 0.0));
    }


    private static void handleBonusChoice() {
        System.out.println("--- You got it right! Choose your bonus! ---");

        List<ChoiceEffect> availableChoices = new ArrayList<>(allChoiceEffects);
        Collections.shuffle(availableChoices);

        List<ChoiceEffect> selectedBonusOptions = new ArrayList<>();

        for (int i = 0; i < 3 && i < availableChoices.size(); i++) {
            selectedBonusOptions.add(availableChoices.get(i));
        }

        for (int i = 0; i < selectedBonusOptions.size(); i++) {
            System.out.println((i + 1) + ". " + selectedBonusOptions.get(i).getDescription());
        }

        int choiceNumber = -1;
        while (choiceNumber < 1 || choiceNumber > selectedBonusOptions.size()) {
            System.out.print("Enter your choice (1, 2, or 3): ");
            try {
                choiceNumber = Integer.parseInt(input.nextLine());
                if (choiceNumber < 1 || choiceNumber > selectedBonusOptions.size()) {
                    System.out.println("Invalid choice. Please enter 1, 2, or " + selectedBonusOptions.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        ChoiceEffect chosenEffect = selectedBonusOptions.get(choiceNumber - 1); 
        double oldGold = gold;
        gold = chosenEffect.applyEffect(gold);

        System.out.println("You chose: " + chosenEffect.getDescription() + "!");
        System.out.println("Your gold changed from " + String.format("%.2f", oldGold) + " to " + String.format("%.2f", gold) + ".");
        
        if (gold < 0) {
            gold = 0;
            System.out.println("Your gold cannot go below zero!");
        }
    }
}

class Question {
    private String questionText;
    private List<String> answerChoices;
    private String correctAnswer;


    public Question(String questionText, List<String> answerChoices, String correctAnswer) {
        this.questionText = questionText;

        this.answerChoices = new ArrayList<>(answerChoices);
        this.correctAnswer = correctAnswer;
        Collections.shuffle(this.answerChoices);
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswerChoices() {
        return new ArrayList<>(answerChoices);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }


    public boolean isCorrect(String selectedAnswer) {
        return correctAnswer.trim().equalsIgnoreCase(selectedAnswer.trim());
    }
}


class ChoiceEffect {
    public enum EffectType {
        PERCENTAGE_MULTIPLIER,
        FLAT_AMOUNT
    }

    private String description;
    private EffectType type;
    private double value;

    public ChoiceEffect(String description, EffectType type, double value) {
        this.description = description;
        this.type = type;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public EffectType getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public double applyEffect(double currentGold) {
        if (type == EffectType.PERCENTAGE_MULTIPLIER) {
            return currentGold * value;
        } else {
            return currentGold + value;
        }
    }
}