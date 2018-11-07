############################################################

# 构建基础镜像centos7+jdk-8u121-linux-x64+newUsc

############################################################

#设置基础镜像
FROM registry.dk:5000/tomcat8


#定义作者
MAINTAINER xuhuanjun "xuhuanjun@yixincapital.com"

#产品版本
ENV DISTRO_NAME=${project.artifactId}-${version}
#指定tomcat端口
ENV HTTP_PORT=18130
ENV HTTPS_PORT=18130
#部署服务
COPY ./$DISTRO_NAME.war $CATALINA_HOME/webapps
#COPY ./tomcat-server.xml $CATALINA_HOME/conf/server.xml
#修改tomcat端口
RUN sed -i s/8080/$HTTP_PORT/g $CATALINA_HOME/conf/server.xml
RUN sed -i s/8443/$HTTPS_PORT/g $CATALINA_HOME/conf/server.xml
#JVM时区设置
RUN rm -rf /etc/localtime && ln -s /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
#暴露端口
EXPOSE $HTTP_PORT
EXPOSE $HTTPS_PORT
WORKDIR $CATALINA_HOME/webapps
#重命名---------------------去掉版本信息
RUN mv -f $DISTRO_NAME.war ${project.artifactId}.war
