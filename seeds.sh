#!/bin/bash
SEEDS=src/main/resources/seeds
for SEED in $SEEDS/*; do
  curl -X POST \
      -F image=@"$SEED" \
      http://localhost:8080/api/v1/album
  echo ''
done
