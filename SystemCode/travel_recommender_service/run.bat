@echo off

set bar=======================================================
set modeldir=travel_recommender_model
set kjardir=travel-recommender
set servicedir=travel_recommender_service
set frontenddir=travel_recommender_frontend

cd ..
echo %bar%
echo Entering %frontenddir%, compiling Angular JS
echo %bar%
cd %frontenddir%
call npm -v
call npm i -s
call npm install --save-dev @angular-devkit/build-angular
call ng version
start CMD /C call ng serve

cd ..
echo %bar%
echo Entering %modeldir%, compiling
echo %bar%
cd %modeldir%
call mvn clean install

cd ..
echo %bar%
echo Entering %kjardir%, compiling
echo %bar%
cd %kjardir% 
call mvn clean install

cd ..
echo %bar%
echo Entering %servicedir%, running service
echo %bar%
cd %servicedir% 
call mvn spring-boot:run

cd ..