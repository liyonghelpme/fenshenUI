#ifndef  _TEA_H_
#define  _TEA_H_

extern "C"
{
void tea_encrypt(unsigned long* v,const unsigned long* k);
void tea_decrypt(unsigned long* v,const unsigned long* k);
}

#endif
