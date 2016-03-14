package app.nahehuo.com.bean;

/**
 * Created by wyb on 2016/3/2.
 */
public class BankCard {

    private String name;
    private String card_name;


    public BankCard(String name, String card_name) {
        this.name = name;
        this.card_name = card_name;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCard_name() {
        return card_name;
    }


    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }
}
