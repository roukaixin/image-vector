package com.roukaixin.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GiteeService {
    /**
     * 上传文件到 gitee 仓库
     * @param file 文件
     */
    void upload(MultipartFile file) throws IOException;
}
