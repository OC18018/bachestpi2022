FROM payara/server-full:5.2022.2-jdk11
USER root
RUN apt update && apt install -y wget
USER payara
RUN wget -O $PAYARA_DIR/glassfish/lib/postgres.jar https://jdbc.postgresql.org/download/postgresql-42.2.5.jar
ARG POSTGRES_USER
ARG POSTGRES_PASS
ARG POSTGRES_DB

RUN echo "create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --property user=$POSTGRES_USER:password=$POSTGRES_PASS:serverName=db:portNumber=5432:databaseName=$POSTGRES_DB baches-pool" >> $PREBOOT_COMMANDS \
&& echo "create-jdbc-resource --connectionpoolid baches-pool jdbc/baches" >> $PREBOOT_COMMANDS
RUN echo "deploy $PAYARA_DIR/Baches.war" >> $POSTBOOT_COMMANDS
COPY Baches.war $PAYARA_DIR
