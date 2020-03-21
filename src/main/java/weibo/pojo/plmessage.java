package weibo.pojo;

import java.util.Date;

public class plmessage {
    private Integer id;

    private String plusername;

    private String pledusername;

    private String plweibo;

    private Date pltime;

    private String plcontent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlusername() {
        return plusername;
    }

    public void setPlusername(String plusername) {
        this.plusername = plusername;
    }

    public String getPledusername() {
        return pledusername;
    }

    public void setPledusername(String pledusername) {
        this.pledusername = pledusername;
    }

    public String getPlweibo() {
        return plweibo;
    }

    public void setPlweibo(String plweibo) {
        this.plweibo = plweibo;
    }

    public Date getPltime() {
        return pltime;
    }

    public void setPltime(Date pltime) {
        this.pltime = pltime;
    }

    public String getPlcontent() {
        return plcontent;
    }

    public void setPlcontent(String plcontent) {
        this.plcontent = plcontent;
    }
}