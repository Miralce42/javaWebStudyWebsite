package beans;

/**
 * Created by Vove on 2017/5/23.
 * 填空题封装
 */
public class CompletionHomework {
   private String completionContent;
   private String score;

   public CompletionHomework(String completionContent, String score) {
      this.completionContent = completionContent;
      this.score = score;
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
