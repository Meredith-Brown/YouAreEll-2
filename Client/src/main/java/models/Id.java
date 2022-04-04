package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * POJO for an Id object
 */
public class Id {
    @JsonProperty("userid")
    private String userid = ""; // auto-generated
    @JsonProperty("name")
    private String name = "";
    @JsonProperty("github")
    private String github = "";

    public Id () {}

    public Id (String name, String githubId) {
        this.name = name;
        github = githubId;
    }

    public Id (String name, String githubId, String uid) {
        this.name = name;
        github = githubId;
        this.userid = uid;
    }

    public String getUid() {
        return userid;
    }

//    public void setUid(String uid) {
//        this.uid = uid;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.github + ") ";
    }
}