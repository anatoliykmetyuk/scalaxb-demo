#!/usr/bin/env bash
SCALAXB_DIR="./scalaxb"
EXEC_NAME="$SCALAXB_DIR/scalaxb"
MUTABLE="$1"

# Build scalaxb executable if not already exists
[[ ! -f "$EXEC_NAME" ]] && (cd "$SCALAXB_DIR";
  sbt "project app" "assembly";
  mv "scalaxb-*" "scalaxb")

# Build sources of the XML schema located under ./schema.
# Output to ./schema_src
time ($EXEC_NAME \
  --autopackages --package-dir --visitor -v "$MUTABLE" \
  -d schema_src/ \
  --attribute-prefix attr_ --protocol-package sxbtest.protocol \
  --no-varargs schema/*)

# Build a jar file out of ./schema_src sources from the previous step
# Output the jar to ./lib/schema.jar, therefore making it available
# from this SBT project (specifically from
# `src/main/scala/sxbtest/Main.scala`)
time (find schema_src/ -type f -name '*.scala' -print0 | \
  xargs -0 scalac -J-XX:-UseGCOverheadLimit -J-mx4g -d \
  lib/schema.jar)
