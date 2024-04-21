package com.ken.taroserver.interceptor;

import com.ken.tarocommon.constant.JwtClaimsConstant;
import com.ken.tarocommon.context.BaseContext;
import com.ken.tarocommon.properties.JwtProperties;
import com.ken.tarocommon.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {
    @Autowired
    JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String authorizationHeader = request.getHeader("Authorization");
//        String token;
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
//            token = authorizationHeader.substring("Bearer ".length());
//            // do something with the token
//        } else {
//            token = null;
//        }
        // 1. 從請求頭拿 jwt token
        String token = request.getHeader(jwtProperties.getUserTokenName());
        //////////////////////////////////////////////////////
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        // if (token == null || token.trim().isempty()) {
        //     log.warn("jwt token is missing in the request");
        //     response.senderror(httpservletresponse.sc_unauthorized, "unauthorized: no jwt token provided");
        //     return false;
        // }
        // if(handlerMethod.getBean().getClass().getName().equals("org.springdoc.webmvc.ui.SwaggerConfigResource")){
        //     return  true;
        // }
        ////////////////////////////////////////////////////////////
        // 2. 校驗
        try {
            log.info("jwt test: {}",token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            log.info("user id: {}", userId);
            BaseContext.setCurrentId(userId);

            // 3. 通過，放行
            return true;
        } catch (Exception ex) {
            // 4. 不通過，響應401狀態碼            log.error("JWT token validation failed", ex);
            response.setStatus(401);
            return false;
        }
    }

}
