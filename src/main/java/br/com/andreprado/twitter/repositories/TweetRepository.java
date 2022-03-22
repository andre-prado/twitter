package br.com.andreprado.twitter.repositories;

import br.com.andreprado.twitter.models.TweetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TweetRepository extends JpaRepository<TweetModel, UUID> {
}
