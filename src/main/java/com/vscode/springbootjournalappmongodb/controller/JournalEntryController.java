package com.vscode.springbootjournalappmongodb.controller;

import com.vscode.springbootjournalappmongodb.model.JournalEntry;
import com.vscode.springbootjournalappmongodb.model.User;
import com.vscode.springbootjournalappmongodb.service.JournalEntryService;
import com.vscode.springbootjournalappmongodb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
@Slf4j
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    private final UserService userService;
    @Autowired
    public JournalEntryController(JournalEntryService journalEntryService, UserService userService) {
        this.journalEntryService = journalEntryService;
        this.userService = userService;
    }


    @GetMapping("/userEntries/{userName}")
    public ResponseEntity<List<JournalEntry>> getAllEntriesOfUser(@PathVariable String userName){
        log.info("Inside JournalEntryController::getAllEntriesOfUser with userName: {}",userName);
        User user = userService.findByUserName(userName);
        List<JournalEntry> list = user.getJournalEntry();
        log.debug("List of Journal Entries: {}",list);
        if(list != null && !list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>(list,HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntryInUser(
            @RequestBody JournalEntry journalEntry,
            @PathVariable String userName
    ) {
        try {
            log.info("Inside JournalEntryController::createEntryInUser with userName: {}",userName);
            log.debug("RequestBody: {}",journalEntry);
            journalEntryService.saveJournalEntry(journalEntry,userName);
            return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/entries/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id){
        Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryById(id);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/entry/{userName}/{id}")
    public ResponseEntity<?> deleteJournalEntryById(
            @PathVariable ObjectId id,
            @PathVariable String userName
    ) {
        log.info("Inside JournalEntryController::deleteJournalEntryById with id: {} and userName: {}",id,userName);
        journalEntryService.deleteJournalEntryById(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateEntry/{myId}/{userName}")
    public ResponseEntity<JournalEntry> updateJournalById(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName
    ) {
        log.info("Inside JournalEntryController::updateJournalById with id: {} and userName: {}",myId,userName);
        log.debug("RequestBody: {}",newEntry);
        JournalEntry oldEntry = journalEntryService.getJournalEntryById(myId).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(!newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveJournalEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
