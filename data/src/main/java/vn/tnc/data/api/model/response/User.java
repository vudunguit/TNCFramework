package vn.tnc.data.api.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 5/25/2015.
 */
public class User {
    @SerializedName("login") public String login;//": "octocat",
    @SerializedName("id") public String id;//": 1,
    @SerializedName("avatar_url") public String avatar_url;//": "https://github.com/images/error/octocat_happy.gif",
    @SerializedName("url") public String url;//: "https://api.github.com/users/octocat",
    @SerializedName("type") public String type;//: "User",
    @SerializedName("name") public String name;//: "monalisa octocat",
    @SerializedName("company") public String company;//: "GitHub",
    @SerializedName("location") public String location;//: "San Francisco",
    @SerializedName("email") public String email;//: "octocat@github.com",
    @SerializedName("bio") public String bio;//: "There once was...",
    @SerializedName("public_repos") public String public_repos;//: 2,
    @SerializedName("followers") public String followers;//: 20,
    @SerializedName("created_at") public String created_at;//: "2008-01-14T04:33:35Z",
}
