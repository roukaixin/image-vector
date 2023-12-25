package com.roukaixin.controller;

import com.roukaixin.common.R;
import com.roukaixin.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * github 控制层
 *
 * @author 不北咪
 * @date 2023/3/30 22:27
 */
@RestController
@RequestMapping("/github")
public class GitHubController {

    private final GitHubService gitHubService;


    @Autowired
    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @PutMapping("/create")
    public R<String> create(MultipartFile file){
        return gitHubService.create(file);
    }

    @DeleteMapping("/delete")
    public R<String> delete(@RequestBody Map<String,String> path){
        return gitHubService.delete(path);
    }

    @PutMapping("/update")
    public R update(MultipartFile file, String path){
        return gitHubService.update(file, path);
    }
}
