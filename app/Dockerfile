FROM openjdk:8-jre-alpine

ENV JAR_FILE app-0.0.1-boot.jar
ENV WORK_DIR /usr/fitness_notes
COPY build/libs/$JAR_FILE $WORK_DIR/

WORKDIR $WORK_DIR
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -Xmx2048m -Xms512m -jar $JAR_FILE"]