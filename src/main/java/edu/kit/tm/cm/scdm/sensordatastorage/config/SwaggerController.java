package edu.kit.tm.cm.scdm.sensordatastorage.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

    /**
     * Redirect for Swagger to swagger URL
     *
     * @return String for redirection
     */
    @GetMapping("/")
    public String redirect() {
        return "redirect:/swagger-ui.html";
    }
}
