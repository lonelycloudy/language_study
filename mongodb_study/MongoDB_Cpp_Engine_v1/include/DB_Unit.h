/********************************************************************
	The GoingGame Copyright (c) 2012 HiGame Team, All Rights reserved.
	created:	2012:6:18 10:45
	filename: 	DB_Unit
	author:		koangel
	
	purpose:	针对C语言数据库模型封装并兼容旧模型的数据库模型

*********************************************************************/
#pragma once
#define BOOST_DISABLE_ASSERTS
// by Koangel
#include <stdint.h>
// 定义
typedef char* 			GoString;
typedef unsigned char 	GoByte;
typedef	GoByte*			GolpByte;
typedef	unsigned short	GoWORD;
typedef unsigned int	GoDWORD;
typedef int64_t			GoInt64;
typedef uint64_t		GoUInt64;
typedef unsigned long	GoUlong;
typedef	void*			GoPvoid;

// system header
#include <iostream>
#include <fstream>
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#ifndef WIN32
#include <time.h>
#include <sys/time.h>
#include <sys/timeb.h>
#include <iconv.h>
#include <stlport/map>
#include <stlport/string>
#include <stlport/queue>

#define EXPORT_CLASS 
#else
#include <WinSock2.h>
#include <Windows.h>
#include <map>
#include <string>
#include <queue>
#ifdef DLL_LIB
	#define EXPORT_CLASS  _declspec(dllexport)
#else
	#define EXPORT_CLASS 
#endif

#pragma comment(lib,"ws2_32.lib")
#endif

#define MONGO_HAVE_STDINT

// mongodb
#include "mongodb/mongo.h"
#include <boost/thread.hpp>