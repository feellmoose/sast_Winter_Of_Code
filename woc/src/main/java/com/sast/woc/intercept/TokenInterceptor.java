package com.sast.woc.intercept;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.sast.woc.ResultData.MyException;
import com.sast.woc.ResultData.ReturnCode;
import com.sast.woc.Utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    private  String[] urls={
            "/login"
    };
    private  String[] userUrls={
            "/user/info",
            "/user/edit_info",
            "/user/register"
    };
    private  String[] adminUrls={
            "/admin/show_all",
            "/admin/find_user_info",
            "/admin/del_user"
    };
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        String url= request.getRequestURI();
        String token= request.getHeader("Token");
        String method= request.getMethod();
        //忽略options请求
        if (!method.equals("OPTIONS")) {
            //先遍历所有不需要token的urls路径
            for (String item : this.urls) {
                if (item.equals(url)) {
                    return true;
                }
            }
            //解析token
            DecodedJWT verify;
            try {
                verify = TokenUtil.tokenVerify(token);
            } catch (Exception e) {
                throw new MyException(ReturnCode.valueOf("INVALID_TOKEN"));
            }
            //从payload中获取role
            Integer role = verify.getClaim("role").asInt();
            //若为普通用户
            if (role == 0) {
                //遍历所有普通用户可用urls
                for (String item : this.userUrls) {
                    if (item.equals(url)) {
                        return true;
                    }
                }
            }
            //若为管理员
            if (role == 1) {
                //遍历所有管理员可用urls
                for (String item : this.adminUrls) {
                    if (item.equals(url)) {
                        return true;
                    }
                }
            }
        }

        //若为其他页面或url不在可使用范围
        throw new MyException(ReturnCode.valueOf("ACCESS_DENIED"));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
