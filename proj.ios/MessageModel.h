//
//  MessageModel.h
//  HelloLua
//
//  Created by easou on 9/29/13.
//
//

#import <Foundation/Foundation.h>

@interface MessageModel : NSObject{
    NSString *ret;
    //代码
    NSString *code;
    //消息
    NSString *message;
}
@property (nonatomic,retain) NSString *ret;
@property (nonatomic,retain) NSString *code;
@property (nonatomic,retain) NSString *message;
@end
