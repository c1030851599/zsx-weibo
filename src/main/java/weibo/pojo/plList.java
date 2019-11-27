package weibo.pojo;

import java.util.Date;
import java.util.List;

public class plList {
    private String id;

    private String weiboid;

    private String username;

    private Integer userid;

    private String plcontent;

    private Date pltime;

    private Integer zan;

    private List<hfplList> hfplLists;

    private String userHeadImg;

    public List<hfplList> getHfplLists() {
        return hfplLists;
    }

    public void setHfplLists(List<hfplList> hfplLists) {
        this.hfplLists = hfplLists;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeiboid() {
        return weiboid;
    }

    public void setWeiboid(String weiboid) {
        this.weiboid = weiboid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPlcontent() {
        return plcontent;
    }

    public void setPlcontent(String plcontent) {
        this.plcontent = plcontent;
    }

    public Date getPltime() {
        return pltime;
    }

    public void setPltime(Date pltime) {
        this.pltime = pltime;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
    }
}