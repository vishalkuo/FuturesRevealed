//
//  BackgroundLayer.m
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-03-15.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import "BackgroundLayer.h"



@implementation BackgroundLayer

+(CAGradientLayer*)blueGrad{
    
    //UIColor *colorOne = [UIColor colorWithRed:(244/255) green:(245/255) blue:(254/255) alpha:1.0];
    //UIColor *colorTwo = [UIColor colorWithRed:(52/255) green:(78/255) blue:(243/255) alpha:0.4];
    
    UIColor *colorOne = [UIColor colorWithRed:(244/255.0) green:(245/255.0) blue:(254/255.0) alpha:1.0];
    UIColor *colorTwo = [UIColor colorWithRed:(140/255.0)  green:(152/255.0)  blue:(188/255.0)  alpha:1.0];
    
    NSArray *colors = [NSArray arrayWithObjects:(id)colorOne.CGColor, colorTwo.CGColor, nil];
    NSNumber *stopOne = [NSNumber numberWithFloat:0.0];
    NSNumber *stopTwo = [NSNumber numberWithFloat:1.0];
    
    NSArray *locations = [NSArray arrayWithObjects:stopOne, stopTwo, nil];
    
    CAGradientLayer *headerLayer = [CAGradientLayer layer];
    headerLayer.colors = colors;
    headerLayer.locations = locations;
    
    return headerLayer;
    
}

@end
