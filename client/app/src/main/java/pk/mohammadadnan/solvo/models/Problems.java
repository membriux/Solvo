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


}


