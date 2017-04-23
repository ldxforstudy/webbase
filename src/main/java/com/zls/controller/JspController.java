package com.zls.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dxlau on 2017/4/23.
 */
@RequestMapping("/jsp")
@Controller
public class JspController {
    private static final Logger logger = LoggerFactory.getLogger(JspController.class);

    @RequestMapping("/home")
    public String home() {
        logger.debug("To home");
        return "home";
    }
}
