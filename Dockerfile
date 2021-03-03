# 建议生产使用，ref: http://blog.tenxcloud.com/?p=1894
#FROM fabric8/java-jboss-openjdk8-jdk
FROM bu-hub.deepexi.com/public/openjdk-skywalking:1.1

ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8

USER root

COPY common/common_base/target/demo.jar /home/
COPY infrastructure/api_gateway/target/demo.jar /home/
COPY service/cms/demo.jar /home/
COPY service/edu/demo.jar /home/
COPY service/order/demo.jar /home/
COPY service/oss/demo.jar /home/
COPY service/sms/demo.jar /home/
COPY service/statistics/demo.jar /home/
COPY service/user/demo.jar /home/
COPY service/vod/demo.jar /home/

WORKDIR /home/
