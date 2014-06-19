#include <string>
using namespace std;

extern "C"
{
	void startPay(const char* orderUrl)
	{
		printf("win32 -> unsupport easou pay\n"); 
	}

	bool catchBackPressed()
	{
		printf("win32 -> unsupport catchBackPressed \n"); 
		
		return false;
	}

	void startShare(const char* jniJson)
	{
		printf("win32 -> unsupport easou startShare\n"); 
	}
}