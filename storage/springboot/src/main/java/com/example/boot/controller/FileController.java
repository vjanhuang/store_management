package com.example.boot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件上传相关接口
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private static final String FILE_UPLOAD_PATH = System.getProperty("user.dir") + File.separator + "/files/";

    @Value("${ip}")
    private String ip;

    /**
     * 文件上传接口
     *
     * @param file 前端传递过来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename;
        File uploadFile = new File(FILE_UPLOAD_PATH + fileName);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        while (uploadFile.exists()) {
            fileName = originalFilename + "_" + RandomUtil.randomNumbers(4);
            uploadFile = new File(FILE_UPLOAD_PATH + fileName);
        }
        // 上传文件到磁盘
        file.transferTo(uploadFile);
        return "http://" + ip + ":9090/file/" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
    }

    /**
     * 文件下载接口   http://localhost:9090/file/{fileName}
     *
     * @param fileName
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(FILE_UPLOAD_PATH + fileName);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        try {
            os.write(FileUtil.readBytes(uploadFile));
        } catch (Exception e) {
            System.err.println("文件下载失败，文件不存在");
        }
        os.flush();
        os.close();
    }

}
