package cn.trusteye.ssm.exception;

import cn.trusteye.ssm.result.ExceptionResultInfo;
import cn.trusteye.ssm.result.ResultInfo;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * * Created by Rayman on 2017/3/1.
 * 全局异常处理器
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
    //json转换器
    //将异常信息转换json
    private HttpMessageConverter<ExceptionResultInfo> jsonMessageConverter;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        //输出异常信息
        ex.printStackTrace();
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ResponseBody responseBody = AnnotationUtils.findAnnotation(method, ResponseBody.class);
        if (responseBody != null) {
            //将异常信息转JSON输出
            return resolveJsonException(request, response, handler, ex);
        }
        //acion返回的是jsp页面

        //解析异常
        ExceptionResultInfo exceptionResultInfo = resolveException(ex);
        request.setAttribute("exceptionResultInfo", exceptionResultInfo.getResultInfo());


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exceptionResultInfo", exceptionResultInfo.getResultInfo());
        modelAndView.setViewName("items/error");

        return modelAndView;
    }

    //异常信息解析
    private ExceptionResultInfo resolveException(Exception ex) {
        ResultInfo resultInfo = null;
        if (ex instanceof ExceptionResultInfo) {
            resultInfo = ((ExceptionResultInfo) ex).getResultInfo();

        } else {
            resultInfo = new ResultInfo();
            resultInfo.setType(ResultInfo.TYPE_RESULT_FAILURE);
            resultInfo.setMessage("未知错误！");
        }
        return new ExceptionResultInfo(resultInfo);
    }

    //将异常信息转json
    public ModelAndView resolveJsonException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //解析异常
        ExceptionResultInfo exceptionResultInfo = resolveException(ex);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);

        try {
            jsonMessageConverter.write(exceptionResultInfo, MediaType.APPLICATION_JSON, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }

/*    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        CustomException customException = null;
        if(ex instanceof CustomException){
            customException = (CustomException) ex;
        }
        else {
            customException = new CustomException("未知错误");
        }

        String message = customException.getMessage();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");
        return modelAndView;
    }*/
}
