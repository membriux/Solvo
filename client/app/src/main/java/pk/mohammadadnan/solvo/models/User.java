package pk.mohammadadnan.solvo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("id")
    private Integer id;

    @SerializedName("email")
    private String email;

    @SerializedName("problems")
    private List<Problems.Problem> problems;

    public User(String email, Integer id, List<Problems.Problem> problems) {
        this.email = email;
        this.id = id;
        this.problems = problems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Problems.Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problems.Problem> problems) {
        this.problems = problems;
    }

}