package org.NowruzProject.AnswerAndQuestion;

import org.NowruzProject.Accounts.Account;

public class Answer {
    private final String answerText;
    private final Account answeredBy;

    public Answer(String answerText, Account answeredBy) {
        this.answerText = answerText;
        this.answeredBy = answeredBy;
    }

    public String getAnswerText() {
        return answerText;
    }

    public Account getAnsweredBy() {
        return answeredBy;
    }
}
