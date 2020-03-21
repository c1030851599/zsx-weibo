package weibo.pojo;

import java.io.Serializable;
import java.util.Date;

public class chatlist implements Serializable {
    private Integer id;

    private Integer me;

    private Integer you;

    private String sessionid;

    private Date lasttimetalk;

    private Integer sxMessage;

    public Integer getSxMessage() {
        return sxMessage;
    }

    public void setSxMessage(Integer sxMessage) {
        this.sxMessage = sxMessage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMe() {
        return me;
    }

    public void setMe(Integer me) {
        this.me = me;
    }

    public Integer getYou() {
        return you;
    }

    public void setYou(Integer you) {
        this.you = you;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Date getLasttimetalk() {
        return lasttimetalk;
    }

    public void setLasttimetalk(Date lasttimetalk) {
        this.lasttimetalk = lasttimetalk;
    }
}