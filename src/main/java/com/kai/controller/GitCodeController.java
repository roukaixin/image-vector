package com.kai.controller;

import com.kai.common.R;
import com.kai.service.GitCodeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public R<String> createNewFile(MultipartFile file){
        return gitCodeService.createNewFile(file);
    }

    @SneakyThrows
    @DeleteMapping
    public R<Object> deleteExistingFile(@RequestBody Map<String,String> map){
        return gitCodeService.deleteExistingFile(map);
    }

    @SneakyThrows
    @PostMapping("/create")
    public R<String> create(MultipartFile file){
        return gitCodeService.create(file);
    }

    @SneakyThrows
    @PostMapping("/delete")
    public R<String> delete(@RequestBody Map<String,String> path){
        return gitCodeService.delete(path);
    }
}
