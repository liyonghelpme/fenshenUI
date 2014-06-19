//
//  UserModel.h
//  HelloLua
//
//  Created by easou on 9/30/13.
//
//

#import <Foundation/Foundation.h>

@interface UserModel : NSObject{
    NSString *_id;
    NSString *name;
    NSString *nickName;
    int sex;
    int status;
    NSString *mobile;
}
@property (nonatomic,retain) NSString *_id;
@property (nonatomic,retain) NSString *name;
@property (nonatomic,retain) NSString *nickName;
@property (nonatomic) int sex;
@property (nonatomic) int status;
@property (nonatomic,retain) NSString *mobile;
@end
