FROM 10.12.20.20/pub/openjdk:8-jre
MAINTAINER dehuisun 825612263@qq.com
ENV LANG C.UTF-8
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/shanghai" > /etc/timezone
VOLUME /tmp
#ADD target/lib /app/lib
ADD target/resources /app/resources
ADD target/railway-site-back.jar /app/app.jar
RUN sh -c 'touch /app/app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=oracle-dev -jar /app/app.jar" ]
