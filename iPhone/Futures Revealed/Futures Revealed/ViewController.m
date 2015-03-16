//
//  ViewController.m
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-03-13.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import "ViewController.h"
#import "BackgroundLayer.h"
@interface ViewController ()


@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    CAGradientLayer *bgLayer = [BackgroundLayer blueGrad];
    bgLayer.frame = self.view.bounds;
    [self.view.layer insertSublayer:bgLayer atIndex:0];
    

    
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(IBAction)abtClick{
    NSLog(@"About clicked!");
}

-(IBAction)signClick{
    NSLog(@"Sign up clicked!");
}

-(IBAction)contactClick{
    NSLog(@"Contact clicked!");
}

-(IBAction)surveysClick{
    NSLog(@"Surveys clicked!");
}

@end
