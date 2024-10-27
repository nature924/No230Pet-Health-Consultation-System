package com.dao;

import com.entity.GuwenYuyueEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.GuwenYuyueView;

/**
 * 顾问预约 Dao 接口
 *
 * @author 
 */
public interface GuwenYuyueDao extends BaseMapper<GuwenYuyueEntity> {

   List<GuwenYuyueView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
