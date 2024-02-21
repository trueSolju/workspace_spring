package com.green.First.vo;

import java.util.Arrays;

public class Member {
    private String id;
    private String pw;
    private String name;
    private String[] tel;
    private String mail;
    private String birthday;
    private String[] lesson;
    private String yesOrNo;
    private String intro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTel() {
        return tel;
    }

    public void setTel(String[] tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String[] getLesson() {
        return lesson;
    }

    public void setLesson(String[] lesson) {
        this.lesson = lesson;
    }

    public String getYesOrNo() {
        return yesOrNo;
    }

    public void setYesOrNo(String yesOrNo) {
        this.yesOrNo = yesOrNo;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", tel=" + Arrays.toString(tel) +
                ", mail='" + mail + '\'' +
                ", birthday='" + birthday + '\'' +
                ", lesson=" + Arrays.toString(lesson) +
                ", yesOrNo='" + yesOrNo + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
