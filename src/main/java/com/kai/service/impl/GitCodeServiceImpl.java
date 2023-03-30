package com.kai.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.kai.common.R;
import com.kai.enums.ActionEnum;
import com.kai.service.GitCodeService;
import com.kai.utlis.GitCodeUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

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
    public R<String> createNewFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String content = Base64.encode(file.getInputStream());
        String pathUrl = gitCodeUtil.createNewFile(getPath(filename), content);
        return R.ok("https://gitcode.net/weixin_45300702/image_vector/-/raw/" + pathUrl);
    }

    @Override
    public R<Object> deleteExistingFile(Map<String, String> map) {
        gitCodeUtil.deleteExistingFile(map.get("path"));
        return R.ok();
    }

    @SneakyThrows
    @Override
    public R<String> create(MultipartFile file) {
        String content = Base64.encode(file.getInputStream());
        String filename = file.getOriginalFilename();
        String pathUrl = gitCodeUtil.commitActions(ActionEnum.CREATE, getPath(filename), content,null);
        return R.ok("https://gitcode.net/weixin_45300702/image_vector/-/raw/" + pathUrl);
    }

    @Override
    public R<String> delete(Map<String, String> path) {
        String s = gitCodeUtil.commitActions(ActionEnum.DELETE, path.get("path"), null, "删除一个图片", null);
        return R.ok(s);
    }


    /**
     * 获取文件上传路径
     * @param filename 文件名
     * @return String
     */
    private String getPath(String filename){
        String date = DateUtil.formatDate(new Date());
        Assert.notNull(filename,"文件名为空");
        String suffix = filename.substring(filename.lastIndexOf("."));
        return date + "/" + IdUtil.getSnowflake(1, 1).nextId() + suffix;
    }
}
