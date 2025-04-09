package org.NowruzProject.AnswerAndQuestion;

import org.NowruzProject.Accounts.Account;
import org.NowruzProject.Music.Song;
import java.util.ArrayList;
import java.util.List;

import static org.NowruzProject.ColoredOutput.*;

public class Question {
    private final String questionText;
    private final Account askedBy;
    private final Song relatedSong;
    private final List<Answer> answers;

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
        System.out.println(BLUE+"\n🎤 Question about: " + relatedSong.getTitle());
        System.out.println("❓ " + questionText);
        System.out.println(GREEN+"Asked by: " + askedBy.getUsername());

        if (answers.isEmpty()) {
            System.out.println(RED+"No answers yet.");
        } else {
            System.out.println(PURPLE+"\n💬 Answers:");
            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i + 1) + ". " + answers.get(i).getAnswerText() + " - by " + answers.get(i).getAnsweredBy().getUsername()+RESET);
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
