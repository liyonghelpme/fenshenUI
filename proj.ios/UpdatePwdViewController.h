//
//  UpdatePwdViewController.h
//  HelloLua
//
//  Created by easou on 9/24/13.
//
//

#import <UIKit/UIKit.h>

@interface UpdatePwdViewController : UIViewController{
    NSString *myToken;
    IBOutlet UITextField *oldpwdTextField;
    IBOutlet UITextField *newpwdTextField;
    
}
@property (retain, nonatomic) IBOutlet UITextField *oldpwdTextField;
@property (retain, nonatomic) IBOutlet UITextField *newpwdTextField;
@property (retain, nonatomic) NSString *myToken;
- (IBAction)updateAction:(id)sender;
- (IBAction)textFiledReturnEditing:(id)sender;
- (IBAction)backAction:(id)sender;
@end
