

# 论文查重

| 这个作业属于哪个课程 | [22级计科1班](https://edu.cnblogs.com/campus/gdgy/CSGrade22-12) |
| -------------------- | ------------------------------------------------------------ |
| 这个作业要求在哪里   | [作业要求](https://edu.cnblogs.com/campus/gdgy/CSGrade22-12/homework/13220) |
| 这个作业的目标       | 使用代码实现论文查                                           |
| github项目链接       | [链接](https://github.com/wankaiyi/wankaiyi/3122004788)                 |

# PSP

| PSP2.1                                  | Personal Software Process Stages | 预估耗时（分钟） | 实际耗时（分钟） |
|-----------------------------------------|----------------------------------|----------|----------|
| **Planning**                            | 计划                               | 20       | 20       |
| · Estimate                              | 估计这个任务需要多少时间                     | 20       | 20       |
| **Development**                         | 开发                               | 170      | 190      |
| · Analysis                              | 需求分析（包括学习新技术）                    | 30       | 30       |
| · Design Spec                           | 生成设计文档                           | 10       | 20       |
| · Design Review                         | 设计复审                             | 20       | 30       |
| · Coding Standard                       | 代码规范（为当前的开发制定合适的规范）              | 20       | 20       |
| · Design                                | 具体设计                             | 30       | 30       |
| · Coding                                | 具体编码                             | 30       | 30       |
| · Code Review                           | 代码复审                             | 10       | 10       |
| · Test                                  | 测试（自我测试、修改代码、提交修改）               | 20       | 20       |
| **Reporting**                           | 报告                               | 115      | 130      |
| · Test Report                           | 测试报告                             | 60       | 80       |
| · Size Measurement                      | 计算工作量                            | 25       | 20       |
| · Postmortem & Process Improvement Plan | 事后总结，并提出过程改进计划                   | 30       | 30       |
| **Total**                               | 合计                               | 305      | 340      |

# 功能实现

## Jaccard 相似度

**定义**：Jaccard 相似度）计算两个集合的交集与并集的比例。对于文本，它通常应用于词汇集合，计算两个文本的词汇集合的交集与并集的比例。

**公式**：Jaccard相似度 = |A ∩ B| / |A ∪ B|

## 模块接口实现
![image](https://github.com/user-attachments/assets/815b8b9d-1735-4f84-b8a7-13a8fd005ef3)

调用步骤：
1. main方法中使用hutool的工具类读取两个文件
2. 调用TextSegmentUtils类的ikSegment方法，使用IK分词器，对两篇论文的内容分别进行分词
3. 将分词的结果去重后分别放入两个集合中
4. 调用JaccardUtils的calculateJaccardSimilarity方法，将两个集合的 交集/并集 作为论文相似度的结果写入到目标文件

# 功能测试
![image](https://github.com/user-attachments/assets/723e6aa5-3cad-411c-910d-f00cc78db11b)
![image](https://github.com/user-attachments/assets/e18d95e0-54de-4aee-9643-1858d701239f)

打成jar包后运行：

``` maven
java -jar .\3122004788-1.0.jar D:\test\origin.txt D:\test\originAdd.txt D:\test\target.txt
```

运行结果：![image](https://github.com/user-attachments/assets/7df1dbb7-f54c-4701-9594-1016a2a32f54)
# 异常情况测试
1. args数组长度不足3
2. args数组中的字符串为空
3. args数组中的字符串不是绝对路径
4. 读取的文件不存在
5. 将计算结果写入文件时，路径不存在

# 覆盖率
![image](https://github.com/user-attachments/assets/7307a406-bf9f-4db2-86a6-f188c2d304e0)

# 性能
## 内存占用
![image](https://github.com/user-attachments/assets/c9e56f89-91d6-4367-8006-baa9ae050027)
## 方法耗时
![image](https://github.com/user-attachments/assets/de5476cc-a87f-460f-95d5-d8e345185e4b)

分析：整个过程耗时的步骤为分词和读文件两个操作，这两个操作都涉及到io，所以可以判断出整个操作的瓶颈zai分词和读文件的io上
改进思路：开两个线程分别对两个文件进行读取和分词操作
