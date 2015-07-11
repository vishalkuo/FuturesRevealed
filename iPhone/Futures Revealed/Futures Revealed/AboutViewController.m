//
//  AboutViewController.m
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-07-08.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import "AboutViewController.h"

@interface AboutViewController ()

@end

@implementation AboutViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    //[self.navigationController.navigationBar setTranslucent:YES];
    // Do any additional setup after loading the view.
    
    [self.navigationController.navigationBar setBackgroundImage:[UIImage new]
                                                  forBarMetrics:UIBarMetricsDefault];
    self.navigationController.navigationBar.shadowImage = [UIImage new];
    self.navigationController.navigationBar.translucent = YES;
    self.navigationController.view.backgroundColor = [UIColor clearColor];
    self.navigationController.navigationBar.backgroundColor = [UIColor clearColor];
    self.navigationController.navigationBar.tintColor = [UIColor whiteColor];


}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


-(void)viewWillAppear:(BOOL)animated{
    [self.navigationController setNavigationBarHidden:NO];
}

-(void)viewDidLayoutSubviews{
        [_aboutView sizeToFit];
}

@end
