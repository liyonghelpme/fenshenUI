//
//  LoadingViewController.h
//  HelloLua
//
//  Created by easou on 9/29/13.
//
//

#import <UIKit/UIKit.h>

@protocol LoadingDelegate;
@interface LoadingViewController : UIViewController
{
    @private NSString *_versionStr;
    @private id<LoadingDelegate> delegate;
    IBOutlet UIImageView *firstImage;
    IBOutlet UIView *secondUIView;
    IBOutlet UIImageView *secondImageView;
}

@property (nonatomic, assign) id<LoadingDelegate>delegate;
@end
@protocol LoadingDelegate <NSObject>
-(void) startSdKController;
@end
