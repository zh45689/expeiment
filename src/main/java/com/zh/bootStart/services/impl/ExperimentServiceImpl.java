package com.zh.bootStart.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.bootStart.bean.*;
import com.zh.bootStart.bean.Collections;
import com.zh.bootStart.bean.Condition;
import com.zh.bootStart.bean.Experiment;
import com.zh.bootStart.bean.Step;
import com.zh.bootStart.dao.ExperimentDao;
import com.zh.bootStart.services.ExperimentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExperimentServiceImpl implements ExperimentServiceI {
    @Autowired
    private ExperimentDao experimentDao;

    @Override
    public List<Experiment> selectAllExp(String conditionJson, String username) throws JsonProcessingException {
        Condition condition = new ObjectMapper().readValue(conditionJson,Condition.class);
        //取出当前页码数、一页显示条目
        int currentPage = condition.getCurrentPage();
        int pageSize = condition.getPageSize();
        //利用公式，将当前页码数转换为sql语句中limit的条件
        currentPage = (currentPage-1)*pageSize;

        condition.setCurrentPage(currentPage);
        condition.setUsername(username);
        return experimentDao.selectAllExp(condition);
    }

    @Override
    public Long selectCountExp(String username) {
        return experimentDao.selectCountExp(username);
    }

    @Override
    public void addCollection(String eid, String username) {
        Collections collections = new Collections(eid,username);
        collections.setEcid(UUID.randomUUID().toString());
        int num = experimentDao.addCollection(collections);
        if(num == 0){
            throw  new RuntimeException("收藏失败，请联系管理员！");
        }
    }

    @Override
    public Experiment selectDetail(String eid) {
        Experiment experiment = experimentDao.selectDetail(eid);
        if(experiment == null){
            throw  new RuntimeException("此实验负责人暂未填写实验步骤，请耐心等待！");
        }
        return experiment;

    }

    @Override
    public List<Experiment> selectMyCollection(String username) {
        return experimentDao.selectMyCollection(username);
    }

    @Override
    public void delCollection(String ecid) {
        int num =experimentDao.delCollection(ecid);
        if(num == 0){
            throw  new RuntimeException("取消收藏失败，请联系管理员！");
        }
    }

    @Override
    public List<Experiment> selectMyExp(String username) {
        return experimentDao.selectMyExp(username);
    }

    @Override
    public void editExpPublish(String eid) {
        int num = experimentDao.editExpPublish(eid);
        if(num == 0){
            throw  new RuntimeException("发布实验失败，请联系管理员！");
        }
    }

    @Override
    public void addExp(String expJson,String username) throws JsonProcessingException {
        //{ename:"",tid:"",steps:[{sdata:""},..,{}]}
        Experiment experiment = new ObjectMapper().readValue(expJson,Experiment.class);
        //1、向实验表新增数据 -- 实验数据: 主键、实验名称、创建人用户名、创建时间、实验类别主键
        String eid = UUID.randomUUID().toString();
        experiment.setEid(eid);
        experiment.setUsername(username);
        Date etime = new Date(new java.util.Date().getTime());
        experiment.setEtime(etime);
        int num = experimentDao.insertExp(experiment);
        if(num == 0){
            throw  new RuntimeException("添加实验数据失败，请联系管理员！");
        }
        //取出所有步骤
        List<Step> list = experiment.getSteps();
        for (int i = 0;i<list.size();i++){
            //2、向步骤表新增数据 -- 步骤数据: 主键、步骤名称、步骤内容
            Step step = list.get(i);
            step.setSid(UUID.randomUUID().toString());
            step.setSname("步骤"+(i+1));
            num = experimentDao.insertStep(step);
            if(num == 0){
                throw  new RuntimeException("添加实验步骤失败，请联系管理员！");
            }
            //3、向实验_步骤关联表新增数据: 主键、实验主键、步骤主键
            step.setEsid(UUID.randomUUID().toString());
            step.setEid(eid);
            num = experimentDao.insertExpAndStep(step);
            if(num == 0){
                throw  new RuntimeException("添加实验步骤失败，请联系管理员！");
            }
        }
    }

    @Override
    public void delExp(String eid) {
        //利用eid查询所有sid，再利用eid查询所有esid
        //先利用实验主键  -- 查询到所有的 关联主键、步骤主键
        //[{sid:"",esid:""},...]
        List<Step> list = experimentDao.selectSteps(eid);

        //1、删除实验表数据
        int num = experimentDao.delExp(eid);
        if(num == 0){
            throw  new RuntimeException("删除实验数据失败，请联系管理员！");
        }

        if(list.size() != 0){//当实验没有步骤时，不需要删除步骤相关数据
            for (int i = 0; i < list.size(); i++) {
                String sid = list.get(i).getSid();
                String esid = list.get(i).getEsid();
                //2、删除步骤表数据
                num = experimentDao.delStep(sid);
                if(num == 0){
                    throw  new RuntimeException("删除实验步骤失败，请联系管理员！");
                }
                //3、删除实验步骤关联表数据
                num = experimentDao.delExpAndStep(esid);
                if(num == 0){
                    throw  new RuntimeException("删除实验关联步骤失败，请联系管理员！");
                }
            }
        }
    }

    @Override
    public void editExp(String expJson) throws JsonProcessingException {
        Experiment experiment = new ObjectMapper().readValue(expJson,Experiment.class);
        //后续操作与新增无异，所有数据都修改一遍即可

        //1、向实验表新增数据 -- 实验数据: 主键、实验名称、创建人用户名、创建时间、实验类别主键
        Date etime = new Date(new java.util.Date().getTime());
        experiment.setEtime(etime);
        //务必重新放置  {ename:"",type:{tid:"1"},tid:""}
        experiment.setTid(experiment.getType().getTid());
        int num = experimentDao.editExp(experiment);
        if(num == 0){
            throw  new RuntimeException("添加实验数据失败，请联系管理员！");
        }
        //取出所有步骤
        List<Step> list = experiment.getSteps();
        for (int i = 0;i<list.size();i++){
            //2、向步骤表新增数据 -- 步骤数据: 主键、步骤名称、步骤内容
            Step step = list.get(i);
            num = experimentDao.editStep(step);
            if(num == 0){
                throw  new RuntimeException("添加实验步骤失败，请联系管理员！");
            }
        }
    }



}
