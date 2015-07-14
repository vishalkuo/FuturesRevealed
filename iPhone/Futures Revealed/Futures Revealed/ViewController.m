//
//  ViewController.m
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-03-13.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import "ViewController.h"
#import "Reachability.h"
#import "ToastView.h"
#import "AFNetworking.h"

static NSString *const URL_CONSTANT = @"http://www.vishalkuo.com/phptest.php";
static NSString *const OFFICAL_SITE = @"http://www.futuresrevealed.ca";

@interface ViewController ()

-(BOOL)internetCheck;
-(void)emailMethod;
-(void)postEmail:(NSString *)name emailAddress:(NSString *)address;
-(void)toggleLearnMore;
-(void)backMethod;
-(void)navToWebsite;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    CGRect originalFrame = _masterView.frame;
    CGRect originalTitleFrame = _titleView.frame;
    CGRect titleFrame = originalTitleFrame;
    titleFrame.origin.y += 12;
    _titleView.frame = titleFrame;
    CGRect frame = _masterView.frame;
    frame.origin.y += 10;
    _masterView.frame = frame;
    [UIView animateWithDuration:0.55
                          delay:0.1 options:UIViewAnimationOptionCurveEaseOut animations:^{
                              
                              _masterView.frame= originalFrame;
                              _titleView.frame= originalTitleFrame;
                          } completion:^(BOOL finished) {
                              
                          }];

    
    [UIView animateWithDuration:0.5 delay:0.1 options:UIViewAnimationOptionCurveEaseOut animations:^{
        _masterView.alpha = 1.0;
    } completion:^(BOOL finished) {
        
    }];
    [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent];
    // Do any additional setup after loading the view, typically from a nib.
    _isInAlternateState = NO;
    [_emailButton addTarget:self action:@selector(emailMethod) forControlEvents:UIControlEventTouchUpInside];
    
    [_learnMoreButton addTarget:self action:@selector(toggleLearnMore) forControlEvents:UIControlEventTouchUpInside];
    
    [_backButton addTarget:self action:@selector(backMethod) forControlEvents:UIControlEventTouchUpInside];
    
    [_websiteButton addTarget:self action:@selector(navToWebsite) forControlEvents:UIControlEventTouchUpInside];
    
    
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:YES];
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    [self.navigationController setNavigationBarHidden:NO];
}

-(BOOL)internetCheck{
    Reachability *networkReachability = [Reachability reachabilityForInternetConnection];
    NetworkStatus stat = [networkReachability currentReachabilityStatus];
    if (stat == NotReachable){
        return NO;
    }
    return YES;
}


-(BOOL)shouldPerformSegueWithIdentifier:(NSString *)identifier sender:(id)sender{
    if ([identifier isEqualToString:@"internetSegue"]){
        if ([self internetCheck]){
            return YES;
        }
        [ToastView showToast:self.view withText:@"No internet. Try again later." withDuaration:1.0];
        return NO;

    }
    return YES;
}

-(void)emailMethod{
    if([self internetCheck]){

        UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"Sign Up" message:@"Sign up to recieve email updates for userful information and upcoming talks.\nNote: Futures Revealed will never distribute your email"  preferredStyle:UIAlertControllerStyleAlert];
        
        UIAlertAction *ok = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {
            UITextField *tempName = (UITextField *)alert.textFields.firstObject;
            UITextField *tempField = (UITextField *)alert.textFields[1];
            if ([tempField.text length] != 0){
                NSLog(@"%@ %@", tempName.text, tempField.text);
                [self postEmail:tempName.text emailAddress:tempField.text];
            }else{
                [ToastView showToast:self.view withText:@"Please enter an email address." withDuaration:1.0];
            }
        }];
        
        UIAlertAction *cancel = [UIAlertAction actionWithTitle:@"Cancel" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {
            [alert dismissViewControllerAnimated:YES completion:nil];
        }];
        
        [alert addAction:cancel];
        [alert addAction:ok];

        [alert addTextFieldWithConfigurationHandler:^(UITextField *textField) {
            textField.placeholder = @"Name (Optional)";
        }];
        [alert addTextFieldWithConfigurationHandler:^(UITextField *textField) {
            textField.placeholder = @"Email";

        }];
        
        [self presentViewController:alert animated:YES completion:nil];
        
    }else{
        [ToastView showToast:self.view withText:@"No internet. Try again later." withDuaration:1.0];
    }
}

-(void)postEmail:(NSString *)name emailAddress:(NSString *)address{
    AFHTTPRequestOperationManager *manager= [AFHTTPRequestOperationManager manager];
    NSDictionary *params = @{@"firstname":name, @"email": address};
    [manager POST:URL_CONSTANT parameters:params success:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSLog(@"HERE");
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"%@",[error localizedDescription]);
    }];
}

-(void)toggleLearnMore{
    if (!_isInAlternateState){
        [_backButton setHidden:NO];
        [_aboutButton setHidden:NO];
        [_contactButton setHidden:NO];
        [_websiteButton setHidden:NO];
        
        [_learnMoreButton setHidden:YES];
        [_surveyButton setHidden:YES];
        [_emailButton setHidden:YES];
        
        
    }else{
        [_backButton setHidden:YES];
        [_aboutButton setHidden:YES];
        [_contactButton setHidden:YES];
        [_websiteButton setHidden:YES];
        
        [_learnMoreButton setHidden:NO];
        [_surveyButton setHidden:NO];
        [_emailButton setHidden:NO];
        
        
    }
    _isInAlternateState = !_isInAlternateState;
}

-(void)backMethod{
    [self toggleLearnMore];
}
-(void)navToWebsite{
    if ([self internetCheck]){
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:OFFICAL_SITE]];
    }else{
        [ToastView showToast:self.view withText:@"No internet. Try again later." withDuaration:1.0];
    }
    
}


@end
