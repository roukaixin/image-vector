package com.kai.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface GitCodeService {
    /**
     * 创建新文件
     * @param file 文件
     * @return String
     */
    String createNewFile(MultipartFile file);

    /**
     * 删除文件
     * @param map 文件路径
     */
    void deleteExistingFile(Map<String, String> map);


    String commitActions(MultipartFile file);
}
