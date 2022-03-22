package br.com.andreprado.twitter.repositories;

import br.com.andreprado.twitter.models.UserMetricsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserMetricsRepository extends JpaRepository<UserMetricsModel, UUID> {
}
