package cool.delete.servicebase.handler;

import cool.delete.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-02 20:40
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler(CollegeException.class)
    @ResponseBody
    public Result error(CollegeException e){
        e.printStackTrace();

        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
