
import React, { Component, PropTypes } from 'react';

import {
  NativeModules,
  AppRegistry,
} from 'react-native';

const { TexteditManipulation } = NativeModules;

const {
  insertText,
  setText,
  gettext,
  backSpace,
  doDelete,
  moveLeft,
  moveRight,
} = TexteditManipulation;

export {
  insertText,
  setText,
  gettext,
  backSpace,
  doDelete,
  moveLeft,
  moveRight,
};
