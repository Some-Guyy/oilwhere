#!/bin/bash
if [ "$BASH_SOURCE" = "" ] ; then
    echo "This script should be sourced, not executed. Run \"source ./package.sh\" instead."
    exit 1
fi

set -a
source .env
set +a

./mvnw package
