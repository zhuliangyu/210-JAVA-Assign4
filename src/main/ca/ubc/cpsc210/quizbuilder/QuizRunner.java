package ca.ubc.cpsc210.quizbuilder;

import ca.ubc.cpsc210.quizbuilder.model.question.Question;
import ca.ubc.cpsc210.quizbuilder.model.question.TrueFalseQuestion;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.RandomizedQuestionsList;
import ca.ubc.cpsc210.quizbuilder.model.quiz.EachAnswerMustBeRightQuiz;
import ca.ubc.cpsc210.quizbuilder.model.quiz.Quiz;
import ca.ubc.cpsc210.quizbuilder.model.quiz.TimedQuiz;

import java.util.Scanner;

/**
 * The class that orchestrates the running of the quiz.
 * <p>
 * Run this class as a "Java Application" and follow instructions on the
 * console.
 */
public class QuizRunner {

    // EFFECTS: returns a list of questions
    public QuestionsList getQuestions() {
        this.print("Welcome to the quiz!\n");

        Question q1 = null;
        Question q2 = null;
        Question q3 = null;

        q1 = new TrueFalseQuestion(8, "You are awesome.", true);
        q2 = new TrueFalseQuestion(8, "Donuts are bad for you.", true);
        // Un-comment next line to test MultiplicationQuestion:
        // q3 = new MultiplicationQuestion(8, 5, 2);

        QuestionsList qnList = new RandomizedQuestionsList();
        qnList.addQuestion(q1);
        qnList.addQuestion(q2);
        // Un-comment next line to test MultiplicationQuestion:
        // qnList.addQuestion(q3);
        return qnList;
    }

    // EFFECTS: prints s to standard out.
    public void print(String s) {
        if (s != null) {
            System.out.println(s);
        }
    }

    // EFFECTS: reads a line of text from standard input and returns it
    private String getUserResponse() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }


    public static void main(String[] args) {
        QuizRunner quizRunner = new QuizRunner();
        QuestionsList qnList = quizRunner.getQuestions();

        // Build the right quiz based on user's input.
        Quiz quiz = buildQuiz(quizRunner, qnList);

        // Generic quiz runner:
        runQuiz(quizRunner, quiz);

        String endOfQuizString = quiz.endQuiz();
        quizRunner.print(endOfQuizString);
        quizRunner.print("\nThanks for taking the quiz!\n");
    }

    // EFFECTS: returns quiz built from questions in qnList
    private static Quiz buildQuiz(QuizRunner quizRunner, QuestionsList qnList) {
        Quiz quiz = null;

        do {
            showMenu(quizRunner);

            String qnType = quizRunner.getUserResponse();
            if (qnType.equals("1")) {
                quiz = new EachAnswerMustBeRightQuiz(qnList);
            } else if (qnType.equals("2")) {
                // Un-comment next line to interactively test DecrementMarksQuiz:
                // quiz = new DecrementMarksQuiz(qnList);
            } else if (qnType.equals("3")) {
                int numSeconds = getTimeLimitForQuiz(quizRunner);
                quiz = new TimedQuiz(qnList, numSeconds);
            }
        } while (quiz == null);
        return quiz;
    }

    // EFFECTS: prompts user for time limit for quiz in seconds until user enters
    // a positive value, then returns it
    private static int getTimeLimitForQuiz(QuizRunner quizRunner) {
        int numSeconds = 0;
        do {
            quizRunner.print("Enter number of seconds for the quiz:");
            Scanner in = new Scanner(System.in);
            numSeconds = in.nextInt();
        } while (numSeconds <= 0);
        return numSeconds;
    }

    // EFFECTS: displays menu of options to user
    private static void showMenu(QuizRunner quizRunner) {
        quizRunner.print("Enter a number for the type of quiz:");
        quizRunner.print("1 : Each answer must be right quiz.");
        // Un-comment the next line to interactively test DecrementMarksQuiz:
        // quizRunner.print("2 : Decrement marks quiz.");
        quizRunner.print("3 : A quiz with a timer.");
        quizRunner.print("Your response: ");
    }

    // EFFECTS: runs the given quiz
    private static void runQuiz(QuizRunner quizRunner, Quiz quiz) {
        quiz.startQuiz();
        while (quiz.anymoreQuestions()) {
            Question q = quiz.getNextQuestion();
            askQuizQuestion(quizRunner, quiz, q);
        }
    }

    // EFFECTS: ask user a quiz question, process answer, allow retries as appropriate to type of quiz
    private static void askQuizQuestion(QuizRunner quizRunner, Quiz quiz, Question q) {
        while (!quiz.isOutOfTries() && !quiz.isOutOfTime()) {
            String answer = getAnswer(quizRunner, q);
            String feedback = quiz.submitAnswer(answer);
            quizRunner.print(feedback);
            quizRunner.print("\n");
        }
    }

    // EFFECTS: shows a question to the user, prompts for an answer and returns it
    private static String getAnswer(QuizRunner quizRunner, Question q) {
        quizRunner.print(q.getQuestionString());
        quizRunner.print("Your response: ");
        String answer = quizRunner.getUserResponse();
        quizRunner.print("");
        return answer;
    }
}
