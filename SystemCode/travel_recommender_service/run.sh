!/bash/sh
export bar="======================================================"
export modeldir="travel_recommender_model"
export kjardir="travel-recommender"
export servicedir="travel_recommender_service"
export frontenddir="travel_recommender_frontend"

cd ..
echo $bar
echo Entering $frontenddir, compiling Angular JS
echo $bar
cd $frontenddir
npm -v
npm i -s
npm install --save-dev @angular-devkit/build-angular
ng version
gnome-terminal -x bash -c "ng serve"

cd ..
echo $bar
echo Entering $modeldir, compiling
echo $bar
cd $modeldir
mvn clean install

cd ..
echo $bar
echo Entering $kjardir, compiling
echo $bar
cd $kjardir
mvn clean install

cd ..
echo $bar
echo Entering $servicedir, running service
echo $bar
cd $servicedir
mvn spring-boot:run

cd ..