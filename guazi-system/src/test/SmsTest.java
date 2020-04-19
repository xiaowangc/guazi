package test;

import util.SendSms;

public class SmsTest {
    public static void main(String[] args) {
        int code = SendSms.random();
        System.out.println(code);
        boolean flag = SendSms.send("13144873244",code);
        System.out.println(flag?"Send success.":"Send fail.");
    }
}
