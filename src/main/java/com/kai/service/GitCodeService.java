package com.kai.service;

import com.kai.common.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface GitCodeService {

    /**
     * 创建新文件
     * @param file 文件
     * @return String
     */
    R<String> createNewFile(MultipartFile file);

    /**
     * 删除文件 （只能删除文件）
     * @param map 文件路径
     */
    R<Object> deleteExistingFile(Map<String, String> map);


    /**
     * 创建新文件（可以创建目录）
     * @param file 文件
     * @return R
     */
    R<String> create(MultipartFile file);

    /**
     * 删除文件
     * @param path 文件路径
     * @return R
     */
    R<String> delete(Map<String, String> path);
}
