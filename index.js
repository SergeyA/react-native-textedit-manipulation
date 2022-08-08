
import React, { Component, PropTypes } from 'react';

import {
  NativeModules,
  AppRegistry,
} from 'react-native';

const { TexteditManipulation } = NativeModules;

const {
  insertText,
  backSpace,
  doDelete,
  moveLeft,
  moveRight,
} = TexteditManipulation;

export {
  insertText,
  backSpace,
  doDelete,
  moveLeft,
  moveRight,
};
