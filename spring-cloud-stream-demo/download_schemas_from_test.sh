#!/usr/bin/env bash
# to be called in the same folder as pom.xml
export KAFKA_SCHEMA_REGISTRY_URL=http://test-swarm.voverc.com:8081
mvn schema-registry:download