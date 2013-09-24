/********************************************************************
	The GoingGame Copyright (c) 2012 HiGame Team, All Rights reserved.
	created:	2012:6:18 10:54
	filename: 	DE_DatabaseEngine
	author:		koangel
	
	purpose:	数据库对象主类，负责连接并建立数据库对象
				审查数据库内容等

				1.0:
				建立并设计类基于MongoDB C Driver
*********************************************************************/
#pragma once
#include "DB_Unit.h"
#include "DE_RowObject.h"
#include "DE_Rows.h"

class EXPORT_CLASS DE_DatabaseEngine
{
public:
	DE_DatabaseEngine(void);
	DE_DatabaseEngine( const char* host,unsigned short port,bool useException = false );
	virtual ~DE_DatabaseEngine(void);

	// 连接至MongoDb
	bool	connect( std::string host,unsigned short port,bool useException = false );

	// 操作行为(无结果集行为)
	void	insert( std::string tables,std::string jsons );
	void	remove( std::string tables,std::string jsons );
	void	update( std::string tables,std::string oldjson,std::string newjson );

	// 查询行为
	bool	find( std::string tables,std::string jsons,DE_RowObject& _row );
	bool	find( std::string tables,std::string jsons,std::string sortJson,DE_Rows& _row,int skip = 0,int limit = 0,int options = 0 );

	int		count( std::string tables,std::string _obj  );

	void	disconnect();

	std::string getVersion();
private:
	mongo					m_Connection;	// 连接对象
	bool					m_useException;	// 是否使用异常对象
	std::string				m_errorMessage;
	mongo_write_concern		m_writeConcern;
	boost::recursive_mutex	m_mutex;
};

#define DEDatabaseInstance Go_Singleton<DE_DatabaseEngine>::getInstance

enum sp_opttype{ sp_insert,sp_remove,sp_find,sp_update,sp_endopt };
class EXPORT_CLASS Go_DatabaseApiHelper
{
public:
	static void				initDatabaseEngine( std::string _connStr = "127.0.0.1:27017" );
	// 同步函数，直接使用直接返回
	static void				findN( std::string _tables,std::string queryString,DE_Rows& _obv,bool isSort = false,std::string _sortJson = "",bool isAsc = false,int toReturn = 10000,int skip = 0 );
	static void				find( std::string _tables,std::string queryString,DE_RowObject& _row);
	static void				execute( std::string _tables,std::string queryString,GoByte _type );
	static void				update( std::string _tables,std::string _obj,std::string _after );
	static GoDWORD			count( std::string _tables,std::string _obj );
};
