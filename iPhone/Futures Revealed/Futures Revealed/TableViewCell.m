//
//  TableViewCell.m
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-07-05.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import "TableViewCell.h"

@implementation TableViewCell

- (void)awakeFromNib {
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

-(void)layoutSubviews{
    [self.cardView setAlpha:1];
    self.cardView.layer.masksToBounds = NO;
    self.cardView.layer.cornerRadius = 2;
    self.cardView.layer.shadowOffset = CGSizeMake(-.8f, .8f);
    self.cardView.layer.shadowRadius = 1;
    self.cardView.layer.shadowOpacity = 0.2;
    
    [_surveyDescrip sizeToFit];
 
    
}

@end
