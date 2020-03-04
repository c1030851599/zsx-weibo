package weibo.pojo;

import java.io.Serializable;

public class message implements Serializable {

  private User user;

  private String postTime;

  private String plContent;

  private zfweibo zfweibo;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getPostTime() {
    return postTime;
  }

  public void setPostTime(String postTime) {
    this.postTime = postTime;
  }

  public zfweibo getZfweibo() {
    return zfweibo;
  }

  public void setZfweibo(zfweibo zfweibo) {
    this.zfweibo = zfweibo;
  }

  public String getPlContent() {
    return plContent;
  }

  public void setPlContent(String plContent) {
    this.plContent = plContent;
  }

}
