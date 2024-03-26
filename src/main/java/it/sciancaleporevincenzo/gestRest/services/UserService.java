package it.sciancaleporevincenzo.gestRest.services;

import it.sciancaleporevincenzo.gestRest.entity.User;
import it.sciancaleporevincenzo.gestRest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
