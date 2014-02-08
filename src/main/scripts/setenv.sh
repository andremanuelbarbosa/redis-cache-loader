#!/bin/bash

JSVC_EXECUTABLE="$( which jsvc )"
JSVC_PID_FILE=/tmp/${project.artifactId}.pid

if [ -z "$JSVC_USER" ]; then
	JSVC_USER="$USER"
fi

DIST_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )/../" && pwd )"
LIB_DIR="$DIST_DIR/lib"
CONF_DIR="$DIST_DIR/conf"

JAVA_EXEC="$( which java )"
JAVA_CLASSPATH="$CONF_DIR:$LIB_DIR/*"
JAVA_MAIN_CLASS="com.andremanuelbarbosa.redis.cacheloader.RedisCacheLoader"
JAVA_OPTS="-Ddistribution.dir=$DIST_DIR"

if [ -z "$JAVA_HOME" ]; then
	export JAVA_HOME="$( $JAVA_EXEC -cp "$JAVA_CLASSPATH" -server \
		$JAVA_MAIN_CLASS )"
fi

export JSVC_EXECUTABLE JSVC_PID_FILE JSVC_USER DIST_DIR CONF_DIR JAVA_EXEC \
	JAVA_CLASSPATH JAVA_MAIN_CLASS JAVA_HOME