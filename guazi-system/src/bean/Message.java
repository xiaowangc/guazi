package bean;

import com.google.gson.Gson;

/**
 * Message类用来将String类型的字符串和对象组装转换成JSON类型
 * 第一个属性result 成功或者失败的字符串信息
 * 第二个属性Object 将一个对象类型转换成JSON格式
 * 需要一个方法，将object类型的对象转换成JSON格式，调用gson.jar来辅助完成
 */
public class Message {
    private String result;
    private Object data;

    public Message() {
    }

    public Message(String result) {
        this.result = result;
    }

    public Message(String result, Object data) {
        this.result = result;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getObj() {
        return data;
    }

    public void setObj(Object obj) {
        this.data = data;
    }

    /**
     * 将当前对象转换成json格式的字符串
     * @return
     */
    public String toJSON(){
        return new Gson().toJson(this);
    }


}
