<!--
 * @Descripttion: 
 * @version: 1.0.0
 * @Author: 鱼小陌
 * @Date: 2019-09-04 19:25:06
 * @LastEditors: 鱼小陌
 * @LastEditTime: 2019-09-05 20:56:12
 -->
# 驾校报名系统


## API

### 首页  code 200 401 500
   1、验证码 get ./getImg   { t } 
   2、提交 post ./userRegister  { u_name,u_sex,unit,a_id,phone, code  }

### 提交成功 code 200 401 500
   1、post ./userInfo { hash }

### 后台登录  code 200 401 500
   1、验证码 get  ./getImg   { t } 
   2、登录 post ./Login { type: admin/agent, name|phone , pass,code  }

### 登录成功  cookie  code 200 401 500
   一、管理员 get ./adminInfo  post ./setAdmin {name,oldpass,pass,code}  get ./adminUsers  get ./adminAgents  post ./addAgent { a_name,a_sex,unit,a_id,a_phone,a_password,a_prove, code  }
   二、代理人 get ./agentInfo  get ./agentUsers 
