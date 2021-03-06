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
#import "TableViewCell.h"

static NSString *const URL_CONSTANT = @"http://www.vishalkuo.com/phpGet.php";

@interface TableViewController ()

@end

@implementation TableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.navigationController.navigationBar.tintColor = [UIColor blueColor];

    _surveyList= [[NSMutableArray alloc] init];
    // Do any additional setup after loading the view.
    
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
    static NSString *simpleTableIdentifier = @"TableCell";
    
    TableViewCell *cell = (TableViewCell *)[tableView dequeueReusableCellWithIdentifier:simpleTableIdentifier];
    if (cell == nil)
    {
        NSArray *nib = [[NSBundle mainBundle] loadNibNamed:@"TableCell" owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    
    NSMutableDictionary *dict = (NSMutableDictionary *)[_surveyList objectAtIndex:indexPath.row];
    
    cell.surveyName.text = [dict valueForKey:@"name"];
    cell.surveyDescrip.text = [dict valueForKey:@"surveyDescription"];
    
    return cell;
}


- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{

    
    NSString *urlString = [_surveyList[indexPath.row] website];
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:urlString]];
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

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 200;
}

-(void)viewWillAppear:(BOOL)animated{

}

@end
