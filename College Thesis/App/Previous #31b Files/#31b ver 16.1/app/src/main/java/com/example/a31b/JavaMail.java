package com.example.a31b;

public class JavaMail{

    public static void emailVerification(String s) throws Exception {
        JavaMailUtil.sendMail("31b.emergency@gmail.com");
    }
    /*public static void main(String[] args) throws Exception{
        JavaMailUtil.sendMail("31b.emergency@gmail.com");
    }*/
}
