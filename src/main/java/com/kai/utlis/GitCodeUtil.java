package com.kai.utlis;

import cn.hutool.http.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kai.domain.GitCode;
import com.kai.enums.ActionEnum;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * gitcode 工具类
 *
 * @author 不北咪
 * @date 2023/3/29 20:48
 */
@Component
@ConditionalOnBean(GitCode.class)
public class GitCodeUtil {

    private final GitCode gitCode;

    private String GITCODEHOST = "https://gitcode.net/api/v4";

    @Autowired
    public GitCodeUtil(GitCode gitCode) {
        this.gitCode = gitCode;
    }

    /**
     * 创建新文件（不会创建目录）
     * @param filePath 文件路径
     * @param content 图片内容（base64编码）
     * @param commitMessage 提交内容
     * @return String
     */
    @SneakyThrows
    public String createNewFile(String filePath, String content, String commitMessage){
        String url = GITCODEHOST + "/projects/" + gitCode.getId() + "/repository/files/" + filePath;
        Map<String, String> map = new HashMap<>();
        map.put("branch",gitCode.getBranch());
        map.put("content",content);
        map.put("commit_message",commitMessage);
        // encoding 默认是 text
        map.put("encoding","base64");
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(map);
        String response = HttpRequest
                .post(url)
                .header("PRIVATE-TOKEN", gitCode.getPrivateToken())
                .body(body)
                .execute().body();
        String path = objectMapper.readTree(response).get("file_path").textValue();
        String branch = objectMapper.readTree(response).get("branch").textValue();
        return "/" + branch +"/" + path;
    }


    /**
     * 创建新文件（不会创建目录）
     * @param filePath 文件路径
     * @param content 图片内容（base64编码）
     * @return 图片访问路径
     */
    public String createNewFile(String filePath, String content){
        return this.createNewFile(filePath,content,"api 上传一张图片");
    }


    /**
     * 删除文件
     * @param path 文件路径 需要对 path 进行 url 编码
     * @param commitMessage 消息
     */
    @SneakyThrows
    public void deleteExistingFile(String path, String commitMessage){
        String encodePath = URLEncoder.encode(path, "UTF-8");
        String url = GITCODEHOST + "/projects/" + gitCode.getId() + "/repository/files/" + encodePath;
        Map<String, String> map = new HashMap<>();
        map.put("branch", gitCode.getBranch());
        map.put("commit_message", commitMessage);
        String body = new ObjectMapper().writeValueAsString(map);
        String response = HttpRequest
                .delete(url)
                .header("PRIVATE-TOKEN", gitCode.getPrivateToken())
                .body(body)
                .execute().body();
    }

    /**
     * 删除文件
     * @param path 文件路径 需要对 path 进行 url 编码
     */
    @SneakyThrows
    public void deleteExistingFile(String path){
        this.deleteExistingFile(path,"删除一个文件");
    }


    /**
     * 创建包含多个文件和操作的提交（这个才会创建目录）
     * create：创建、delete：删除、move：移动、update：更新
     * @param filePath 文件路径
     * @param content 图片内容（base64编码）
     * @param commitMessage 提交内容
     * @return String
     */
    @SneakyThrows
    public String commitActions(ActionEnum actionEnum, String filePath, String content, String commitMessage, String previousPath){
        String url = GITCODEHOST + "/projects/" + gitCode.getId() + "/repository/commits";
        Map<String, Object> map = new HashMap<>();
        map.put("branch",gitCode.getBranch());
        map.put("commit_message",commitMessage);
        List<Map<String, String>> actions = new ArrayList<>();
        Map<String, String> action = new HashMap<>();
        action.put("action",actionEnum.getAction());
        action.put("file_path",filePath);
        // encoding 默认是 text
        action.put("encoding","base64");
        if (!actionEnum.equals(ActionEnum.DELETE)){
            action.put("content",content);
            if (actionEnum.equals(ActionEnum.MOVE)){
                map.put("previous_path",previousPath);
            }
        }
        actions.add(action);
        map.put("actions",actions);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(map);
        String response = HttpRequest
                .post(url)
                .header("PRIVATE-TOKEN", gitCode.getPrivateToken())
                .body(body)
                .execute().body();
        return "/" + gitCode.getBranch() +"/" + filePath;
    }

    /**
     * 创建包含多个文件和操作的提交（这个才会创建目录）
     * @param filePath 文件路径
     * @param content 图片内容（base64编码）
     * @return 图片访问路径
     */
    public String commitActions(ActionEnum actionEnum, String filePath, String content, String previousPath){
        return this.commitActions(actionEnum, filePath, content,"api 上传一张图片", previousPath);
    }


}
