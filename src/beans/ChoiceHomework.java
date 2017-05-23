package beans;

/**
 * Created by Vove on 2017/5/22.
 */
public class ChoiceHomework {
   private String question;
   private String choice_A;
   private String choice_B;
   private String choice_C;
   private String choice_D;
   private char ref_ky;

   public String getQuestion() {
      return question;
   }

   public String getChoice_A() {
      return choice_A;
   }

   public String getChoice_B() {
      return choice_B;
   }

   public String getChoice_C() {
      return choice_C;
   }

   public String getChoice_D() {
      return choice_D;
   }

   public char getRef_ky() {
      return ref_ky;
   }

   public ChoiceHomework(String question, String choice_A, String choice_B, String choice_C, String choice_D, char ref_ky) {
      this.question = question;
      this.choice_A = choice_A;
      this.choice_B = choice_B;
      this.choice_C = choice_C;
      this.choice_D = choice_D;
      this.ref_ky = ref_ky;
   }
}