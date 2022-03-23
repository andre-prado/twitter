package br.com.andreprado.twitter.controllers;

import br.com.andreprado.twitter.models.UserMetricsModel;
import br.com.andreprado.twitter.models.UserModel;
import br.com.andreprado.twitter.repositories.TweetRepository;
import br.com.andreprado.twitter.repositories.UserMetricsRepository;
import br.com.andreprado.twitter.repositories.UserRepository;
import br.com.andreprado.twitter.services.TwitterApiConsumerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TweetController {
    final TwitterApiConsumerService consumerService;
    final TweetRepository tweetRepository;
    final UserRepository userRepository;
    final UserMetricsRepository metricsRepository;

    public TweetController(TwitterApiConsumerService consumerService, TweetRepository tweetRepository, UserRepository userRepository, UserMetricsRepository metricsRepository) {
        this.consumerService = consumerService;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.metricsRepository = metricsRepository;
    }

    @GetMapping("search")
    public ResponseEntity searchTweetByHashtag(@RequestParam("hashtag") String hashtag,@RequestHeader String authorization) {
        var twitterApiResponseModel = consumerService.consumeApi(hashtag, authorization);
        var tweets = twitterApiResponseModel.getData();

        if(twitterApiResponseModel != null) {
            return ResponseEntity.status(HttpStatus.OK).body(tweets);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tweets not found");
    }

    @GetMapping("feed")
    public ResponseEntity feed(@RequestHeader String authorization){
        try {
            consumerService.clearDatabases();
            consumerService.saveData(authorization);
            return ResponseEntity.status(HttpStatus.OK).body("Data about hashtags was stored");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getCause());
        }
    }
}
