package com.zh.bootStart.dao;

import com.zh.bootStart.bean.Collections;
import com.zh.bootStart.bean.Condition;
import com.zh.bootStart.bean.Experiment;
import com.zh.bootStart.bean.Step;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperimentDao {

    /**
     * 查询所有实验: 非本人创建 | 本人未收藏
     * @param condition
     * @return
     */
    public List<Experiment> selectAllExp(Condition condition);

    /**
     * 查询所有非本人创建 | 本人未收藏 的实验数据总条数
     * @param username
     * @return
     */
    public Long selectCountExp(String username);

    /**
     * 收藏实验
     * @param collections
     * @return
     */
    public int addCollection(Collections collections);

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
    public int delCollection(String ecid);

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
    public int editExpPublish(String eid);

    /**
     * 新增实验数据
     * @param experiment
     * @return
     */
    public int insertExp(Experiment experiment);

    /**
     * 新增步骤数据
     * @param step
     * @return
     */
    public int insertStep(Step step);

    /**
     * 新增实验步骤关联表数据
     * @param step
     * @return
     */
    public int insertExpAndStep(Step step);

    /**
     * 删除实验表数据
     * @param eid
     * @return
     */
    public int delExp(String eid);

    /**
     * 查询所有步骤主键与 实验关联步骤主键
     * @param eid
     * @return
     */
    public List<Step> selectSteps(String eid);

    /**
     * 删除所有步骤主键
     * @param sid
     * @return
     */
    public int delStep(String sid);

    /**
     * 删除所有 实验关联步骤主键
     * @param esid
     * @return
     */
    public int delExpAndStep(String esid);

    /**
     * 编辑实验
     * @param experiment
     * @return
     */
    public int editExp(Experiment experiment);

    /**
     * 编辑实验步骤
     * @param step
     * @return
     */
    public int editStep(Step step);

    /**
     * 根据类别名称查询类别主键
     * @param tname
     * @return
     */
    public String selectTidByTname(String tname);
}
