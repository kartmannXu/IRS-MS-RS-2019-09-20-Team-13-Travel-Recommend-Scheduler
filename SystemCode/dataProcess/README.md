不新建分支的情况下强行 push 项目正确打开方式
===

1. `git checkout -b {已存在的分支名 A}`
2. `git push origin {分支名 A} -f`，-f 强制 push

这意味着远程仓库中可能有东西被抹掉，请慎重。

终端中管理不想上传的目录或文件
===

1. 项目根目录下打开 git bash，新建 `.gitignore` 文件：

    `touch .gitignore`

2. `echo '{不想上传的目录或文件名，比如 node_model 和 .idea}' >> .gitignore`
3. `git status` 确认这些文件名确实被 git 忽略了
4. `git add . && git commit -m "messege"`
5. 正常 push

Database fields & Spot object members
===

### __Spot__ table

| Field              | Type         | Null | Key | Default | Extra |
| ------------------ | ------------ | ---- | --- | ------- | ----- |
| Spot_id            | bigint(20)   | NO   | PRI | NULL    |       |
| Spot_name          | varchar(255) | NO   |     | NULL    |       |
| City_id            | int(11)      | NO   |     | NULL    |       |
| City_name          | varchar(255) | NO   |     | NULL    |       |
| Country_id         | int(11)      | NO   |     | NULL    |       |
| Country_name       | varchar(255) | NO   |     | NULL    |       |
| Postal             | int(11)      | YES  |     | NULL    |       |
| imageUrl           | varchar(255) | YES  |     | NULL    |       |
| introduction       | varchar(255) | YES  |     | NULL    |       |
| Address            | varchar(255) | YES  |     | NULL    |       |
| Open_times         | varchar(255) | NO   |     | NULL    |       |
| Close_times        | varchar(255) | NO   |     | NULL    |       |
| Special_close_date | varchar(255) | YES  |     | NULL    |       |
| Est_duration       | int(11)      | NO   |     | NULL    |       |
| Score              | float        | YES  |     | NULL    |       |
| Gourmet            | float        | YES  |     | NULL    |       |
| Cultural           | float        | YES  |     | NULL    |       |
| Downtown           | float        | YES  |     | NULL    |       |
| Bustle             | float        | YES  |     | NULL    |       |
| Family             | float        | YES  |     | NULL    |       |
| Shopping           | float        | YES  |     | NULL    |       |
| Popular            | float        | YES  |     | NULL    |       |
| Parks              | boolean      | YES  |     | NULL    |       |
| Museums            | boolean      | YES  |     | NULL    |       |
| Observation_deck   | boolean      | YES  |     | NULL    |       |
| Gardens            | boolean      | YES  |     | NULL    |       |
| Zoos               | boolean      | YES  |     | NULL    |       |
| Themeparks         | boolean      | YES  |     | NULL    |       |
| Neighbourhoods     | boolean      | YES  |     | NULL    |       |  
| Religious_Sites    | boolean      | YES  |     | NULL    |       |  
| Historical_Sites   | boolean      | YES  |     | NULL    |       |  
| Shopping_Malls     | boolean      | YES  |     | NULL    |       |  
| Resort             | boolean      | YES  |     | NULL    |       |   
| Landmarks          | boolean      | YES  |     | NULL    |       |   
| Island             | boolean      | YES  |     | NULL    |       |
| Bridges            | boolean      | YES  |     | NULL    |       |  
| Activities         | boolean      | YES  |     | NULL    |       |  
| Beaches            | boolean      | YES  |     | NULL    |       |  
