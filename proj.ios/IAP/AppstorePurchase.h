#ifndef APPSTOREPURCHASE_H
#define APPSTOREPURCHASE_H

#include <string>
class CAppstorePurchase
{
public:
	CAppstorePurchase(void);
	~CAppstorePurchase(void);

	static CAppstorePurchase* getInstance();
	
	//必须注册的两个回调
	void registerPaymentQueue(const std::string& strFun);
	void registerRequestProduct(const std::string& strFun);

	//获取商品信息 0表示请求成功  1获取商品列表为空 2表示该设备不支持支付 －1表示请求失败
	void buyProduct(const std::string& strGoodsID);
	
	//购买物品的回调
	// error codes for the SKErrorDomain
	/*
	 enum {
	 SKPaymentTransactionStatePurchasing,    // Transaction is being added to the server queue.
	 SKPaymentTransactionStatePurchased,     // Transaction is in queue, user has been charged.  Client should complete the transaction.
	 SKPaymentTransactionStateFailed,        // Transaction was cancelled or failed before being added to the server queue.
	 SKPaymentTransactionStateRestored       // Transaction was restored from user's purchase history.  Client should complete the transaction.
	 };*/
	
	//交易成功返回的校验信息
	void setTranscationReceipt(std::string& str);
	const char* getTranscationReceipt();
	
	
	/*
	 // error codes for the SKErrorDomain
	 enum {
	 SKErrorUnknown,
	 SKErrorClientInvalid,       // client is not allowed to issue the request, etc.
	 SKErrorPaymentCancelled,    // user cancelled the request, etc.
	 SKErrorPaymentInvalid,      // purchase identifier was invalid, etc.
	 SKErrorPaymentNotAllowed    // this device is not allowed to make the payment
	 };
	 */
	void setRecordTransactionErrorCode(int value);
	int getRecordTransactionErrorCode();


	//内部使用的接口
	void excReuestProductCallback(int retValue);
	void excPaymentQueue(int retValue);
	void excCallback(const std::string& strFun, int value);
	
	//以下这个接口暂时不用
	void registerIsCanMakePayments(const std::string& strFun);
private:
	std::string m_strPaymenQueueFun;
	std::string m_strIsCanMakePaymentsFun;
	std::string m_strRequestProductFun;
	static CAppstorePurchase* instance; 
	std::string m_strTransactionReceipt;
	int			m_nRecordTransactionErrorCode;
};

#endif