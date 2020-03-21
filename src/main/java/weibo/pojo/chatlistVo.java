package weibo.pojo;

import java.io.Serializable;

public class chatlistVo implements Serializable {

    private chatlist list;

    private User me;

    private User you;

    private String newestMsg;

    public String getNewestMsg() {
        return newestMsg;
    }

    public void setNewestMsg(String newestMsg) {
        this.newestMsg = newestMsg;
    }

    public chatlist getList() {
        return list;
    }

    public void setList(chatlist list) {
        this.list = list;
    }

    public User getMe() {
        return me;
    }

    public void setMe(User me) {
        this.me = me;
    }

    public User getYou() {
        return you;
    }

    public void setYou(User you) {
        this.you = you;
    }




}