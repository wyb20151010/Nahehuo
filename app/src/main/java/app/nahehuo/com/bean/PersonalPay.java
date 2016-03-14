package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/3/1.
 */
public class PersonalPay {

    private String type;
    private String tradestatus;
    private String money;
    private String time;


    public PersonalPay(String type, String tradestatus, String money, String time) {
        this.type = type;
        this.tradestatus = tradestatus;
        this.money = money;
        this.time = time;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getTradestatus() {
        return tradestatus;
    }


    public void setTradestatus(String tradestatus) {
        this.tradestatus = tradestatus;
    }


    public String getMoney() {
        return money;
    }


    public void setMoney(String money) {
        this.money = money;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }
}
