package com.hiekn.demo.test.java.editor;

import com.hiekn.demo.bean.UserBean;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * describe about this class
 *
 * @author DingHao
 * @date 2019/1/4 14:01
 */
public class UserBeanEditor extends PropertyEditorSupport {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 重写从一个字符串String变成bean的方法
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        String[] tokens = text.split("\\|");
        UserBean user = new UserBean();
        user.setName(tokens[0]);
        user.setTitle(tokens[1]);
        try{
            user.setDate(sdf.parse(tokens[6]));
        }catch(ParseException e){
            throw new IllegalArgumentException(e);
        }
        setValue(user);
    }
}
