package br.com.andreprado.twitter.repositories;

import br.com.andreprado.twitter.models.UserModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

//    @Query("select u from UserModel u order by u.publicMetrics.followersCount DESC")
//    List<UserModel> topFive(new PageRequest(0, 5));

    @Query(value = "select * from users u inner join metrics m on u.public_metrics_id = m.id order by m.followers_count desc limit 5", nativeQuery = true)
    List<UserModel> topFive();
}
