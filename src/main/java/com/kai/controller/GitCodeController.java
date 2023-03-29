package com.kai.controller;

import cn.hutool.core.codec.Base64;
import com.kai.domain.GitCode;
import com.kai.service.GitCodeService;
import com.kai.utlis.GitCodeUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

/**
 * gitcode 控制层
 *
 * @author 不北咪
 * @date 2023/3/29 21:03
 */
@RestController
@RequestMapping("/gitcode")
@Slf4j
public class GitCodeController {

    private final GitCodeService gitCodeService;

    @Autowired
    public GitCodeController(GitCodeService gitCodeService) {
        this.gitCodeService = gitCodeService;
    }

    @SneakyThrows
    @PostMapping
    public String createNewFile(MultipartFile file){
        return gitCodeService.createNewFile(file);
    }

    @SneakyThrows
    @DeleteMapping
    public void deleteExistingFile(@RequestBody Map<String,String> map){
        gitCodeService.deleteExistingFile(map);
    }

    @SneakyThrows
    @PostMapping("/commitActions")
    public String commitActions(MultipartFile file){
        return gitCodeService.commitActions(file);
    }
}
