package com.zh.bootStart.controller;

import com.zh.bootStart.bean.User;
import com.zh.bootStart.services.UserServiceI;
import com.zh.bootStart.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController  //@Controller+@ResponseBody
public class UserController {
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private ResultMap resultMap;

    @RequestMapping("/login")
    public ResultMap login(String username, String password, HttpSession session){
        try{
            User user = userServiceI.login(username,password);
            session.setAttribute("user",user);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/getPersonMsg")
    public ResultMap getPersonMsg(HttpSession session){
        try{
            User user = (User) session.getAttribute("user");
            resultMap.setStatus(true);
            resultMap.setObject(user);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/updatePerson")
    public ResultMap updatePerson(String userJson, MultipartFile file){
        try{
            userServiceI.updatePerson(userJson,file);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

}
