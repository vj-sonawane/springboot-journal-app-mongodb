package com.vscode.springbootjournalappmongodb.repository;

import com.vscode.springbootjournalappmongodb.model.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
