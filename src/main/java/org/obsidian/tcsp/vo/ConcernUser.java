package org.obsidian.tcsp.vo;

/**
 * @Author Rin
 * @Date 2017/12/4
 */
public class ConcernUser {
    Integer id;
    String userName;
    Integer concernNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getConcernNum() {
        return concernNum;
    }

    public void setConcernNum(Integer concernNum) {
        this.concernNum = concernNum;
    }
}
