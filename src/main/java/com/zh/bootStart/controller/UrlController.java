package com.zh.bootStart.controller;

import com.zh.bootStart.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UrlController {

    @RequestMapping("/")
    public String main(){
        return "login";
    }

    @RequestMapping("goInLogin")
    public String goInLogin(HttpSession session){
        session.invalidate();//session失效
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();//session失效
        return "login";
    }

    @RequestMapping("/person")
    public String person(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "login";
        }
        return "person";
    }

    @RequestMapping("/index")
    public String indexHtml(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "login";
        }
        return "index";
    }

    @RequestMapping("/type")
    public String typeHtml(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "login";
        }
        return "type";
    }
    @RequestMapping("/experiment")
    public String experimentHtml(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "login";
        }
        return "experiment";
    }
    @RequestMapping("/myExperiment")
    public String myExperimentHtml(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "login";
        }
        return "myExperiment";
    }
    @RequestMapping("/collectionExp")
    public String collectionExpHtml(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "login";
        }
        return "collectionExp";
    }

}
