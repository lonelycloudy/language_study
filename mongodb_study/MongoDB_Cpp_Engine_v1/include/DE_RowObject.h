/********************************************************************
	The GoingGame Copyright (c) 2012 HiGame Team, All Rights reserved.
	created:	2012:6:18 12:03
	filename: 	DE_RowObject
	author:		koangel
	
	purpose:	单个数据对象
*********************************************************************/
#pragma once
#include "DB_Unit.h"

class DE_RowObject
{
public:
	DE_RowObject(void);
	virtual ~DE_RowObject(void);

	void fromBson( bson* object );

	std::string toString( std::string key );
	int			toInt	( std::string key );
	bool		toBool	( std::string key );
	GoInt64		toInt64	( std::string key );
private:
	bson  m_BsonObject;
};

typedef boost::shared_ptr<DE_RowObject> DERow;
