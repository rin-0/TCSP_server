package org.obsidian.tcsp.model;

public class UserConcern {
    private Integer selfUserId;

    private Integer concernUserId;

    public Integer getSelfUserId() {
        return selfUserId;
    }

    public void setSelfUserId(Integer selfUserId) {
        this.selfUserId = selfUserId;
    }

    public Integer getConcernUserId() {
        return concernUserId;
    }

    public void setConcernUserId(Integer concernUserId) {
        this.concernUserId = concernUserId;
    }
}