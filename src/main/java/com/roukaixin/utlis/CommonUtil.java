package com.roukaixin.utlis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.util.Assert;

import java.util.Date;

public class CommonUtil {

    /**
     * 获取文件上传路径
     * @param filename 文件名
     * @return String
     */
    public static String getPath(String filename){
        String date = DateUtil.formatDate(new Date());
        Assert.notNull(filename,"文件名为空");
        String suffix = filename.substring(filename.lastIndexOf("."));
        return date + "/" + IdUtil.getSnowflake(1, 1).nextId() + suffix;
    }
}
