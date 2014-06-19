//
//  StorePayClass.h
//  PurchaseInAppTest
//
//  Created by liuruibin on 10-9-15.
//  Copyright 2010 nd. All rights reserved.
//

#import <Foundation/Foundation.h>
#import	<StoreKit/StoreKit.h>
#import	<StoreKit/SKPaymentTransaction.h>

@interface StorePayClass : NSObject< SKPaymentTransactionObserver>
{
	
}

- (void)paymentQueue:(SKPaymentQueue *)queue restoreCompletedTransactionsFailedWithError:(NSError *)error;
- (void)paymentQueue:(SKPaymentQueue *)queue updatedTransactions:(NSArray *)transactions;

- (void)PurchaseTransaction:(SKPaymentTransaction *)transaction;
- (void)completeTransaction:(SKPaymentTransaction *)transaction;
- (void)failedTransaction:(SKPaymentTransaction *)transaction;
- (void)restoreTransaction:(SKPaymentTransaction *)transaction;

- (void) recordTransaction: (SKPaymentTransaction *)transaction;

-(void) paymentQueueRestoreCompletedTransactionsFinished: (SKPaymentTransaction *)transaction;




@end
