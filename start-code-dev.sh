#!/bin/bash
cd `dirname $0`

img_mvn="maven:3.3.9-jdk-8"                 # docker image of maven
m2_cache=~/.m2                              # the local maven cache dir
proj_home=$PWD                              # the project root dir
#img_output="aofeng/common"                  # output image tag


git pull  # should use git clone https://name:pwd@xxx.git

echo "use docker maven"

docker run --rm \
   -v $m2_cache:/root/.m2 \
   -v $proj_home:/usr/src/mymaven \
   -w /usr/src/mymaven $img_mvn mvn clean  compile  -U -Pdev -Dmaven.test.skip=true

docker run --rm \
   -v $m2_cache:/root/.m2 \
   -v $proj_home:/usr/src/mymaven \
   -w /usr/src/mymaven $img_mvn mvn package -U -Pdev -Dmaven.test.skip=true

echo "mv jar start"
sudo mv $proj_home/common/service_base/target/service_base-0.0.1-SNAPSHOT.jar $proj_home/common/service_base/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/common .

sudo mv $proj_home/infrastructure/api_gateway/target/api_gateway-0.0.1-SNAPSHOT.jar $proj_home/infrastructure/api_gateway/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/api-gateway .

sudo mv $proj_home/service/cms/target/cms-0.0.1-SNAPSHOT.jar $proj_home/service/cms/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/cms .

sudo mv $proj_home/service/edu/target/edu-0.0.1-SNAPSHOT.jar $proj_home/service/edu/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/edu .

sudo mv $proj_home/service/order/target/order-0.0.1-SNAPSHOT.jar  $proj_home/service/order/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/order .

sudo mv $proj_home/service/oss/target/oss-0.0.1-SNAPSHOT.jar $proj_home/service/oss/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/oss .

sudo mv $proj_home/service/sms/target/sms-0.0.1-SNAPSHOT.jar $proj_home/service/sms/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/sms .

sudo mv $proj_home/service/statistics/target/statistics-0.0.1-SNAPSHOT.jar $proj_home/service/statistics/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/statistics .

sudo mv $proj_home/service/user/target/user-0.0.1-SNAPSHOT.jar $proj_home/service/user/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/user .

sudo mv $proj_home/service/vod/target/vod-0.0.1-SNAPSHOT.jar $proj_home/service/vod/target/demo.jar # 兼容所有sh脚本
docker build -t aofeng/vod .

mkdir -p $PWD/logs
chmod 777 $PWD/logs

echo "docker rm start"
# 删除容器
docker rm -f college &> /dev/null

version=`date "+%Y%m%d%H"`

echo "docker run start"
# 启动镜像
docker run -d --restart=on-failure:5 --privileged=true \
    --net=host \
    -w /home \
    -v $PWD/logs:/home/logs \
    --name collage-common aofeng/common \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xmx100m \
        -Xms100m \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar

docker run -d --restart=on-failure:5 --privileged=true \
    --net=host \
    -w /home \
    -v $PWD/logs:/home/logs \
    --name collage-api-gateway aofeng/api-gateway \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xmx200m \
        -Xms200m \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar

docker run -d --restart=on-failure:5 --privileged=true \
    --net=host \
    -w /home \
    -v $PWD/logs:/home/logs \
    --name college-cms aofeng/cms \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xmx200m \
        -Xms200m \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar


docker run -d --restart=on-failure:5 --privileged=true \
    --net=host \
    -w /home \
    -v $PWD/logs:/home/logs \
    --name college-edu aofeng/edu \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xmx200m \
        -Xms200m \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar

docker run -d --restart=on-failure:5 --privileged=true \
    --net=host \
    -w /home \
    -v $PWD/logs:/home/logs \
    --name college-order aofeng/order \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xmx200m \
        -Xms200m \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar



docker run -d --restart=on-failure:5 --privileged=true \
    --net=host \
    -w /home \
    -v $PWD/logs:/home/logs \
    --name college-oss aofeng/oss \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xmx200m \
        -Xms200m \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar


docker run -d --restart=on-failure:5 --privileged=true \
    --net=host \
    -w /home \
    -v $PWD/logs:/home/logs \
    --name college-sms aofeng/sms \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xmx200m \
        -Xms200m \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar

docker run -d --restart=on-failure:5 --privileged=true \
    --net=host \
    -w /home \
    -v $PWD/logs:/home/logs \
    --name college-statistics aofeng/statistics \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xmx200m \
        -Xms200m \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar


docker run -d --restart=on-failure:5 --privileged=true \
    --net=host \
    -w /home \
    -v $PWD/logs:/home/logs \
    --name college-user aofeng/user \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xmx200m \
        -Xms200m \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar



docker run -d --restart=on-failure:5 --privileged=true \
    --net=host \
    -w /home \
    -v $PWD/logs:/home/logs \
    --name college-vod aofeng/vod \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xmx200m \
        -Xms200m \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar