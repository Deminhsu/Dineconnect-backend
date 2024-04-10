package com.ken.tarocommon.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "dinner.jwt")
public class JwtProperties {
    // 管理員
//    private String adminSecretKey;
//    private long adminTtl;
//    private String adminTokenName;
    // 用戶
    private String userSecretKey;
    private long userTtl; // ?
    private String userTokenName;
}
