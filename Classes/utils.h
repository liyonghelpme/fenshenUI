#ifndef  _UTILS_H_
#define  _UTILS_H_

extern "C"
{
	const unsigned long KEY[4] = {1362230201ul,3622302013ul,1342093071ul,3420930712ul};
	void file_encrypt(const char* pRes,unsigned char * pDes,int pDesSize);
	char *  file_decrypt(const unsigned char * pRes,int pSize);
}

#endif
