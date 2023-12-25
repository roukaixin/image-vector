package com.roukaixin.utlis;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roukaixin.domain.Gitee;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * gitee 工具类
 *
 * @author 不北咪
 * @date 2023/3/28 22:10
 */
@Component
@ConditionalOnBean(value = Gitee.class)
public class GiteeUtil {


    private final Gitee giteeBean;

    private static Gitee gitee = new Gitee();


    @Autowired
    public GiteeUtil(Gitee giteeBean) {
        this.giteeBean = giteeBean;
        gitee = giteeBean;
    }


    /**
     * 新建文件
     * @param path 保存的文件路径（日期 + 文件名）
     * @param content base64 编码的图片
     * @param message 提交的信息
     * @return String
     */
    @SneakyThrows
    public static String createNewFile(String path, String content, String message){

        Map<String, String> map = new HashMap<>();
        map.put("access_token",gitee.getAccessToken());
        map.put("content",content);
        map.put("message",message);

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(map);
        /*
         * gitee 新建文件 api
         * https://gitee.com/api/v5/repos/{owner}/{repo}/contents/{path}
         */
        String CREATE_NEW_FILE_API = "https://gitee.com/api/v5/repos/%s/%s/contents/%s";
        String url = String.format(CREATE_NEW_FILE_API, gitee.getOwner(), gitee.getRepo(), path);

        String response = HttpUtil.post(url, body);

        return null;

    }

}
