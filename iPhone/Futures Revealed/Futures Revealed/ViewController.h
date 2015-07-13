//
//  ViewController.h
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-03-13.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController

@property(weak, nonatomic)IBOutlet UIButton *surveyButton;
@property(weak, nonatomic)IBOutlet UIButton *emailButton;
@property(weak, nonatomic)IBOutlet UIButton *learnMoreButton;

//Alternate State
@property(nonatomic, assign)BOOL isInAlternateState;
@property(weak, nonatomic)IBOutlet UIButton *backButton;
@property(weak, nonatomic)IBOutlet UIButton *aboutButton;
@property(weak, nonatomic)IBOutlet UIButton *contactButton;
@property(weak, nonatomic)IBOutlet UIButton *websiteButton;
@property(weak, nonatomic)IBOutlet UIView *masterView;
@end

