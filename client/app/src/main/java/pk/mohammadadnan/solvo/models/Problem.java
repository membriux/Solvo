package pk.mohammadadnan.solvo.models;

import com.google.gson.annotations.SerializedName;

public class Problem {

    @SerializedName("owner_id")
    private Integer owner_id;
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;

    public Problem(Integer owner_id, Integer id, String title, String description) {
        this.owner_id = owner_id;
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer albumId) { this.owner_id = owner_id; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
