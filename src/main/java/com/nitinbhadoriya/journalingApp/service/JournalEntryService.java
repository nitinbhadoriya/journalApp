package com.nitinbhadoriya.journalingApp.service;

import com.nitinbhadoriya.journalingApp.entity.JournalEntry;
import com.nitinbhadoriya.journalingApp.entity.User;
import com.nitinbhadoriya.journalingApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private  UserService userService;

    public void createJournalEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().add(journalEntry);
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
        userService.createUser(user);
    }

    public List<JournalEntry> getAllJournalEntriesForUser(String userName) {
        User user = userService.findByUserName(userName);
        return user.getJournalEntries();
    }

    public Optional<JournalEntry> findJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteEntryById(ObjectId id, String userName) {
        User user = userService.findByUserName(userName);
        journalEntryRepository.deleteById(id);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.createUser(user);
    }

    public void createJournalEntry(JournalEntry oldEntry) {
        journalEntryRepository.save(oldEntry);
    }
}
