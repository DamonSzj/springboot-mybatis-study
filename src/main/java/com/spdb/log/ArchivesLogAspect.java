package com.spdb.log;

import com.spdb.annotation.ArchivesLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

@Aspect
@Component
public class ArchivesLogAspect {

    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间
    private HttpServletRequest request = null;

    /**
     * @param joinPoint
     * @Description: 方法调用后触发   记录结束时间
     * @author CB
     */
    @After(value = "within(com.spdb.controller.*) && @annotation(log)")
    public void after(JoinPoint joinPoint,ArchivesLog log) {
        startTimeMillis = System.currentTimeMillis();
        request = getHttpServletRequest();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = targetClass.getMethods();
        String operationName = "";
        String operationType = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs != null && clazzs.length == arguments.length && method.getAnnotation(ArchivesLog.class) != null) {
                    operationName = method.getAnnotation(ArchivesLog.class).operationName();
                    operationType = method.getAnnotation(ArchivesLog.class).operationType();
                    break;
                }
            }
        }
        endTimeMillis = System.currentTimeMillis();
        //操作时间
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
        String ip = getIpAddress(request);
        String uri = request.getRequestURI();//返回请求行中的资源名称
        String url = request.getRequestURL().toString();//获得客户端发送请求的完整url
        String params = request.getQueryString();//返回请求行中的参数部分
        String host = request.getRemoteHost();//返回发出请求的客户机的主机名
        int port = request.getRemotePort();//返回发出请求的客户机的端口号。
        System.out.println(ip);
        System.out.println(url);
        System.out.println(uri);
        System.out.println(params);
        System.out.println(host);
        System.out.println(port);
        System.out.println(" 操作人: " + request.getRemoteUser() + " 操作方法: " + operationName + " 操作时间: " + startTime
                + "operationType" + operationType + methodName + "用户ip:" + ip);
    }

    /**
     * @param
     * @return HttpServletRequest
     * @Description: 获取request
     * @author CB
     */
    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * 用户真实IP为： 192.168.1.110
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
