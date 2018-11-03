#!/usr/bin/env bash
EXEC_NAME="./scalaxb/scalaxb-1.5.2"

[[ ! -f "$EXEC_NAME" ]] && (cd ../scalaxb; sbt "project app" "assembly")

time ($EXEC_NAME \
  --autopackages --package-dir --visitor -v \
  -d schema_src/ \
  --attribute-prefix attr_ --protocol-package sxbtest.protocol \
  --no-varargs schema/*)

time (find schema_src/ -type f -name '*.scala' -print0 | \
  xargs -0 scalac -J-XX:-UseGCOverheadLimit -J-mx4g -d \
  lib/schema.jar)
