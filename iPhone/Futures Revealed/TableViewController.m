//
//  TableViewController.m
//  Futures Revealed
//
//  Created by Vishal Kuo on 2015-07-05.
//  Copyright (c) 2015 Futures Revealed. All rights reserved.
//

#import "TableViewController.h"
#import "SurveyObject.h"
#import "AFNetworking.h"
static NSString *const URL_CONSTANT = @"http://www.vishalkuo.com/phpGet.php";

@interface TableViewController ()

@end

@implementation TableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    _surveyList= [[NSMutableArray alloc] init];
    // Do any additional setup after loading the view.
    _recipes = [NSArray arrayWithObjects:@"Egg Benedict", @"Mushroom Risotto", @"Full Breakfast", @"Hamburger", @"Ham and Egg Sandwich", @"Creme Brelee", @"White Chocolate Donut", @"Starbucks Coffee", @"Vegetable Curry", @"Instant Noodle with Egg", @"Noodle with BBQ Pork", @"Japanese Noodle with Pork", @"Green Tea", @"Thai Shrimp Cake", @"Angry Birds Cake", @"Ham and Cheese Panini", nil];
    
    [self loadSurveys];

    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [_surveyList count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *simpleTableIdentifier = @"SimpleTableCell";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:simpleTableIdentifier];
    
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:simpleTableIdentifier];
    }
    
    NSMutableDictionary *dict = (NSMutableDictionary *)[_surveyList objectAtIndex:indexPath.row];
    
    cell.textLabel.text = [dict valueForKey:@"name"];
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    //Your Code here
    //NSLog(_recipes[indexPath.row]);
}

-(void)loadSurveys{
    AFHTTPRequestOperationManager *manager= [AFHTTPRequestOperationManager manager];
    [manager POST:URL_CONSTANT parameters:nil success:^(AFHTTPRequestOperation *operation, id responseObject) {
        NSArray *resp = responseObject;
        for (int i = 0; i < [resp count]; i++){
                NSDictionary *dict = resp[i];
                NSString *name = [dict valueForKey:@"name"];
                NSString *description = [dict valueForKey:@"description"];
                NSString *url = [dict valueForKey:@"url"];
            SurveyObject *myObj = [[SurveyObject alloc] initWithParams:name description:description website:url];
            [_surveyList addObject:myObj];
        
            
        }
        [self.tableView reloadData];
        
        

    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        NSLog(@"%@",[error localizedDescription]);
    }];
}

@end