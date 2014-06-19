//
//  ServerUIView.h
//  HelloLua
//
//  Created by easou on 10/3/13.
//
//

#import <UIKit/UIKit.h>
#import "ServerModel.h"

@interface ServerUIView : UIView<UITableViewDelegate, UITableViewDataSource>{
    NSMutableArray *serverArray;
    NSMutableArray *localServerArray;
    
	UITableView *myTableView;
	int tableHeight;
	int tableExtHeight;
	id <UITableViewDataSource> dataSource;
	id <UITableViewDelegate> tableDelegate;
}
@property (nonatomic,retain) NSMutableArray *serverArray;
@property (nonatomic,retain) NSMutableArray *localServerArray;

@property (nonatomic, assign) id dataSource;
@property (nonatomic, assign) id tableDelegate;

@property (nonatomic, retain) UITableView *myTableView;
@property (nonatomic, assign) int tableHeight;

-(void) setServer;


@end
