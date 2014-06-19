//
//  TokenModel.h
//  HelloLua
//
//  Created by easou on 9/30/13.
//
//

#import <Foundation/Foundation.h>

@interface TokenModel : NSObject{
    NSString *age;
    NSString *domain;
    NSString *path;
    NSString *token;
}
@property (nonatomic,retain) NSString *age;
@property (nonatomic,retain) NSString *domain;
@property (nonatomic,retain) NSString *path;
@property (nonatomic,retain) NSString *token;
@end
