package com.example.boot.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.net.URLEncoder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.boot.common.Result;
import org.springframework.web.multipart.MultipartFile;
import com.example.boot.mapper.StoreMapper;
import com.example.boot.entity.Store;

import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* StoreAPI接口
* </p>
*
* 
*/
@RestController
@RequestMapping("/store")
public class StoreController {

    @Resource
    private StoreMapper storeMapper;

    @PostMapping
    public Result save(@RequestBody Store store) {
        storeMapper.insert(store);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Store store) {
        storeMapper.updateById(store);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        storeMapper.deleteById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        storeMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(storeMapper.selectList(null));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(storeMapper.selectById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<Store>().orderByDesc("id");
        queryWrapper.like(!"".equals(name), "name", name);
        Page<Store> page = storeMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }

    /**
    * 导出接口
   * 
 */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Store> list = storeMapper.selectList(null);
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("Store信息表", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    /**
    * excel 导入
    * @param file
    * @throws Exception
   * 
 */
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<Store> list = reader.readAll(Store.class);
        list.forEach(o -> storeMapper.insert(o));
        return Result.success();
    }

}
