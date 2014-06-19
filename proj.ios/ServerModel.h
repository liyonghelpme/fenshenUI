//
//  ServerModel.h
//  HelloLua
//
//  Created by easou on 9/23/13.
//
//

#import <Foundation/Foundation.h>

@interface ServerModel : NSObject{
    NSString * serverId;
    NSString * serverName;
    NSString * serverPort;
    int status;
    long openTime;
    
}
@property (nonatomic,retain) NSString *serverId;
@property (nonatomic,retain) NSString *serverName;
@property (nonatomic,retain) NSString *serverPort;
@property (nonatomic) int status;
@property (nonatomic) long openTime;

@end
