package br.com.andreprado.twitter.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID internalId;

    private String name;
    private String id;
    private String username;

    @OneToOne
    @JsonAlias("public_metrics")
    private UserMetricsModel publicMetrics;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserMetricsModel getPublicMetrics() {
        return publicMetrics;
    }

    public void setPublicMetrics(UserMetricsModel publicMetrics) {
        this.publicMetrics = publicMetrics;
    }
}
