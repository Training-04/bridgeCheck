package group.bridge.web.reslover;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        System.out.println("==============异常开始==============");
        //如果是shiro无权操作，因为shiro 不进行转发至无权限url,则跳转到404.html
        if (e instanceof UnauthorizedException){
            ModelAndView modelAndView = new ModelAndView("/sysmanagement/404");
            System.out.println("==============解决异常中=============");
            return modelAndView;
        }
        e.printStackTrace();
        System.out.println("==============异常结束=============");
        ModelAndView modelAndView = new  ModelAndView("/sysmanagement/404");
        modelAndView.addObject("exception",e.toString().replace("\n","<br/>"));
        return modelAndView;
    }
}
