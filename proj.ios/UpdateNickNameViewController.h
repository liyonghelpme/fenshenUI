//
//  UpdateNickNameViewController.h
//  HelloLua
//
//  Created by easou on 10/2/13.
//
//

#import <UIKit/UIKit.h>

@interface UpdateNickNameViewController : UIViewController{
    
    IBOutlet UITextField *nickNameTextField;
    NSString *nickName;
    NSString *token;
}
@property (retain, nonatomic) IBOutlet UITextField *nickNameTextField;
@property (retain, nonatomic) NSString *nickName;
@property (copy, nonatomic) NSString *token;
- (IBAction)backAction:(id)sender;
@end
