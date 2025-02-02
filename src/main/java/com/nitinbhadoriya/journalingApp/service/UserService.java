package com.nitinbhadoriya.journalingApp.service;

import com.nitinbhadoriya.journalingApp.entity.JournalEntry;
import com.nitinbhadoriya.journalingApp.entity.User;
import com.nitinbhadoriya.journalingApp.repository.JournalEntryRepository;
import com.nitinbhadoriya.journalingApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteEntryById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
