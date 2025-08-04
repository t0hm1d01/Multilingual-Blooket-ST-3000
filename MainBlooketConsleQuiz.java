import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import blooketconsolequiz.ChoiceEffect.EffectType;

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
                System.out.println("\n--- Current Gold: " + String.format("%.2f", gold) + " ---"); // Display gold
                System.out.println("Question " + currentQuestionNumber + ": " + q.getQuestionText());

                // Display choices with A, B, C, D prefix
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
        questionBank.add(new Question(
            "What is the capital of France?",
            Arrays.asList("Paris", "Berlin", "Madrid", "Rome"),
            "Paris"
        ));
        questionBank.add(new Question(
            "Which planet is known as the 'Red Planet'?",
            Arrays.asList("Mars", "Earth", "Jupiter", "Venus"),
            "Mars"
        ));
        questionBank.add(new Question(
            "What is 7 + 8?",
            Arrays.asList("15", "12", "16", "14"),
            "15"
        ));
        questionBank.add(new Question(
            "Who painted the Mona Lisa?",
            Arrays.asList("Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Claude Monet"),
            "Leonardo da Vinci"
        ));
        questionBank.add(new Question(
            "What is the largest ocean on Earth?",
            Arrays.asList("Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"),
            "Pacific Ocean"
        ));
        questionBank.add(new Question(
            "What is the chemical symbol for water?",
            Arrays.asList("H2O", "CO2", "O2", "N2"),
            "H2O"
        ));
        questionBank.add(new Question(
            "What is the fastest land animal?",
            Arrays.asList("Cheetah", "Lion", "Gazelle", "Horse"),
            "Cheetah"
        ));
        questionBank.add(new Question(
            "How many continents are there?",
            Arrays.asList("7", "5", "6", "8"),
            "7"
        ));
        questionBank.add(new Question(
            "What is the square root of 81?",
            Arrays.asList("9", "8", "7", "10"),
            "9"
        ));
        questionBank.add(new Question(
            "Which gas do plants absorb from the atmosphere?",
            Arrays.asList("Carbon Dioxide", "Oxygen", "Nitrogen", "Hydrogen"),
            "Carbon Dioxide"
        ));


        allChoiceEffects = new ArrayList<>();
        allChoiceEffects.add(new ChoiceEffect("+200% Gold", EffectType.PERCENTAGE_MULTIPLIER, 3.0));
        allChoiceEffects.add(new ChoiceEffect("+300% Gold", EffectType.PERCENTAGE_MULTIPLIER, 4.0));
        allChoiceEffects.add(new ChoiceEffect("-99% Gold", EffectType.PERCENTAGE_MULTIPLIER, 0.01)); 
        allChoiceEffects.add(new ChoiceEffect("-20% Gold", EffectType.PERCENTAGE_MULTIPLIER, 0.8));
        allChoiceEffects.add(new ChoiceEffect("-50% Gold", EffectType.PERCENTAGE_MULTIPLIER, 0.5));
        allChoiceEffects.add(new ChoiceEffect("+250 Gold", EffectType.FLAT_AMOUNT, 250.0));
        allChoiceEffects.add(new ChoiceEffect("+10 Gold", EffectType.FLAT_AMOUNT, 10.0));
        allChoiceEffects.add(new ChoiceEffect("+500 Gold", EffectType.FLAT_AMOUNT, 500.0));
        allChoiceEffects.add(new ChoiceEffect("-100 Gold", EffectType.FLAT_AMOUNT, -100.0));
        allChoiceEffects.add(new ChoiceEffect("-200 Gold", EffectType.FLAT_AMOUNT, -200.0));
        allChoiceEffects.add(new ChoiceEffect("No Change", EffectType.FLAT_AMOUNT, 0.0));
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