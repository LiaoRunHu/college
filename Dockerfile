# 建议生产使用，ref: http://blog.tenxcloud.com/?p=1894
#FROM fabric8/java-jboss-openjdk8-jdk
FROM bu-hub.deepexi.com/public/openjdk-skywalking:1.1

ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8

USER root

COPY /home/project/college/common/common_base/target/demo.jar /home/common_base/
COPY /home/project/college/infrastructure/api_gateway/target/demo.jar /home/api_gateway/
COPY /home/project/college/service/cms/demo.jar /home/cms/
COPY /home/project/college/service/edu/demo.jar /home/edu/
COPY /home/project/college/service/order/demo.jar /home/order/
COPY /home/project/college/service/oss/demo.jar /home/oss/
COPY /home/project/college/service/sms/demo.jar /home/sms/
COPY /home/project/college/service/statistics/demo.jar /home/statistics/
COPY /home/project/college/service/user/demo.jar /home/user/
COPY /home/project/college/service/vod/demo.jar /home/vod/

WORKDIR /home/
