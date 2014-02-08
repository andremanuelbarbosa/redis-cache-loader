#!/bin/bash

. "$( dirname "${BASH_SOURCE[0]}" )/setenv.sh"

if [ -f "$JSVC_PID_FILE" ]; then
	echo "${project.name} is running. ($( cat "$JSVC_PID_FILE" ))" >&2
	exit 1
fi

if [ -f "$JSVC_EXECUTABLE" ]; then
    echo 'Starting Daemon ${project.name} ${project.version}...'
    $JSVC_EXECUTABLE -server -cp "$JAVA_CLASSPATH" -user "$JSVC_USER" \
	    -pidfile $JSVC_PID_FILE $JAVA_OPTS $JAVA_MAIN_CLASS
else
    echo 'Starting ${project.name} ${project.version}...'
    $JAVA_EXEC -cp "$JAVA_CLASSPATH" $JAVA_OPTS $JAVA_MAIN_CLASS
fi