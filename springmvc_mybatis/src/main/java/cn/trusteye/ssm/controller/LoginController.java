package cn.trusteye.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Rayman on 2017/3/2.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(HttpSession session,String username,String password) throws Exception{
        //身份验证
        //...
        session.setAttribute("username",username);
        return "redirect:/items/queryItems.action";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) throws Exception{
        session.invalidate();
        return "redirect:/items/queryItems.action";
    }
}
