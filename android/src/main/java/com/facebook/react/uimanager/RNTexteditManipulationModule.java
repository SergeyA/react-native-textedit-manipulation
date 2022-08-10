
package com.facebook.react.uimanager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.views.textinput.ReactEditText;

public class RNTexteditManipulationModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNTexteditManipulationModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    Handler handle = new Handler(Looper.getMainLooper());

    private ReactEditText getEditById(int id) {

        UIViewOperationQueue uii = this.getReactApplicationContext()
            .getNativeModule(UIManagerModule.class)
            .getUIImplementation()
            .getUIViewOperationQueue();

        return (ReactEditText) uii.getNativeViewHierarchyManager().resolveView(id);
    }

    ReactRootView rootView = null;

    @ReactMethod
    public void insertText(final int tag, final String text) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Activity activity = getCurrentActivity();
                final ReactEditText edit = getEditById(tag);
                if (edit == null) {
                    return;
                }

                int start = Math.max(edit.getSelectionStart(), 0);
                int end = Math.max(edit.getSelectionEnd(), 0);
                edit.getText().replace(Math.min(start, end), Math.max(start, end),
                        text, 0, text.length());

                start = Math.min(start + text.length(), edit.getText().length());

                edit.setSelection(start, start);
            }
        });
    }

    @ReactMethod
    public void setText(final int tag, final String text) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Activity activity = getCurrentActivity();
                final ReactEditText edit = getEditById(tag);
                if (edit == null) {
                    return;
                }

                int end = edit.getText().length();
                edit.getText().replace(0, end, text, 0, text.length());

                edit.setSelection(text.length(), text.length());
            }
        });
    }

    @ReactMethod
    public void getText(final int tag, final Promise promise) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                try {
                    final Activity activity = getCurrentActivity();
                    final ReactEditText edit = getEditById(tag);
                    if (edit == null) {
                        promise.reject("EditText not found");
                        return;
                    }

                    promise.resolve(edit.getText().toString());

                } catch(Throwable t) {
                    promise.reject(t);
                }
            }
        });
    }

    @ReactMethod
    public void backSpace(final int tag) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Activity activity = getCurrentActivity();
                final ReactEditText edit = getEditById(tag);
                if (edit == null) {
                    return;
                }

                int start = Math.max(edit.getSelectionStart(), 0);
                int end = Math.max(edit.getSelectionEnd(), 0);
                if (start != end) {
                    edit.getText().delete(start, end);
                } else if (start > 0){
                    edit.getText().delete(start - 1, start);
                }
            }
        });
    }

    @ReactMethod
    public void doDelete(final int tag) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Activity activity = getCurrentActivity();
                final ReactEditText edit = getEditById(tag);
                if (edit == null) {
                    return;
                }

                int start = Math.max(edit.getSelectionStart(), 0);
                int end = Math.max(edit.getSelectionEnd(), 0);
                if (start != end) {
                    edit.getText().delete(start, end);
                } else if (end < edit.getText().length() - 1){
                    edit.getText().delete(start, end + 1);
                }
            }
        });
    }

    @ReactMethod
    public void moveLeft(final int tag) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Activity activity = getCurrentActivity();
                final ReactEditText edit = getEditById(tag);
                if (edit == null) {
                    return;
                }

                int start = Math.max(edit.getSelectionStart(), 0);
                int end = Math.max(edit.getSelectionEnd(), 0);
                if (start != end) {
                    edit.setSelection(start, start);
                } else if (start > 0) {
                    edit.setSelection(start - 1, start - 1);
                }
            }
        });
    }

    @ReactMethod
    public void moveRight(final int tag) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Activity activity = getCurrentActivity();
                final ReactEditText edit = getEditById(tag);
                if (edit == null) {
                    return;
                }

                int start = Math.max(edit.getSelectionStart(), 0);
                int end = Math.max(edit.getSelectionEnd(), 0);
                if (start != end) {
                    edit.setSelection(end, end);
                } else if (end < edit.getText().length() - 1){
                    edit.setSelection(end + 1, end + 1);
                }
            }
        });
    }

    @Override
    public String getName() {
      return "TexteditManipulation";
    }
}
