//
//  RequestProduct.h
//  iosApp
//
//  Created by CD on 12-2-17.
//  Copyright 2012 __MyCompanyName__. All rights reserved.
//

#import <StoreKit/StoreKit.h>
#import <StoreKit/SKPaymentTransaction.h>
#import "StorePayClass.h"

@interface RequestProduct : StorePayClass<SKProductsRequestDelegate> {
	NSString* mStrGoodsID;
}
@property(nonatomic, retain) NSString* strGoodsID;
+(RequestProduct*) getIntance;
-(bool) canPayment;
-(void) requestProduct:(NSString*) strProduct;

@end
