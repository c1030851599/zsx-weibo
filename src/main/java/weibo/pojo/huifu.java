package weibo.pojo;

import java.io.Serializable;

public class huifu implements Serializable {
    //    序列化值不同，所以重新修改。
    private static final long serialVersionUID = 6427774415406558904L;
    public String getAtName() {
        return atName;
    }

    public void setAtName(String atName) {
        this.atName = atName;
    }

    public String getHfContent() {
        return hfContent;
    }

    public void setHfContent(String hfContent) {
        this.hfContent = hfContent;
    }

    private String atName;
    private String hfContent;
}
