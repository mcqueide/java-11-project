package br.com.arqdev;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String helloWorld(@RequestParam(value = "name", defaultValue = "Hello") String name,
                             @RequestParam(value = "lastName", defaultValue = "World") String lastName) {
        return List.of(name, lastName).stream()
                .collect(Collectors.joining(" "));
    }
}
