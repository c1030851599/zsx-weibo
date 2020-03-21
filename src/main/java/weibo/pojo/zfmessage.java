package weibo.pojo;

import java.io.Serializable;
import java.util.Date;

public class zfmessage implements Serializable {
    private Integer id;

    private String zfusername;

    private String zfedusername;

    private String zfweibo;

    private Date zftime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZfusername() {
        return zfusername;
    }

    public void setZfusername(String zfusername) {
        this.zfusername = zfusername;
    }

    public String getZfedusername() {
        return zfedusername;
    }

    public void setZfedusername(String zfedusername) {
        this.zfedusername = zfedusername;
    }

    public String getZfweibo() {
        return zfweibo;
    }

    public void setZfweibo(String zfweibo) {
        this.zfweibo = zfweibo;
    }

    public Date getZftime() {
        return zftime;
    }

    public void setZftime(Date zftime) {
        this.zftime = zftime;
    }
}