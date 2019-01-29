package ca.ubc.cpsc210.quizbuilder.test;

import ca.ubc.cpsc210.quizbuilder.model.question.Question;
import ca.ubc.cpsc210.quizbuilder.model.question.TrueFalseQuestion;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;
import ca.ubc.cpsc210.quizbuilder.model.quiz.DecrementMarksQuiz;
import ca.ubc.cpsc210.quizbuilder.model.quiz.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DecrementMarksQuizTests {

    private Question q1, q2;
    private QuestionsList qList;
    private Quiz testQuiz;

    @BeforeEach
    public void runBefore() {
        // feel free to modify this as needed
        q1 = new TrueFalseQuestion(8, "You are awesome.", true);
        q2 = new TrueFalseQuestion(16, "Donuts are bad for you.", true);
        qList = new QuestionsList();
        qList.addQuestion(q1);
        qList.addQuestion(q2);
        testQuiz = new DecrementMarksQuiz(qList);

    }

    @Test
    public void testConstruct() {
        assertEquals(testQuiz.getMaxMark(), 24);
        assertEquals(testQuiz.getMarkSoFar(), 0);
    }

    @Test
    public void testSubmitAnswerCorrect() {
//        decrementMarksQuiz.curQuestion();

        testQuiz.curQuestion = q1;
        String ans = testQuiz.submitAnswer("true");
        assertEquals(24, testQuiz.getMaxMark());
        assertEquals("Your answer is correct. You earned 8 points!", ans);

    }

    @Test
    public void testSubmitAnswerWrong() {

        testQuiz.curQuestion = q1;
        String ans = testQuiz.submitAnswer("false");
        assertEquals(23, testQuiz.getMaxMark());
        assertEquals("Your answer is not correct. Max marks remaining for question: 7", ans);

    }
    @Test
    public void testSubmitAnswerOutOfTries() {

        testQuiz.curQuestion = q1;
        testQuiz.nextQuestion = 1;
        testQuiz.curQuestion.setMaxMark(1);
        String ans = testQuiz.submitAnswer("false");
        assertEquals(q2, testQuiz.getNextQuestion());

    }


}
