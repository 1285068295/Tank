package com.lsp.tank.serializable;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author ：Lisp
 * @date： 2021/9/1
 * @version: V1.0
 * @slogan:
 * @description :测试对象序列化和反序列化
 * 序列化时属性为 name age sex 反序列化时属性为 name  age1 sex1 只能反序列出 name 属性
 * 不需要提供get set方法
 *
 * 在使用序列化操作时，不是所有的成员都可以进行序列化操作:
 *
 * 1）静态成员不会进行序列化操作；
 *
 * 2）瞬态成员也不会进行序列化操作；
 *
 *
 */
public class Person implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -5809782578272943999L;
    private transient int age = 100;
    private String name;
    private String sex;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    /**
     * 自己定制反序列化过程
     */
    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException{

        System.out.println("定制person序列化过程 start");
        in.defaultReadObject();
        this.age = 1000;
        System.out.println("定制person序列化过程 end");
    }

}