#!/bin/bash
set -e
USER_NAME="${POSTGRES_USER:-catalog}"
if [ -z "$POSTGRES_MULTIPLE_DATABASES" ];
then
  echo "POSTGRES_MULTIPLE_DATABASES is empty, skipping"
  exit 0
fi

echo "Creating extra databases : $POSTGRES_MULTIPLE_DATABASES"
IFS=',' read -ra DBS <<< "$POSTGRES_MULTIPLE_DATABASES"
for db in "${DBS[@]}"; do
  db="$(echo "$db" | xargs)"
  echo "Creating database: $db"
  psql -v ON_ERROR_STOP=1 -U catalog <<-EOSQL
    CREATE DATABASE "$db";
EOSQL
done