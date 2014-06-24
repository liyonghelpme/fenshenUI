#!/usr/bin/python

import os
#os.system("./build_native.sh")
fs = os.listdir('assets')
for i in fs:
    if i.find('.dll') != -1 or i.find('.exe') != -1 or i.find('.jar') != -1 or i.find('.bat') != -1:
        os.system('rm assets/%s' % (i))
        print 'rm', i


fs = os.listdir('../Resources')
for i in fs:
    if i.find('.lua') != -1:
        os.system('cp ../Resources/%s  assets/%s' % (i, i))
        print 'cp lua', i
