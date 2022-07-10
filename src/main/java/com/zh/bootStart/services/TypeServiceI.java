package com.zh.bootStart.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zh.bootStart.bean.Type;

import java.util.List;

public interface TypeServiceI {

    /**
     * 查询所有类别
     * @return
     */
    public List<Type> selectAllType();

    /**
     * 创建实验类别
     * @param typeJson
     * @return
     */
    public void addType(String typeJson,String username) throws JsonProcessingException;

    /**
     * 修改实验类别
     * @param typeJson
     * @return
     */
    public void editType(String typeJson,String username) throws JsonProcessingException;

    /**
     * 删除实验类别
     * @param tid
     * @return
     */
    public void delType(String tid);
}
