package crane.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by crane on 16/7/18.
 */
@Controller
public class PageController {
    @RequestMapping("/qq/img")
    String qqimg() {
        return "forward:/page/qcloud/qimg.html";
    }
}
