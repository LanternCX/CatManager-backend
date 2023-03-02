package xyz.cdbxinhe.cat.backend;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;

/**
 * 数据返回结构体
 * package xyz.cdbxinhe.cat.backend
 * project backend
 * Created by @author CaoXin on date 2023/03/01
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {

    /**
     * 请求是否成功
     */
    private boolean success;
    /**
     * 请求代码
     */
    private int code;
    /**
     * 错误原因
     */
    private String msg;
    /**
     * 返回数据
     */
    private JSONObject data;

    /**
     * 请求正常完整返回体构造方法
     */
    public Response() {
        this.success = true;
        this.code = 201;
        this.data = null;
    }

    /**
     * 请求正常完整返回体构造方法
     *
     * @param result 返回数据
     */
    public Response(HashMap<String, Object> result) {
        this.success = true;
        this.code = 200;
        this.data = new JSONObject(result);
    }

    /**
     * 请求异常退出返回体构造方法
     *
     * @param code 错误代码
     * @param msg  错误信息
     */
    public Response(int code, String msg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 覆写toString方法
     *
     * @return 类toString
     */
    @Override
    public String toString() {
        if (this.success) {
            final JSONObject data = this.data;
            return new JSONObject(new HashMap<>(3) {{
                put("success", true);
                put("code", 200);
                put("data", data);
            }}).toString();
        } else {
            final String msg = this.msg;
            final int code = this.code;
            return new JSONObject(new HashMap<>(3) {{
                put("success", false);
                put("code", code);
                put("msg", msg);
            }}).toString();
        }
    }
}
