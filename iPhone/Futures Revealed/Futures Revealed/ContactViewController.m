//
//  ContactViewController.m
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-07-11.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import "ContactViewController.h"
#import "Reachability.h"
#import "ToastView.h"

static NSString *const FB_ID = @"1382959188665784";

@interface ContactViewController ()
-(void)fbMethod;
-(void)twitterMethod;
-(void)linkedInMethod;
-(void)emailMethod;
-(void)webMethod;

@end

@implementation ContactViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    
    [self.navigationController.navigationBar setBackgroundImage:[UIImage new]
                                                  forBarMetrics:UIBarMetricsDefault];
    self.navigationController.navigationBar.shadowImage = [UIImage new];
    self.navigationController.navigationBar.translucent = YES;
    self.navigationController.view.backgroundColor = [UIColor clearColor];
    self.navigationController.navigationBar.backgroundColor = [UIColor clearColor];
    self.navigationController.navigationBar.tintColor = [UIColor whiteColor];
    
    [_facebookBtn addTarget:self action:@selector(fbMethod) forControlEvents:UIControlEventTouchUpInside];
    [_twitterBtn addTarget:self action:@selector(twitterMethod) forControlEvents:UIControlEventTouchUpInside];
    [_linkedInBtn addTarget:self action:@selector(linkedInMethod) forControlEvents:UIControlEventTouchUpInside];
    [_emailBtn addTarget:self action:@selector(emailMethod) forControlEvents:UIControlEventTouchUpInside];
    [_websiteBtn addTarget:self action:@selector(webMethod) forControlEvents:UIControlEventTouchUpInside];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)viewWillAppear:(BOOL)animated{
    [self.navigationController setNavigationBarHidden:NO];
}


-(BOOL)isConnected{
    Reachability *networkReachability = [Reachability reachabilityForInternetConnection];
    NetworkStatus stat = [networkReachability currentReachabilityStatus];
    if (stat == NotReachable){
        [ToastView showToast:self.view withText:@"No internet. Please try again later" withDuaration:1.0];
        return NO;
    }
    return YES;
}

-(void)fbMethod{
    if ([self isConnected]){
        NSURL *facebookUrl = [NSURL URLWithString:[NSString stringWithFormat:@"fb://profile/%@", FB_ID]];
        if ([[UIApplication sharedApplication] canOpenURL:facebookUrl]){
            [[UIApplication sharedApplication] openURL:facebookUrl];
        }else{
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"http://www.facebook.com/futuresrevealed"]];
        }
    }
}

-(void)twitterMethod{
    if ([self isConnected]){
        NSURL *twitterUrl = [NSURL URLWithString:@"twitter://user?screen_name=FuturesRevealed"];
        if ([[UIApplication sharedApplication] canOpenURL:twitterUrl]){
            [[UIApplication sharedApplication] openURL:twitterUrl];
        }else{
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"https://twitter.com/futuresrevealed"]];
        }
    }
}

-(void)linkedInMethod{
    if ([self isConnected]){
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"https://www.linkedin.com/company/9257128?trk=tyah&trkInfo=tarId%3A1421712447867%2Ctas%3Afutures%20revealed%2Cidx%3A1-1-1"]];
    }
}

-(void)emailMethod{
    if ([self isConnected]){
        NSURL*urlEmail = [NSURL URLWithString:@"mailto:info@futuresrevealed.com"];
        [[UIApplication sharedApplication] openURL:urlEmail];
    }
}

-(void)webMethod{
    if ([self isConnected]){
        NSURL *officialUrl = [NSURL URLWithString:@"http://www.futuresrevealed.ca"];
        [[UIApplication sharedApplication] openURL:officialUrl];
    }
}

@end
