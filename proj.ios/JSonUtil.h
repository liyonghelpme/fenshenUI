//
//  JSonUtil.h
//  HelloLua
//
//  Created by easou on 9/23/13.
//
//

#import <Foundation/Foundation.h>
#import "MessageModel.h"
#import "UserModel.h"
#import "TokenModel.h"
#import "DtModel.h"
@interface JSonUtil : NSObject
//解析时间撮
-(NSString *) reolveTimestamp:(NSString *)jsonStr;
-(NSDictionary *) jsonReolveDic:(NSString *)jsonStr;
//解析Token
-(NSString *) reolveToken:(NSString *)jsonStr;
-(MessageModel *) getMessage:(NSString *)jsonStr;
-(UserModel *) getUserModel:(NSString *)jsonStr;
-(TokenModel *) getTokenModel:(NSString *)jsonStr;
-(DtModel *) getDtModel:(NSString *)jsonStr;
@end
