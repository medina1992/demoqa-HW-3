package components;

import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {

        public final SelenideElement resultsTable =  $(".table-responsive");
        public void checkResult(String key, String value){
            resultsTable.$(byText(key)).parent().shouldHave(text(value));

    }
}
