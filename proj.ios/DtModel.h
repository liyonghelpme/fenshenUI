//
//  DtModel.h
//  HelloLua
//
//  Created by easou on 9/30/13.
//
//

#import <Foundation/Foundation.h>

@interface DtModel : NSObject{
    NSString *uid;
    NSString *tk;
    NSString *puid;
    NSString *ptk;
    NSString *exti;
    NSString *rc;
}
@property (nonatomic,retain) NSString *uid;
@property (nonatomic,retain) NSString *tk;
@property (nonatomic,retain) NSString *puid;
@property (nonatomic,retain) NSString *ptk;
@property (nonatomic,retain) NSString *exti;
@property (nonatomic,retain) NSString *rc;
@end
