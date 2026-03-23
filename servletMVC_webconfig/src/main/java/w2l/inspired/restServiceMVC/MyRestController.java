package w2l.inspired.restServiceMVC;
import org.springframework.web.bind.annotation.*;

import w2l.inspired.restServiceMVC.domain.Message;

@RestController
public class MyRestController {

    @RequestMapping("/rest")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to RestTemplate Example.";
    }
    @GetMapping("/helloRest")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    //
    @RequestMapping("/helloRest1/{player}")

  // public Message helloRest(@RequestParam(value = "player", defaultValue = "World")String player){
    public Message message(@PathVariable String player) {//REST Endpoint.

        Message msg = new Message(player, "Hello " + player);

        return msg;
    }

}