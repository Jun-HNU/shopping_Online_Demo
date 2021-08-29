package com.hnu.websocket.server;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
//@RequestMapping("webSocket")
public class WebSocketController {

    @RequestMapping(value = "messagePage/{userID}")
    public ModelAndView messagePage(@PathVariable String userID, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("userID", userID);
        mav.setViewName("wekSocket");
        return mav;
    }
}