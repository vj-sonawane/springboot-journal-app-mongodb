package com.vscode.springbootjournalappmongodb.repository;

import com.vscode.springbootjournalappmongodb.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);

}
