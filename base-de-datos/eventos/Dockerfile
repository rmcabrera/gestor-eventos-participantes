FROM container-registry.oracle.com/database/express:21.3.0-xe

USER root
RUN mkdir -p /opt/oracle/scripts

COPY oracle-eventos.sql /opt/oracle/scripts/oracle-eventos.sql
COPY startup.sh /opt/oracle/scripts/startup.sh

RUN chmod +x /opt/oracle/scripts/startup.sh

CMD ["/opt/oracle/scripts/startup.sh"]
