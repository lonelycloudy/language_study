#!/bin/sh

MOBILEHUB_DIST_ROOT=/var/www/mobilehub

VER=`date +%y%m%d`
BACKUP_PATH=/home/backup/235/labs-backup-$VER.tar.gz
BACKUP_FILE_DATA="data/portlets.php \
        data/portlets-editing.php \
        data/aggregation/index3 \
        data/aggregation/rss2 \
        data/search/index \
        data/admin \
        data/attach \
        data/config \
        data/mail \
        data/temp \
        data/upload \
        data/userinfor \
        www/upload \
        www/community/sites/default/files"
BACKUP_FILE_CONF="/etc/httpd/conf \
        /etc/httpd/conf.d \
        /etc/my.cnf \
        /etc/php.ini \
        /usr/local/nginx/conf \
        /usr/local/etc/varnish/default.vcl"

tar -C $MOBILEHUB_DIST_ROOT -cz $BACKUP_FILE_DATA $BACKUP_FILE_CONF \
        | ssh root@192.168.0.233 "cat > $BACKUP_PATH"

exit

test -d /var/backup || mkdir -p /var/backup

echo 'Backup file ...'
tar -C $MOBILEHUB_DIST_ROOT --exclude=.svn -jcf $BACKUP_FILE \
        $BACKUP_FILE_DATA \
        $BACKUP_FILE_CONF

echo 'Tranfer file to FTP ...'
printf "user labs labslabs\nbin\nmkdir backup\nmkdir backup/`date +%y`\nmkdir backup/`date +%y/%m`\nput $BACKUP_FILE /backup/`date +%y/%m`/`basename $BACKUP_FILE`" | ftp -in www.datiba.cn 1008

BACKUP_FILE_SIZE=`ls -l $BACKUP_FILE | awk '{print $5}'`
FTP_FILE_SIZE=$(echo -ne "user labs labslabs\nls /backup/$(date +%y/%m)/$(basename $BACKUP_FILE)" | ftp -in www.datiba.cn 1008 | grep labs | awk '{print $5}')

echo $BACKUP_FILE_SIZE
echo $FTP_FILE_SIZE

test "$BACKUP_FILE_SIZE" = "$FTP_FILE_SIZE" && rm -f $BACKUP_FILE
