package org.NowruzProject.Comments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class CommentManager {
    private List<Comment> comments;

    public CommentManager() {
        this.comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    // Sort by date descending
    public void sortCommentsByDateDescending() {
        comments.sort(Comparator.comparing(Comment::getDate).reversed());
    }

    // Sort by date ascending
    public void sortCommentsByDateAscending() {
        comments.sort(Comparator.comparing(Comment::getDate));
    }

    public void printComments() {
        for (Comment c : comments) {
            System.out.println(c);
        }
    }
}