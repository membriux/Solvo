package pk.mohammadadnan.solvo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Problems{
    @SerializedName("problems")
    private List<Problem> problems;

    public Problems(List<Problem> problems) {
        this.problems = problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public static class Problem {

        @SerializedName("interest")
        private Integer interest;
        @SerializedName("id")
        private Integer id;
        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private String description;
        @SerializedName("createdAt")
        private Long createdAt;

        public Problem(Integer interest, Integer id, String title, String description, Long createdAt) {
            this.interest = interest;
            this.id = id;
            this.title = title;
            this.description = description;
            this.createdAt = createdAt;
        }

        public Integer getInterest() {
            return interest;
        }

        public void setInterest(Integer interest) {
            this.interest = interest;
        }

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

        public Long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Long createdAt) {
            this.createdAt = createdAt;
        }
    }
}


