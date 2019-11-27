package weibo.pojo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
//    序列化值不同，所以重新修改。
    private static final long serialVersionUID = 6427774415406558904L;
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String headImgName;

    private String personal_label;

    public String getPersonalLabel() {
        return personalLabel;
    }

    public void setPersonalLabel(String personalLabel) {
        this.personalLabel = personalLabel;
    }

    private String personalLabel;

    private String webName;

    private String name;

    private String city;

    private Integer gender;

    private Date birthday;

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public String getHeadImgName() {
        return headImgName;
    }

    public void setHeadImgName(String headImgName) {
        this.headImgName = headImgName;
    }

    public String getpersonal_label() {
        return personal_label;
    }

    public void setpersonal_label(String personal_label) {
        this.personal_label = personal_label;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}