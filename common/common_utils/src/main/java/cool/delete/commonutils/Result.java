package cool.delete.commonutils;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果对象
 * @author Tiger
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    /**把构造方法私有*/
    private Result() {}

    /**
     * 成功静态方法
     */
    public static Result ok() {
        Result result = new Result();
        result.setCode(Const.ResponseCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }

    /**
     * 失败静态方法
     */
    public static Result error() {
        Result result = new Result();
        result.setCode(Const.ResponseCode.ERROR);
        result.setMessage("失败");
        return result;
    }


    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
