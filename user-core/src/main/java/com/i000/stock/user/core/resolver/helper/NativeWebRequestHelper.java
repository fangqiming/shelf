package com.i000.stock.user.core.resolver.helper;

import org.apache.commons.io.IOUtils;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public final class NativeWebRequestHelper {

    private NativeWebRequestHelper() {
    }

    /**
     * 获取 Http Servlet Request
     *
     * @param nativeWebRequest request
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest(NativeWebRequest nativeWebRequest) {
        return nativeWebRequest.getNativeRequest(HttpServletRequest.class);
    }

    /**
     * 获取当前请求中的数据流
     *
     * @param nativeWebRequest request
     * @return InputStream
     * @throws IOException io exception
     */
    public static InputStream getRequestInputStream(NativeWebRequest nativeWebRequest)
            throws IOException {
        return getHttpServletRequest(nativeWebRequest).getInputStream();
    }

    /**
     * 解析当前请求体中的数据
     *
     * @param nativeWebRequest request
     * @param charset          编码
     * @return String
     * @throws IOException io exception
     */
    public static String parseBodyAsString(NativeWebRequest nativeWebRequest, Charset charset)
            throws IOException {
        return IOUtils.toString(getRequestInputStream(nativeWebRequest), charset);
    }
}