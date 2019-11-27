package weibo.pojo;

import java.io.Serializable;
import java.util.List;

public class weiboCustom extends weibo implements Serializable {

    private User user;

    private List<plList> plLists;

    private boolean iflike;

    private boolean ifcollect;

    private Integer likeCount;

    private zfweibo zfweibo;

    public boolean isIfcollect() {
        return ifcollect;
    }

    public void setIfcollect(boolean ifcollect) {
        this.ifcollect = ifcollect;
    }
    public zfweibo getZfweibo() {
        return zfweibo;
    }

    public void setZfweibo(zfweibo zfweibo) {
        this.zfweibo = zfweibo;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isIflike() {
        return iflike;
    }

    public void setIflike(boolean iflike) {
        this.iflike = iflike;
    }



    public List<plList> getPlLists() {
        return plLists;
    }

    public void setPlLists(List<plList> plLists) {
        this.plLists = plLists;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
