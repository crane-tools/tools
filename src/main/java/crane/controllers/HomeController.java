package crane.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by crane on 16/6/26.
 */
@Controller
@RequestMapping("/")
public class HomeController {
    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/")
    String home() {
        return "forward:/index.html";
    }

}
