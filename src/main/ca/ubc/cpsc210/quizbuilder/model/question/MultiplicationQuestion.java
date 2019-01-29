package ca.ubc.cpsc210.quizbuilder.model.question;

public class MultiplicationQuestion extends Question {

    private String correctAnswer;
    // REQUIRES: maxMark must be >= 0, factor1 and factor2 should be integer
    // EFFECTS: construct the MultiplicationQuestion and set the correct answer for this question

    public MultiplicationQuestion(int maxMark, int factor1, int factor2) {

        //q3 = new MultiplicationQuestion(8, 5, 2);
        //Tell us the missing number: 5 * 2 = ??? [8 points]
        //"Tell us the missing number: " + factor1 + " * " + factor2 + " = ??? [" + maxMark + " points]";

        super(maxMark, "Tell us the missing number: " + factor1 + " * " + factor2 + " = ???");

        correctAnswer = Integer.toString(factor1 * factor2);

    }

    // EFFECTS: returns true if answer is the correct answer to this question,
    // false otherwise
    @Override
    public boolean isCorrect(String answer) {
        return correctAnswer.equals(answer);
    }
}
