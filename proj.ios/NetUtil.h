//
//  NetUtil.h
//  HelloLua
//
//  Created by easou on 9/23/13.
//
//

#import <Foundation/Foundation.h>

@interface NetUtil : NSObject
-(NSString *) userGameRequest:(NSString *) userName andPassword:(NSString *) password;
-(NSString *) userGameLogin:(NSString *)token partnerId:(NSString *)partnerId serverId:(NSString *)serverId timestamp:(NSString *)timestamp fr:(NSString *)fr version:(NSString *)version  qn:(NSString *)qn key:(NSString *)key;
-(NSString *)sendRequestGame:(NSString *)paraStr;
-(NSArray *) getServer;
-(NSString *) registerRequest;
-(NSString *) updatepasswordRequest:(NSString *) oldPwd andNewPwd:(NSString *) newPwd andToken:(NSString *) myToken;
-(NSString *) pay:(NSString *)json andMoney:(NSString *) money andProductId:(NSString *) productId;
-(NSString *) requestResetPass:(NSString *) phoneNum;
-(NSString *) applyResetPass:(NSString *) phoneNum andNewPwd:(NSString *) newpwd andVeriCode:(NSString *) veriCode;
-(NSString *) getUserById:(NSString *) _id;
-(NSString *) registByName:(NSString *) name andPassword:(NSString *) password;
-(NSString *) updateNickName:(NSString *) nickName andToken:(NSString *) token;
-(NSString *) getPhoneProving:(NSString *) mobile andToken:(NSString *) token;
-(NSString *) bindPhone:(NSString *) mobile andProving:(NSString *) proving andToken:(NSString *) token;
@end
