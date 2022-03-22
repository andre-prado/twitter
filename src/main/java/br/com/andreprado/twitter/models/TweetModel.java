package br.com.andreprado.twitter.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="tweets")
public class TweetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID internalId;

    @JsonProperty("text")
    @Column(length = 1000)
    private String text;
    @JsonProperty("author_id")
    private String authorId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("lang")
    private String lang;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
