package beans;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by 韩壮 on 2017/5/19.
 */
public class TopicComments {
    private String topicId;
    private String commentId;
    private String username;
    private String content;
    private Date date;
    private boolean isDelete;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getDate() {
        return date.toLocaleString();
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
