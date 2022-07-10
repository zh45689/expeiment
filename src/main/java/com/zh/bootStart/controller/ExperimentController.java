package com.zh.bootStart.controller;

import com.zh.bootStart.bean.Experiment;
import com.zh.bootStart.bean.User;
import com.zh.bootStart.services.*;
import com.zh.bootStart.services.ExperimentServiceI;
import com.zh.bootStart.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ExperimentController {
    @Autowired
    private ExperimentServiceI experimentServiceI;
    @Autowired
    private ResultMap resultMap;

    @RequestMapping("/selectAllExp")
    private ResultMap selectAllExp(String conditionJson,HttpSession session){
        try{
            User user = (User) session.getAttribute("user");
            List<Experiment> list = experimentServiceI.selectAllExp(conditionJson,user.getUsername());
            long total = experimentServiceI.selectCountExp(user.getUsername());
            resultMap.setStatus(true);
            resultMap.setList(list);
            resultMap.setTotal(total);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/addCollection")
    private ResultMap addCollection(String eid,HttpSession session){
        try{
            User user = (User) session.getAttribute("user");
            experimentServiceI.addCollection(eid,user.getUsername());
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/selectDetail")
    private ResultMap selectDetail(String eid){
        try{
            Experiment exp = experimentServiceI.selectDetail(eid);
            resultMap.setStatus(true);
            resultMap.setObject(exp);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/selectMyCollection")
    private ResultMap selectMyCollection(HttpSession session){
        try{
            User user = (User) session.getAttribute("user");
            List<Experiment> list = experimentServiceI.selectMyCollection(user.getUsername());
            resultMap.setStatus(true);
            resultMap.setList(list);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/delCollection")
    private ResultMap delCollection(String ecid){
        try{
            experimentServiceI.delCollection(ecid);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/selectMyExp")
    private ResultMap selectMyExp(HttpSession session){
        try{
            User user = (User) session.getAttribute("user");
            List<Experiment> list = experimentServiceI.selectMyExp(user.getUsername());
            resultMap.setStatus(true);
            resultMap.setList(list);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/editExpPublish")
    private ResultMap editExpPublish(String eid){
        try{
            experimentServiceI.editExpPublish(eid);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/addExp")
    private ResultMap addExp(String expJson,HttpSession session){
        try{
            User user = (User) session.getAttribute("user");
            experimentServiceI.addExp(expJson,user.getUsername());
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/delExp")
    private ResultMap delExp(String eid){
        try{
            experimentServiceI.delExp(eid);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping("/editExp")
    private ResultMap editExp(String expJson){
        try{
            experimentServiceI.editExp(expJson);
            resultMap.setStatus(true);
        }catch (Exception e){
            resultMap.setStatus(false);
            resultMap.setMessage(e.getMessage());
        }
        return resultMap;
    }



}
