package ca.ubc.cpsc210.quizbuilder.model.quiz;

import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;

/**
 * Represents a Quiz that requires the right answer to a question before the
 * user can move on.
 */
public class EachAnswerMustBeRightQuiz extends Quiz {

    // REQUIRES: questions cannot be an empty list
    // EFFECTS: constructs quiz with given list of questions
    public EachAnswerMustBeRightQuiz(QuestionsList questions) {
        super(questions);
    }

    // REQUIRES: !isOutOfTries()
    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string
    @Override
    public String submitAnswer(String answer) {
        boolean isCorrect = checkAnswer(answer);
        return "Your answer is " + (isCorrect ? "" : "not ") + "correct";
    }
}
