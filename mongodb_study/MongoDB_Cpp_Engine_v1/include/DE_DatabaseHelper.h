/********************************************************************
	The GoingGame Copyright (c) 2012 HiGame Team, All Rights reserved.
	created:	2012:6:18 11:39
	filename: 	DE_DatabaseHelper
	author:		koangel
	
	purpose:	数据库的帮助函数
				用于解决一些需要轻量级转换的内容
*********************************************************************/
#pragma once
#include "DB_Unit.h"

class DE_DatabaseHelper
{
public:
	DE_DatabaseHelper(void);
	virtual ~DE_DatabaseHelper(void);

	static bson* fromJson( std::string jsonString );
private:
	static void json_to_bson_append_array( bson* bb , struct json_object *a );
	static void json_to_bson_append( bson* bb , struct json_object *o );
	static void json_to_bson_append_element( bson *bb , const char *k , struct json_object *v );

};

