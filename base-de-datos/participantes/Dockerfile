FROM container-registry.oracle.com/database/express:21.3.0-xe

USER root
RUN mkdir -p /opt/oracle/scripts

COPY oracle-participantes.sql /opt/oracle/scripts/oracle-participantes.sql
COPY startup.sh /opt/oracle/scripts/startup.sh

RUN chmod +x /opt/oracle/scripts/startup.sh

CMD ["/opt/oracle/scripts/startup.sh"]
