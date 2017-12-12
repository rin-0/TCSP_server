package org.obsidian.tcsp.dto;

/**
 * 用于某些只需获取路径点简单信息的地方
 * @Author Rin
 * @Date 2017/12/11
 */
public class SimpleRoutepoint {
    protected Integer id;

    protected String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

}
