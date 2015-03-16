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
    
    UIButton *aboutBtn = (UIButton *)self.aboutBtn;
    UIButton *signupBtn = (UIButton *)self.signUpBtn;
    UIButton *contactBtn = (UIButton *)self.contactBtn;
    UIButton *surveyBtn = (UIButton *)self.surveyBtn;
    

    NSArray *btns = [NSArray arrayWithObjects:aboutBtn, signupBtn, contactBtn, surveyBtn, nil];
    
    for (UIButton *btn in btns){
        CAGradientLayer *btnGradient = [CAGradientLayer layer];
        btnGradient.frame = btn.bounds;
        btnGradient.colors = [NSArray arrayWithObjects:(id)[[UIColor colorWithRed:102.0f / 255.0f green:102.0f / 255.0f blue:102.0f / 255.0f alpha:1.0]CGColor],(id)[[UIColor colorWithRed:51.0f / 255.0f green:51.0f / 255.0f blue:51.0f / 255.0f alpha:1.0]CGColor],nil];
        
        [btn.layer insertSublayer:btnGradient atIndex:0];
        
        
        CALayer *btnLayer = [btn layer];
        [btnLayer setMasksToBounds:YES];
        [btnLayer setCornerRadius:2.0f];
    }
    
    

    
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*-(IBAction)abtClick{
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
}*/

@end
