#!/bin/bash
cd `dirname $0`

img_mvn="maven:3.3.9-jdk-8"                 # docker image of maven
m2_cache=~/.m2                              # the local maven cache dir
proj_home=$PWD                              # the project root dir
img_output="aofeng/college-parent"            # output image tag

git pull  # should use git clone https://name:pwd@xxx.git

echo "use docker maven"
docker run --rm \
   -v $m2_cache:/root/.m2 \
   -v $proj_home:/usr/src/mymaven \
   -w /usr/src/mymaven $img_mvn mvn clean package -U -Pdev -Dmaven.test.skip=true

echo "mv jar start"
sudo mv $proj_home/college-parent/target/college-parent-*.jar $proj_home/college-parent/target/demo.jar # 兼容所有sh脚本
docker build -t $img_output .

mkdir -p $PWD/logs
chmod 777 $PWD/logs

echo "docker rm start"
# 删除容器
docker rm -f college-parent &> /dev/null

version=`date "+%Y%m%d%H"`

echo "docker run start"
# 启动镜像
docker run -d --restart=on-failure:5 --privileged=true \
    --net=host \
    -w /home \
    -v $PWD/logs:/home/logs \
    --name college-parent aofeng/college-parent \
    java \
        -Djava.security.egd=file:/dev/./urandom \
        -Duser.timezone=Asia/Shanghai \
        -XX:+PrintGCDateStamps \
        -XX:+PrintGCTimeStamps \
        -XX:+PrintGCDetails \
        -XX:+HeapDumpOnOutOfMemoryError \
        -Xmx500m \
        -Xms500m \
        -Xloggc:logs/gc_$version.log \
        -jar /home/demo.jar
