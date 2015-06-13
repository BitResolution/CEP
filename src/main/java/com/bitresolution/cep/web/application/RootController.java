package com.bitresolution.cep.web.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootController {

    @RequestMapping("/")
    public ModelAndView showHomepage() {
        return new ModelAndView("index");
    }

    @RequestMapping("/view/{viewName}")
    public ModelAndView showAngularView(@PathVariable String viewName) {
        return new ModelAndView("angular/" + viewName + "::content");
    }
}
