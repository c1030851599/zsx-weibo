package weibo.pojo;

import java.util.Date;

public class chatrecord {
    private Integer id;

    private String sessionid;

    private Integer sendman;

    private Integer receiveman;

    private String contant;

    private Date sendtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Integer getSendman() {
        return sendman;
    }

    public void setSendman(Integer sendman) {
        this.sendman = sendman;
    }

    public Integer getReceiveman() {
        return receiveman;
    }

    public void setReceiveman(Integer receiveman) {
        this.receiveman = receiveman;
    }

    public String getContant() {
        return contant;
    }

    public void setContant(String contant) {
        this.contant = contant;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }
}