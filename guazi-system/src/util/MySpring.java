package util;

import java.util.HashMap;

public class MySpring {

    private static HashMap<String,Object> beanMap = new HashMap<>();

    public static <T>T getBean(String className){
        T object = (T)beanMap.get(className);
        if(object == null){
            try {
                Class clazz = Class.forName(className);
                object = (T)clazz.newInstance();
                beanMap.put(className,object);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
