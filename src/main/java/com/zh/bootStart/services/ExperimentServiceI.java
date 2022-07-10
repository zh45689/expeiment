package com.zh.bootStart.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zh.bootStart.bean.Experiment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExperimentServiceI {

    /**
     * 查询所有实验: 非本人创建 | 本人未收藏
     * @param conditionJson
     * @param username
     * @return
     */
    public List<Experiment> selectAllExp(String conditionJson, String username) throws JsonProcessingException;

    /**
     * 查询所有非本人创建 | 本人未收藏 的实验数据总条数
     * @param username
     * @return
     */
    public Long selectCountExp(String username);

    /**
     * 收藏实验
     * @param eid
     * @param username
     */
    public void addCollection(String eid,String username);

    /**
     * 查询实验详情
     * @param eid
     * @return
     */
    public Experiment selectDetail(String eid);

    /**
     * 查询所有我收藏的实验
     * @param username
     * @return
     */
    public List<Experiment> selectMyCollection(String username);

    /**
     * 取消收藏实验
     * @param ecid
     */
    public void delCollection(String ecid);

    /**
     * 查询我的所有实验
     * @param username
     * @return
     */
    public List<Experiment> selectMyExp(String username);

    /**
     * 发布实验: 根据实验主键修改 发布状态
     * @param eid
     */
    public void editExpPublish(String eid);

    /**
     * 创建实验
     * @param expJson
     */
    public void addExp(String expJson,String username) throws JsonProcessingException;

    /**
     * 删除实验
     * @param eid
     */
    public void delExp(String eid);

    /**
     * 编辑实验
     * @param expJson
     */
    public void editExp(String expJson) throws JsonProcessingException;


}
