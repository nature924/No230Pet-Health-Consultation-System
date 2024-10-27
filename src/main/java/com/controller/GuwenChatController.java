
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
 * 用户咨询
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/guwenChat")
public class GuwenChatController {
    private static final Logger logger = LoggerFactory.getLogger(GuwenChatController.class);

    private static final String TABLE_NAME = "guwenChat";

    @Autowired
    private GuwenChatService guwenChatService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private GuwenService guwenService;//顾问
    @Autowired
    private GuwenYuyueService guwenYuyueService;//顾问预约
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
        PageUtils page = guwenChatService.queryPage(params);

        //字典表数据转换
        List<GuwenChatView> list =(List<GuwenChatView>)page.getList();
        for(GuwenChatView c:list){
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
        GuwenChatEntity guwenChat = guwenChatService.selectById(id);
        if(guwenChat !=null){
            //entity转view
            GuwenChatView view = new GuwenChatView();
            BeanUtils.copyProperties( guwenChat , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(guwenChat.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"
, "guwenId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //级联表 顾问
            //级联表
            GuwenEntity guwen = guwenService.selectById(guwenChat.getGuwenId());
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
    public R save(@RequestBody GuwenChatEntity guwenChat, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,guwenChat:{}",this.getClass().getName(),guwenChat.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            guwenChat.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("顾问".equals(role))
            guwenChat.setGuwenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<GuwenChatEntity> queryWrapper = new EntityWrapper<GuwenChatEntity>()
            .eq("yonghu_id", guwenChat.getYonghuId())
            .eq("guwen_id", guwenChat.getGuwenId())
            .eq("zhuangtai_types", guwenChat.getZhuangtaiTypes())
            .eq("guwen_chat_types", guwenChat.getGuwenChatTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuwenChatEntity guwenChatEntity = guwenChatService.selectOne(queryWrapper);
        if(guwenChatEntity==null){
            guwenChat.setInsertTime(new Date());
            guwenChat.setCreateTime(new Date());
            guwenChatService.insert(guwenChat);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GuwenChatEntity guwenChat, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,guwenChat:{}",this.getClass().getName(),guwenChat.toString());
        GuwenChatEntity oldGuwenChatEntity = guwenChatService.selectById(guwenChat.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            guwenChat.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("顾问".equals(role))
//            guwenChat.setGuwenId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            guwenChatService.updateById(guwenChat);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<GuwenChatEntity> oldGuwenChatList =guwenChatService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        guwenChatService.deleteBatchIds(Arrays.asList(ids));

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
            List<GuwenChatEntity> guwenChatList = new ArrayList<>();//上传的东西
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
                            GuwenChatEntity guwenChatEntity = new GuwenChatEntity();
//                            guwenChatEntity.setYonghuId(Integer.valueOf(data.get(0)));   //提问人 要改的
//                            guwenChatEntity.setGuwenId(Integer.valueOf(data.get(0)));   //回答人 要改的
//                            guwenChatEntity.setGuwenChatIssueText(data.get(0));                    //问题 要改的
//                            guwenChatEntity.setIssueTime(sdf.parse(data.get(0)));          //问题时间 要改的
//                            guwenChatEntity.setGuwenChatReplyText(data.get(0));                    //回复 要改的
//                            guwenChatEntity.setReplyTime(sdf.parse(data.get(0)));          //回复时间 要改的
//                            guwenChatEntity.setZhuangtaiTypes(Integer.valueOf(data.get(0)));   //状态 要改的
//                            guwenChatEntity.setGuwenChatTypes(Integer.valueOf(data.get(0)));   //数据类型 要改的
//                            guwenChatEntity.setInsertTime(date);//时间
//                            guwenChatEntity.setCreateTime(date);//时间
                            guwenChatList.add(guwenChatEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        guwenChatService.insertBatch(guwenChatList);
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
        PageUtils page = guwenChatService.queryPage(params);

        //字典表数据转换
        List<GuwenChatView> list =(List<GuwenChatView>)page.getList();
        for(GuwenChatView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GuwenChatEntity guwenChat = guwenChatService.selectById(id);
            if(guwenChat !=null){


                //entity转view
                GuwenChatView view = new GuwenChatView();
                BeanUtils.copyProperties( guwenChat , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(guwenChat.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    GuwenEntity guwen = guwenService.selectById(guwenChat.getGuwenId());
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
    public R add(@RequestBody GuwenChatEntity guwenChat, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,guwenChat:{}",this.getClass().getName(),guwenChat.toString());
        Wrapper<GuwenChatEntity> queryWrapper = new EntityWrapper<GuwenChatEntity>()
            .eq("yonghu_id", guwenChat.getYonghuId())
            .eq("guwen_id", guwenChat.getGuwenId())
            .eq("guwen_chat_issue_text", guwenChat.getGuwenChatIssueText())
            .eq("guwen_chat_reply_text", guwenChat.getGuwenChatReplyText())
            .eq("zhuangtai_types", guwenChat.getZhuangtaiTypes())
            .eq("guwen_chat_types", guwenChat.getGuwenChatTypes())
//            .notIn("guwen_chat_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuwenChatEntity guwenChatEntity = guwenChatService.selectOne(queryWrapper);
        if(guwenChatEntity==null){
            guwenChat.setInsertTime(new Date());
            guwenChat.setCreateTime(new Date());
        guwenChatService.insert(guwenChat);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

