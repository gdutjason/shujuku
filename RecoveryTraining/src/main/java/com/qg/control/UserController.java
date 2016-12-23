package com.qg.control;

import com.qg.control.base.BaseController;
import com.qg.entity.User;
import com.qg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.qg.control.base.BaseController.UN_SUCCESS;

/**
 * Created by symon on 16-12-23.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password) {
        User user = userService.login(username, password);
        if(user != null) {
            session.setAttribute("user", user);
            return BaseController.SUCCESS;
        } else {
            return BaseController.UN_SUCCESS;
        }
    }
}
