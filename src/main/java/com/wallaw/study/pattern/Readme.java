package com.wallaw.study.pattern;

/**
 * @author zhangzb
 *
 * 设计原则全新解读
 *    1）单一职责：一个接口或者类，它的职责是单一而纯粹的
 *    2）里氏替换：继承，使用基类的地方，一定可以使用子类，反过来则不行
 *    3）依赖倒置：依赖抽象（接口或者抽象类），而不是依赖具体实现类
 *    4）接口隔离：尽量让接口里面的方法“筋骨肉”，而非“肥嘟嘟”，方便应对需求修改或扩展
 *    5）迪 米 特：一个对象应该对其他对象有最少的了解
 *    6）开闭原则：对扩展开放，对修改关闭（卖书-打折）
 *
 *
 *
 */
public class Readme {

    /**
     * 中介者模式：
     * 各方不直接交流和依赖，而是通过一个中介者来处理各个关系。如：采购管理，库存管理，借用管理，中介者
     *
     * 命令模式：
     * 将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能
     *
     * 迭代器模式：
     * 可以需要继承java.util.Iterator迭代器接口来实现
     *
     */
}
