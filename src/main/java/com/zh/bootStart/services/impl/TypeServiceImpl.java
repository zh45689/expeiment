package com.zh.bootStart.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.bootStart.bean.Type;
import com.zh.bootStart.dao.TypeDao;
import com.zh.bootStart.services.TypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TypeServiceImpl implements TypeServiceI {
    @Autowired
    private TypeDao typeDao;

    @Override
    public List<Type> selectAllType() {
        return typeDao.selectAllType();
    }

    @Override
    public void addType(String typeJson,String username) throws JsonProcessingException {
        //前端参数: typeJson所包含的属性，在Type实体类中必须全部存在
        Type type = new ObjectMapper().readValue(typeJson,Type.class);
        //生成全宇宙唯一的字符串
        String tid = UUID.randomUUID().toString();
        Date ttime = new Date(new java.util.Date().getTime());
        type.setTid(tid);
        type.setTtime(ttime);
        type.setUsername(username);
        int num = typeDao.addType(type);
        if(num == 0){
            throw  new RuntimeException("创建实验类别失败，请联系管理员！");
        }
    }

    @Override
    public void editType(String typeJson,String username) throws JsonProcessingException {
        Type type = new ObjectMapper().readValue(typeJson,Type.class);
        Date ttime = new Date(new java.util.Date().getTime());
        type.setTtime(ttime);
        type.setUsername(username);
        int num = typeDao.editType(type);
        if(num == 0){
            throw  new RuntimeException("创建实验类别失败，请联系管理员！");
        }
    }

    @Override
    public void delType(String tid) {
        if(tid.equals("")){
            throw new RuntimeException("未知实验类别！");
        }
        int num = typeDao.delType(tid);
        if(num == 0){
            throw  new RuntimeException("删除实验类别失败，请联系管理员！");
        }
    }
}
