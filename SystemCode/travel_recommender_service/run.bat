@echo off

set modeldir=travel_recommender_model
set kjardir=travel-recommender
set servicedir=travel_recommender_service
set frontenddir=travel_recommender_frontend

cd ..
echo ==============================
echo Entering %frontenddir%, compiling
echo ==============================
cd %frontenddir%
call npm -v
call npm i -s

call ng version
start /b ng serve

cd ..
echo ==============================
echo Entering %modeldir%, compiling
echo ==============================
cd %modeldir%
call mvn clean install

cd ..
echo ==============================
echo Entering %kjardir%, compiling
echo ==============================
cd %kjardir% 
call mvn clean install

cd ..
echo ==============================
echo Entering %servicedir%, running service
echo ==============================
cd %servicedir% 
call mvn spring-boot:run

cd ..