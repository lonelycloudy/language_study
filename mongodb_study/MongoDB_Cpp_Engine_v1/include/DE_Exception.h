/********************************************************************
	The GoingGame Copyright (c) 2012 HiGame Team, All Rights reserved.
	created:	2012:6:18 10:58
	filename: 	DE_Exception
	author:		koangel
	
	purpose:	Exception对象，提供以及输出基本的错误
*********************************************************************/
#pragma once
#include "DB_Unit.h"

class DE_Exception : protected std::exception
{
public:
	DE_Exception( std::string exceptionStr );
	virtual ~DE_Exception(void);
};

