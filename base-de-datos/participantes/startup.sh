#!/bin/bash
echo "Esperando a que Oracle esté listo..."
until echo "exit" | sqlplus -s sys/${ORACLE_PWD}@//localhost:1522/XEPDB1 as sysdba > /dev/null; do
  sleep 5
done

echo "Ejecutando script de creación de base de datos..."
sqlplus sys/${ORACLE_PWD}@//localhost:1521/XEPDB1 as sysdba @/base-de-datos/participantes/oracle-participantes.sql

echo "Base de datos inicializada."
tail -f /dev/null
