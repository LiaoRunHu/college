package cool.delete.edu.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import cool.delete.edu.entity.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-05 19:05
 */
@Data
@NoArgsConstructor
public class TreeCollectionVo {
    private String id;
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<TreeCollectionVo> children;

    public TreeCollectionVo(String id, String label) {
        this.id = id;
        this.title = label;
    }
}
