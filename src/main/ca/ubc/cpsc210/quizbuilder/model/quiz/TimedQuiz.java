package ca.ubc.cpsc210.quizbuilder.model.quiz;

import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;

/**
 * Represents a Quiz that enforces a time constraint -- the quiz is initializes
 * with the number of seconds a user is allowed for the quiz. The quiz ends if
 * the user takes longer than this time to complete the quiz.
 */
public class TimedQuiz extends Quiz {
    private long quizStartTime;
    private double numSeconds;

    // EFFECTS: construct quiz with given list of questions and timed to last
    // a maximum of numSeconds seconds.
    public TimedQuiz(QuestionsList questions, double numSeconds) {
        super(questions);
        this.numSeconds = numSeconds;
    }

    // MODIFIES: this
    // EFFECTS: starts the quiz and sets time that quiz starts
    public void startQuiz() {
        super.startQuiz();
        quizStartTime = System.nanoTime();
    }

    // REQUIRES: !isOutOfTime() && !isOutOfTries()
    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string
    // that specifies time remaining
    @Override
    public String submitAnswer(String answer) {
        boolean isCorrect = checkAnswer(answer);
        return "Your answer is " + (isCorrect ? "" : "not ") + "correct\n"
                + "Time remaining: " + timeLeftInSeconds() + " seconds\n";
    }

    @Override
    public boolean isOutOfTime() {
        return timeLeftInSeconds() <= 0;
    }

    // EFFECTS: returns time left in this quiz in seconds
    private double timeLeftInSeconds() {
        long curTime = System.nanoTime();
        double elapsedSeconds = (curTime - quizStartTime) / 1000000000.0;
        return Math.max(numSeconds - elapsedSeconds, 0);
    }
}
