package beans;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Vove on 2017/5/19.
 *
 * 互动话题
 */
public class InteractionTopic {
    private String topicId;
    private String username;
    private String title;
    private String content;
    private TopicType topicType;
    private Boolean isDeleted;
    private Date date;

    public String getTopicType() {
        return topicType.toString();
    }

    public void setTopicType(String topicType) {
        this.topicType = TopicType.valueOf(topicType);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @SuppressWarnings("deprecation")
    public String getDate() {
        return date.toLocaleString();
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public enum TopicType{
        JAVA,
        HTML,
        JSP,
        Other,
        BACK_END
    }
}
