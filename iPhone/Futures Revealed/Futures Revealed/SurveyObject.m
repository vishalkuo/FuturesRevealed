
//
//  SurveyObject.m
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-07-05.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import "SurveyObject.h"

@implementation SurveyObject

-(id)initWithParams:(NSString *)name description:(NSString *)description website:(NSString *)website{
    self = [super init];
    if (self){
        _name = name;
        _surveyDescription = description;
        _website = website;
    }
    return self;
}

-(NSString *)description{
    return [NSString stringWithFormat:@"Name: %@, Description: %@, URL: %@", _name, _surveyDescription, _website];
}

@end
