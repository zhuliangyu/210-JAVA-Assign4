package ca.ubc.cpsc210.quizbuilder.test;

import ca.ubc.cpsc210.quizbuilder.model.question.Question;
import ca.ubc.cpsc210.quizbuilder.model.question.TrueFalseQuestion;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;
import ca.ubc.cpsc210.quizbuilder.model.quiz.Quiz;
import org.junit.jupiter.api.BeforeEach;

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
    }

}
