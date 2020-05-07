#!/bin/bash
#SERVER_NAME="$( cd "$( dirname "$0"  )" && pwd  )"

SERVER_NAME="logfileserver-0.0.1-SNAPSHOT.jar"
SERVER_TAG=$SERVER_NAME
is_server_run(){
	tmp=`ps -ef |grep $SERVER_TAG | grep -v grep | awk '{print $2}'`
	if [ -z "$tmp" ]; then
		return 0
	else
		return $tmp
	fi	
}

start_server(){
	tmp=`ps -ef |grep $SERVER_TAG | grep -v grep | awk '{print $2}'`
	if [ -n "$tmp" ]; then 
		echo "$SERVER_NAME already started!"
		return 1
	fi
	nohup java -jar ../lib/$SERVER_NAME  --spring.config.location=../config/application.properties >/dev/null 2>&1  &
	echo "$SERVER_NAME starting..."
}

kill_server(){
	is_server_run
	tmp=$?	
	if [ $tmp -eq 0 ]; then
		echo "$SERVER_NAME hasn't started yet!"
		return 1
	else
		pid=`ps -ef |grep $SERVER_TAG | grep -v grep | awk '{print $2}'`
		kill -15 $pid 
		if [ ! $? -eq 0 ]; then
			echo "Can't stop $SERVER_NAME!"
			return 1
		else
			echo "$SERVER_NAME stopped!"
			return 0
		fi
	fi
}

status_server(){
	is_server_run
	if [ $? -eq 0 ]; then
		echo "$SERVER_NAME is not running."
	else
		echo "$SERVER_NAME is running.pid=$tmp"
	fi
}

kill_run()
{
    kill -9 $1		
		return $?
}

usage_error() {
	  echo "   	$0 start   	(start $SERVER_NAME)"
	  echo "   	$0 stop    	(stop $SERVER_NAME)"
	  echo "   	$0 restart 	(restart $SERVER_NAME)"
	  echo "   	$0 status  	(list $SERVER_NAME status)"	  
      exit 1
}

if [ $# -ne 1 ]; then
	usage_error
fi

if [ "$1" = "restart" ]; then
	set stop start
fi 

until [ $# -eq 0 ]
do	
	case $1 in
		start)
			start_server			
			
			;;	
		stop)
			kill_server	
			;;			
		status)
			status_server
			;;
		*)
			echo "unknown parameter '$1'"
	esac
shift
done

