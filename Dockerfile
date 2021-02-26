# 建议生产使用，ref: http://blog.tenxcloud.com/?p=1894
#FROM fabric8/java-jboss-openjdk8-jdk
FROM bu-hub.deepexi.com/public/openjdk-skywalking:1.1

ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8

USER root

COPY college-parent/target/demo.jar /home/
WORKDIR /home/
