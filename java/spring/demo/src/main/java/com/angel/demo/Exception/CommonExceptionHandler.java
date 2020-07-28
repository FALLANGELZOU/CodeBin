package com.angel.demo.Exception;

import com.angel.demo.util.Http.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Angel_zou
 * @Date: Created in 23:51 2020/7/12
 * @Connection: ahacgn@gmail.com
 * @Description: 统一异常处理
 */
@Slf4j
@ControllerAdvice   // 用来设置异常处理类，控制器增强，使@ExceptionHandler、@InitBinder、@ModelAttribute注解的方法应用到所有的 @RequestMapping注解的方法。
public class CommonExceptionHandler {

    /**
     * 处理所有异常
     * @param req   spring 中的请求，用它来获取请求的 url，用来打印日志
     * @param e     抛出的异常信息
     * @return      统一返还值
     */

    @ExceptionHandler(value = Exception.class)  //  说明捕获什么Exception，这里捕获所有，处理的异常类型，发生异常时会自动调用。
    @ResponseBody   //  以json返还
    public HttpResult commonErrorHandler(HttpServletRequest req, Throwable e) {
        String message = e.getMessage();
        log.error("[" + req.getRequestURI() + "]：" + message);
//        String pattern = ".* (default message) .*";
//        Pattern p = Pattern.compile(pattern);
//        Matcher match = p.matcher(message);
//        System.out.println(match.group(0));
        return HttpResult.error(message);

    }
}
