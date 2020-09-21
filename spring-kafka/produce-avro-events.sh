#!/bin/bash
for i in {1..20}; do
  curl -X POST "localhost:8080/order-created?id=$i"
done
