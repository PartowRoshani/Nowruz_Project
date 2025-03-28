package org.NowruzProject.Comments;

import org.NowruzProject.Accounts.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Comment {
    private final String text;
    private final User user;
    private final LocalDateTime date;

    public Comment(String text, User user) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment text cannot be empty!");
        }
        this.text = text;
        this.user = Objects.requireNonNull(user, "User cannot be null!");
        this.date = LocalDateTime.now();
    }

    //getters
    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Comment by " + user.getUsername() + " at " + date + ": " + text;
    }

    //This method is used to check comments in order to prevent duplicates.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Comment comment = (Comment) obj;
        return Objects.equals(text, comment.text) &&
                Objects.equals(user, comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, user);
    }


}

