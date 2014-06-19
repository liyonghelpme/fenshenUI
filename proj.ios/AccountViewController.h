//
//  AccountViewController.h
//  HelloLua
//
//  Created by easou on 9/29/13.
//
//

#import <UIKit/UIKit.h>
#import "UserModel.h"
#import "NetUtil.h"
@protocol AccountDelegate;
@interface AccountViewController : UIViewController{
    NSArray *array;
    NSArray *array2;
    @private id<AccountDelegate> delegate;
    NSString *gameCenter;
    UserModel *userModel;
    NetUtil *netUtil;
    NSString *token;
//    JSonUtil *jsonUtil ;
    
}
- (IBAction)backAction:(id)sender;
@property (nonatomic, assign) id<AccountDelegate>delegate;
@property (retain,nonatomic) NSString *gameCenter;
@property (retain,nonatomic) UserModel *userModel;
@property (retain,nonatomic) NSString *token;
@end
@protocol AccountDelegate <NSObject>
-(void) toStartSdKController;
@end
