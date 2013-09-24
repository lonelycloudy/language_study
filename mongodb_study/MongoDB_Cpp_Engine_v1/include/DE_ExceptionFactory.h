#pragma once
#include "DB_Unit.h"

extern class DE_Exception;

class DE_ExceptionFactory
{
public:
	DE_ExceptionFactory(void);
	virtual ~DE_ExceptionFactory(void);

	static DE_Exception* create( const char* lpFormat, ... );
};

#define THROW_EXCEPTION  throw DE_ExceptionFactory::create