//
//  ServerAlertViewCell.h
//  HelloLua
//
//  Created by easou on 9/25/13.
//
//

#import <UIKit/UIKit.h>

@interface ServerAlertViewCell : UITableViewCell{
    
    IBOutlet UIImageView *serverImage;
    IBOutlet UIButton *serverButton;
}
@property (retain, nonatomic) IBOutlet UIImageView *serverImage;
@property (retain, nonatomic) IBOutlet UIButton *serverButton;

@end
