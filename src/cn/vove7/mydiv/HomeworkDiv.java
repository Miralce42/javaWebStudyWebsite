package cn.vove7.mydiv;


/**
 * Created by Vove on 2017/5/20.
 */
public class HomeworkDiv {
   private String hwId;
   private String title;
   private String body;
   private String buttonValue;

   public HomeworkDiv(String hwId, String title, String body, String buttonValue) {
      this.title = title;
      this.hwId = hwId;
      this.body = body;
      this.buttonValue = buttonValue;
   }

   @Override
   public String toString() {
      return
              "        <div class=\"col-md-4\">\n" +
                      "            <div class=\"panel\">\n" +
                      "                <div class=\"panel-heading\">\n" +
                      "                    <div class=\"page-title\">\n" +
                      "                        " + title + "\n" +
                      "                    </div>\n" +
                      "                </div>\n" +
                      "                <div class=\"panel-body\">\n" +
                      "\n" + body +
                      "                </div>\n" +
                      "                <div class=\"panel-buttonValue\">\n" +
                      "                  <a href="+hwId+ " class='floatButton'>  " + buttonValue + "</a>\n" +
                      "                </div>\n" +
                      "            </div>\n" +
                      "        </div>\n";
   }
}