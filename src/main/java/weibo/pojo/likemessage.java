package weibo.pojo;

import java.util.Date;

public class likemessage {
    private Integer id;

    private String likeusername;

    private String likedusername;

    private String dzweibo;

    private Date dztime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLikeusername() {
        return likeusername;
    }

    public void setLikeusername(String likeusername) {
        this.likeusername = likeusername;
    }

    public String getLikedusername() {
        return likedusername;
    }

    public void setLikedusername(String likedusername) {
        this.likedusername = likedusername;
    }

    public String getDzweibo() {
        return dzweibo;
    }

    public void setDzweibo(String dzweibo) {
        this.dzweibo = dzweibo;
    }

    public Date getDztime() {
        return dztime;
    }

    public void setDztime(Date dztime) {
        this.dztime = dztime;
    }
}