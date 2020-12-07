package com.example.quiz;

public class questions {
    private int questionResID;
    private boolean answerTrue;

    public questions(int questionResID, boolean answerTrue) {
        this.questionResID = questionResID;
        this.answerTrue = answerTrue;
    }

    public int getQuestionResID() {
        return questionResID;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setQuestionResID(int questionResID) {
        this.questionResID = questionResID;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }
}
