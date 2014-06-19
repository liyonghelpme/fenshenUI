//
//  SeverViewController.h
//  HelloLua
//
//  Created by easou on 9/22/13.
//
//

#import <UIKit/UIKit.h>
#import "CustomAlertView.h"
#import "NetUtil.h"
#import "ServerModel.h"
#import "JSonUtil.h"
#import "AccountViewController.h"
#import "UIImageViewAnimator.h"
@protocol MyDelegate;
@interface SeverViewController : UIViewController <UIAlertViewDelegate, UITableViewDelegate, UITableViewDataSource,UITextFieldDelegate>{
    @private id<MyDelegate> delegate;
    UITableView *myTableView;
    IBOutlet UILabel *serverLabel;
    IBOutlet UIActivityIndicatorView *loadView;
    NSArray *array; //服务器列表
    NSIndexPath *lastIndexPath;
    
    NSString *gameCenter;
    NetUtil *netUtil;
    JSonUtil *jsonUtil;
    
    NSString *token;
    
    NSUserDefaults *SaveDefaults ;
    
    
    int tabSelect; //1为注册、0为登录
    IBOutlet UIButton *loginTabButton;
    IBOutlet UIButton *registerTabButton;
    IBOutlet UIButton *loginAndRegisterButton;
    IBOutlet UIView *alertUIView;
    
    IBOutlet UITextField *userNameText;
    IBOutlet UITextField *passwordText;
    
    
    
    IBOutlet UIView *welcomeUiView;
    IBOutlet UILabel *welcomeLabel;
    IBOutlet UIImageView *flagImageView;
    IBOutlet UIImageView *leftCloudImageView;
    IBOutlet UIImageView *rightCloudImageView;
    IBOutlet UIView *picUIView;
    IBOutlet UIImageView *arrowImageView;
    IBOutlet UIImageViewAnimator *arrowImagView;
    
    NSMutableArray * flagAnimateArray;//旗动画
    NSMutableArray * arrowAnimateArray;//箭动画
    
    IBOutlet UIView *serverUIView;
    IBOutlet UITableView *serverTableView;
    IBOutlet UIButton *lastLoginButton;
    IBOutlet UIImageView *lastLoginImageView;
    //本地保存服务器
    ServerModel *localServerModel;
    int didSelectRow;
    //当前选中的服务器
    ServerModel *didServerModel;
    //得到登录注册框的初始Y值
    int loginAlertY;
    
}
@property (retain, nonatomic) IBOutlet UILabel *serverLabel;
@property (retain, nonatomic) IBOutlet UIButton *serverButton;
@property (retain, nonatomic) IBOutlet UIButton *startGameButton;
@property (nonatomic, retain) NSIndexPath *lastIndexPath;
@property (retain,nonatomic) NSString *gameCenter;
- (IBAction)serverAction:(id)sender;
- (IBAction)startGameAction:(id)sender;
- (IBAction)toUpdatePwd:(id)sender;
- (IBAction)toAccount:(id)sender;

@property (retain, nonatomic) IBOutlet UITextField *userNameText;
@property (retain, nonatomic) IBOutlet UITextField *passwordText;

- (IBAction)loginTabAction:(id)sender;
- (IBAction)registerTabAction:(id)sender;
- (IBAction)loginAndRegisteAction:(id)sender;
- (IBAction)toForgetAction:(id)sender;


@property (retain, nonatomic) IBOutlet UIView *welcomeUIView;
@property (retain, nonatomic) IBOutlet UILabel *welcomeLabel;
@property (retain, nonatomic) IBOutlet UIView *serverUIView;
-(void) setHiddenWelcome:(NSTimer *)timer;

@property (nonatomic, assign) id<MyDelegate>delegate;
@end
@protocol MyDelegate <NSObject>
-(void) startGame;
@end
