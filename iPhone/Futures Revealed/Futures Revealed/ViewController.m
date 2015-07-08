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

@interface ViewController ()

-(BOOL)internetCheck;
-(void)emailMethod;
-(void)postEmail:(NSString *)name emailAddress:(NSString *)address;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    [_emailButton addTarget:self action:@selector(emailMethod) forControlEvents:UIControlEventTouchUpInside];
    
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
    return NO;
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
        
        [alert addAction:ok];
        [alert addAction:cancel];
        [alert addTextFieldWithConfigurationHandler:^(UITextField *textField) {
            textField.placeholder = @"Name";
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
    
}


@end
