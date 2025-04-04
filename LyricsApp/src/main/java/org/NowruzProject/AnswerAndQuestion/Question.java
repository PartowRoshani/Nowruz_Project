package org.NowruzProject.AnswerAndQuestion;

import org.NowruzProject.Accounts.Account;
import org.NowruzProject.Music.Song;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private final String questionText;
    private final Account askedBy;
    private final Song relatedSong;
    private List<Answer> answers;

    public Question(String questionText, Account askedBy, Song relatedSong) {
        this.questionText = questionText;
        this.askedBy = askedBy;
        this.relatedSong = relatedSong;
        this.answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void displayQuestion() {
        System.out.println("\n🎤 Question about: " + relatedSong.getTitle());
        System.out.println("❓ " + questionText);
        System.out.println("Asked by: " + askedBy.getUsername());

        if (answers.isEmpty()) {
            System.out.println("No answers yet.");
        } else {
            System.out.println("\n💬 Answers:");
            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i + 1) + ". " + answers.get(i).getAnswerText() + " - by " + answers.get(i).getAnsweredBy().getUsername());
            }
        }
    }

    public Song getRelatedSong() {
        return relatedSong;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
