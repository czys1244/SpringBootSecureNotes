package net.securespringapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class NoteController {

    @Autowired private NoteService service;
    @GetMapping("/shared")
    public String listPublicNotes(Model model) {
		List<Note> listPublicNotes = service.listAll();
		model.addAttribute("listPublicNotes", listPublicNotes);

        return "public_notes";
    }

    @GetMapping("/notes")
    public String showNotesList(Model model){
        List<Note> listNotes = service.listAll();
        model.addAttribute("listNotes", listNotes);
        return "notes";
    }
    @GetMapping("/notes/new")
    public String showNewForm(Model model){
        model.addAttribute("note", new Note());
        model.addAttribute("pageTitle", "Add New Note");
        return "note_form";
    }
    @PostMapping("/notes/save")
    public String saveNote(Note note, RedirectAttributes ra, @AuthenticationPrincipal CustomUserDetails userDetails){

        String userEmail = userDetails.getUsername();
        if (note.getEmail() != null && !userEmail.equals(note.getEmail())){
            return "redirect:/notes";
        }
        note.setEmail(userEmail);
        service.save(note);
        ra.addFlashAttribute("message", "The note was added");
        return "redirect:/notes";
    }

    @PostMapping("/notes/saveencrypted")
    public String encryptNote(Note note, RedirectAttributes ra, @AuthenticationPrincipal CustomUserDetails userDetails){
        if (!note.getEmail().equals(userDetails.getUsername())){
            return "redirect:/notes";
        }
        service.saveEncrypted(note);
        ra.addFlashAttribute("message", "The note was added");
        return "redirect:/notes";
    }

    @GetMapping("/notes/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra, @AuthenticationPrincipal CustomUserDetails userDetails){
        String userEmail = userDetails.getUsername();
        try {
            Note note = service.get(id);
            if (userEmail.equals(note.getEmail())) {
                model.addAttribute("note", note);
                model.addAttribute("pageTitle", "Edit Note(ID: " + id + ")");
                return "note_form";
            }
            return "redirect:/notes";
        } catch (NoteNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/notes";
        }
    }
    @GetMapping("/notes/encrypt/{id}")
    public String encryptDecryptForm(@PathVariable("id") Integer id, Model model,@AuthenticationPrincipal CustomUserDetails userDetails) {
        String userEmail = userDetails.getUsername();
        try {
            Note note = service.get(id);
            if (userEmail.equals(note.getEmail())){
                model.addAttribute("note", note);
            }

        } catch (NoteNotFoundException e) {
        }

        return "encoder";
    }
    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable("id") Integer id, RedirectAttributes ra,@AuthenticationPrincipal CustomUserDetails userDetails){
        String userEmail = userDetails.getUsername();
        try {
            Note mail = service.get(id);
            if (userEmail.equals(mail.getEmail())){
                service.delete(id);
                ra.addFlashAttribute("message", "Note(ID: "+ id+") deleted" );
            }
        } catch (NoteNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/notes";
    }
}
