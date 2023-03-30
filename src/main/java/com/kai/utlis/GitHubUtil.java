package com.kai.utlis;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.kai.domain.GitHub;
import com.kai.enums.ActionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * github
 *
 * @author 不北咪
 * @date 2023/3/30 21:42
 */
@ConditionalOnBean(GitHub.class)
@Component
public class GitHubUtil {

    private final GitHub gitHub;

    private String GITHUBHOST = "https://api.github.com/";

    @Autowired
    public GitHubUtil(GitHub gitHub) {
        this.gitHub = gitHub;
    }

    /**
     * 新建或更新文件
     * @param path 文件路径
     * @param content 文件内容
     * @param message 消息
     * @return string
     */
    public String contents(ActionEnum actionEnum, String path, String content, String message){

        String url = GITHUBHOST + "/repos/" + gitHub.getOwner() + "/" + gitHub.getRepo() + "/contents/" + path;

        Map<String, Object> map = new HashMap<>();
        map.put("message",message);

        switch (actionEnum){
            case DELETE:{
                map.put("sha", getSha(url));
                return HttpRequest
                        .delete(url)
                        .header("Authorization", "Bearer " + gitHub.getAuthorization())
                        .body(JSON.toJSONString(map))
                        .execute()
                        .body();
            }
            case CREATE:{
                Map<String, Object> body = getBody(map, content);
                String response = httpPost(url, map);
                return "https://raw.kgithub.com/roukaixin/" + gitHub.getRepo() + "/" + gitHub.getBranch() + "/" + path ;
            }
            case UPDATE: {
                Map<String, Object> body = getBody(map, content);
                body.put("sha", getSha(url));
                String response = httpPost(url, map);
                return "https://raw.kgithub.com/roukaixin/" + gitHub.getRepo() + "/" + gitHub.getBranch() + "/" + path ;
            }
            default:
                return null;
        }
    }

    /**
     * 获取文件 sha 值
     * @param url 文件路径
     * @return String
     */
    private String getSha(String url){
        String getResponse = HttpRequest
                .get(url)
                .header("Authorization", "Bearer " + gitHub.getAuthorization())
                .execute()
                .body();
        return JSON.parseObject(getResponse).get("sha").toString();
    }

    /**
     * put 请求
     * @param url url
     * @param map body参数
     * @return String
     */
    private String httpPost(String url, Map<String,Object> map){
        return HttpRequest
                .put(url)
                .header("Authorization", "Bearer " + gitHub.getAuthorization())
                .body(JSON.toJSONString(map))
                .execute().body();
    }

    private Map<String,Object> getBody(Map<String,Object> map, String content){
        map.put("content",content);
        Map<String, String> committer = new HashMap<>();
        committer.put("name",gitHub.getOwner());
        committer.put("email", "!a3427173515@163.com");
        map.put("committer", committer);
        return map;
    }
}
