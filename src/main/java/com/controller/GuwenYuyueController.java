
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 顾问预约
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/guwenYuyue")
public class GuwenYuyueController {
    private static final Logger logger = LoggerFactory.getLogger(GuwenYuyueController.class);

    private static final String TABLE_NAME = "guwenYuyue";

    @Autowired
    private GuwenYuyueService guwenYuyueService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private GuwenService guwenService;//顾问
    @Autowired
    private GuwenChatService guwenChatService;//用户咨询
    @Autowired
    private JiankangzhishiService jiankangzhishiService;//健康知识
    @Autowired
    private JiankangzhishiCollectionService jiankangzhishiCollectionService;//健康知识收藏
    @Autowired
    private JiankangzhishiLiuyanService jiankangzhishiLiuyanService;//健康知识留言
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("顾问".equals(role))
            params.put("guwenId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = guwenYuyueService.queryPage(params);

        //字典表数据转换
        List<GuwenYuyueView> list =(List<GuwenYuyueView>)page.getList();
        for(GuwenYuyueView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GuwenYuyueEntity guwenYuyue = guwenYuyueService.selectById(id);
        if(guwenYuyue !=null){
            //entity转view
            GuwenYuyueView view = new GuwenYuyueView();
            BeanUtils.copyProperties( guwenYuyue , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(guwenYuyue.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"
, "guwenId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //级联表 顾问
            //级联表
            GuwenEntity guwen = guwenService.selectById(guwenYuyue.getGuwenId());
            if(guwen != null){
            BeanUtils.copyProperties( guwen , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"
, "guwenId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setGuwenId(guwen.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody GuwenYuyueEntity guwenYuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,guwenYuyue:{}",this.getClass().getName(),guwenYuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            guwenYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("顾问".equals(role))
            guwenYuyue.setGuwenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<GuwenYuyueEntity> queryWrapper = new EntityWrapper<GuwenYuyueEntity>()
            .eq("yonghu_id", guwenYuyue.getYonghuId())
            .eq("guwen_id", guwenYuyue.getGuwenId())
            .eq("guwen_yuyue_time", new SimpleDateFormat("yyyy-MM-dd").format(guwenYuyue.getGuwenYuyueTime()))
            .in("guwen_yuyue_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuwenYuyueEntity guwenYuyueEntity = guwenYuyueService.selectOne(queryWrapper);
        if(guwenYuyueEntity==null){
            guwenYuyue.setInsertTime(new Date());
            guwenYuyue.setGuwenYuyueYesnoTypes(1);
            guwenYuyue.setCreateTime(new Date());
            guwenYuyueService.insert(guwenYuyue);
            return R.ok();
        }else {
            if(guwenYuyueEntity.getGuwenYuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(guwenYuyueEntity.getGuwenYuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GuwenYuyueEntity guwenYuyue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,guwenYuyue:{}",this.getClass().getName(),guwenYuyue.toString());
        GuwenYuyueEntity oldGuwenYuyueEntity = guwenYuyueService.selectById(guwenYuyue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            guwenYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("顾问".equals(role))
//            guwenYuyue.setGuwenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            guwenYuyueService.updateById(guwenYuyue);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody GuwenYuyueEntity guwenYuyueEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,guwenYuyueEntity:{}",this.getClass().getName(),guwenYuyueEntity.toString());

        GuwenYuyueEntity oldGuwenYuyue = guwenYuyueService.selectById(guwenYuyueEntity.getId());//查询原先数据

//        if(guwenYuyueEntity.getGuwenYuyueYesnoTypes() == 2){//通过
//            guwenYuyueEntity.setGuwenYuyueTypes();
//        }else if(guwenYuyueEntity.getGuwenYuyueYesnoTypes() == 3){//拒绝
//            guwenYuyueEntity.setGuwenYuyueTypes();
//        }
        guwenYuyueEntity.setGuwenYuyueShenheTime(new Date());//审核时间
        guwenYuyueService.updateById(guwenYuyueEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<GuwenYuyueEntity> oldGuwenYuyueList =guwenYuyueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        guwenYuyueService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<GuwenYuyueEntity> guwenYuyueList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            GuwenYuyueEntity guwenYuyueEntity = new GuwenYuyueEntity();
//                            guwenYuyueEntity.setGuwenYuyueUuidNumber(data.get(0));                    //报名编号 要改的
//                            guwenYuyueEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            guwenYuyueEntity.setGuwenId(Integer.valueOf(data.get(0)));   //顾问 要改的
//                            guwenYuyueEntity.setInsertTime(date);//时间
//                            guwenYuyueEntity.setGuwenYuyueText(data.get(0));                    //预约缘由 要改的
//                            guwenYuyueEntity.setGuwenYuyueTime(sdf.parse(data.get(0)));          //预约日期 要改的
//                            guwenYuyueEntity.setGuwenYuyueYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            guwenYuyueEntity.setGuwenYuyueYesnoText(data.get(0));                    //审核回复 要改的
//                            guwenYuyueEntity.setGuwenYuyueShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            guwenYuyueEntity.setCreateTime(date);//时间
                            guwenYuyueList.add(guwenYuyueEntity);


                            //把要查询是否重复的字段放入map中
                                //报名编号
                                if(seachFields.containsKey("guwenYuyueUuidNumber")){
                                    List<String> guwenYuyueUuidNumber = seachFields.get("guwenYuyueUuidNumber");
                                    guwenYuyueUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> guwenYuyueUuidNumber = new ArrayList<>();
                                    guwenYuyueUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("guwenYuyueUuidNumber",guwenYuyueUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报名编号
                        List<GuwenYuyueEntity> guwenYuyueEntities_guwenYuyueUuidNumber = guwenYuyueService.selectList(new EntityWrapper<GuwenYuyueEntity>().in("guwen_yuyue_uuid_number", seachFields.get("guwenYuyueUuidNumber")));
                        if(guwenYuyueEntities_guwenYuyueUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GuwenYuyueEntity s:guwenYuyueEntities_guwenYuyueUuidNumber){
                                repeatFields.add(s.getGuwenYuyueUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报名编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        guwenYuyueService.insertBatch(guwenYuyueList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = guwenYuyueService.queryPage(params);

        //字典表数据转换
        List<GuwenYuyueView> list =(List<GuwenYuyueView>)page.getList();
        for(GuwenYuyueView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GuwenYuyueEntity guwenYuyue = guwenYuyueService.selectById(id);
            if(guwenYuyue !=null){


                //entity转view
                GuwenYuyueView view = new GuwenYuyueView();
                BeanUtils.copyProperties( guwenYuyue , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(guwenYuyue.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    GuwenEntity guwen = guwenService.selectById(guwenYuyue.getGuwenId());
                if(guwen != null){
                    BeanUtils.copyProperties( guwen , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setGuwenId(guwen.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody GuwenYuyueEntity guwenYuyue, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,guwenYuyue:{}",this.getClass().getName(),guwenYuyue.toString());
        Wrapper<GuwenYuyueEntity> queryWrapper = new EntityWrapper<GuwenYuyueEntity>()
            .eq("yonghu_id", guwenYuyue.getYonghuId())
            .eq("guwen_id", guwenYuyue.getGuwenId())
            .eq("guwen_yuyue_time", new SimpleDateFormat("yyyy-MM-dd").format(guwenYuyue.getGuwenYuyueTime()))
            .in("guwen_yuyue_yesno_types", new Integer[]{1,2})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuwenYuyueEntity guwenYuyueEntity = guwenYuyueService.selectOne(queryWrapper);
        if(guwenYuyueEntity==null){
            guwenYuyue.setInsertTime(new Date());
            guwenYuyue.setGuwenYuyueYesnoTypes(1);
            guwenYuyue.setCreateTime(new Date());
        guwenYuyueService.insert(guwenYuyue);

            return R.ok();
        }else {
            if(guwenYuyueEntity.getGuwenYuyueYesnoTypes()==1)
                return R.error(511,"该用户该天已经预约了该顾问了,请等待审核");
            else if(guwenYuyueEntity.getGuwenYuyueYesnoTypes()==2)
                return R.error(511,"该用户该天已经预约通过了该顾问了,不能重复预约");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

