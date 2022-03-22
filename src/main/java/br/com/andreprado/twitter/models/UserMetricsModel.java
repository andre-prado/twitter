package br.com.andreprado.twitter.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="metrics")
public class UserMetricsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonAlias("followers_count")
    private long followersCount;
    @JsonAlias("following_count")
    private long followingCount;
    @JsonAlias("tweet_count")
    private long tweetCount;

    public long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public long getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public long getTweetCount() {
        return tweetCount;
    }

    public void setTweetCount(int tweetCount) {
        this.tweetCount = tweetCount;
    }
}
