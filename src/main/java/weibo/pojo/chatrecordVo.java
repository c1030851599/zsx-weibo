package weibo.pojo;

import java.io.Serializable;

public class chatrecordVo implements Serializable {
    private chatrecord chatrecord;

    private User me;

    private User you;

    public chatrecord getChatrecord() {
        return chatrecord;
    }

    public void setChatrecord(chatrecord chatrecord) {
        this.chatrecord = chatrecord;
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