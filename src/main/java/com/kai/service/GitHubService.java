package com.kai.service;

import com.kai.common.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * github 接口
 *
 * @author 不北咪
 * @date 2023/3/30 22:28
 */
public interface GitHubService {

    /**
     * 上传文件
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

    /**
     * 更新文件
     * @param file 文件
     * @param path 旧文件路径
     * @return R
     */
    R<String> update(MultipartFile file, String path);
}
