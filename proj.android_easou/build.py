#!/usr/bin/python
import os
#fs = os.listdir('../proj.android_lib/assets/testUI/')
if not os.path.exists('assets/testUI'):
    os.mkdir('assets/testUI')
#os.system('cp -rf ../proj.android_lib/assets/testUI/*  assets/testUI/')
os.system('cp -rf ../Resources/testUI/*  assets/testUI/')
os.system('cp -f ../Resources/Entry assets/Entry')
