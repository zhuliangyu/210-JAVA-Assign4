package ca.ubc.cpsc210.quizbuilder.model.quiz;

import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;

public class DecrementMarksQuiz extends EachAnswerMustBeRightQuiz {


    public DecrementMarksQuiz(QuestionsList questions) {
        super(questions);
    }

    @Override
    public String submitAnswer(String answer) {

        boolean isCorrect = checkAnswer(answer);
        while (!isCorrect && curQuestion.getMaxMark() > 0) {

            if (curQuestion.getMaxMark() == 1) {
                outOfTries = true;
            }
            curQuestion.setMaxMark(curQuestion.getMaxMark() - 1);
            // return "Your answer is " + (isCorrect ? "" : "not ") + "correct";

            //Your answer is not correct. Max marks remaining for question: 7
            return "Your answer is not correct. Max marks remaining for question: " + curQuestion.getMaxMark() + "";

        }

        //Your answer is correct. You earned 8 points!
        return "Your answer is correct. You earned " + curQuestion.getMaxMark() + " points!";
    }

    //test total mark correct

}
