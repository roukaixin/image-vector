package com.kai.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.kai.enums.ActionEnum;
import com.kai.service.GitCodeService;
import com.kai.utlis.GitCodeUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Slf4j
public class GitCodeServiceImpl implements GitCodeService {


    private final GitCodeUtil gitCodeUtil;

    @Autowired
    public GitCodeServiceImpl(GitCodeUtil gitCodeUtil) {
        this.gitCodeUtil = gitCodeUtil;
    }


    @SneakyThrows
    @Override
    public String createNewFile(MultipartFile file) {
        String date = DateUtil.formatDate(new Date());
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String path = date + "/" + IdUtil.getSnowflake(1, 1).nextId() + suffix;
        String content = Base64.encode(file.getInputStream());
        String pathUrl = gitCodeUtil.createNewFile(path, content);
        return "https://gitcode.net/weixin_45300702/image_vector/-/raw/" + pathUrl;
    }

    @Override
    public void deleteExistingFile(Map<String, String> map) {
        gitCodeUtil.deleteExistingFile(map.get("path"));
    }

    @SneakyThrows
    @Override
    public String commitActions(MultipartFile file) {
        String date = DateUtil.formatDate(new Date());
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String path = date + "/" + IdUtil.getSnowflake(1, 1).nextId() + suffix;
        String content = Base64.encode(file.getInputStream());
        String pathUrl = gitCodeUtil.commitActions(ActionEnum.CREATE, path, content,null);
        return "https://gitcode.net/weixin_45300702/image_vector/-/raw/" + pathUrl;
    }
}
