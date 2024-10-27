package com.dao;

import com.entity.JiankangzhishiCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiankangzhishiCollectionView;

/**
 * 健康知识收藏 Dao 接口
 *
 * @author 
 */
public interface JiankangzhishiCollectionDao extends BaseMapper<JiankangzhishiCollectionEntity> {

   List<JiankangzhishiCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
