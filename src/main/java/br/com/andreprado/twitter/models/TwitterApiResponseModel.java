package br.com.andreprado.twitter.models;

import java.util.List;

public class TwitterApiResponseModel {

    private List<TweetModel> data;

    private ApiUserReponseMapModel includes;

    public List<TweetModel> getData() {
        return data;
    }

    public void setData(List<TweetModel> data) {
        this.data = data;
    }

    public ApiUserReponseMapModel getIncludes() {
        return includes;
    }

    public void setIncludes(ApiUserReponseMapModel includes) {
        this.includes = includes;
    }
}
