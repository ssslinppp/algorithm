# 一致性Hash算法
是一种分布式算法，常用于负载均衡

## 测试
测试代码：
KetamaNodeLocatorTest.java

### 当虚拟节点个数=160，node个数分别等于3,4,5时
```
######## nodes个数: 3
[node: 192.168.35.10, itemCount: 12, items: [item-1, item-4, item-8, item-10, item-11, item-13, item-14, item-17, item-24, item-26, item-27, item-35]]
[node: 192.168.35.11, itemCount: 15, items: [item-2, item-3, item-6, item-7, item-9, item-12, item-18, item-19, item-20, item-22, item-23, item-25, item-31, item-38, item-39]]
[node: 192.168.35.12, itemCount: 13, items: [item-0, item-5, item-15, item-16, item-21, item-28, item-29, item-30, item-32, item-33, item-34, item-36, item-37]]
######## nodes个数: 4
[node: 192.168.35.10, itemCount: 10, items: [item-1, item-4, item-8, item-10, item-11, item-13, item-14, item-17, item-24, item-27]]
[node: 192.168.35.11, itemCount: 9, items: [item-6, item-7, item-9, item-12, item-18, item-19, item-23, item-25, item-31]]
[node: 192.168.35.12, itemCount: 7, items: [item-0, item-15, item-21, item-28, item-29, item-32, item-36]]
[node: 192.168.35.13, itemCount: 14, items: [item-2, item-3, item-5, item-16, item-20, item-22, item-26, item-30, item-33, item-34, item-35, item-37, item-38, item-39]]
######## nodes个数: 5
[node: 192.168.35.10, itemCount: 7, items: [item-4, item-8, item-10, item-13, item-14, item-17, item-27]]
[node: 192.168.35.11, itemCount: 8, items: [item-6, item-7, item-9, item-12, item-18, item-19, item-23, item-25]]
[node: 192.168.35.12, itemCount: 6, items: [item-15, item-21, item-28, item-29, item-32, item-36]]
[node: 192.168.35.13, itemCount: 14, items: [item-2, item-3, item-5, item-16, item-20, item-22, item-26, item-30, item-33, item-34, item-35, item-37, item-38, item-39]]
[node: 192.168.35.14, itemCount: 5, items: [item-0, item-1, item-11, item-24, item-31]]
```
结论：
- 当node个数改变时：大部分item都不需要进行node重新分配；

### 当虚拟节点个数=1024，node个数分别等于3,4,5时
```
######## nodes个数: 3
[node: 192.168.35.10, itemCount: 15, items: [item-0, item-2, item-4, item-8, item-9, item-13, item-18, item-24, item-25, item-26, item-30, item-32, item-36, item-38, item-39]]
[node: 192.168.35.11, itemCount: 11, items: [item-1, item-7, item-10, item-11, item-12, item-15, item-16, item-21, item-22, item-23, item-31]]
[node: 192.168.35.12, itemCount: 14, items: [item-3, item-5, item-6, item-14, item-17, item-19, item-20, item-27, item-28, item-29, item-33, item-34, item-35, item-37]]
######## nodes个数: 4
[node: 192.168.35.10, itemCount: 14, items: [item-0, item-2, item-4, item-8, item-9, item-13, item-18, item-24, item-25, item-26, item-32, item-36, item-38, item-39]]
[node: 192.168.35.11, itemCount: 9, items: [item-1, item-7, item-10, item-11, item-12, item-15, item-16, item-22, item-23]]
[node: 192.168.35.12, itemCount: 10, items: [item-3, item-5, item-6, item-14, item-17, item-19, item-20, item-28, item-29, item-33]]
[node: 192.168.35.13, itemCount: 7, items: [item-21, item-27, item-30, item-31, item-34, item-35, item-37]]
######## nodes个数: 5
[node: 192.168.35.10, itemCount: 13, items: [item-2, item-4, item-8, item-9, item-13, item-18, item-24, item-25, item-26, item-32, item-36, item-38, item-39]]
[node: 192.168.35.11, itemCount: 7, items: [item-1, item-7, item-10, item-11, item-12, item-15, item-23]]
[node: 192.168.35.12, itemCount: 9, items: [item-3, item-6, item-14, item-17, item-19, item-20, item-28, item-29, item-33]]
[node: 192.168.35.13, itemCount: 5, items: [item-30, item-31, item-34, item-35, item-37]]
[node: 192.168.35.14, itemCount: 6, items: [item-0, item-5, item-16, item-21, item-22, item-27]]
```
结论：
- 当node个数改变时：大部分item都不需要进行node重新分配；
- 与虚拟节点=160相比，并没有看到明显的平均分布；

### 当虚拟节点个数=160，node个数分别等于3,4,5
item=4000时
```
######## nodes个数: 3
[node: 192.168.35.10, itemCount: 1363]
[node: 192.168.35.11, itemCount: 1332]
[node: 192.168.35.12, itemCount: 1305]
######## nodes个数: 4
[node: 192.168.35.10, itemCount: 1011]
[node: 192.168.35.11, itemCount: 963]
[node: 192.168.35.12, itemCount: 971]
[node: 192.168.35.13, itemCount: 1055]
######## nodes个数: 5
[node: 192.168.35.10, itemCount: 803]
[node: 192.168.35.11, itemCount: 776]
[node: 192.168.35.12, itemCount: 761]
[node: 192.168.35.13, itemCount: 900]
[node: 192.168.35.14, itemCount: 760]
```
item=10000时
```
######## nodes个数: 3
[node: 192.168.35.10, itemCount: 3383]
[node: 192.168.35.11, itemCount: 3337]
[node: 192.168.35.12, itemCount: 3280]
######## nodes个数: 4
[node: 192.168.35.10, itemCount: 2503]
[node: 192.168.35.11, itemCount: 2449]
[node: 192.168.35.12, itemCount: 2450]
[node: 192.168.35.13, itemCount: 2598]
######## nodes个数: 5
[node: 192.168.35.10, itemCount: 1923]
[node: 192.168.35.11, itemCount: 1971]
[node: 192.168.35.12, itemCount: 1922]
[node: 192.168.35.13, itemCount: 2220]
[node: 192.168.35.14, itemCount: 1964]
```
结论：
- 当item个数增大时，分配到各node变得更加均匀了；

## 参考链接
- [【并发编程】使用BlockingQueue实现<多生产者，多消费者>](http://www.cnblogs.com/ssslinppp/p/6279796.html)   
- [五分钟理解一致性哈希算法(consistent hashing)](http://blog.csdn.net/cywosp/article/details/23397179)   
- [一致性哈希算法的理解与实践](https://yikun.github.io/2016/06/09/%E4%B8%80%E8%87%B4%E6%80%A7%E5%93%88%E5%B8%8C%E7%AE%97%E6%B3%95%E7%9A%84%E7%90%86%E8%A7%A3%E4%B8%8E%E5%AE%9E%E8%B7%B5/)    
- [KetamaConsistentHash.java包括动态添加和删除node](https://gist.github.com/linux-china/7817485)
