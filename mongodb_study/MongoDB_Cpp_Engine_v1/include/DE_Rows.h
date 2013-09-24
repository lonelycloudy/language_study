#pragma once
#include "DB_Unit.h"
#include "DE_RowObject.h"

class DE_Rows : protected std::vector<DERow>
{
public:
	DE_Rows(void);
	virtual ~DE_Rows(void);

	void	add( DERow _row );
	bool	HasRow( int index );
	DERow	getRow( int index );
	int		getCount( ) { return size(); }

	DERow   operator [ ] ( int index ) { return getRow(index); }
private:
	boost::recursive_mutex	m_mutex;
};

