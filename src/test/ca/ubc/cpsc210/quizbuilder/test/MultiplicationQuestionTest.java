package ca.ubc.cpsc210.quizbuilder.test;

import ca.ubc.cpsc210.quizbuilder.model.question.MultiplicationQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MultiplicationQuestionTest {
    private MultiplicationQuestion multiplicationQuestion;
    private String correctAnswer;
    private int maxMark;
    private int fact1;
    private int fact2;

    @BeforeEach
    public void runBefore() {
        fact1 = 4;
        fact2 = 5;
        maxMark = 10;
        multiplicationQuestion = new MultiplicationQuestion(maxMark, fact1, fact2);
        correctAnswer = Integer.toString(fact1 * fact2);
    }

    @Test
    public void testConstruct() {

        assertEquals(multiplicationQuestion.getMaxMark(), maxMark);
        assertEquals(multiplicationQuestion.getQuestionString(),
                "Tell us the missing number: 4 * 5 = ??? [10 points]");
    }

    @Test
    public void testisCorrect(){

        assertTrue(multiplicationQuestion.isCorrect("20"));
        assertFalse(multiplicationQuestion.isCorrect("25"));

    }


}