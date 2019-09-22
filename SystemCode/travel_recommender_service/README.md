Run backend
===

__prerequisite:__
安装 jdk8, 安装 maven3.6.1


```cmd
cd ..\travel_recommender_model/ && mvn clean install
cd ..

cd travel-recommender/ && mvn clean install
cd ..

cd travel_recommender_service
mvn clean spring-boot:run
```
