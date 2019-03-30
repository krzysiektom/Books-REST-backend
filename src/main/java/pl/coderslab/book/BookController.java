package pl.coderslab.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
    class BookController {
        @RequestMapping("/hello")
        String hello(){
            return "{hello: World}";
        }
    }