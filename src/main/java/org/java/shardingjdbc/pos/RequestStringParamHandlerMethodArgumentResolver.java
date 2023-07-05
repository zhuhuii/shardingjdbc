package org.java.shardingjdbc.pos;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * RequestStringParam参数解析器 实现 HandlerMethodArgumentResolver 接口
 * @author created by wutong li on 2021/04/29
 */
public class RequestStringParamHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestSingleParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestSingleParam requestSingleParam = parameter.getParameterAnnotation(RequestSingleParam.class);
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int rd;
        while ((rd = reader.read(buf)) != -1) {
            sb.append(buf, 0, rd);
        }
        JSONObject jsonObject = JSONObject.parseObject(sb.toString());
        String value = requestSingleParam.value();
        return jsonObject.get(value);
    }

}
