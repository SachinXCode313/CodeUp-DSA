import java.util.Scanner;

class Question extends Thread {
    final String question;
    private final String[] options;
    private final String correctAnswer;
    final int questionNumber;
    private String userAnswer;
    private long timeTaken;
    private boolean answered;
    private boolean isCorrect;

    public Question(int questionNumber, String question, String[] options, String correctAnswer) {
        this.questionNumber = questionNumber;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.answered = false;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Question " + questionNumber + ": " + question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        long startTime = System.currentTimeMillis();

        // Start a timer thread that interrupts this question after 10 seconds
        Thread timerThread = new Thread(() -> {
            try {
                Thread.sleep(10000); // 10 seconds timer
                if (!answered) {
                    System.out.println("Time's up for Question " + questionNumber + "!\n");
                    interrupt();
                }
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
            }
        });

        timerThread.start();

        try {
            System.out.print("Your answer (1-4): ");
            String userInput = scanner.nextLine();

            if (userInput.matches("[1-4]")) {
                int answerIndex = Integer.parseInt(userInput) - 1;
                userAnswer = options[answerIndex];
                answered = true;
                isCorrect = userAnswer.equalsIgnoreCase(correctAnswer);
            } else {
                userAnswer = "Invalid answer";
                System.out.println("Invalid answer. Please enter a number between 1 and 4.\n");
            }
        } catch (Exception e) {
            System.out.println("No answer provided.");
        }

        timeTaken = System.currentTimeMillis() - startTime;
        timerThread.interrupt();

        if (!answered) {
            userAnswer = "No answer";
        }
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}