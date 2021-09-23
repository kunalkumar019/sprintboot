package com.kunal.rest.webservices.resetfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//Controller
//@SpringBootApplication
@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;
    //GET
    //URL - /hello-world
    //Method - "HelloWorld"
    //@RequestMapping(method = RequestMethod.GET, path="/hello-world")
    @GetMapping(path="/hello-world")
    public String helloWorld(){
        return "Hello World";

    }

    //hello-world-bean
    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello Wold");

    }

    @GetMapping(path ="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name)
    {
        return new HelloWorldBean(String.format("Hello world %s",name));
    }

    @GetMapping(path ="/hello-world-internationalized")
    public String helloWorldInternalionalized(@RequestHeader(name="Accept-Language", required=false) Locale locale)
    {
        return messageSource.getMessage("good.morning.message",null,"Default message", LocaleContextHolder.getLocale());
        //return messageSource.getMessage("good.morning.message",null,"Default message",locale);
       // return new HelloWorldBean("Hello world");
        //en= Hello World
        //nl= Geode Morgen
        //fr = Bonjour
    }
}
