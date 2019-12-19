#runner

<pre>
在日常的项目开发中经常会遇到这样的需求：项目启动的时候进行一些一次性的初始化工作，
如读取加载资源文件、或者执行其它外部程序。

这个时候我们就可以用到spring-boot为我们提供的一种扩展机制--Runner。

在spring-boot中提供了两种Runner接口：ApplicationRunner和CommandLineRunner
</pre>


#原理

<pre>
SpringApplication.run调用callRunners方法
查找实现了ApplicationRunner和CommandLineRunner接口的Bean，统一存放在一个list中
根据Bean的order进行排序
循环调用每一个Runner Bean的run接口。
</pre>