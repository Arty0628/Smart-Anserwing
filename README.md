# 智答答 AI 答题平台

> 基于 Spring Boot + AI 的智能答题应用平台。

## 📖 项目简介

**智答答 AI 答题平台** 是一个基于 AI 技术的在线答题应用聚合平台。用户可以在平台上创建各种类型的答题应用（如性格测试、知识问答、心理测评等），支持手动录入题目或通过 AI 智能生成题目。用户参与答题后，系统会根据预设的评分策略（规则评分或 AI 智能评分）生成详细的分析报告。

本项目为后端代码，提供了完整的用户管理、应用管理、题目管理、评分策略管理、回答记录及统计分析等功能。

## ✨ 核心功能

*   **用户体系**：注册、登录（账号密码/微信小程序/公众号）、个人信息管理。
*   **应用管理**：创建和管理答题应用，支持“得分类”和“测评类”两种应用类型。
    *   **得分类**：根据答对题目数量计算得分（如知识竞赛）。
    *   **测评类**：根据选项匹配结果分析属性（如MBTI性格测试）。
*   **题目管理**：
    *   **手动录入**：支持自定义题目、选项及得分。
    *   **AI 生成**：接入智谱 AI，一键生成指定主题的题目。
*   **评分策略**：
    *   **自定义规则**：基于复杂的规则匹配逻辑进行评分。
    *   **AI 评分**：利用 AI 对用户的回答进行深度分析和评价。
*   **答题记录**：记录用户的每一次答题详情及结果。
*   **统计分析**：应用热度、答题分布等数据统计。

## 🛠 技术栈

*   **Java 8**
*   **Spring Boot 2.7.2**
*   **MySQL 8.0**
*   **MyBatis Plus**：ORM 框架
*   **Redis**：缓存 & 分布式 Session (Redisson)
*   **Zhipu AI (智谱 AI)**：大模型能力支持
*   **ShardingSphere**：分库分表（用户回答记录表）
*   **Knife4j**：API 接口文档
*   **Tencent Cloud COS**：对象存储（文件上传）
*   **EasyExcel**：Excel 导入导出

## 🚀 快速开始

### 1. 环境准备

确保你的开发环境已安装以下工具：
*   JDK 1.8+
*   Maven 3.6+
*   MySQL 8.0+
*   Redis

### 2. 数据库初始化

1.  创建数据库 `zhidada`。
2.  执行 `sql/create_table.sql` 脚本创建表结构。
3.  (可选) 执行 `sql/init_data.sql` 导入初始数据。

### 3. 项目配置

修改 `src/main/resources/application.yml` 文件，配置你的环境信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/zhidada?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: your_username # 数据库用户名
    password: your_password # 数据库密码
  redis:
    host: localhost # Redis 地址
    port: 6379
    database: 0
```

**配置 AI 及对象存储密钥：**

请在 `application.yml` 或 `application-prod.yml` 中补充以下配置（或在本地环境变量中设置）：

```yaml
# 智谱 AI 配置
ai:
  api-key: your_zhipu_api_key

# 腾讯云 COS 配置
cos:
  client:
    accessKey: your_access_key
    secretKey: your_secret_key
    region: your_region
    bucket: your_bucket
```

### 4. 启动项目

运行 `src/main/java/com/yupi/zhidada/MainApplication.java` 即可启动项目。

启动成功后，访问接口文档：`http://localhost:8101/api/doc.html`

## 📂 目录结构

```
src/main/java/com/yupi/zhidada
├── annotation      # 自定义注解
├── aop             # AOP 切面（日志、权限校验）
├── common          # 通用类（响应封装、错误码）
├── config          # 配置类（MyBatis, Redis, AI, etc.）
├── controller      # 控制器层
├── exception       # 全局异常处理
├── manager         # 通用模块（AI调用, COS封装）
├── mapper          # DAO 层
├── model           # 数据模型 (DTO, Entity, VO, Enums)
├── scoring         # 评分策略模块 (核心业务逻辑)
├── service         # 业务逻辑层
└── utils           # 工具类
```

## 🤝 贡献

欢迎提交 Issue 和 Pull Request 改进本项目。
