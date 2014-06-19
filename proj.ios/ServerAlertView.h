//
//  ServerAlertView.h
//  HelloLua
//
//  Created by easou on 9/23/13.
//
//

#import <UIKit/UIKit.h>

@class UIAlertView;

@interface ServerAlertView : UIAlertView {
	UIAlertView *alertView;
	UITableView *tableView;
	int tableHeight;
	int tableExtHeight;
	id <UITableViewDataSource> dataSource;
	id <UITableViewDelegate> tableDelegate;
}

@property (nonatomic, assign) id dataSource;
@property (nonatomic, assign) id tableDelegate;

@property (nonatomic, retain) UITableView *tableView;
@property (nonatomic, assign) int tableHeight;

- (void)prepare;

@end
