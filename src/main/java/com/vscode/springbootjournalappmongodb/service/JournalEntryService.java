package com.vscode.springbootjournalappmongodb.service;

import com.vscode.springbootjournalappmongodb.model.JournalEntry;
import com.vscode.springbootjournalappmongodb.model.User;
import com.vscode.springbootjournalappmongodb.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;

    private final UserService userService;

    @Autowired
    public JournalEntryService(JournalEntryRepository journalEntryRepository, UserService userService) {
        this.journalEntryRepository = journalEntryRepository;
        this.userService = userService;

    }
    @Transactional  //ToAchieve_AUTOMICITY_ISOLATION <- ACID
    public void saveJournalEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntry().add(saved);
            userService.saveUser(user);
        }catch (Exception ex){
            log.error("Exception occurred while saving journal: {}",ex.getMessage());
        }
    }

    public void saveJournalEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteJournalEntryById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntry().removeIf(entry -> entry.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception ex) {
            log.error("Exception occurred while deleting journal entry of user: {}, Exception message: {}",userName,ex.getMessage());
            throw new RuntimeException("An error occurred while deleting the entry.",ex);
        }
        return removed;
    }
}