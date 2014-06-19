//
//  AccountCell.h
//  HelloLua
//
//  Created by easou on 9/29/13.
//
//

#import <UIKit/UIKit.h>

@interface AccountCell : UITableViewCell{
    
    IBOutlet UILabel *nameLabel;
    IBOutlet UILabel *valueLabel;
    IBOutlet UIImageView *lastImage;
}
@property (retain, nonatomic) IBOutlet UILabel *nameLabel;
@property (retain, nonatomic) IBOutlet UILabel *valueLabel;
@property (retain, nonatomic) IBOutlet UIImageView *lastImage;

@end
