
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
 * 健康知识
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiankangzhishi")
public class JiankangzhishiController {
    private static final Logger logger = LoggerFactory.getLogger(JiankangzhishiController.class);

    private static final String TABLE_NAME = "jiankangzhishi";

    @Autowired
    private JiankangzhishiService jiankangzhishiService;


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
    private GuwenYuyueService guwenYuyueService;//顾问预约
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
        params.put("jiankangzhishiDeleteStart",1);params.put("jiankangzhishiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = jiankangzhishiService.queryPage(params);

        //字典表数据转换
        List<JiankangzhishiView> list =(List<JiankangzhishiView>)page.getList();
        for(JiankangzhishiView c:list){
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
        JiankangzhishiEntity jiankangzhishi = jiankangzhishiService.selectById(id);
        if(jiankangzhishi !=null){
            //entity转view
            JiankangzhishiView view = new JiankangzhishiView();
            BeanUtils.copyProperties( jiankangzhishi , view );//把实体数据重构到view中
            //级联表 顾问
            //级联表
            GuwenEntity guwen = guwenService.selectById(jiankangzhishi.getGuwenId());
            if(guwen != null){
            BeanUtils.copyProperties( guwen , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "guwenId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody JiankangzhishiEntity jiankangzhishi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiankangzhishi:{}",this.getClass().getName(),jiankangzhishi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("顾问".equals(role))
            jiankangzhishi.setGuwenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JiankangzhishiEntity> queryWrapper = new EntityWrapper<JiankangzhishiEntity>()
            .eq("guwen_id", jiankangzhishi.getGuwenId())
            .eq("jiankangzhishi_name", jiankangzhishi.getJiankangzhishiName())
            .eq("jiankangzhishi_video", jiankangzhishi.getJiankangzhishiVideo())
            .eq("zan_number", jiankangzhishi.getZanNumber())
            .eq("cai_number", jiankangzhishi.getCaiNumber())
            .eq("jiankangzhishi_types", jiankangzhishi.getJiankangzhishiTypes())
            .eq("shangxia_types", jiankangzhishi.getShangxiaTypes())
            .eq("jiankangzhishi_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiankangzhishiEntity jiankangzhishiEntity = jiankangzhishiService.selectOne(queryWrapper);
        if(jiankangzhishiEntity==null){
            jiankangzhishi.setZanNumber(1);
            jiankangzhishi.setCaiNumber(1);
            jiankangzhishi.setJiankangzhishiClicknum(1);
            jiankangzhishi.setShangxiaTypes(1);
            jiankangzhishi.setJiankangzhishiDelete(1);
            jiankangzhishi.setInsertTime(new Date());
            jiankangzhishi.setCreateTime(new Date());
            jiankangzhishiService.insert(jiankangzhishi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiankangzhishiEntity jiankangzhishi, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jiankangzhishi:{}",this.getClass().getName(),jiankangzhishi.toString());
        JiankangzhishiEntity oldJiankangzhishiEntity = jiankangzhishiService.selectById(jiankangzhishi.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("顾问".equals(role))
//            jiankangzhishi.setGuwenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(jiankangzhishi.getJiankangzhishiPhoto()) || "null".equals(jiankangzhishi.getJiankangzhishiPhoto())){
                jiankangzhishi.setJiankangzhishiPhoto(null);
        }
        if("".equals(jiankangzhishi.getJiankangzhishiFile()) || "null".equals(jiankangzhishi.getJiankangzhishiFile())){
                jiankangzhishi.setJiankangzhishiFile(null);
        }
        if("".equals(jiankangzhishi.getJiankangzhishiVideo()) || "null".equals(jiankangzhishi.getJiankangzhishiVideo())){
                jiankangzhishi.setJiankangzhishiVideo(null);
        }

            jiankangzhishiService.updateById(jiankangzhishi);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JiankangzhishiEntity> oldJiankangzhishiList =jiankangzhishiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<JiankangzhishiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            JiankangzhishiEntity jiankangzhishiEntity = new JiankangzhishiEntity();
            jiankangzhishiEntity.setId(id);
            jiankangzhishiEntity.setJiankangzhishiDelete(2);
            list.add(jiankangzhishiEntity);
        }
        if(list != null && list.size() >0){
            jiankangzhishiService.updateBatchById(list);
        }

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
            List<JiankangzhishiEntity> jiankangzhishiList = new ArrayList<>();//上传的东西
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
                            JiankangzhishiEntity jiankangzhishiEntity = new JiankangzhishiEntity();
//                            jiankangzhishiEntity.setGuwenId(Integer.valueOf(data.get(0)));   //顾问 要改的
//                            jiankangzhishiEntity.setJiankangzhishiName(data.get(0));                    //健康知识名称 要改的
//                            jiankangzhishiEntity.setJiankangzhishiUuidNumber(data.get(0));                    //健康知识编号 要改的
//                            jiankangzhishiEntity.setJiankangzhishiPhoto("");//详情和图片
//                            jiankangzhishiEntity.setJiankangzhishiFile(data.get(0));                    //附件 要改的
//                            jiankangzhishiEntity.setJiankangzhishiVideo(data.get(0));                    //视频 要改的
//                            jiankangzhishiEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            jiankangzhishiEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            jiankangzhishiEntity.setJiankangzhishiTypes(Integer.valueOf(data.get(0)));   //健康知识类型 要改的
//                            jiankangzhishiEntity.setJiankangzhishiClicknum(Integer.valueOf(data.get(0)));   //健康知识热度 要改的
//                            jiankangzhishiEntity.setJiankangzhishiContent("");//详情和图片
//                            jiankangzhishiEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            jiankangzhishiEntity.setJiankangzhishiDelete(1);//逻辑删除字段
//                            jiankangzhishiEntity.setInsertTime(date);//时间
//                            jiankangzhishiEntity.setCreateTime(date);//时间
                            jiankangzhishiList.add(jiankangzhishiEntity);


                            //把要查询是否重复的字段放入map中
                                //健康知识编号
                                if(seachFields.containsKey("jiankangzhishiUuidNumber")){
                                    List<String> jiankangzhishiUuidNumber = seachFields.get("jiankangzhishiUuidNumber");
                                    jiankangzhishiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jiankangzhishiUuidNumber = new ArrayList<>();
                                    jiankangzhishiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jiankangzhishiUuidNumber",jiankangzhishiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //健康知识编号
                        List<JiankangzhishiEntity> jiankangzhishiEntities_jiankangzhishiUuidNumber = jiankangzhishiService.selectList(new EntityWrapper<JiankangzhishiEntity>().in("jiankangzhishi_uuid_number", seachFields.get("jiankangzhishiUuidNumber")).eq("jiankangzhishi_delete", 1));
                        if(jiankangzhishiEntities_jiankangzhishiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiankangzhishiEntity s:jiankangzhishiEntities_jiankangzhishiUuidNumber){
                                repeatFields.add(s.getJiankangzhishiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [健康知识编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiankangzhishiService.insertBatch(jiankangzhishiList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<JiankangzhishiView> returnJiankangzhishiViewList = new ArrayList<>();

        //查看收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = jiankangzhishiCollectionService.queryPage(params1);
        List<JiankangzhishiCollectionView> collectionViewsList =(List<JiankangzhishiCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(JiankangzhishiCollectionView collectionView:collectionViewsList){
            Integer jiankangzhishiTypes = collectionView.getJiankangzhishiTypes();
            if(typeMap.containsKey(jiankangzhishiTypes)){
                typeMap.put(jiankangzhishiTypes,typeMap.get(jiankangzhishiTypes)+1);
            }else{
                typeMap.put(jiankangzhishiTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("jiankangzhishiTypes",type);
            PageUtils pageUtils1 = jiankangzhishiService.queryPage(params2);
            List<JiankangzhishiView> jiankangzhishiViewList =(List<JiankangzhishiView>)pageUtils1.getList();
            returnJiankangzhishiViewList.addAll(jiankangzhishiViewList);
            if(returnJiankangzhishiViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = jiankangzhishiService.queryPage(params);
        if(returnJiankangzhishiViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnJiankangzhishiViewList.size();//要添加的数量
            List<JiankangzhishiView> jiankangzhishiViewList =(List<JiankangzhishiView>)page.getList();
            for(JiankangzhishiView jiankangzhishiView:jiankangzhishiViewList){
                Boolean addFlag = true;
                for(JiankangzhishiView returnJiankangzhishiView:returnJiankangzhishiViewList){
                    if(returnJiankangzhishiView.getId().intValue() ==jiankangzhishiView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnJiankangzhishiViewList.add(jiankangzhishiView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnJiankangzhishiViewList = returnJiankangzhishiViewList.subList(0, limit);
        }

        for(JiankangzhishiView c:returnJiankangzhishiViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnJiankangzhishiViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = jiankangzhishiService.queryPage(params);

        //字典表数据转换
        List<JiankangzhishiView> list =(List<JiankangzhishiView>)page.getList();
        for(JiankangzhishiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiankangzhishiEntity jiankangzhishi = jiankangzhishiService.selectById(id);
            if(jiankangzhishi !=null){

                //点击数量加1
                jiankangzhishi.setJiankangzhishiClicknum(jiankangzhishi.getJiankangzhishiClicknum()+1);
                jiankangzhishiService.updateById(jiankangzhishi);

                //entity转view
                JiankangzhishiView view = new JiankangzhishiView();
                BeanUtils.copyProperties( jiankangzhishi , view );//把实体数据重构到view中

                //级联表
                    GuwenEntity guwen = guwenService.selectById(jiankangzhishi.getGuwenId());
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
    public R add(@RequestBody JiankangzhishiEntity jiankangzhishi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jiankangzhishi:{}",this.getClass().getName(),jiankangzhishi.toString());
        Wrapper<JiankangzhishiEntity> queryWrapper = new EntityWrapper<JiankangzhishiEntity>()
            .eq("guwen_id", jiankangzhishi.getGuwenId())
            .eq("jiankangzhishi_name", jiankangzhishi.getJiankangzhishiName())
            .eq("jiankangzhishi_uuid_number", jiankangzhishi.getJiankangzhishiUuidNumber())
            .eq("jiankangzhishi_video", jiankangzhishi.getJiankangzhishiVideo())
            .eq("zan_number", jiankangzhishi.getZanNumber())
            .eq("cai_number", jiankangzhishi.getCaiNumber())
            .eq("jiankangzhishi_types", jiankangzhishi.getJiankangzhishiTypes())
            .eq("jiankangzhishi_clicknum", jiankangzhishi.getJiankangzhishiClicknum())
            .eq("shangxia_types", jiankangzhishi.getShangxiaTypes())
            .eq("jiankangzhishi_delete", jiankangzhishi.getJiankangzhishiDelete())
//            .notIn("jiankangzhishi_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiankangzhishiEntity jiankangzhishiEntity = jiankangzhishiService.selectOne(queryWrapper);
        if(jiankangzhishiEntity==null){
                jiankangzhishi.setZanNumber(1);
                jiankangzhishi.setCaiNumber(1);
            jiankangzhishi.setJiankangzhishiClicknum(1);
            jiankangzhishi.setJiankangzhishiDelete(1);
            jiankangzhishi.setInsertTime(new Date());
            jiankangzhishi.setCreateTime(new Date());
        jiankangzhishiService.insert(jiankangzhishi);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

