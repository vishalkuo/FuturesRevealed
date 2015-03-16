//
//  ViewController.h
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-03-13.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController
@property (strong, nonatomic) IBOutlet UIView *aboutBtn;
@property (weak, nonatomic) IBOutlet UIButton *signUpBtn;
@property (weak, nonatomic) IBOutlet UIButton *surveyBtn;
@property (weak, nonatomic) IBOutlet UIButton *contactBtn;


-(IBAction)abtClick;
-(IBAction)signClick;
-(IBAction)contactClick;
-(IBAction)surveysClick;


@end

