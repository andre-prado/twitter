package br.com.andreprado.twitter.services;

import br.com.andreprado.twitter.models.UserModel;
import br.com.andreprado.twitter.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserModel> getTopFive() {
        return repository.topFive();
    }

}
