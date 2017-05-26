package beans;

/**
 * Created by Vove on 2017/5/23.
 * 填空题封装
 */
public class CompletionHomework {
   private String id;
   private String completionContent;
   private String score;

   public CompletionHomework(String id,String completionContent, String score) {
      this.id=id;
      this.completionContent = completionContent;
      this.score = score;
   }

   public CompletionHomework(String completionContent, String score) {
      this.completionContent = completionContent;
      this.score = score;
   }

   public String getId() {
      return id;
   }

   public void setCompletionContent(String completionContent) {
      this.completionContent = completionContent;
   }

   public String getScore() {
      return score;
   }

   public void setScore(String score) {
      this.score = score;
   }

   public String getCompletionContent() {
      return completionContent;
   }

   @Override
   public String toString() {
      return "CompletionHomework{" +
              "completionContent='" + completionContent + '\'' +
              '}';
   }
}
