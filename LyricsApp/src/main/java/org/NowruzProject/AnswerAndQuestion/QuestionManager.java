package org.NowruzProject.AnswerAndQuestion;

import org.NowruzProject.Music.Song;
import java.util.ArrayList;
import java.util.List;

public class QuestionManager {
    private List<Question> questions;

    public QuestionManager() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void displayQuestionsForSong(Song song) {
        System.out.println("\n Questions about " + song.getTitle() + ":");
        boolean found = false;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getRelatedSong().equals(song)) {
                System.out.println((i + 1) + ". " + questions.get(i).getAnswers().size() + " answers - " + questions.get(i).getRelatedSong().getTitle());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No questions yet.");
        }
    }

    public Question getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    public int getQuestionCount() {
        return questions.size(); // list of question
    }

}
