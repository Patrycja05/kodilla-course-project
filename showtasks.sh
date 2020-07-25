#!/usr/bin/env bash

echo "./runcrud.sh";

if "./gradlew build"; then
    open "http://localhost:8080/crud/v1/task/getTasks"
  else
    echo "There were errors"
    open  "https://kodilla.com/pl"
  fi