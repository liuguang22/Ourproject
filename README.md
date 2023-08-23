# Ourproject

# 概要设计

## 一个简单的电商APP 



## 分包

- core
  - base
  - data
    - network
    - database
    - bean
- module
  - main
    - home
      - ui
      - viewmodel
      - repository
    - App.class
  - business
    - account
      - ui
      - viewmodel
    - goods
      - ui
      - viewmodel
      - repository
    - cart
      - ui
      - viewmodel
      - repository
    - order
      - ui
      - viewmodel
      - repository

 

core包是基础包

​	其中包含base包,里面放入base类

​	data包,里面是数据来源,有远程数据network和本地数据database

module包是业务包

​	里面的main是主项目的壳子,放application和闪屏页,主页

​	business包是具体的业务包,简单的分为账户,商品,购物车,订单






