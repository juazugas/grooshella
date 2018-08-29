FROM openjdk:8-jre-alpine

ENV WAR_FILE grooshella-0.1.0-SNAPSHOT.war
ENV WAR_HOME /usr/grooshella

EXPOSE 8080

RUN mkdir -p WAR_HOME
COPY target/$WAR $WAR_HOME/
WORKDIR $WAR_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $WAR_FILE"]


# FROM fabric8/java-alpine-openjdk8-jre
# 
# EXPOSE 8080
# 
# # Copy dependencies
# COPY target/dependency/* /deployment/libs/
# 
# ENV JAVA_APP_DIR=/deployment
# ENV JAVA_LIB_DIR=/deployment/libs
# ENV JAVA_CLASSPATH=${JAVA_APP_DIR}/classes:${JAVA_LIB_DIR}/*
# ENV JAVA_OPTIONS="-Dvertx.cacheDirBase=/tmp"
# ENV JAVA_MAIN_CLASS="io.vertx.intro.first.MyFirstVerticle"
# 
# # Copy classes
# COPY target/classes /deployment/classes
