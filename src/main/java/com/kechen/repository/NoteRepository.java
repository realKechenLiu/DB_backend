package com.kechen.repository;

import com.kechen.domain.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note,Integer>{

    // Get Note by codeId
    public Note findByCode_CodeId(int codeId);
}
