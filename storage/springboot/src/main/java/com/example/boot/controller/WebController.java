package com.example.boot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.boot.common.Result;
import com.example.boot.config.AuthAccess;
import com.example.boot.entity.Store;
import com.example.boot.entity.Storein;
import com.example.boot.entity.Storeout;
import com.example.boot.entity.User;
import com.example.boot.mapper.StoreMapper;
import com.example.boot.mapper.StoreinMapper;
import com.example.boot.mapper.StoreoutMapper;
import com.example.boot.mapper.UserMapper;
import com.example.boot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class WebController {

    @Resource
    UserMapper userMapper;

    @Resource
    StoreinMapper storeinMapper;

    @Resource
    StoreoutMapper storeoutMapper;

    @Resource
    StoreMapper storeMapper;

    @GetMapping("/")
    public Result success() {
        return Result.success();
    }

    /**
     * 登录
     *
     * @param user
     * @return
    * 
 */
    @AuthAccess
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error("参数错误");
        }
        User dbUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (dbUser == null || !user.getPassword().equals(dbUser.getPassword())) {
            return Result.error("用户名或密码错误");
        }
        if (dbUser.getDisable()) {
            return Result.error("禁止登录");
        }
        String token = TokenUtils.genToken(String.valueOf(dbUser.getId()), dbUser.getPassword());
        dbUser.setToken(token);
        dbUser.setPassword(null);
        return Result.success(dbUser);
    }

    /**
     * 注册
     *
     * @param user
     * @return
    * 
 */
    @AuthAccess
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error("参数错误");
        }
        User dbUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (dbUser != null) {
            return Result.error("用户名已存在");
        }
        if (StrUtil.isBlank(user.getName())) {
            user.setName("小可爱" + RandomUtil.randomNumbers(6) + "号");
        }
        userMapper.insert(user);
        return Result.success();
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
    * 
 */
    @PutMapping("/password")
    public Result password(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword()) || StrUtil.isBlank(user.getNewPass())) {
            return Result.error("参数错误");
        }
        userMapper.update(null, new UpdateWrapper<User>().set("password", user.getNewPass())
                .eq("username", user.getUsername()).eq("password", user.getPassword()));
        return Result.success();
    }

    /**
     * 重置密码
     * @param user
     * @return
    * 
 */
    @AuthAccess
    @PutMapping("/resetPassword")
    public Result resetPassword(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPhone())) {
            return Result.error("参数错误");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("phone", user.getPhone());
        List<User> list = userMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            User dnUser = list.get(0);
            dnUser.setPassword("123");
            userMapper.updateById(dnUser);
        } else {
            return Result.error("未找到用户");
        }
        return Result.success();
    }

    @GetMapping("/data")
    public Result getData() {
        Long users = userMapper.selectCount(null);
        Integer in = storeinMapper.selectList(null).stream().map(Storein::getNum).reduce(Integer::sum).orElse(0);
        Integer out = storeoutMapper.selectList(null).stream().map(Storeout::getNum).reduce(Integer::sum).orElse(0);
        Integer store = storeMapper.selectList(null).stream().map(Store::getNum).reduce(Integer::sum).orElse(0);
        return Result.success(Dict.create().set("users", users).set("in", in).set("out", out).set("store", store));
    }

    // 折线图
    @GetMapping("/line")
    public Result getLine() {
        List<Storein> storeins = storeinMapper.selectList(null);
        List<Storeout> storeouts = storeoutMapper.selectList(null);
        List<Store> stores = storeMapper.selectList(null);
        Date today = new Date();
        DateTime date = DateUtil.offsetDay(today, -6);
        DateRange range = DateUtil.range(date, today, DateField.DAY_OF_WEEK);
        List<String> dates = new ArrayList<>();
        List<Integer> listIn = new ArrayList<>();
        List<Integer> listOut = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (DateTime dateTime : range) {
            dates.add(DateUtil.formatDate(dateTime));
            Integer inCount = storeins.stream().filter(in -> DateUtil.formatDate(dateTime).equals(in.getDate())).map(Storein::getNum).reduce(Integer::sum).orElse(0);
            listIn.add(inCount);
            Integer outCount = storeouts.stream().filter(out -> DateUtil.formatDate(dateTime).equals(out.getDate())).map(Storeout::getNum).reduce(Integer::sum).orElse(0);
            listOut.add(outCount);
            Integer count = stores.stream().filter(s -> DateUtil.formatDate(dateTime).equals(s.getDate())).map(Store::getNum).reduce(Integer::sum).orElse(0);
            list.add(count);
        }

        return Result.success(Dict.create().set("dates", dates).set("in", listIn).set("out", listOut).set("store", list));
    }

}
