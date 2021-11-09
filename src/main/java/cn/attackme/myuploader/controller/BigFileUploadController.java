package cn.attackme.myuploader.controller;

import cn.attackme.myuploader.service.FileService;
import cn.attackme.myuploader.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 大文件上传
 */
@RestController
@RequestMapping("/BigFile")
@CrossOrigin
public class BigFileUploadController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public void upload(String name,
                       String md5,
                       Long size,
                       Integer chunks,
                       Integer chunk,
                       MultipartFile file) throws IOException {
        if (chunks != null && chunks != 0) {
            fileService.uploadWithBlock(name, md5,size,chunks,chunk,file);
        } else {
            fileService.upload(name, md5,file);
        }
    }

    @RequestMapping("/showChunkMap")
    public String showChunkMap(){
        Map<String, UploadUtils.Value> map = UploadUtils.chunkMap;
        return map.toString();
    }

}
