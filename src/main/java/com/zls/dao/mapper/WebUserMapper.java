package com.zls.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.zls.dao.model.WebUser;

public interface WebUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WebUser record);

    int insertSelective(WebUser record);

    WebUser selectByPrimaryKey(Long id);
    
    WebUser selectByUsername(@Param("username")String username);

    int updateByPrimaryKeySelective(WebUser record);

    int updateByPrimaryKey(WebUser record);
}