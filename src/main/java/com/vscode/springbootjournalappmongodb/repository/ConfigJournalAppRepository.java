package com.vscode.springbootjournalappmongodb.repository;

import com.vscode.springbootjournalappmongodb.model.ConfigJournalApp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalApp, ObjectId> {



}
