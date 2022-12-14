# 基于百度 UID 的分布式 id 方案

## Requirement

1. Jdk8

## 新特性

1. 新增 GLOBAL_TOKEN, token 相同的应用返回相同的 workId
2. 支持给 workId 加前缀

## id 自动填充
如果需要使用注解式 id 自动生成并填充功能，请使用该[id-auto-fill-spring-boot-starter](https://github.com/alansun2/id-auto-fill-spring-boot-starter)