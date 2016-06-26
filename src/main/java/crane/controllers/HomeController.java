package crane.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by crane on 16/6/26.
 */
@RestController
@RequestMapping("/")
public class HomeController implements ErrorController {
    /**
     * 错误页面路径
     */
    private static final String ERROR_PATH = "/error";
    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/")
    String home() {
        return "Welcome to crane.tools !";
    }

    /**
     * Returns the path of the error page.
     *
     * @return the error path
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    public String error() {
        return "Sorry!!! You encountered an error ";
    }

    /**
     * 异常处理方法
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public Object exceptionHandler(final Throwable ex) {
        return ex;
    }
}
