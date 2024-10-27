
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
 * 顾问
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/guwen")
public class GuwenController {
    private static final Logger logger = LoggerFactory.getLogger(GuwenController.class);

    private static final String TABLE_NAME = "guwen";

    @Autowired
    private GuwenService guwenService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private GuwenChatService guwenChatService;//用户咨询
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
        PageUtils page = guwenService.queryPage(params);

        //字典表数据转换
        List<GuwenView> list =(List<GuwenView>)page.getList();
        for(GuwenView c:list){
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
        GuwenEntity guwen = guwenService.selectById(id);
        if(guwen !=null){
            //entity转view
            GuwenView view = new GuwenView();
            BeanUtils.copyProperties( guwen , view );//把实体数据重构到view中
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
    public R save(@RequestBody GuwenEntity guwen, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,guwen:{}",this.getClass().getName(),guwen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<GuwenEntity> queryWrapper = new EntityWrapper<GuwenEntity>()
            .eq("username", guwen.getUsername())
            .or()
            .eq("guwen_phone", guwen.getGuwenPhone())
            .or()
            .eq("guwen_id_number", guwen.getGuwenIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuwenEntity guwenEntity = guwenService.selectOne(queryWrapper);
        if(guwenEntity==null){
            guwen.setCreateTime(new Date());
            guwen.setPassword("123456");
            guwenService.insert(guwen);
            return R.ok();
        }else {
            return R.error(511,"账户或者顾问手机号或者顾问身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GuwenEntity guwen, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,guwen:{}",this.getClass().getName(),guwen.toString());
        GuwenEntity oldGuwenEntity = guwenService.selectById(guwen.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(guwen.getGuwenPhoto()) || "null".equals(guwen.getGuwenPhoto())){
                guwen.setGuwenPhoto(null);
        }

            guwenService.updateById(guwen);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<GuwenEntity> oldGuwenList =guwenService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        guwenService.deleteBatchIds(Arrays.asList(ids));

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
            List<GuwenEntity> guwenList = new ArrayList<>();//上传的东西
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
                            GuwenEntity guwenEntity = new GuwenEntity();
//                            guwenEntity.setUsername(data.get(0));                    //账户 要改的
//                            guwenEntity.setPassword("123456");//密码
//                            guwenEntity.setGuwenName(data.get(0));                    //顾问姓名 要改的
//                            guwenEntity.setGuwenPhone(data.get(0));                    //顾问手机号 要改的
//                            guwenEntity.setGuwenIdNumber(data.get(0));                    //顾问身份证号 要改的
//                            guwenEntity.setGuwenPhoto("");//详情和图片
//                            guwenEntity.setGuwenShanchang(data.get(0));                    //擅长 要改的
//                            guwenEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            guwenEntity.setGuwenEmail(data.get(0));                    //顾问邮箱 要改的
//                            guwenEntity.setGuwenContent("");//详情和图片
//                            guwenEntity.setCreateTime(date);//时间
                            guwenList.add(guwenEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //顾问手机号
                                if(seachFields.containsKey("guwenPhone")){
                                    List<String> guwenPhone = seachFields.get("guwenPhone");
                                    guwenPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> guwenPhone = new ArrayList<>();
                                    guwenPhone.add(data.get(0));//要改的
                                    seachFields.put("guwenPhone",guwenPhone);
                                }
                                //顾问身份证号
                                if(seachFields.containsKey("guwenIdNumber")){
                                    List<String> guwenIdNumber = seachFields.get("guwenIdNumber");
                                    guwenIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> guwenIdNumber = new ArrayList<>();
                                    guwenIdNumber.add(data.get(0));//要改的
                                    seachFields.put("guwenIdNumber",guwenIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<GuwenEntity> guwenEntities_username = guwenService.selectList(new EntityWrapper<GuwenEntity>().in("username", seachFields.get("username")));
                        if(guwenEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GuwenEntity s:guwenEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //顾问手机号
                        List<GuwenEntity> guwenEntities_guwenPhone = guwenService.selectList(new EntityWrapper<GuwenEntity>().in("guwen_phone", seachFields.get("guwenPhone")));
                        if(guwenEntities_guwenPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GuwenEntity s:guwenEntities_guwenPhone){
                                repeatFields.add(s.getGuwenPhone());
                            }
                            return R.error(511,"数据库的该表中的 [顾问手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //顾问身份证号
                        List<GuwenEntity> guwenEntities_guwenIdNumber = guwenService.selectList(new EntityWrapper<GuwenEntity>().in("guwen_id_number", seachFields.get("guwenIdNumber")));
                        if(guwenEntities_guwenIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GuwenEntity s:guwenEntities_guwenIdNumber){
                                repeatFields.add(s.getGuwenIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [顾问身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        guwenService.insertBatch(guwenList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        GuwenEntity guwen = guwenService.selectOne(new EntityWrapper<GuwenEntity>().eq("username", username));
        if(guwen==null || !guwen.getPassword().equals(password))
            return R.error("账号或密码不正确");
        String token = tokenService.generateToken(guwen.getId(),username, "guwen", "顾问");
        R r = R.ok();
        r.put("token", token);
        r.put("role","顾问");
        r.put("username",guwen.getGuwenName());
        r.put("tableName","guwen");
        r.put("userId",guwen.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody GuwenEntity guwen, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<GuwenEntity> queryWrapper = new EntityWrapper<GuwenEntity>()
            .eq("username", guwen.getUsername())
            .or()
            .eq("guwen_phone", guwen.getGuwenPhone())
            .or()
            .eq("guwen_id_number", guwen.getGuwenIdNumber())
            ;
        GuwenEntity guwenEntity = guwenService.selectOne(queryWrapper);
        if(guwenEntity != null)
            return R.error("账户或者顾问手机号或者顾问身份证号已经被使用");
        guwen.setCreateTime(new Date());
        guwenService.insert(guwen);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        GuwenEntity guwen = guwenService.selectById(id);
        guwen.setPassword("123456");
        guwenService.updateById(guwen);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        GuwenEntity guwen = guwenService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(guwen.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(guwen.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        guwen.setPassword(newPassword);
		guwenService.updateById(guwen);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        GuwenEntity guwen = guwenService.selectOne(new EntityWrapper<GuwenEntity>().eq("username", username));
        if(guwen!=null){
            guwen.setPassword("123456");
            guwenService.updateById(guwen);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrGuwen(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        GuwenEntity guwen = guwenService.selectById(id);
        if(guwen !=null){
            //entity转view
            GuwenView view = new GuwenView();
            BeanUtils.copyProperties( guwen , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = guwenService.queryPage(params);

        //字典表数据转换
        List<GuwenView> list =(List<GuwenView>)page.getList();
        for(GuwenView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GuwenEntity guwen = guwenService.selectById(id);
            if(guwen !=null){


                //entity转view
                GuwenView view = new GuwenView();
                BeanUtils.copyProperties( guwen , view );//把实体数据重构到view中

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
    public R add(@RequestBody GuwenEntity guwen, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,guwen:{}",this.getClass().getName(),guwen.toString());
        Wrapper<GuwenEntity> queryWrapper = new EntityWrapper<GuwenEntity>()
            .eq("username", guwen.getUsername())
            .or()
            .eq("guwen_phone", guwen.getGuwenPhone())
            .or()
            .eq("guwen_id_number", guwen.getGuwenIdNumber())
//            .notIn("guwen_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuwenEntity guwenEntity = guwenService.selectOne(queryWrapper);
        if(guwenEntity==null){
            guwen.setCreateTime(new Date());
            guwen.setPassword("123456");
        guwenService.insert(guwen);

            return R.ok();
        }else {
            return R.error(511,"账户或者顾问手机号或者顾问身份证号已经被使用");
        }
    }

}

