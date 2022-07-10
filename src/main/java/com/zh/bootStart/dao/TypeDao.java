package com.zh.bootStart.dao;

import com.zh.bootStart.bean.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDao {

    /**
     * 查询所有类别
     * @return
     */
    public List<Type> selectAllType();

    /**
     * 创建实验类别
     * @param type
     * @return
     */
    public int addType(Type type);

    /**
     * 修改实验类别
     * @param type
     * @return
     */
    public int editType(Type type);

    /**
     * 删除实验类别
     * @param tid
     * @return
     */
    public int delType(String tid);
}
