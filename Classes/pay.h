#ifndef  _APP_PAY_H_
#define  _APP_PAY_H_


extern "C"
{
void startPay(const char* orderUrl);
bool catchBackPressed();
void startShare(const char* jniJson);
}

#endif
