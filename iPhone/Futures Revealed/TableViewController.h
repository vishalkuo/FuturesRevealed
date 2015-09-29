//
//  TableViewController.h
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-07-05.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import <UIKit/UIKit.h>



@interface TableViewController : UITableViewController <UITableViewDelegate, UITableViewDataSource>
@property(strong, nonatomic)NSMutableArray *surveyList;




@end
