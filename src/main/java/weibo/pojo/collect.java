package weibo.pojo;

import java.io.Serializable;

public class collect implements Serializable {
    //    序列化值不同，所以重新修改。
    private static final long serialVersionUID = 6427774415406558904L;
    private String collectid;

    private Integer userid;

    private String wbid;

    public String getCollectid() {
        return collectid;
    }

    public void setCollectid(String collectid) {
        this.collectid = collectid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getWbid() {
        return wbid;
    }

    public void setWbid(String wbid) {
        this.wbid = wbid;
    }
}