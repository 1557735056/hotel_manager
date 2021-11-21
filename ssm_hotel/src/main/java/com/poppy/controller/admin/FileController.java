package com.poppy.controller.admin;

import com.alibaba.fastjson.JSON;
import com.poppy.utils.UUIDUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author poppy
 * @Date 2021/11/7 15:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/file")
public class FileController {


    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile file){
        //创建map集合保存返回的JSON数据
        Map<String,Object> map = new HashMap<>();
        //判断是否有选中文件
        if(!file.isEmpty()){
            //获取文件上传的地址
            String path = "E:\\study_file\\hotel\\upload";
            //获取源文件的名称
            String oldFileName = file.getOriginalFilename();
            //获取文件的后缀名
            String extension = FilenameUtils.getExtension(oldFileName);
            //重新命名文件
            String newFileName = UUIDUtils.randomUUID()+"."+extension;
            //为了解决同一个文件夹下文件过多的问题，使用日期作为文件夹管理
            String dataPath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //组装最后的文件名
            String finalName = dataPath + "/" +newFileName;
            //创建文件对象
            File dest  = new File(path,finalName);
            //判断文件是否存在,不存在则创建文件夹
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();//创建文件夹
            }
            //进行文件上传
            try{
                file.transferTo(dest);
                map.put("code",0);
                map.put("msg","上传成功");
                Map<String,Object> dataMap = new HashMap<>();
                dataMap.put("src","/hotel/show/"+finalName);
                map.put("data",dataMap);//文件数据
                map.put("imagePath",finalName);//隐藏域的值,只保留日期文件夹+重命名的文件名
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(map);
    }
}
