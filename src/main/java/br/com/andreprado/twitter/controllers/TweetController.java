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
        List<String> hashtags = Arrays
                .asList("openbanking", "remediation", "devops", "sre", "microservices", "observability", "oauth",
                        "metrics", "logmonitoring", "opentracing");

        consumerService.clearDatabases();

        for(String hashtag : hashtags) {
            System.out.println("Armazenando hashtag " + hashtag);
            var twitterApiResponseModel = consumerService.consumeApi(hashtag, authorization);
            var users = twitterApiResponseModel.getIncludes().getUsers();
            var tweets = twitterApiResponseModel.getData();
            List<UserMetricsModel> metrics = new ArrayList<UserMetricsModel>();

            if(twitterApiResponseModel != null) {
                for (UserModel user : users) {
                    metrics.add(user.getPublicMetrics());
                }

                tweetRepository.saveAll(tweets);
                metricsRepository.saveAll(metrics);
                userRepository.saveAll(users);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("You database is feed");
    }
}
