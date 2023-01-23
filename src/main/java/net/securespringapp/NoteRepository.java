package net.securespringapp;

import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Integer> {
    public Long countById(Integer id);
}
