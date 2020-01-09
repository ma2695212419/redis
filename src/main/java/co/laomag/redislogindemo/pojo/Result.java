package co.laomag.redislogindemo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 马泽朋
 * @version 1.0
 * @date 2020/1/4 下午 2:41
 */
@ApiModel
public class Result {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "消息")
    private String message;
    @ApiModelProperty(value = "返回的数据")
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
