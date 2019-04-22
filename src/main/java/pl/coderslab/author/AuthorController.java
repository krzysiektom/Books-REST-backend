package pl.coderslab.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/hello")
    String hello() {
        return "{hello :Word}";
    }

    @GetMapping("/")
    List<Author> authors() {
        return authorService.getList();
    }

    @GetMapping("/{id}")
    Author authorById(@PathVariable long id) {
        return authorService.getById(id);
    }

    @PostMapping("/")
    Author newAuthor(@RequestBody Author author) {
        return authorService.addNew(author);
    }

    @PutMapping("/{id}")
    Author modifyAuthor(@PathVariable long id,
                        @RequestBody Author author) {
        return authorService.modify(author, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Author> deleteAuthor(@PathVariable long id) {
        Author author = authorService.getById(id);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
