#!/bin/bash
for i in {1..100}; do
  curl -X POST "localhost:8080/create-event?id=$i"
done
