//
//  ForgetPasswordViewController.h
//  HelloLua
//
//  Created by easou on 9/23/13.
//
//

#import <UIKit/UIKit.h>

@interface ForgetPasswordViewController : UIViewController{
    
    IBOutlet UITextField *phoneTextField;
}
- (IBAction)updateAction:(id)sender;
- (IBAction)backAction:(id)sender;
@end
