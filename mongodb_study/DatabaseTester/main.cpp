#include <SP_DatabaseEngine.hpp>
#include <boost/thread.hpp>

#pragma comment(lib,"ws2_32.lib")

void writeThread()
{
	// 插入10000条数据
	for (int i = 0;i < 50000;i++)
	{
		Go_DatabaseApiHelper::execute("sportmeeting.testDB","{ \"userid\" : 11,\"string\":\"strasdasd\" }",sp_insert);
	}
}

void readThread()
{
	DE_Rows _rows;
	Go_DatabaseApiHelper::findN("sportmeeting.testDB","{ \"userid\" : 11 }",_rows,false,"",false,0);

	for (int i = 0;i < _rows.getCount();i++)
	{
		std::cout << _rows[i]->toString("string") << i << std::endl;
	}
}

void main()
{
	Go_DatabaseApiHelper::initDatabaseEngine("192.168.1.13:27017");

	boost::thread_group _group;
	_group.create_thread(boost::bind(&writeThread));
	_group.create_thread(boost::bind(&readThread));

	_group.join_all();

	system("pause");
}