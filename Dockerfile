# OpenJDK 17을 사용한 베이스 이미지
FROM openjdk:17

# Build 된 Jar 파일 경로 설정 (= Gradle 기준)
# ARG JAR_FILE_PATH=build/libs/*.jar
ARG JAR_FILE_PATH=build/libs/kdy-0.0.1-SNAPSHOT.jar

# Jar 파일을 Container 내부로 복사
COPY ${JAR_FILE_PATH} toyProject.jar

# Container 실행 시 Application 시작
ENTRYPOINT ["java", "-jar", "/toyProject.jar"]

# Container가 외부와 연결할 포트를 8080으로 오픈
EXPOSE 8080