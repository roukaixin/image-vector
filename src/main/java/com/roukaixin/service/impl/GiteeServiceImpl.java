package com.roukaixin.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import com.roukaixin.service.GiteeService;
import com.roukaixin.utlis.GiteeUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class GiteeServiceImpl implements GiteeService {

    /**
     * 时间格式
     */
    private static String FORMAT = "yyyy/MM/dd";

    @Override
    public void upload(MultipartFile file) throws IOException {
        String content = Base64.encode(file.getInputStream());
        String path = DateUtil.format(new Date(), FORMAT) + "/" + file.getOriginalFilename();
        GiteeUtil.createNewFile(path,content,"上传图片");

    }
}
