package com.example.securityproject.service;

import com.example.securityproject.domain.note.Note;
import com.example.securityproject.domain.note.NoteRepository;
import com.example.securityproject.domain.user.User;
import com.example.securityproject.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Transactional(readOnly = true)
    public List<Note> findByUser(User user){
        if(user == null){
            throw new UserNotFoundException();
        }
        if(user.isAdmin()) {
            return noteRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        return noteRepository.findByUserOrderByIdDesc(user);
    }

    public Note saveNote(User user, String title, String content){
        if(user == null){
            throw new UserNotFoundException();
        }
        Note note = Note.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
        return noteRepository.save(note);
    }

    public void deleteNote(User user, Long noteId){
        if(user == null){
            throw new UserNotFoundException();
        }
        Note note = noteRepository.findByIdAndUser(noteId, user);
        if(note != null){
            noteRepository.delete(note);
        }
    }
}
