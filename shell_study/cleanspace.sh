#!/bin/sh
PLAN_PATH=/var/www/mobilehub

VER=`date +%y%m%d`

for (( i=9; i<29; i++ )) do printf "user labs labslabs\nbin\nput /var/lib/mysql/mysql-bin.0000`printf "%02d" $i` /backup/dblo
g/mysql-bin.0000`printf "%02d" $i` " | ftp -i -n -v www.commr.cn 1008; 
done