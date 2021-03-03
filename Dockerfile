# 建议生产使用，ref: http://blog.tenxcloud.com/?p=1894
#FROM fabric8/java-jboss-openjdk8-jdk
FROM bu-hub.deepexi.com/public/openjdk-skywalking:1.1

ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8

USER root

COPY /root/college/common/service_base/target/demo.jar /home/common_base/
COPY /root/college/infrastructure/api_gateway/target/demo.jar /home/api_gateway/
COPY /root/college/service/cms/demo.jar /home/cms/
COPY /root/college/service/edu/demo.jar /home/edu/
COPY /root/college/service/order/demo.jar /home/order/
COPY /root/college/service/oss/demo.jar /home/oss/
COPY /root/college/service/sms/demo.jar /home/sms/
COPY /root/college/service/statistics/demo.jar /home/statistics/
COPY /root/college/service/user/demo.jar /home/user/
COPY /root/college/service/vod/demo.jar /home/vod/

WORKDIR /home/
