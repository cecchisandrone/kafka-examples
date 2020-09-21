#!/bin/bash
for i in {1..20}; do
  curl -X POST "localhost:8080/create-event?id=$i"
done
