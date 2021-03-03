#!/bin/bash
cd $(dirname $0)

img_mvn="maven:3.3.3-jdk-8" # docker image of maven
m2_cache=~/.m2              # the local maven cache dir
proj_home=$PWD              # the project root dir

git pull # should use git clone https://name:pwd@xxx.git

echo "use docker maven"
docker run --rm \
  -v $m2_cache:/root/.m2 \
  -v $proj_home:/usr/src/mymaven \
  -w /usr/src/mymaven $img_mvn mvn clean package -U -Pdev -DskipTests

sudo mv $proj_home/common/service_base/target/service_base-*.jar $proj_home/common/service_base/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/common .

sudo mv $proj_home/infrastructure/api_gateway/target/api_gateway-*.jar $proj_home/infrastructure/api_gateway/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/api-gateway .

sudo mv $proj_home/service/cms/target/cms-*.jar $proj_home/service/cms/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/cms .

sudo mv $proj_home/service/edu/target/edu-*.jar $proj_home/service/edu/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/edu .

sudo mv $proj_home/service/order/target/order-*.jar  $proj_home/service/order/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/order .

sudo mv $proj_home/service/oss/target/oss-*.jar $proj_home/service/oss/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/oss .

sudo mv $proj_home/service/sms/target/sms-*.jar $proj_home/service/sms/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/sms .

sudo mv $proj_home/service/statistics/target/statistics-*.jar $proj_home/service/statistics/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/statistics .

sudo mv $proj_home/service/user/target/user-*.jar $proj_home/service/user/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/user .

sudo mv $proj_home/service/vod/target/vod-*.jar $proj_home/service/vod/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/vod .

echo "构建镜像"
docker build -t $APP_NAME:v$VERSION .
