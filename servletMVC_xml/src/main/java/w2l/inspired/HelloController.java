package w2l.inspired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String handle(Model model) {
        model.addAttribute("message", "Hello World! Welcome to Spring MVC with Gradle and XML.");
        return "index";
    }
//    @RequestMapping("/hello")
//    public String helloWorld(Model model) {
//        model.addAttribute("message", "Hello World! Welcome to Spring MVC with Gradle and XML.");
//        return "helloworld"; // Refers to the helloworld.jsp view
//    }
}
