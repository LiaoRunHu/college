package cool.delete.servicebase.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-02 21:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollegeException extends RuntimeException{

    /**状态码*/
    private Integer code;
    /**状态信息*/
    private String msg;
}
