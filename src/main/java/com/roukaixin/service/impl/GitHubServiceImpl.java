package com.roukaixin.service.impl;

import cn.hutool.core.codec.Base64;
import com.roukaixin.common.R;
import com.roukaixin.enums.ActionEnum;
import com.roukaixin.service.GitHubService;
import com.roukaixin.utlis.CommonUtil;
import com.roukaixin.utlis.GitHubUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * github 接口实现类
 *
 * @author 不北咪
 * @date 2023/3/30 22:28
 */
@Service
@Slf4j
public class GitHubServiceImpl implements GitHubService {

    private final GitHubUtil gitHubUtil;

    @Autowired
    public GitHubServiceImpl(GitHubUtil gitHubUtil) {
        this.gitHubUtil = gitHubUtil;
    }

    @SneakyThrows
    @Override
    public R<String> create(MultipartFile file) {
        String content = Base64.encode(file.getInputStream());
        String response = gitHubUtil.contents(ActionEnum.CREATE, CommonUtil.getPath(file.getOriginalFilename()), content, "上传图片");
        log.info(response);
        return R.ok(response);
    }

    @Override
    public R<String> delete(Map<String, String> path) {
        String response = gitHubUtil.contents(ActionEnum.DELETE, path.get("path"), null, "删除文件");
        log.info(response);
        return R.ok();
    }

    @SneakyThrows
    @Override
    public R<String> update(MultipartFile file, String path) {
        String content = Base64.encode(file.getInputStream());
        String response = gitHubUtil.contents(ActionEnum.UPDATE, path, content, "更新文件");
        log.info(response);
        return R.ok(response);
    }

}
