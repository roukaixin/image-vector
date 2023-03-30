package com.kai.service.impl;

import cn.hutool.core.codec.Base64;
import com.kai.common.R;
import com.kai.enums.ActionEnum;
import com.kai.service.GitCodeService;
import com.kai.utlis.CommonUtil;
import com.kai.utlis.GitCodeUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * gitcode 实现类
 *
 * @author 不北咪
 * @date 2023/3/30 23:17
 */

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
        String pathUrl = gitCodeUtil.createNewFile(CommonUtil.getPath(filename), content);
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
        String pathUrl = gitCodeUtil.commitActions(ActionEnum.CREATE, CommonUtil.getPath(filename), content,null);
        return R.ok("https://gitcode.net/weixin_45300702/image_vector/-/raw/" + pathUrl);
    }

    @Override
    public R<String> delete(Map<String, String> path) {
        String s = gitCodeUtil.commitActions(ActionEnum.DELETE, path.get("path"), null, "删除一个图片", null);
        return R.ok(s);
    }


}
