import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(1, "What is the capital of France?",
                new String[]{"Paris", "London", "Berlin", "Madrid"}, "Paris"));
        questions.add(new Question(2, "What is 2 + 2?",
                new String[]{"3", "4", "5", "6"}, "4"));
        questions.add(new Question(3, "Who wrote 'To Kill a Mockingbird'?",
                new String[]{"Harper Lee", "Mark Twain", "F. Scott Fitzgerald", "Ernest Hemingway"}, "Harper Lee"));

        for (Question question : questions) {
            question.start();
            question.join();
        }

        System.out.println("\nQuiz Summary:");
        for (Question question : questions) {
            System.out.println("Question " + question.questionNumber + ": " + question.question);
            System.out.println("Your answer: " + question.getUserAnswer() + "  -> " + (question.isCorrect() ? "Correct" : "Incorrect"));
            System.out.println("Time taken: " + question.getTimeTaken() / 1000.0 + " seconds");
            System.out.println();
        }
    }
}
