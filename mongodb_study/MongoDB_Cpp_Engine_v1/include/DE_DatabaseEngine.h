/********************************************************************
	The GoingGame Copyright (c) 2012 HiGame Team, All Rights reserved.
	created:	2012:6:18 10:54
	filename: 	DE_DatabaseEngine
	author:		koangel
	
	purpose:	���ݿ�������࣬�������Ӳ��������ݿ����
				������ݿ����ݵ�

				1.0:
				��������������MongoDB C Driver
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

	// ������MongoDb
	bool	connect( std::string host,unsigned short port,bool useException = false );

	// ������Ϊ(�޽������Ϊ)
	void	insert( std::string tables,std::string jsons );
	void	remove( std::string tables,std::string jsons );
	void	update( std::string tables,std::string oldjson,std::string newjson );

	// ��ѯ��Ϊ
	bool	find( std::string tables,std::string jsons,DE_RowObject& _row );
	bool	find( std::string tables,std::string jsons,std::string sortJson,DE_Rows& _row,int skip = 0,int limit = 0,int options = 0 );

	int		count( std::string tables,std::string _obj  );

	void	disconnect();

	std::string getVersion();
private:
	mongo					m_Connection;	// ���Ӷ���
	bool					m_useException;	// �Ƿ�ʹ���쳣����
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
	// ͬ��������ֱ��ʹ��ֱ�ӷ���
	static void				findN( std::string _tables,std::string queryString,DE_Rows& _obv,bool isSort = false,std::string _sortJson = "",bool isAsc = false,int toReturn = 10000,int skip = 0 );
	static void				find( std::string _tables,std::string queryString,DE_RowObject& _row);
	static void				execute( std::string _tables,std::string queryString,GoByte _type );
	static void				update( std::string _tables,std::string _obj,std::string _after );
	static GoDWORD			count( std::string _tables,std::string _obj );
};
