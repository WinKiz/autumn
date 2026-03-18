package ru.bgpu.autumn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bgpu.autumn.dto.HelloMessageDTO;
import ru.bgpu.autumn.services.HelloService;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired private HelloService helloService;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public HelloMessageDTO index(@PathVariable(name = "name") String name) {
        return new HelloMessageDTO(name, helloService.message(name));
    }
}
