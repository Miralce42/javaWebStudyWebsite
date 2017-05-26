package beans;

/**
 * Created by Vove on 2017/5/22.
 * 选择题类封装
 */
public class ChoiceHomework {
   private String id;
   private String question;
   private String choice_A;
   private String choice_B;
   private String choice_C;
   private String choice_D;
   private String ref_ky;
   private String score;

   public String getId() {
      return id;
   }

   public ChoiceHomework setId(String id) {
      this.id = id;
      return this;
   }

   public ChoiceHomework() {}

   public String getScore() {
      return score;
   }

   public ChoiceHomework setScore(String score) {
      this.score = score;
      return this;
   }

   public ChoiceHomework setQuestion(String question) {
      this.question = question;
      return this;
   }

   public ChoiceHomework setChoice_A(String choice_A) {
      this.choice_A = choice_A;
      return this;
   }

   public ChoiceHomework setChoice_B(String choice_B) {
      this.choice_B = choice_B;
      return this;
   }

   public ChoiceHomework setChoice_C(String choice_C) {
      this.choice_C = choice_C;
      return this;
   }

   public ChoiceHomework setChoice_D(String choice_D) {
      this.choice_D = choice_D;
      return this;
   }

   public ChoiceHomework setRef_ky(String ref_ky) {
      this.ref_ky = ref_ky;
      return this;
   }

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

   public String getRef_ky() {
      return ref_ky;
   }

   public ChoiceHomework(String question, String choice_A, String choice_B,
                         String choice_C, String choice_D, String ref_ky,String score) {
      this.question = question;
      this.choice_A = choice_A;
      this.choice_B = choice_B;
      this.choice_C = choice_C;
      this.choice_D = choice_D;
      this.ref_ky = ref_ky;
      this.score=score;
   }

   @Override
   public String toString() {
      return "ChoiceHomework{" +
              "question='" + question + '\'' +
              ", choice_A='" + choice_A + '\'' +
              ", choice_B='" + choice_B + '\'' +
              ", choice_C='" + choice_C + '\'' +
              ", choice_D='" + choice_D + '\'' +
              ", ref_ky=" + ref_ky +
              '}';
   }

}