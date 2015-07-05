//
//  SurveyObject.h
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-07-05.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SurveyObject : NSObject
@property NSString *name;
@property NSString *surveyDescription;
@property NSString *website;

-(NSString *)description;
-(id)initWithParams:(NSString *)name description:(NSString *)description website:(NSString *)website;
@end
