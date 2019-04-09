package pl.coderslab.author;

import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
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
    void  deleteAuthor (@PathVariable long id){
        authorService.delete(id);
    }
}
