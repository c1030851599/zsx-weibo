package weibo.pojo;

import java.util.Date;

public class hfplList {
    private String id;

    private String plid;

    private Integer plzid;

    private String username;

    private String hfcontent;

    private Date hfpltime;

    private Integer zan;

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    private String userHeadImg;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlid() {
        return plid;
    }

    public void setPlid(String plid) {
        this.plid = plid;
    }

    public Integer getPlzid() {
        return plzid;
    }

    public void setPlzid(Integer plzid) {
        this.plzid = plzid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHfcontent() {
        return hfcontent;
    }

    public void setHfcontent(String hfcontent) {
        this.hfcontent = hfcontent;
    }

    public Date getHfpltime() {
        return hfpltime;
    }

    public void setHfpltime(Date hfpltime) {
        this.hfpltime = hfpltime;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
    }
}