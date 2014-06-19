//
//  BindingPhoneViewController.h
//  HelloLua
//
//  Created by easou on 10/2/13.
//
//

#import <UIKit/UIKit.h>
#import "NetUtil.h"
#import "JSonUtil.h"


@interface BindingPhoneViewController : UIViewController{
    NSString *token;
    IBOutlet UITextField *mobileTextField;
    IBOutlet UITextField *provingTextField;
    NetUtil *netUtil;
    JSonUtil *jsonUtil;
    NSString *mobile;
}
@property (retain, nonatomic) IBOutlet UITextField *mobileTextField;
@property (retain, nonatomic) IBOutlet UITextField *provingTextField;
- (IBAction)getProvingAction:(id)sender;
@property (nonatomic,copy) NSString *token;
- (IBAction)backAction:(id)sender;
@property (retain, nonatomic) NSString *mobile;
@end
