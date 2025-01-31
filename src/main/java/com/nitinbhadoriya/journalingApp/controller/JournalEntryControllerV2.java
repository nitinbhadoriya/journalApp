package com.nitinbhadoriya.journalingApp.controller;

import com.nitinbhadoriya.journalingApp.entity.JournalEntry;
import com.nitinbhadoriya.journalingApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createJournalEntry(@RequestBody JournalEntry journalEntry) {
        return journalEntryService.createJournalEntry(journalEntry);
    }

    @GetMapping("/id/{id}")
    public JournalEntry findJournalEntryById(@PathVariable ObjectId id) {
        return journalEntryService.findJournalEntryById(id).orElse(null);
    }

    @DeleteMapping("/id/{id}")
    public Boolean deleteEntryById(@PathVariable ObjectId id) {
        journalEntryService.deleteEntryById(id);
        return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry oldEntry = journalEntryService.findJournalEntryById(id).orElse(null);
        if(oldEntry != null) {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.createJournalEntry(oldEntry);
        return oldEntry;
    }
}
