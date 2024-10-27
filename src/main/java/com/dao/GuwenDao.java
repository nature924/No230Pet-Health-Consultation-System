package com.dao;

import com.entity.GuwenEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.GuwenView;

/**
 * 顾问 Dao 接口
 *
 * @author 
 */
public interface GuwenDao extends BaseMapper<GuwenEntity> {

   List<GuwenView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
