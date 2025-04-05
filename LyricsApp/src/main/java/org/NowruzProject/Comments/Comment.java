package org.NowruzProject.Comments;

import org.NowruzProject.Accounts.Artist;
import org.NowruzProject.Accounts.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Comment {
    private final String text;
    private final User user;
    private final LocalDateTime date;
    private int likeCount;
    private int dislikeCount;
    private List<User> likedUsers;
    private List<User> dislikedUsers;

    public Comment(String text, User user) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment text cannot be empty!");
        }
        this.text = text;
        this.user = Objects.requireNonNull(user, "User cannot be null!");
        this.date = LocalDateTime.now();
        this.likeCount = 0;
        this.dislikeCount = 0;
        this.likedUsers = new ArrayList<>();
        this.dislikedUsers = new ArrayList<>();
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

    public int getLikeCount() {
        return likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }


    public void like(User user) {
        if (likedUsers.contains(user)) {
            System.out.println("You have already liked this comment.");
        } else {
            likedUsers.add(user);
            likeCount++;

            // if dislike before
            if (dislikedUsers.contains(user)) {
                dislikedUsers.remove(user);
                dislikeCount--;
            }
            System.out.println(user.getUsername() + " liked this comment.");
        }
    }

    public void dislike(User user) {
        if (dislikedUsers.contains(user)) {
            System.out.println("You have already disliked this comment.");
        } else {
            dislikedUsers.add(user);
            dislikeCount++;

            // if liked before
            if (likedUsers.contains(user)) {
                likedUsers.remove(user);
                likeCount--;
            }
            System.out.println(user.getUsername() + " disliked this comment.");
        }
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