//
//  ForgetPassword2ViewController.h
//  HelloLua
//
//  Created by easou on 10/2/13.
//
//

#import <UIKit/UIKit.h>
#import "NetUtil.h"
#import "JSonUtil.h"
#import "MessageModel.h"
#import "MsgAlertView.h"
@interface ForgetPassword2ViewController : UIViewController{
    
    IBOutlet UITextField *newPwdTextField;
    IBOutlet UITextField *veriCodeTextField;
    NSString *mobile;
}
@property (retain, nonatomic) IBOutlet UITextField *newPwdTextField;
@property (retain, nonatomic) IBOutlet UITextField *veriCodeTextField;
@property (retain, nonatomic) NSString *mobile;
- (IBAction)backAction:(id)sender;
-(IBAction)findPwdThread:(id)sender;
@end
