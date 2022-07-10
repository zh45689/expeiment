package com.zh.bootStart.controller;

import com.zh.bootStart.bean.Type;
import com.zh.bootStart.bean.User;
import com.zh.bootStart.services.TypeServiceI;
import com.zh.bootStart.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class TypeController{
    @Autowired
    private TypeServiceI typeServiceI;
    @Autowired
    private ResultMap resultMap;

    @RequestMapping("/selectAllType")
    private ResultMap selectAllType(){
        try{
            List<Type> list = typeServiceI.selectAllType();
            resultMap.setStatus(true);
            resultMap.setList(list);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/addType")
    private ResultMap addType(String typeJson, HttpSession session){
        try{
            User user = (User) session.getAttribute("user");
            typeServiceI.addType(typeJson,user.getUsername());
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/editType")
    private ResultMap editType(String typeJson, HttpSession session){
        try{
            User user = (User) session.getAttribute("user");
            typeServiceI.editType(typeJson,user.getUsername());
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/delType")
    private ResultMap delType(String tid){
        try{
            typeServiceI.delType(tid);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }


}
