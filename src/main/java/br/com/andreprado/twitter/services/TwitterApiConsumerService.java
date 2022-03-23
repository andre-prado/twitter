package br.com.andreprado.twitter.services;

import br.com.andreprado.twitter.models.TwitterApiResponseModel;
import br.com.andreprado.twitter.models.UserMetricsModel;
import br.com.andreprado.twitter.models.UserModel;
import br.com.andreprado.twitter.repositories.TweetRepository;
import br.com.andreprado.twitter.repositories.UserMetricsRepository;
import br.com.andreprado.twitter.repositories.UserRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TwitterApiConsumerService {

    final UserRepository userRepository;
    final UserMetricsRepository metricsRepository;
    final TweetRepository tweetRepository;

    public TwitterApiConsumerService(UserRepository userRepository, UserMetricsRepository metricsRepository, TweetRepository tweetRepository) {
        this.userRepository = userRepository;
        this.metricsRepository = metricsRepository;
        this.tweetRepository = tweetRepository;
    }

    public TwitterApiResponseModel consumeApi(String hashtag, String authorization) {
        RestTemplate template = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.twitter.com")
                .path("2/tweets/search/recent")
                .queryParam("query", hashtag)
                .queryParam("expansions", "author_id")
                .queryParam("user.fields", "public_metrics")
                .queryParam("tweet.fields", "author_id,created_at,lang")
                .queryParam("max_results", "100")

                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        var twitterPostModel = template.exchange(uri.toUriString(), HttpMethod.GET, entity, TwitterApiResponseModel.class);

        var twitterApiResponseModel = twitterPostModel.getBody();
        return twitterApiResponseModel;
    }

    public void clearDatabases() {
        tweetRepository.deleteAll();
        userRepository.deleteAll();
        metricsRepository.deleteAll();
    }

    public List<String> getHashtags() {
        List<String> hashtags = Arrays.asList("openbanking", "remediation", "devops", "sre", "microservices", "observability", "oauth", "metrics", "logmonitoring", "opentracing");
        return hashtags;
    }

    public void saveData(String authorization) {
        var hashtags = this.getHashtags();

        for(String hashtag : hashtags) {
            System.out.println("Armazenando hashtag " + hashtag);
            var twitterApiResponseModel = this.consumeApi(hashtag, authorization);
            var users = twitterApiResponseModel.getIncludes().getUsers();
            var tweets = twitterApiResponseModel.getData();
            List<UserMetricsModel> metrics = new ArrayList<UserMetricsModel>();

            if(twitterApiResponseModel != null) {
                for (UserModel user : users) {
                    metrics.add(user.getPublicMetrics());
                }

                metricsRepository.saveAll(metrics);
                userRepository.saveAll(users);
                tweetRepository.saveAll(tweets);
            }
        }
    }
}
