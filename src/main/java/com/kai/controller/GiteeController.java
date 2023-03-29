package com.kai.controller;

import com.kai.service.GiteeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * gitee 控制层
 *
 * @author 不北咪
 * @date 2023/3/28 23:28
 */
@RestController
@RequestMapping("/gitee")
public class GiteeController {

    private final GiteeService giteeService;

    @Autowired
    public GiteeController(GiteeService giteeService) {
        this.giteeService = giteeService;
    }

    @SneakyThrows
    @PostMapping("/upload")
    public void upload(MultipartFile file){
        giteeService.upload(file);
    }
}
