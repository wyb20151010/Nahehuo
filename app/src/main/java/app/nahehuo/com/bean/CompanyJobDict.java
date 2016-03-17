package app.nahehuo.com.bean;

/**
 * Created by WYB on 2016/1/11.
 */
public class CompanyJobDict {
    private String job_name;
    private String job_time;
    private String job_wage;
    private String job_need;


    public CompanyJobDict() {
    }


    public CompanyJobDict(String job_name, String job_time, String job_wage, String job_need) {
        this.job_name = job_name;
        this.job_time = job_time;
        this.job_wage = job_wage;
        this.job_need = job_need;
    }


    public String getJob_name() {
        return job_name;
    }


    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }


    public String getJob_time() {
        return job_time;
    }


    public void setJob_time(String job_time) {
        this.job_time = job_time;
    }


    public String getJob_wage() {
        return job_wage;
    }


    public void setJob_wage(String job_wage) {
        this.job_wage = job_wage;
    }


    public String getJob_need() {
        return job_need;
    }


    public void setJob_need(String job_need) {
        this.job_need = job_need;
    }
}
