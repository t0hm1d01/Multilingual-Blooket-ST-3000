import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions {
    private String questionText;
    private List<String> answerChoices;
    private String correctAnswer;


    public Questions(String questionText, List<String> answerChoices, String correctAnswer) {
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