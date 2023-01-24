package net.securespringapp;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    static final String secretKey = "wUZOQ6Xc+1lenkZTQ9ZDf";
    @Autowired private NoteRepository repo;

    public List<Note> listAll(){
        return (List<Note>) repo.findAll();
    }

    public void save(Note note) {
        String title = note.getTitle();
        String saf = Jsoup.clean(title, Safelist.basic());
        note.setTitle(saf);

        String markdown = note.getText();
        String unsafe = convertMarkdownToHTML(markdown);
        String safe = Jsoup.clean(unsafe, Safelist.relaxed());
        note.setText(safe);
        repo.save(note);
    }
    public void saveEncrypted(Note note) {
//        if (note.getPassword()==null){
//            note.setPassword("");
//        }

        Optional<Note> correct = repo.findById(note.getId());
        String correctPassword = correct.get().getPassword();

        String originalString= note.getText();
//        System.out.println(note.getPassword());
//        System.out.println(note);
        if (note.isEncrypted()){
//            System.out.println("hura!");
            if (passwordEncoder.matches(note.getPassword(), correctPassword)){
//                System.out.println("decrypting!");
                String decryptedString = Aes.decrypt(originalString, secretKey);
                note.setText(decryptedString);
                note.setEncrypted(false);
                String encPassword = passwordEncoder.encode(note.getPassword());
                note.setPassword(encPassword);
                repo.save(note);
            }
        } else{
//            System.out.println("encrypting!");
            String encryptedString = Aes.encrypt(originalString, secretKey) ;
            System.out.println(Aes.decrypt(encryptedString, secretKey));
            note.setText(encryptedString);
            note.setEncrypted(true);
            String encPassword = passwordEncoder.encode(note.getPassword());
            note.setPassword(encPassword);
            repo.save(note);
        }


    }



    public Note get(Integer id) throws NoteNotFoundException {
        Optional<Note> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new NoteNotFoundException("Could not find note with id : " + id);
    }
    public void delete(Integer id) throws NoteNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new NoteNotFoundException("Could not find note with id : " + id);
        }
        repo.deleteById(id);
    }

    private String convertMarkdownToHTML(String markdown) {
        if (markdown==null){
            return "";
        }
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
        return htmlRenderer.render(document);
    }
}
