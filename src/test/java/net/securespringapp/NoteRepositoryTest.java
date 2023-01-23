package net.securespringapp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class NoteRepositoryTest {
    @Autowired private NoteRepository repo;

//    @Test
//    public void testAddNew(){
//        Note note = new Note();
//        note.setEmail("a@example.com");
//        note.setTitle("notey");
//        note.setText("abc");
//        note.setPublic(false);
//        Note savedNote = repo.save(note);
//        Assertions.assertThat(savedNote).isNotNull();
//        Assertions.assertThat(savedNote.getId()).isGreaterThan(0);
//    }

//
//    @Test
//    public  void testListAll(){
//        Note note = new Note();
//        note.setEmail("b@example.com");
//        note.setTitle("note2");
//        note.setText("abc");
//        note.setPublic(false);
//        repo.save(note);
//        note = new Note();
//        note.setEmail("c@example.com");
//        note.setTitle("note3");
//        note.setText("abc");
//        note.setPublic(false);
//        repo.save(note);
//        Iterable<Note> notes = repo.findAll();
//        Assertions.assertThat(notes).hasSizeGreaterThan(0);
//        for (Note n : notes){
//            System.out.println(n);
//        }
//    }
//
//    @Test
//    public void testUpdate(){
//        Note note = new Note();
//        note.setEmail("b@example.com");
//        note.setTitle("note2");
//        note.setText("abc");
//        repo.save(note);
//        note = new Note();
//        note.setEmail("c@example.com");
//        note.setTitle("note3");
//        note.setText("abc");
//        repo.save(note);
//
//        Integer id = 1;
//        Optional<Note> optionalNote = repo.findById(id);
//        note = optionalNote.get();
//        note.setTitle("notex");
//        note.setText("abc");
//        repo.save(note);
//        Note updatedNote =repo.findById(id).get();
//        Assertions.assertThat(updatedNote.getTitle()).isEqualTo("notex");
//    }
//    @Test
//    public void testGet(){
//        Note note = new Note();
//        note.setEmail("b@example.com");
//        note.setTitle("note2");
//        note.setText("abc");
//        repo.save(note);
//        note = new Note();
//        note.setEmail("c@example.com");
//        note.setTitle("note3");
//        note.setText("abc");
//        repo.save(note);
//
//        Integer id = 2;
//        Optional<Note> optionalNote = repo.findById(id);
//        Assertions.assertThat(optionalNote).isPresent();
//        System.out.println(optionalNote.get());
//    }
//
//    @Test
//    public void testDelete(){
//        Note note = new Note();
//        note.setEmail("b@example.com");
//        note.setTitle("note2");
//        note.setText("abc");
//        repo.save(note);
//        note = new Note();
//        note.setEmail("c@example.com");
//        note.setTitle("note3");
//        note.setText("abc");
//        repo.save(note);
//
//        Integer id = 1;
//        repo.deleteById(1);
//        Optional<Note> optionalNote = repo.findById(id);
//        Assertions.assertThat(optionalNote).isNotPresent();
//        System.out.println(optionalNote.get());
//    }
}
