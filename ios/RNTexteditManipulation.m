
#import <React/RCTSinglelineTextInputView.h>
#import "RNTexteditManipulation.h"
#import "RCTBridge+Private.h"
#import "RCTUIManager.h"

@implementation RNTexteditManipulation

@synthesize bridge = _bridge;

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE(TexteditManipulation)

RCT_EXPORT_METHOD(insertText:(nonnull NSNumber *)reactTag withText:(NSString*)text) {
    
    RCTSinglelineTextInputView *reactView = (RCTSinglelineTextInputView*)[_bridge.uiManager viewForReactTag:reactTag];
    
    UIView<RCTBackedTextInputViewProtocol> *view = reactView.backedTextInputView;

  [view replaceRange:view.selectedTextRange withText:text];
}

RCT_EXPORT_METHOD(setText:(nonnull NSNumber *)reactTag withText:(NSString*)text) {
    
    RCTSinglelineTextInputView *reactView = (RCTSinglelineTextInputView*)[_bridge.uiManager viewForReactTag:reactTag];
    
    UIView<RCTBackedTextInputViewProtocol> *view = reactView.backedTextInputView;
    
    UITextPosition *firstCharacterPosition = [view beginningOfDocument];
    UITextPosition *secondCharacterPosition = [view endOfDocument];
    UITextRange *range = [view textRangeFromPosition:firstCharacterPosition toPosition:secondCharacterPosition];

  [view replaceRange:range withText:@""];
}

RCT_EXPORT_METHOD(backSpace:(nonnull NSNumber *)reactTag) {
    RCTSinglelineTextInputView *reactView = (RCTSinglelineTextInputView*)[_bridge.uiManager viewForReactTag:reactTag];
    
    UIView<RCTBackedTextInputViewProtocol> *view = reactView.backedTextInputView;

  UITextRange* range = view.selectedTextRange;
  if ([view comparePosition:range.start toPosition:range.end] == 0) {
    range = [view textRangeFromPosition:[view positionFromPosition:range.start offset:-1] toPosition:range.start];
  }
  [view replaceRange:range withText:@""];
}

RCT_EXPORT_METHOD(doDelete:(nonnull NSNumber *)reactTag) {
    RCTSinglelineTextInputView *reactView = (RCTSinglelineTextInputView*)[_bridge.uiManager viewForReactTag:reactTag];
    
    UIView<RCTBackedTextInputViewProtocol> *view = reactView.backedTextInputView;

  UITextRange* range = view.selectedTextRange;
  if ([view comparePosition:range.start toPosition:range.end] == 0) {
    range = [view textRangeFromPosition:range.start toPosition:[view positionFromPosition: range.start offset: 1]];
  }
  [view replaceRange:range withText:@""];
}

RCT_EXPORT_METHOD(moveLeft:(nonnull NSNumber *)reactTag) {
    RCTSinglelineTextInputView *reactView = (RCTSinglelineTextInputView*)[_bridge.uiManager viewForReactTag:reactTag];
    
    UIView<RCTBackedTextInputViewProtocol> *view = reactView.backedTextInputView;

  UITextRange* range = view.selectedTextRange;
  UITextPosition* position = range.start;

  if ([view comparePosition:range.start toPosition:range.end] == 0) {
      position = [view positionFromPosition: position offset: -1];
  }

  UITextRange* newRange = [view textRangeFromPosition: position toPosition:position];
    
  [view setSelectedTextRange:newRange notifyDelegate:false];
}

RCT_EXPORT_METHOD(moveRight:(nonnull NSNumber *)reactTag) {
    RCTSinglelineTextInputView *reactView = (RCTSinglelineTextInputView*)[_bridge.uiManager viewForReactTag:reactTag];
    
    UIView<RCTBackedTextInputViewProtocol> *view = reactView.backedTextInputView;

  UITextRange* range = view.selectedTextRange;
  UITextPosition* position = range.end;

  if ([view comparePosition:range.start toPosition:range.end] == 0) {
      position = [view positionFromPosition: position offset: 1];
  }

  UITextRange* newRange = [view textRangeFromPosition: position toPosition:position];
    
  [view setSelectedTextRange:newRange notifyDelegate:false];
}

@end
