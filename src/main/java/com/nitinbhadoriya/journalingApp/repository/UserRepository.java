package com.nitinbhadoriya.journalingApp.repository;

import com.nitinbhadoriya.journalingApp.entity.JournalEntry;
import com.nitinbhadoriya.journalingApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    public User findByUserName(String userName);
}
