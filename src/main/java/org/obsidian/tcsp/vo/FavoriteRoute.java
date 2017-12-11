package org.obsidian.tcsp.vo;

/**
 * 用户的收藏列表里的收藏路径
 * @Author Rin
 * @Date 2017/12/4
 */
public class FavoriteRoute {
    Integer id;
    String creatorName;
    String name;
    //收藏时间
    Long time;
    String coverPic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }
}
