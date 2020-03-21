package weibo.pojo;

import java.io.Serializable;

public class hotWeibo implements Serializable {

  private String content;

  private Integer score;

  private String weiboId;

  public String getWeiboId() {
    return weiboId;
  }

  public void setWeiboId(String weiboId) {
    this.weiboId = weiboId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }





}
