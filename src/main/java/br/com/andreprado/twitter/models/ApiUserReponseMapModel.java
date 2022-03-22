package br.com.andreprado.twitter.models;

import java.util.List;

public class ApiUserReponseMapModel {
    private List<UserModel> users;

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
