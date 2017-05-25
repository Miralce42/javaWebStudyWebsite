package beans;

/**
 * Created by Vove on 2017/5/23.
 */
public class CompletionHomework {
   private String completionContent;

   public CompletionHomework(String completionContent) {
      this.completionContent = completionContent;
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
