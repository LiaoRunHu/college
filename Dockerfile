# 建议生产使用，ref: http://blog.tenxcloud.com/?p=1894
#FROM fabric8/java-jboss-openjdk8-jdk
FROM bu-hub.deepexi.com/public/openjdk-skywalking:1.1

ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8

USER root

cd ~
COPY /college/common/service_base/target/demo.jar /home/common_base/
COPY /college/infrastructure/api_gateway/target/demo.jar /home/api_gateway/
COPY /college/service/cms/demo.jar /home/cms/
COPY /college/service/edu/demo.jar /home/edu/
COPY /college/service/order/demo.jar /home/order/
COPY /college/service/oss/demo.jar /home/oss/
COPY /college/service/sms/demo.jar /home/sms/
COPY /college/service/statistics/demo.jar /home/statistics/
COPY /college/service/user/demo.jar /home/user/
COPY /college/service/vod/demo.jar /home/vod/

WORKDIR /home/
