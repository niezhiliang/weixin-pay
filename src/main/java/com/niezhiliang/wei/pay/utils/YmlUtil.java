package com.niezhiliang.wei.pay.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */

public class YmlUtil {
    private static final Logger logger = LoggerFactory.getLogger(YmlUtil.class);
    /**
     * 获取yml的值
     * @param key
     * @return
     */

    public static String get(String key) {
        String [] nodes = key.split("\\.");
        Yaml yaml = new Yaml();
        ClassPathResource resource = new ClassPathResource("application.yml");
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object obj = yaml.load(inputStream);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(obj);
        for (int i = 0; i < nodes.length; i++) {

            if (i == nodes.length-1) {
                return jsonObject.getString(nodes[i]);
            }
            jsonObject = (JSONObject) JSON.toJSON(jsonObject.get(nodes[i]));
        }
        return null;
    }
}
