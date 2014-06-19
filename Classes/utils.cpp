#include <string>
#include "utils.h"
#include "tea.h"

using namespace std;
extern "C"
{
	unsigned char byte_reverse(unsigned char pByte)
	{
			unsigned char t1 = pByte;
			unsigned char t2 = t1 & 0xF0;
			unsigned char t3 = t1 & 0x0F;
			t3 = t3 << 4;
			t2 = t2 >> 4;
			t1 = t2 | t3;
			return t1;
	}

	//ppRes需加密的字符串
	//pDes加密之后的数据
	//pDesSize加密之后数据的长度
	void file_encrypt(const char* pRes,unsigned char * pDes,int pDesSize)
	{
		int len = strlen(pRes);
		int remain_len = (len%8);
		
		pDes[0] = remain_len;//分组后，剩下不齐的字节数

		//拷贝原始数据
		for(int i=1;i<=len;i++){
			pDes[i] = pRes[i-1];
		}
		for(int i=len+1;i<pDesSize;i++){
			pDes[i] = '\0';
		}

		//printf("%08X\n",KEY[0]);
		//printf("%08X\n",KEY[1]);
		//printf("%08X\n",KEY[2]);
		//printf("%08X\n",KEY[3]);
		//printf("\n");

		unsigned long v[2];
		for(int i=8;i<pDesSize;i+=8)
		{
			//反转
			for(int j=0;j<8;j++){
				pDes[i-j] = byte_reverse(pDes[i-j]);
			}

			//tea算法解密一次
			
			//将长度为8的unsigned char数组，转换为长度为2个unsigned long型数组
			v[0] = (((unsigned char)pDes[i-7])<<0) | (((unsigned char)pDes[i-6])<<8) | (((unsigned char)pDes[i-5])<<16) | (((unsigned char)pDes[i-4])<<24);
			v[1] = (((unsigned char)pDes[i-3])<<0) | (((unsigned char)pDes[i-2])<<8) | (((unsigned char)pDes[i-1])<<16) | (((unsigned char)pDes[i-0])<<24);

			//printf("%08X\n",v[0]);
			//printf("%08X\n",v[1]);
			//使用tea加密
			tea_encrypt(v,KEY);
			//printf("%08X\n",v[0]);
			//printf("%08X\n",v[1]);
			//printf("\n");

			//将长度为2个unsigned long型数组,拆解成长度为8的unsigned char数组
			pDes[i-7] = ((v[0] & 0x000000FF) >> 0);
			pDes[i-6] = ((v[0] & 0x0000FF00) >> 8);
			pDes[i-5] = ((v[0] & 0x00FF0000) >> 16);
			pDes[i-4] = ((v[0] & 0xFF000000) >> 24);
			pDes[i-3] = ((v[1] & 0x000000FF) >> 0);
			pDes[i-2] = ((v[1] & 0x0000FF00) >> 8);
			pDes[i-1] = ((v[1] & 0x00FF0000) >> 16);
			pDes[i-0] = ((v[1] & 0xFF000000) >> 24);
		}
	}

	//接入c字符串，作为加密对象，即char数组中，不包含'\0',否则会出错
	char *  file_decrypt(const unsigned char * pRes,int pSize)
	{
		if(!pRes || pSize==0){return NULL;};
		int len = pSize;
		int remain_len = pRes[0];

		char * arr = (char*)malloc((len+1)*sizeof(char));


		//拷贝原始数据
		for(int i=1;i<len;i++){
			arr[i-1] = pRes[i];
		}
		arr[len-1] = '\0';

		unsigned long v[2];
		for(int i=7;i<len-1;i+=8)
		{
			//tea算法解密一次
			
			//将长度为8的unsigned char数组，转换为长度为2个unsigned long型数组
			v[0] = (((unsigned char)arr[i-7])<<0) | (((unsigned char)arr[i-6])<<8) | (((unsigned char)arr[i-5])<<16) | (((unsigned char)arr[i-4])<<24);
			v[1] = (((unsigned char)arr[i-3])<<0) | (((unsigned char)arr[i-2])<<8) | (((unsigned char)arr[i-1])<<16) | (((unsigned char)arr[i-0])<<24);
			//printf("%08X\n",v[0]);
			//printf("%08X\n",v[1]);
			//使用tea解密
			tea_decrypt(v,KEY);
			//printf("%08X\n",v[0]);
			//printf("%08X\n",v[1]);
			//printf("\n");
			//将长度为2个unsigned long型数组,拆解成长度为8的unsigned char数组
			arr[i-7] = ((v[0] & 0x000000FF) >> 0);
			arr[i-6] = ((v[0] & 0x0000FF00) >> 8);
			arr[i-5] = ((v[0] & 0x00FF0000) >> 16);
			arr[i-4] = ((v[0] & 0xFF000000) >> 24);
			arr[i-3] = ((v[1] & 0x000000FF) >> 0);
			arr[i-2] = ((v[1] & 0x0000FF00) >> 8);
			arr[i-1] = ((v[1] & 0x00FF0000) >> 16);
			arr[i-0] = ((v[1] & 0xFF000000) >> 24);

			//反转
			for(int j=0;j<8;j++){
				arr[i-j] = byte_reverse(arr[i-j]);
			}
		}

		if(remain_len > 0){
			for(int i=len-(8-remain_len)-1;i<len;i++){
				arr[i] = '\0';
			}
		}
		arr[len] = '\0';

		return arr;
	}
}
