package beans;

/**
 * Created by Vove on 2017/5/19.
 *
 * 互动话题
 */
public class InteractionTopic {
    private String username;
    private String title;
    private String content;
    private TopicType topicType;
    private Boolean isDeleted;

    public TopicType getTopicType() {
        return topicType;
    }

    public void setTopicType(TopicType topicType) {
        this.topicType = topicType;
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

    public enum TopicType{
        JAVA,
        HTML,
        JSP,
        Other,
        BACK_END
    }
}
