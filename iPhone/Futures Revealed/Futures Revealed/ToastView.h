//
//  ToastView.h
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-07-06.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ToastView : UIView

@property (strong, nonatomic) NSString *text;

+ (void)showToast: (UIView *)parentView withText:(NSString *)text withDuaration:(float)length;

@end